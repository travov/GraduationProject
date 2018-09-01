package ru.web.grad.repository;

import ru.web.grad.model.User;

import java.util.List;

public interface UserRepository {

    User save(User user);

    boolean delete(int id);

    User get(int id);

    List<User> getAll();

    User getByName(String login);

}
