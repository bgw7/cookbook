package com.bgw.cookbook.adapter.service;

import com.bgw.cookbook.domain.event.CookbookEventListener;
import com.bgw.cookbook.domain.event.EventBus;
import com.bgw.cookbook.domain.event.EventService;
import io.reactivex.disposables.Disposable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Service
public class CookbookEventService implements EventService {

    @Autowired
    private EventBus bus;

    public SseEmitter getStream(Long chefid) {
        CookbookEventListener listener = new CookbookEventListener(chefid);
        Disposable disposable = bus.register(listener);
        listener.setDisposable(disposable);
        return listener.getEmitter();
    }
}
