package com.bgw.cookbook.adapter.service;

import com.bgw.cookbook.domain.event.CookbookEventListener;
import com.bgw.cookbook.domain.event.EventBus;
import io.reactivex.disposables.Disposable;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;


import static org.hamcrest.CoreMatchers.isA;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CookbookEventServiceTest {

    @InjectMocks
    private CookbookEventService service;

    @Mock
    private EventBus bus;

    @Test
    public void should_set_disposable() {
        when(bus.register(any())).thenReturn(expectedDisposable());
        service.getStream(1L);
        ArgumentCaptor<CookbookEventListener> listenerCaptor = ArgumentCaptor.forClass(CookbookEventListener.class);
        verify(bus).register(listenerCaptor.capture());
        CookbookEventListener actualListener = listenerCaptor.getValue();
        assertNotNull(actualListener.getDisposable());
    }

    @Test
    public void should_init_listener() {
        CookbookEventListener expectedListener = new CookbookEventListener(1L);
        service.getStream(1L);
        ArgumentCaptor<CookbookEventListener> listenerCaptor = ArgumentCaptor.forClass(CookbookEventListener.class);
        verify(bus).register(listenerCaptor.capture());
        CookbookEventListener actualListener = listenerCaptor.getValue();
        assertThat(actualListener.getChefId(), equalTo(expectedListener.getChefId()));
        assertThat(actualListener.getDisposable(), equalTo(expectedListener.getDisposable()));
        assertThat(actualListener.getEmitter(), isA(SseEmitter.class));

    }

    Disposable expectedDisposable() {
        return new Disposable() {
            @Override
            public void dispose() {

            }

            @Override
            public boolean isDisposed() {
                return false;
            }
        };
    }
}
