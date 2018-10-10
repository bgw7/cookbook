package com.bgw.cookbook.domain.event;

import com.bgw.cookbook.domain.ingredient.Ingredient;
import com.bgw.cookbook.test.observables.TrampolineSchedulerRule;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.TestObserver;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CookbookEventListenerTest {

    private static final Event ANY_EVENT = new Event() {};
    private CookbookEvent event;
    private TestObserver<Event> observer = new TestObserver<>();
    private EventBus eventBus = new EventBus();

    @Spy
    private SseEmitter emitter;

    @Mock
    private Disposable disposable;

    @Captor
    private ArgumentCaptor<Runnable> onCompleteRunnable;

    @Rule
    public TrampolineSchedulerRule trampolineRule = new TrampolineSchedulerRule();

    @Spy
    private CookbookEventListener listener = new CookbookEventListener(1L, Arrays.asList(1L, 2L), Arrays.asList(1L, 2L));

    @Mock
    private SendEventPredicate predicate;

    @Before
    public void setUp() {
        ReflectionTestUtils.setField(listener, "sendEventPredicate", predicate);
        eventBus.register(listener);
        observer = eventBus.asObservable().test();
        Ingredient ingredient = new Ingredient(1L, "pepper", 2L, "whole");
        event = new CookbookEvent<>(ingredient);
        ReflectionTestUtils.setField(listener, "emitter", emitter);
    }

    @Test
    public void onEvent_emitterSendEvent_Success() throws Exception{
        listener.onEvent(event);

        verify(emitter).send(event);
        assertThat(listener.onEvent(event), equalTo(Observable.empty()));
    }

    @Test
    public void onEvent_Fail() throws Exception {
        IOException exception = new IOException("fail send");
        doThrow(exception).when(emitter).send(event);
        assertThat(listener.onEvent(event).getClass(), equalTo(Observable.error(exception).getClass()));
    }

    @Test
    public void whenPredicateFalse_EmitterNotSend() throws Exception {
        when(predicate.test(event)).thenReturn(true);
        eventBus.dispatch(event);

        verify(predicate).test(event);
        verify(listener.getEmitter()).send(event);
        observer.assertNoErrors().assertValueCount(1);
    }

    @Test
    public void onEmitterException_Dispose() throws Exception {
        doThrow(new IOException("fail send")).when(emitter).send(event);
        listener.setDisposable(disposable);
        listener.onEvent(event);

        verify(emitter).complete();
        verify(emitter).onCompletion(onCompleteRunnable.capture());

        Runnable lambda = onCompleteRunnable.getValue();
        lambda.run();
        verify(disposable).isDisposed();
        verify(disposable).dispose();
    }

    @Test
    public void whenNotCookbookEvent_ShouldNotEmit() throws Exception {
        eventBus.dispatch(ANY_EVENT);

        verifyZeroInteractions(emitter, predicate);
    }
}
