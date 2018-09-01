package ru.web.grad.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.web.grad.model.Dish;
import ru.web.grad.repository.DishRepository;
import ru.web.grad.util.DateTimeUtil;
import ru.web.grad.util.ValidationUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class DishServiceImpl implements DishService {

    @Autowired
    private DishRepository repository;

    @Override
    public Dish get(int id) {
        return ValidationUtil.checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    public List<Dish> getAll() {
        return repository.getAll();
    }

    @Override
    public Dish create(Dish dish, int restaurantId) {
        Assert.notNull(dish, "dish must not be null!");
        ValidationUtil.checkNew(dish);
        return repository.save(dish, restaurantId);
    }

    @Override
    public Dish update(Dish dish, int restaurantId) {
        return ValidationUtil.checkNotFoundWithId(repository.save(dish, restaurantId), dish.getId());
    }

    @Override
    public List<Dish> getExactRest(int restaurant_id) {
        return repository.getExactRest(restaurant_id);
    }

    @Override
    public List<Dish> getBetween(LocalDate start, LocalDate end) {
        return repository.getBetween(DateTimeUtil.getMin(start), DateTimeUtil.getMax(end));
    }

    @Override
    public void delete(int id) {
        ValidationUtil.checkNotFoundWithId(repository.delete(id), id);
    }
}
