package ru.web.grad.model;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@NamedQueries({
        @NamedQuery(name = Vote.DELETE, query = "DELETE FROM Vote v WHERE v.id=:id"),
        @NamedQuery(name = Vote.GET_ALL, query = "SELECT v FROM Vote v"),
        @NamedQuery(name = Vote.GET_EXACT_DAY, query = "SELECT v FROM Vote v WHERE v.voted=:voted"),
        @NamedQuery(name = Vote.GET_EXACT_USER, query = "SELECT v FROM Vote v WHERE v.user_id=:user_id AND v.restaurant_id=:restaurant_id AND v.voted=:voted"),
        @NamedQuery(name = Vote.GET_BETWEEN, query = "SELECT v FROM Vote v WHERE v.voted >=:startDate AND v.voted <=:endDate ORDER BY v.voted DESC"),
        @NamedQuery(name = Vote.GET_BETWEEN_WITH_REST_ID, query = "SELECT v FROM Vote v WHERE v.restaurant_id=:restaurant_id AND v.voted >=:startDate AND v.voted <=:endDate ORDER BY v.voted DESC"),
        @NamedQuery(name = Vote.GET_WITH_EXACT_REST_ID, query = "SELECT v FROM Vote v WHERE v.restaurant_id=:restaurant_id")
})
@Entity
@Table(name = "votes")
public class Vote extends AbstractEntity {

    public static final String GET_ALL = "Vote.getAll";
    public static final String DELETE = "Vote.delete";
    public static final String GET_EXACT_DAY = "Vote.getExactDay";
    public static final String GET_EXACT_USER = "Vote.getExactUser";
    public static final String GET_BETWEEN = "Vote.getBetween";
    public static final String GET_BETWEEN_WITH_REST_ID = "Vote.getBetweenWithRestId";
    public static final String GET_WITH_EXACT_REST_ID = "Vote.getWithExactRestId";

    @Column(name = "user_id", nullable = false)
    private int user_id;

    @Column(name = "restaurant_id", nullable = false)
    private int restaurant_id;

    @Column(name = "voted", nullable = false)
    @NonNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate voted;

    public Vote() {
    }

    public Vote(int id, int user_id, int restaurant_id, LocalDate voted) {
        this.id = id;
        this.user_id = user_id;
        this.restaurant_id = restaurant_id;
        this.voted = voted;
    }

    public Vote(int user_id, int restaurant_id, LocalDate voted) {
        this.id = 0;
        this.user_id = user_id;
        this.restaurant_id = restaurant_id;
        this.voted = voted;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(int restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    public LocalDate getVoted() {
        return voted;
    }

    public void setVoted(LocalDate voted) {
        this.voted = voted;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vote vote = (Vote) o;
        return user_id == vote.user_id &&
                restaurant_id == vote.restaurant_id &&
                Objects.equals(id, vote.id) &&
                Objects.equals(voted, vote.voted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user_id, restaurant_id, voted);
    }
}
