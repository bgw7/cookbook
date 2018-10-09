package com.bgw.cookbook.adapter.api;

import com.bgw.cookbook.domain.chef.Chef;
import com.bgw.cookbook.domain.chef.ChefService;
import com.bgw.cookbook.domain.event.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequestMapping(value = "/api")
public class ChefController {
    @Autowired
    private EventService eventService;

    @Autowired
    private ChefService chefService;

    @GetMapping(path = "/cookbook-events", produces = "text/event-stream")
    public SseEmitter getEventStream(@RequestHeader("ChefId") Long chefId) {
        return eventService.getStream(chefId);
    }

    @GetMapping(path="/chefs/{id}")
    public Chef findChefById(@PathVariable("id") Long id){
        return chefService.findById(id);
    }
}
