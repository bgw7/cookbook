package com.bgw.cookbook.adapter.service;

import com.bgw.cookbook.domain.event.EventService;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Service
public class CookbookEventService implements EventService {
    public SseEmitter getStream() {
        return new SseEmitter(0L);
    }
}
