package ru.web.grad.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.web.grad.AuthorizedUser;
import ru.web.grad.model.User;
import testdata.TestData;

import java.util.Arrays;
import java.util.List;

import static testdata.TestData.*;

public class UserServiceImplTest extends AbstractServiceTest {

    @Autowired
    private UserServiceImpl service;

    @Test
    public void get() {
        User actual = service.get(100000);
        assertMatch(actual, TestData.U1);
    }

    @Test
    public void getAll() {
        List<User> actual = service.getAll();
        assertMatch(actual, U1, ADMIN);
    }

    @Test
    public void create() {
        User created = TestData.createdU;
        service.create(created);
        assertMatch(service.getAll(), U1, ADMIN, created);
    }

    @Test
    public void update() {
        User updated = TestData.updatedU;
        service.update(updated);
        assertMatch(service.getAll(), Arrays.asList(ADMIN, updated));
    }

    @Test
    public void delete() {
        service.delete(U1.getId());
        assertMatch(service.getAll(), Arrays.asList(ADMIN));
    }

    @Test
    public void getByName(){
        AuthorizedUser user = service.loadUserByUsername("Admin");
        assertMatch(user, AuthorizedUser.USER);

    }
}