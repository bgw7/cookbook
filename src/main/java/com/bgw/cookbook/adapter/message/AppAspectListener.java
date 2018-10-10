package com.bgw.cookbook.adapter.message;

import com.bgw.cookbook.domain.chef.Chef;
import com.bgw.cookbook.domain.event.CookbookEvent;
import com.bgw.cookbook.domain.event.EventBus;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AppAspectListener {

    @Autowired
    private EventBus bus;

    @AfterReturning("execution(* com.bgw.cookbook.adapter.service.ChefDataService.updateChef(..)) && args(chef,..)")
    public void afterChefUpdate(Chef chef) {
        CookbookEvent event = new CookbookEvent<>(chef);
        event.setChefId(chef.getId());
        bus.dispatch(event);
    }
}
