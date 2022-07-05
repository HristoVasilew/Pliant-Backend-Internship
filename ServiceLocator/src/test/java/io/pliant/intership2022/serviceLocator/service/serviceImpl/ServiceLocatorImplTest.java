package io.pliant.intership2022.serviceLocator.service.serviceImpl;

import io.pliant.intership2022.serviceLocator.exeption.RegisterExistException;
import io.pliant.intership2022.serviceLocator.exeption.RegisterNotExistException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

class ServiceLocatorImplTest {

    @Test
    void testRegisterObjectCorrectlyWithGivenName() throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        ServiceLocatorImpl serviceLocator = new ServiceLocatorImpl();
        Object currentObject = new Object();

        serviceLocator.register(currentObject,"First Object Name");
        Assertions.assertEquals(currentObject, serviceLocator.get("First Object Name"));
    }

    @Test()
    public void testRegisterObjectWithGivenNameExisting() {
        ServiceLocatorImpl serviceLocator = new ServiceLocatorImpl();
        Object currentObject = new Object();

        serviceLocator.register(currentObject,"First Object Name");
        Assertions.assertThrows(RegisterExistException.class,
                () -> serviceLocator.register(currentObject,"First Object Name"));
    }


    @Test
    void testRegisterObjectCorrectlyWithoutGivenName() throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        ServiceLocatorImpl serviceLocator = new ServiceLocatorImpl();
        Object currentObject = new Object();

        serviceLocator.register(currentObject);
        Assertions.assertEquals(currentObject, serviceLocator.get(currentObject.getClass().getSimpleName()));
    }

    @Test()
    public void testRegisterObjectWithoutGivenNameExisting() {
        ServiceLocatorImpl serviceLocator = new ServiceLocatorImpl();
        Object currentObject = new Object();

        serviceLocator.register(currentObject);
        Assertions.assertThrows(RegisterExistException.class,
                () -> serviceLocator.register(currentObject));
    }


    @Test
    void testRegisterClassCorrectlyWithGivenName() throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        ServiceLocatorImpl serviceLocator = new ServiceLocatorImpl();
        Class<Object> objectClass = Object.class;
        serviceLocator.register(objectClass,"First Class Name");
        Assertions.assertInstanceOf(Object.class,serviceLocator.get("First Class Name"));
    }

    @Test()
    public void testRegisterExistingClassWithGivenName() throws InstantiationException, IllegalAccessException {
        ServiceLocatorImpl serviceLocator = new ServiceLocatorImpl();
        Class<Object> objectClass = Object.class;
        serviceLocator.register(objectClass,"First Class Name");
        Assertions.assertThrows(RegisterExistException.class,
                () -> serviceLocator.register(objectClass,"First Class Name"));
    }


    @Test
    void testRegisterClassCorrectlyWithoutGivenName() throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        ServiceLocatorImpl serviceLocator = new ServiceLocatorImpl();
        Class<Object> objectClass = Object.class;
        serviceLocator.register(objectClass);
        Assertions.assertInstanceOf(Object.class,serviceLocator.get(objectClass.getSimpleName()));
    }

    @Test()
    public void testRegisterClassWithoutGivenNameExisting() throws InstantiationException, IllegalAccessException {
        ServiceLocatorImpl serviceLocator = new ServiceLocatorImpl();
        Class<Object> objectClass = Object.class;
        serviceLocator.register(objectClass);
        Assertions.assertThrows(RegisterExistException.class,
                () -> serviceLocator.register(objectClass));
    }

    @Test()
    public void testGetObjectNotExisting(){
        ServiceLocatorImpl serviceLocator = new ServiceLocatorImpl();

        Assertions.assertThrows(RegisterNotExistException.class,
                () -> serviceLocator.get("String"));
    }

    @Test()
    public void testGetClassNotExisting(){
        ServiceLocatorImpl serviceLocator = new ServiceLocatorImpl();

        Assertions.assertThrows(RegisterNotExistException.class,
                () -> serviceLocator.get(String.class));
    }

    @Test()
    public void testGetClassReturnInstanceOfClass() throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        ServiceLocatorImpl serviceLocator = new ServiceLocatorImpl();

        Class<Object> serviceClass = Object.class;
        serviceLocator.register(serviceClass);

        assertNotNull(serviceLocator.get(serviceClass));
    }

    @Test()
    public void testGetAllClassNotExisting(){
        ServiceLocatorImpl serviceLocator = new ServiceLocatorImpl();

        Assertions.assertThrows(RegisterNotExistException.class,
                () -> serviceLocator.getAll(Object.class));
    }
    @Test
    void getAll() throws InstantiationException, IllegalAccessException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException {
        ServiceLocatorImpl serviceLocator = new ServiceLocatorImpl();

        serviceLocator.register(Object.class,"First Class Name");
        serviceLocator.register(Object.class,"Second Class Name");
        serviceLocator.register(Object.class);

        Assertions.assertEquals(3, serviceLocator.getAll(Object.class).size());
    }
}