package ru.yanmayak.taskmanagementsystem.exeption;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String message) {
        super(message);
    }
}
