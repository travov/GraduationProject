package ru.web.grad.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.web.grad.model.Vote;
import testdata.TestData;
import java.time.LocalDate;
import java.util.Collections;
import static testdata.TestData.*;

public class VoteServiceImplTest extends AbstractServiceTest {

    @Autowired
    private VoteService service;

    @Test
    public void get() {
        assertMatch(service.get(100012), VOTE1);
    }

    @Test
    public void getAll() {
        assertMatch(service.getAll(), VOTE1, VOTE2, VOTE3, VOTE4);
    }

    @Test
    public void getBetween() {
        assertMatch(service.getBetween(LocalDate.of(2015, 1, 1), LocalDate.of(2018, 9 , 1)), VOTE1, VOTE4, VOTE2);
    }

    @Test
    public void getBetweenWithRestId() {
        assertMatch(service.getBetweenWithRestId(100003, LocalDate.of(2015, 1, 1), LocalDate.of(2018, 9 , 1)), VOTE1, VOTE4);
    }

    @Test
    public void getWithExactRestId() {
        assertMatch(service.getWithExactRestId(100003), VOTE1, VOTE4);
    }

    @Test
    public void create() {
        Vote created = TestData.createdVote;
        service.create(created);
        assertMatch(service.getAll(), VOTE1, VOTE2, VOTE3, VOTE4, created);
    }


    @Test
    public void update() {
        Vote updated = TestData.updatedVote;
        service.update(updated);
        assertMatch(service.getAll(), VOTE2, VOTE3, VOTE4, updated);
    }

    @Test
    public void getExactDay() {
        assertMatch(service.getExactDay(LocalDate.of(2018, 3, 20)), Collections.singletonList(VOTE1));
    }

    @Test
    public void delete() {
        service.delete(100015);
        assertMatch(service.getAll(), VOTE1, VOTE2, VOTE3);
    }
}