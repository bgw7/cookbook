package com.bgw.cookbook.domain.event;

import org.springframework.stereotype.Component;
import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.Subject;

import java.util.function.Consumer;

@Component
public class EventBus {

    private final Subject<Event, Event> bus = PublishSubject.create();

    public void post(Event event) {
        bus.onNext(event);
    }

    public void register(ConsumerRegistration consumer) {
        consumer.accept(bus);
    }

    public Observable<Event> asObservable() { return bus; }

    public interface ConsumerRegistration extends Consumer<Observable<Event>> {}
}
