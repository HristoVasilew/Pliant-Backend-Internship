package io.pliant.internship2022.servicelocator.service.locator.impl;

import io.pliant.internship2022.servicelocator.exeptions.ServiceLocatorException;
import io.pliant.internship2022.servicelocator.model.entity.NameEntity;
import io.pliant.internship2022.servicelocator.model.entity.ServiceEntity;
import io.pliant.internship2022.servicelocator.repository.ServiceRepository;
import io.pliant.internship2022.servicelocator.service.locator.ServiceLocator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceLocatorImpl implements ServiceLocator {

    private final ServiceRepository serviceRepository;

    public ServiceLocatorImpl(ServiceRepository userRepository) {
        this.serviceRepository = userRepository;
    }

    @Override
    public void register(Object service, String name) throws ServiceLocatorException {
        NameEntity nameEntity = new NameEntity()
                .setName(name);
        if (serviceRepository.findByName(nameEntity).isPresent()){
            throw new ServiceLocatorException("Service with that name was already registered : " + name);
        }
            ServiceEntity serviceEntity = new ServiceEntity()
                    .setObject(service)
                    .setName(nameEntity);


        serviceRepository.save(serviceEntity);

    }

    @Override
    public void register(Object service) throws ServiceLocatorException {

    }

    @Override
    public void register(Class<?> serviceClass, String name) throws ServiceLocatorException {

    }

    @Override
    public void register(Class<?> serviceClass) throws ServiceLocatorException {

    }

    @Override
    public Object get(String name) throws ServiceLocatorException {
        return null;
    }

    @Override
    public <T> T get(Class<T> serviceClass) throws ServiceLocatorException {
        return null;
    }

    @Override
    public <T> List<T> getAll(Class<T> serviceClass) throws ServiceLocatorException {
        return null;
    }
}
