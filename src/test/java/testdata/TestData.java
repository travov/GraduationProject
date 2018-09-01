package testdata;
import ru.web.grad.model.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.EnumSet;

import static org.assertj.core.api.Assertions.assertThat;

public class TestData {

    public static final int START_MENU_ID = 100002;

    public static final Restaurant RES1 = new Restaurant(START_MENU_ID, "1st restaurant");
    public static final Restaurant RES2 = new Restaurant(START_MENU_ID + 1, "2nd restaurant");
    public static final Restaurant created = new Restaurant(0, "Created restaurant");
    public static final Restaurant updated = new Restaurant(START_MENU_ID, "Updated 1st restaurant");

    public static final Dish DISH1 = new Dish(100004, "ice-cream", LocalDate.of(2015, Month.MAY, 30), 200, null);
    public static final Dish DISH2 = new Dish(100005, "sweet pie", LocalDate.of(2015, Month.MAY, 30), 300, null);
    public static final Dish DISH3 = new Dish(100006, "green tea", LocalDate.of(2015, Month.MAY, 30), 50, null);
    public static final Dish DISH4 = new Dish(100007, "soup", LocalDate.of(2015, Month.MAY, 30), 100, null);
    public static final Dish DISH5 = new Dish(100008, "pizza", LocalDate.of(2015, Month.MAY, 31), 500, null);
    public static final Dish DISH6 = new Dish(100009, "meat", LocalDate.of(2015, Month.MAY, 31), 300, null);
    public static final Dish DISH7 = new Dish(100010, "chicken burger", LocalDate.of(2015, Month.MAY, 31), 150, null);
    public static final Dish DISH8 = new Dish(100011, "big burger", LocalDate.of(2015, Month.MAY, 31), 200, null);
    public static final Dish createdDish = new Dish(0, "pepperoni", LocalDate.now(), 600, null);
    public static final Dish updatedDish = new Dish(100004, "updated ice-cream", LocalDate.of(2015, Month.MAY, 30), 500, null);

    public static final User U1 = new User(100000, "User", "{noop}password", EnumSet.of(Role.ROLE_USER));
    public static final User ADMIN = new User(100001, "Admin", "{noop}admin", EnumSet.of(Role.ROLE_ADMIN, Role.ROLE_USER));
    public static final User createdU = new User(0, "created_user", "{noop}created", EnumSet.of(Role.ROLE_USER));
    public static final User updatedU = new User(100000, "updated_user", "{noop}updated", EnumSet.of(Role.ROLE_USER));

    public static final Vote VOTE1 = new Vote(100012, 100001, 100003, LocalDate.of(2018, 3, 20));
    public static final Vote VOTE2 = new Vote(100013, 100001, 100002, LocalDate.of(2015, 2, 2));
    public static final Vote VOTE3 = new Vote(100014, 100000, 100002, LocalDate.of(2014, 5, 30));
    public static final Vote VOTE4 = new Vote(100015, 100000, 100003, LocalDate.of(2017, 7, 8));
    public static final Vote createdVote = new Vote( 100000, 100003, LocalDate.of(2016, 6, 8));
    public static final Vote updatedVote = new Vote( 100012, 100000, 100002, LocalDate.now());

     public static<T> void assertMatch(T actual, T expected){
            assertThat(actual).isEqualTo(expected);
     }

     public static<T> void assertMatch(Iterable<T> actual, Iterable<T> expected){
            assertThat(actual).isEqualTo(expected);
     }

     public static<T> void assertMatch(Iterable<T> actual, T... expected){
            assertThat(actual).isEqualTo(Arrays.asList(expected));
     }


}
