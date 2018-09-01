package ru.web.grad.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.web.grad.model.Vote;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class VoteRepositoryImpl implements VoteRepository{

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Vote save(Vote vote) {
        if (vote.isNew()){
            em.persist(vote);
            return vote;
        }
        else return em.merge(vote);
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return em.createNamedQuery(Vote.DELETE).setParameter("id", id).executeUpdate() != 0;
    }

    @Override
    public Vote get(int id) {
        return em.find(Vote.class, id);
    }

    @Override
    public List<Vote> getExactDay(LocalDate dateTime) {
        return em.createNamedQuery(Vote.GET_EXACT_DAY, Vote.class).setParameter("voted", dateTime).getResultList();
    }

    @Override
    public List<Vote> get(int userId, int restaurantId, LocalDate voted) {
        return em.createNamedQuery(Vote.GET_EXACT_USER, Vote.class).setParameter("user_id", userId).setParameter("restaurant_id", restaurantId).setParameter("voted", voted).getResultList();
    }

    @Override
    public List<Vote> getAll() {
        return em.createNamedQuery(Vote.GET_ALL, Vote.class).getResultList();
    }

    @Override
    public List<Vote> getBetween(LocalDate startDate, LocalDate endDate) {
        return em.createNamedQuery(Vote.GET_BETWEEN, Vote.class).setParameter("startDate", startDate).setParameter("endDate", endDate).getResultList();
    }

    @Override
    public List<Vote> getBetweenWithRestId(int restaurantId, LocalDate startDate, LocalDate endDate) {
        return em.createNamedQuery(Vote.GET_BETWEEN_WITH_REST_ID, Vote.class).setParameter("restaurant_id", restaurantId).setParameter("startDate", startDate).setParameter("endDate", endDate).getResultList();
    }

    @Override
    public List<Vote> getWithExactRestId(int restaurantId) {
        return em.createNamedQuery(Vote.GET_WITH_EXACT_REST_ID, Vote.class).setParameter("restaurant_id", restaurantId).getResultList();
    }
}
