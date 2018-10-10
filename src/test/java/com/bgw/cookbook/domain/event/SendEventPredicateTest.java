package com.bgw.cookbook.domain.event;

import com.bgw.cookbook.domain.event.CookbookEvent;
import com.bgw.cookbook.domain.event.CookbookEventListener;
import com.bgw.cookbook.domain.event.SendEventPredicate;
import com.bgw.cookbook.domain.ingredient.Ingredient;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SendEventPredicateTest {

    @InjectMocks
    private SendEventPredicate predicate;

    @Mock
    private CookbookEventListener listener;

    CookbookEvent event;

    @Before
    public void setUp() {
        event = new CookbookEvent<>(new Ingredient());
        event.setChefId(1L);
        event.setIngredientIds(Arrays.asList(1L));
        event.setRecipeIds(Arrays.asList(1L));
    }

    @Test
    public void test_WhenGlobalFalse_ReturnFalse() throws Exception {
        event.setGlobal(false);
        assertFalse(predicate.test(event));
    }

    @Test
    public void test_whenGlobalTrue_ReturnTrue() throws Exception {
        event.setGlobal(true);
        assertTrue(predicate.test(event));
    }

    @Test
    public void test_ingredientIds() throws Exception {
        when(listener.getIngredientIds()).thenReturn(Arrays.asList(1L));
        assertTrue(predicate.test(event));
    }

    @Test
    public void test_recipeIds() throws Exception {
        when(listener.getRecipeIds()).thenReturn(Arrays.asList(1L));
        assertTrue(predicate.test(event));
    }
}
