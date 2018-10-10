package com.bgw.cookbook.adapter.api;

import com.bgw.cookbook.domain.chef.Chef;
import com.bgw.cookbook.domain.chef.ChefService;
import com.bgw.cookbook.domain.event.EventService;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ChefControllerTest {

    @Mock
    private EventService eventService;

    @Mock
    private ChefService chefService;

    @InjectMocks
    private ChefController controller;

    @Test
    public void getEventStreamTest() throws Exception {
        SseEmitter expectedEmitter = new SseEmitter();
        when(eventService.getStream(anyLong())).thenReturn(expectedEmitter);
        SseEmitter actualEmitter = controller.getEventStream(1L);
        assertThat(actualEmitter, equalTo(expectedEmitter));
    }

    @Test
    public void findByChefId() throws Exception {
        when(chefService.findById(anyLong())).thenReturn(new Chef());
        controller.findChefById(1L);
        verify(chefService).findById(1L);
    }

    @Test
    public void updateChef() throws Exception {
        Chef chef = new Chef();
        chef.setId(2L);
        chef.setName("joe");
        chefService.updateChef(chef);
        verify(chefService).updateChef(chef);
    }
}
