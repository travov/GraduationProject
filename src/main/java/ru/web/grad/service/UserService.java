package ru.web.grad.service;

import ru.web.grad.model.User;

import java.util.List;

public interface UserService {

    User get(int id);

    List<User> getAll();

    User create(User u);

    User update(User u);

    void delete(int id);
}
