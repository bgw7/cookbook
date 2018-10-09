package com.bgw.cookbook.domain.event;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface EventService {
    SseEmitter getStream();
}
