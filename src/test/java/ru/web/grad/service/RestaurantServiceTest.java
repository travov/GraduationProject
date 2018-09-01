package ru.web.grad.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.web.grad.model.Restaurant;
import testdata.TestData;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static testdata.TestData.*;

public class RestaurantServiceTest extends AbstractServiceTest {

    @Autowired
    private RestaurantService service;

    @Test
    public void get() {
        Restaurant actual = service.get(100002);
        assertMatch(actual, RES1);
    }

    @Test
    public void getAll() {
        List<Restaurant> actual = service.getAll();
        List<Restaurant> expected = new ArrayList<>();
        expected.add(RES1);
        expected.add(RES2);
        assertMatch(actual, expected);

    }

    @Test
    public void create() {
        Restaurant created = TestData.created;
        service.create(created);
        assertMatch(service.getAll(), RES1, RES2, created);
    }

    @Test
    public void update(){
        Restaurant updated = TestData.updated;
        service.update(updated);
        assertMatch(service.get(TestData.START_MENU_ID), updated);
    }

    @Test
    public void delete(){
        service.delete(RES1.getId());
        assertMatch(service.getAll(), Arrays.asList(RES2));
    }
}