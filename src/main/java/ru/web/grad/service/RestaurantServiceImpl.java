package ru.web.grad.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.web.grad.model.Restaurant;
import ru.web.grad.repository.RestaurantRepository;
import ru.web.grad.util.ValidationUtil;

import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantRepository repository;

    @Override
    public Restaurant get(int id) {
        return ValidationUtil.checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    public List<Restaurant> getAll() {
        return repository.getAll();
    }

    @Override
    public Restaurant create(Restaurant r) {
        Assert.notNull(r, "Restaurant must not be null!");
        ValidationUtil.checkNew(r);
        return repository.save(r);
    }

    @Override
    public Restaurant update(Restaurant r) {
        return ValidationUtil.checkNotFoundWithId(repository.save(r), r.getId());
    }

    @Override
    public void delete(int id) {
        ValidationUtil.checkNotFoundWithId(repository.delete(id), id);
    }
}
