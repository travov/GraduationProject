package ru.web.grad.service;

import ru.web.grad.model.Restaurant;

import java.util.List;

public interface RestaurantService {

    Restaurant get(int id);

    List<Restaurant> getAll();

    Restaurant create(Restaurant dish);

    Restaurant update(Restaurant dish);

    void delete(int id);
}
