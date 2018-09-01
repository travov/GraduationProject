package ru.web.grad.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.web.grad.AuthorizedUser;
import ru.web.grad.model.User;
import ru.web.grad.repository.UserRepository;
import ru.web.grad.util.ValidationUtil;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService, UserDetailsService {


    private UserRepository repository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User get(int id) {
        return ValidationUtil.checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    public List<User> getAll() {
        return repository.getAll();
    }

    @Override
    public User create(User u) {
        Assert.notNull(u, "User must not be null!");
        ValidationUtil.checkNew(u);
        return repository.save(u);
    }

    @Override
    public User update(User u) {
        return ValidationUtil.checkNotFoundWithId(repository.save(u), u.getId());
    }

    @Override
    public void delete(int id) {
        ValidationUtil.checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public AuthorizedUser loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = repository.getByName(login);
        if (user == null) {
            throw new UsernameNotFoundException("User " + login + " is not found");
        }
        return new AuthorizedUser(user);
    }
}
