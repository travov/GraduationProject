package ru.web.grad;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.web.grad.model.Role;
import ru.web.grad.model.User;

import java.util.Arrays;
import java.util.HashSet;

import static java.util.Objects.requireNonNull;

public class AuthorizedUser extends org.springframework.security.core.userdetails.User {

    public static final AuthorizedUser USER = new AuthorizedUser(new User(100001, "Admin", "admin", new HashSet<>(Arrays.asList(Role.ROLE_ADMIN))));

    private Integer id;

    public AuthorizedUser(User user) {
        super(user.getLogin(), user.getPassword(), true, true, true, true, user.getRoles());
        this.id = user.getId();
    }

    public static AuthorizedUser safeGet() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return null;
        }
        Object principal = auth.getPrincipal();
        return (principal instanceof AuthorizedUser) ? (AuthorizedUser) principal : null;
    }

    public static AuthorizedUser get() {
        AuthorizedUser user = safeGet();
        requireNonNull(user, "No authorized user found");
        return user;
    }

    public static int id() {
        return get().id;
    }

}
