package ru.web.grad.util.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String message){
        super(message);
    }

    @Override
    public String toString() {
        return getMessage();
    }
}
