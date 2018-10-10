package com.bgw.cookbook.domain.event;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;

public class CookbookEventListener implements EventBus.Subscriber {
    private static final Logger logger = LoggerFactory.getLogger(CookbookEventListener.class);

    private Long chefId;
    private List<Long> recipeIds;
    private List<Long> ingredientIds;
    private Predicate<CookbookEvent> sendEventPredicate;
    private SseEmitter emitter;
    private Disposable disposable;

    public CookbookEventListener(Long chefId) {
        this.chefId = chefId;
        this.sendEventPredicate = new SendEventPredicate(this);
        this.emitter = new SseEmitter(0L);
    }

    public CookbookEventListener(Long chefId, List<Long> recipeIds, List<Long> ingredientIds) {
        this.chefId = chefId;
        this.recipeIds = recipeIds;
        this.ingredientIds = ingredientIds;
    }

    @Override
    public Observable<Event> register(Observable<Event> events) {
        return events
                .ofType(CookbookEvent.class)
                .observeOn(Schedulers.newThread())
                .filter(sendEventPredicate)
                .switchMap(event -> Observable.just(event)
                .flatMap(this::onEvent)
                .onErrorResumeNext(EventErrorHandler.withLogger(logger)));
    }

    Observable<Event> onEvent(CookbookEvent event) {
        try {
            this.emitter.send(event);
        } catch (IOException exception) {
            this.emitter.complete();
            return Observable.error(exception);
        }
        return Observable.empty();
    }

    public SseEmitter getEmitter() {
        return emitter;
    }

    public void dispose() {
        if (!this.disposable.isDisposed()) {
            this.disposable.dispose();
        }
    }

    public Disposable getDisposable() {
        return disposable;
    }

    public void setDisposable(Disposable disposable) {
        this.disposable = disposable;
        this.emitter.onCompletion(this::dispose);
    }

    public Long getChefId() {
        return chefId;
    }

    public List<Long> getRecipeIds() {
        return recipeIds;
    }

    public List<Long> getIngredientIds() {
        return ingredientIds;
    }
}
