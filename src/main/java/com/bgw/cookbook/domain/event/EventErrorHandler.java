package com.bgw.cookbook.domain.event;

import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

import org.slf4j.Logger;

import static io.reactivex.Observable.empty;

public class EventErrorHandler implements Function<Throwable, ObservableSource<? extends Event>> {
    private final Logger logger;

    private EventErrorHandler(Logger logger) { this.logger = logger; }

    public static EventErrorHandler withLogger(Logger logger) { return new EventErrorHandler(logger); }

    @Override
    public ObservableSource<? extends Event> apply(Throwable throwable) {
        logger.error("processing event", throwable);
         return empty();
    }
}
