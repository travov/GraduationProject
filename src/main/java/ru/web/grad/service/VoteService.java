package ru.web.grad.service;

import ru.web.grad.model.Vote;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface VoteService {

    Vote get(int id);

    List<Vote> getAll();

    Vote create(Vote vote);

    Vote update(Vote vote);

    List<Vote> getExactDay(LocalDate dateTime);

    Vote get(int userId, int restaurantId, LocalDate voted);

    void delete(int id);

    Vote vote(int restaurant_id);

    List<Vote> getBetween(LocalDate startDate, LocalDate endDate);

    List<Vote> getBetweenWithRestId(int restaurantId, LocalDate startDate, LocalDate endDate);

    List<Vote> getWithExactRestId(int restaurantId);
}
