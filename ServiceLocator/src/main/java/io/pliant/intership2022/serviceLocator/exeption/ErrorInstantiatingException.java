package io.pliant.intership2022.serviceLocator.exeption;

public class ErrorInstantiatingException extends ServiceLocatorException{
    public ErrorInstantiatingException(Object errorMessage) {
        super(String.format("Error instantiation class \"%s\"", errorMessage));
    }
}
