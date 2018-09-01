package ru.web.grad.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.web.grad.model.Dish;
import testdata.TestData;
import java.util.List;
import static testdata.TestData.*;

public class DishServiceImplTest extends AbstractServiceTest {

    @Autowired
    private DishService service;

    @Test
    public void get() {
        Dish actual = service.get(100004);
        assertMatch(actual, DISH1);
    }

    @Test
    public void getAll() {
        List<Dish> actual = service.getAll();
        assertMatch(actual, DISH1, DISH2, DISH3, DISH4, DISH5, DISH6, DISH7, DISH8);
    }

    @Test
    public void getExactDay() {
        List<Dish> actual = service.getExactRest(100003);
        assertMatch(actual, DISH5, DISH6, DISH7, DISH8);
    }

    @Test
    public void create() {
        Dish created = TestData.createdDish;
        service.create(created, 100002);
        assertMatch(service.getAll(), DISH1, DISH2, DISH3, DISH4, DISH5, DISH6, DISH7, DISH8, createdDish);
    }

    @Test
    public void update(){
        Dish updated = TestData.updatedDish;
        service.update(updated,100002);
        assertMatch(service.get(updated.getId()), updated);
    }

    @Test
    public void delete(){
        service.delete(DISH2.getId());
        assertMatch(service.getAll(), DISH1, DISH3, DISH4, DISH5, DISH6, DISH7, DISH8);
    }
}