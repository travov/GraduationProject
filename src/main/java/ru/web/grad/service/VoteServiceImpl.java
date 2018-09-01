package ru.web.grad.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.web.grad.AuthorizedUser;
import ru.web.grad.model.Vote;
import ru.web.grad.repository.VoteRepository;
import ru.web.grad.util.ValidationUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class VoteServiceImpl implements VoteService {

    @Autowired
    private VoteRepository repository;

    @Override
    public Vote get(int id) {
        return ValidationUtil.checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    public List<Vote> getAll() {
        return repository.getAll();
    }

    @Override
    public List<Vote> getBetween(LocalDate startDate, LocalDate endDate) {
        return repository.getBetween(startDate, endDate);
    }

    @Override
    public List<Vote> getBetweenWithRestId(int restaurantId, LocalDate startDate, LocalDate endDate) {
        return repository.getBetweenWithRestId(restaurantId, startDate, endDate);
    }

    @Override
    public List<Vote> getWithExactRestId(int restaurantId) {
        return repository.getWithExactRestId(restaurantId);
    }

    @Override
    public Vote create(Vote vote) {
        Assert.notNull(vote, "vote must not be null!");
        return repository.save(vote);
    }

    @Override
    public Vote vote(int restaurant_id) {
        Vote vote = get(AuthorizedUser.id(), restaurant_id, LocalDate.now());
        if (vote == null) {
            vote = new Vote(AuthorizedUser.id(), restaurant_id, LocalDate.now());
        }
        vote.setRestaurant_id(restaurant_id);
        vote = !vote.isNew() && LocalTime.now().isAfter(LocalTime.of(11, 0)) ? null: create(vote);

        return ValidationUtil.checkVote(vote);
    }

    @Override
    public Vote update(Vote vote) {
        return ValidationUtil.checkNotFoundWithId(repository.save(vote), vote.getId());
    }

    @Override
    public List<Vote> getExactDay(LocalDate dateTime) {
        return repository.getExactDay(dateTime);
    }

    @Override
    public Vote get(int userId, int restaurantId, LocalDate voted) {
        return DataAccessUtils.singleResult(repository.get(userId, restaurantId, voted));
    }

    @Override
    public void delete(int id) {
        ValidationUtil.checkNotFoundWithId(repository.delete(id), id);
    }
}
