package ru.web.grad.repository;

import ru.web.grad.model.Dish;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface DishRepository {

    Dish get(int id);

    List<Dish> getAll();

    Dish save(Dish dish, int restaurant_id);

    List<Dish> getExactRest(int restaurant_id);

    boolean delete(int id);

    List<Dish> getBetween(LocalDate start, LocalDate end);

}
