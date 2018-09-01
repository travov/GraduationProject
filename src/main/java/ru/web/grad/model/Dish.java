package ru.web.grad.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@NamedQueries({
        @NamedQuery(name = Dish.GET_ALL, query = "SELECT d FROM Dish d"),
        @NamedQuery(name = Dish.GET_EXACT_REST_ID, query = "SELECT d FROM Dish d WHERE d.restaurant.id=:restaurant_id"),
        @NamedQuery(name = Dish.DELETE, query = "DELETE FROM Dish d WHERE d.id=:id"),
        @NamedQuery(name = Dish.GET_BETWEEN, query = "SELECT d FROM Dish d WHERE d.day >=:start AND d.day <=:end ORDER BY d.day DESC"),
        @NamedQuery(name = Dish.GET, query = "SELECT d FROM Dish d JOIN FETCH d.restaurant WHERE d.id=:id")
})
@Entity
@Table(name = "dishes")
public class Dish extends AbstractEntity {

    public static final String GET_ALL = "Dish.getAll";
    public static final String GET_EXACT_REST_ID = "Dish.getExactRestId";
    public static final String DELETE = "Dish.delete";
    public static final String GET_BETWEEN = "Dish.getBetween";
    public static final String GET = "Dish.get";

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "day")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate day;

    @Column(name = "price", nullable = false)
    private int price;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Restaurant restaurant;

    public Dish(int id, String name, LocalDate day, int price, Restaurant restaurant) {
        this.id = id;
        this.name = name;
        this.day = day;
        this.price = price;
        this.restaurant = restaurant;
    }

    public Dish(int id, String name, LocalDate day, int price) {
        this(id, name, day, price, null);
    }

    public Dish() {
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public LocalDate getDay() {
        return day;
    }

    public void setDay(LocalDate day) {
        this.day = day;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dish dish = (Dish) o;
        return price == dish.price &&
                //restaurant_id == dish.restaurant_id &&
                Objects.equals(id, dish.id) &&
                Objects.equals(name, dish.name) &&
                Objects.equals(day, dish.day);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, day, price);
    }

    @Override
    public String toString() {
        return "Dish{" +
                "name='" + name + '\'' +
                ", day=" + day +
                ", price=" + price +
                ", restaurant_id=" +
                ", id=" + id +
                '}';
    }
}
