package com.bgw.cookbook.adapter.api.api;

import com.bgw.cookbook.app.Application;
import com.bgw.cookbook.domain.chef.Chef;
import com.bgw.cookbook.domain.event.CookbookEvent;
import com.bgw.cookbook.domain.event.EventBus;


import com.bgw.cookbook.test.observables.TrampolineSchedulerRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class ChefControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private EventBus bus;

    @Rule
    public TrampolineSchedulerRule rule = new TrampolineSchedulerRule();

    private HttpHeaders headers = new HttpHeaders();

    private static final Long chefId = 1L;

    @Before
    public void setUp() throws Exception {
        headers.add("ChefId", String.valueOf(chefId));

    }

    @Test
    public void getEventStream_Success() throws Exception {
        mvc.perform(get("/api/cookbook-events")
        .headers(headers)
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getStream_andDispatch() throws Exception {
        MvcResult result = mvc.perform(get("/api/cookbook-events")
                .headers(headers)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        Chef chef = new Chef();
        chef.setName("Joe");
        CookbookEvent event = new CookbookEvent<>(chef);
        event.setGlobal(true);

        bus.dispatch(event);
        assertThat(result.getResponse().getContentAsString(), equalTo("data:{\"Chef\":{\"id\":null,\"name\":\"Joe\",\"chefIngredients\":[],\"recipes\":[]}}\n\n"));

    }
}
