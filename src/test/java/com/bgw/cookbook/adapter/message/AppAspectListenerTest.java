package com.bgw.cookbook.adapter.message;

import com.bgw.cookbook.adapter.service.ChefDataService;
import com.bgw.cookbook.domain.chef.Chef;
import com.bgw.cookbook.domain.event.CookbookEvent;
import com.bgw.cookbook.domain.event.Event;
import com.bgw.cookbook.domain.event.EventBus;
import com.bgw.cookbook.domain.ingredient.Ingredient;
import com.bgw.cookbook.test.observables.TrampolineSchedulerRule;

import io.reactivex.observers.TestObserver;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
public class AppAspectListenerTest {

    private TestObserver<Event> observer = new TestObserver<>();

    @Mock
    private EventBus eventBus;

    @Rule
    public TrampolineSchedulerRule trampolineRule = new TrampolineSchedulerRule();

    @InjectMocks
    private AppAspectListener listener;

    @Captor
    private ArgumentCaptor<CookbookEvent> busCaptor;


    @Test
    public void afterChefUpdateTest() throws Exception {
        Chef chef = new Chef(2L, "joe");
        listener.afterChefUpdate(chef);
        verify(eventBus).dispatch(busCaptor.capture());
        CookbookEvent event = busCaptor.getValue();
        assertThat(event.getChefId(), equalTo(chef.getId()));
    }
}
