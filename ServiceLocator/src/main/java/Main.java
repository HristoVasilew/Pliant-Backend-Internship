import io.pliant.intership2022.serviceLocator.service.ServiceLocator;
import io.pliant.intership2022.serviceLocator.service.serviceImpl.ServiceLocatorImpl;

import java.lang.reflect.InvocationTargetException;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, ClassNotFoundException {
        ServiceLocatorImpl serviceLocator = new ServiceLocatorImpl();

        serviceLocator.register(new Object(),"First Object Name");
        serviceLocator.register(String.class, "Some string");
        serviceLocator.register(Object.class);


        System.out.println(serviceLocator.get("First Object Name"));
        System.out.println(serviceLocator.get(Object.class));
        System.out.println(serviceLocator.getAll(Object.class));


    }
}