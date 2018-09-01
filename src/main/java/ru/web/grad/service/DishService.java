package ru.web.grad.service;

import ru.web.grad.model.Dish;
import java.time.LocalDate;
import java.util.List;

public interface DishService {

    Dish get(int id);

    List<Dish> getAll();

    Dish create(Dish dish, int restaurantId);

    Dish update(Dish dish, int restaurantId);

    List<Dish> getExactRest(int restaurant_id);

    List<Dish> getBetween(LocalDate start, LocalDate end);

    void delete(int id);
}
