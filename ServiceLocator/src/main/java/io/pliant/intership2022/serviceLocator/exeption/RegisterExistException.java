package io.pliant.intership2022.serviceLocator.exeption;

public class RegisterExistException extends ServiceLocatorException {
    public RegisterExistException(String errorMessage) {
        super(String.format("Register with that name \"%s\" already exists!", errorMessage));
    }
}
