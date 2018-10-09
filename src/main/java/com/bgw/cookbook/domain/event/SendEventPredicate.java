package com.bgw.cookbook.domain.event;

import io.reactivex.functions.Predicate;
import org.apache.commons.collections4.CollectionUtils;

import javax.validation.constraints.NotNull;

public class SendEventPredicate implements Predicate<CookbookEvent> {
    private final CookbookEventListener listener;

    SendEventPredicate(CookbookEventListener listener) { this.listener = listener; }

    @Override
    public boolean test(@NotNull CookbookEvent event) throws Exception {
        return event.isGlobal() || event.getChefId().equals(listener.getChefId()) || testIdLists(event);
    }

    private boolean testIdLists(CookbookEvent event) {
        return  CollectionUtils.containsAny(event.getIngredientIds(), listener.getRecipeIds()) ||
                CollectionUtils.containsAny(event.getRecipeIds(), listener.getRecipeIds());
    }
}
