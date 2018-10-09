package com.bgw.cookbook.domain.event;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;
import org.springframework.stereotype.Component;

@Component
public class EventBus {

    private final Subject<Event> bus = PublishSubject.create();

    public void dispatch(Event event) {
        bus.onNext(event);
    }

    public Disposable register(Subscriber subscriber) { return subscriber.register(bus).subscribe(this::dispatch); }

    public Observable<Event> asObservable() { return bus; }

    @FunctionalInterface
    public interface Subscriber {
        Observable<Event> register(Observable<Event> events);
    }
}
