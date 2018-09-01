package ru.web.grad.util;

import ru.web.grad.model.AbstractEntity;
import ru.web.grad.model.Vote;
import ru.web.grad.util.exception.IllegalRequestDataException;
import ru.web.grad.util.exception.NotFoundException;

import javax.persistence.NoResultException;

public class ValidationUtil {

    public static void checkNotFoundWithId(boolean found, int id) {
        checkNotFound(found, "id=" + id);
    }

    public static <T> T checkNotFoundWithId(T object, int id) {
        return checkNotFound(object, "id=" + id);
    }

    public static <T> T checkNotFound(T object, String msg) {
        checkNotFound(object != null, msg);
        return object;
    }

    public static void checkNotFound(boolean found, String msg) {
        if (!found) {
            throw new NotFoundException("Not found entity with " + msg);
        }
    }

    public static void checkNew(AbstractEntity entity){
        if (!entity.isNew()){
            throw new IllegalRequestDataException(entity + " must be new {id=null]");
        }
    }

    public static Vote checkVote(Vote vote){
        if (vote == null){
            throw  new IllegalRequestDataException("You cannot revote after 11:00!");
        }
        return vote;
    }
}
