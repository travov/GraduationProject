package ru.web.grad.repository;

import ru.web.grad.model.Restaurant;

import java.util.List;

public interface RestaurantRepository {

    Restaurant get(int id);

    List<Restaurant> getAll();

    Restaurant save(Restaurant r);

    boolean delete(int id);
}
