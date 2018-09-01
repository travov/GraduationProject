package ru.web.grad.repository;

import ru.web.grad.model.Vote;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface VoteRepository {

    Vote save(Vote vote);

    boolean delete(int id);

    Vote get(int id);

    List<Vote> getExactDay(LocalDate dateTime);

    List<Vote> get(int userId, int restaurantId, LocalDate voted);

    List<Vote> getAll();

    List<Vote> getBetween(LocalDate startDate, LocalDate endDate);

    List<Vote> getBetweenWithRestId(int restaurantId, LocalDate startDate, LocalDate endDate);

    List<Vote> getWithExactRestId(int resturantId);
}
