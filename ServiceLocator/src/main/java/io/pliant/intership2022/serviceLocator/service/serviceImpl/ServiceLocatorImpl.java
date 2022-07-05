package io.pliant.intership2022.serviceLocator.service.serviceImpl;

import io.pliant.intership2022.serviceLocator.exeption.ErrorInstantiatingException;
import io.pliant.intership2022.serviceLocator.exeption.RegisterExistException;
import io.pliant.intership2022.serviceLocator.exeption.RegisterNotExistException;
import io.pliant.intership2022.serviceLocator.exeption.ServiceLocatorException;
import io.pliant.intership2022.serviceLocator.service.ServiceLocator;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ServiceLocatorImpl implements ServiceLocator {
    private Map<String, Object> serviceLocator = new HashMap<>();

    @Override
    public void register(Object service, String name) throws ServiceLocatorException {
        if (serviceLocator.containsKey(name)) {
            throw new RegisterExistException(name);
        }

        serviceLocator.put(name, service);
    }

    @Override
    public void register(Object service) throws ServiceLocatorException {
        if (serviceLocator.containsKey(service.getClass().getSimpleName())) {
            throw new RegisterExistException(service.getClass().getSimpleName());
        }

        serviceLocator.put(service.getClass().getSimpleName(), service);
    }

    @Override
    public void register(Class<?> serviceClass, String name) throws ServiceLocatorException, InstantiationException, IllegalAccessException {
        if (serviceLocator.containsKey(name)) {
            throw new RegisterExistException(name);
        }

        serviceLocator.put(name, serviceClass);
    }

    @Override
    public void register(Class<?> serviceClass) throws ServiceLocatorException, InstantiationException, IllegalAccessException {
        if (serviceLocator.containsKey(serviceClass.getSimpleName())) {
            throw new RegisterExistException(serviceClass.getSimpleName());
        }

        serviceLocator.put(serviceClass.getSimpleName(), serviceClass);
    }

    @Override
    public Object get(String name) throws ServiceLocatorException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, ClassNotFoundException {
        Object service = serviceLocator.get(name);

        if (service == null) {
            throw new RegisterNotExistException(name);
        }

        if (service instanceof Class<?>){
            Object newInstance = Class.forName(((Class<?>) service).getName()).getConstructor().newInstance();

            serviceLocator.replace(name, newInstance);
            return serviceLocator.get(name);
        }

        return service;
    }

    @Override
    public <T> T get(Class<T> serviceClass) throws ServiceLocatorException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Object service = serviceLocator.get(serviceClass.getSimpleName());
        if (!serviceLocator.containsKey(serviceClass.getSimpleName())) {
            throw new RegisterNotExistException(serviceClass.getSimpleName());
        }

        Object newInstance = Class.forName(((Class<?>) service).getName()).getConstructor().newInstance();

        serviceLocator.replace(serviceClass.getSimpleName(), newInstance);
        return (T) serviceLocator.get(serviceClass.getSimpleName());
    }

    @Override
    public <T> List<T> getAll(Class<T> serviceClass) throws ServiceLocatorException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        if (!serviceLocator.containsKey(serviceClass.getSimpleName())) {
            throw new RegisterNotExistException(serviceClass.getSimpleName());
        }
        List<T> result = new ArrayList<>();

        try {
            for (Map.Entry<String, Object> service : serviceLocator.entrySet()) {
                if (service.getValue() instanceof Class<?>){
                    Object newInstance =
                            Class.forName(((Class<?>) service.getValue())
                                    .getName())
                                    .getConstructor()
                                    .newInstance();

                    serviceLocator.replace(service.getKey(), newInstance);
                }
                result.add((T) service);
            }
        }catch (ErrorInstantiatingException e){
            throw new ErrorInstantiatingException("");
        }

       return result;
    }


}
