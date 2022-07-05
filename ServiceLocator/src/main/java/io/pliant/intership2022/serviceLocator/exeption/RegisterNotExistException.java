package io.pliant.intership2022.serviceLocator.exeption;

public class RegisterNotExistException extends ServiceLocatorException{
    public RegisterNotExistException(String errorMessage) {
        super(String.format("Not existing register with that name \"%s\"!", errorMessage));
    }
}
