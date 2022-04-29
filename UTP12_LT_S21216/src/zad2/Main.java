package zad2;

import pakiet.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Main {
    public static void main (String[] args) {
        Field[] allFields = Car.class.getFields();
        Method[] allMethods = Car.class.getDeclaredMethods();
        Constructor<?>[] allConstructors = Car.class.getDeclaredConstructors();
        Field[] superFields = Car.class.getSuperclass().getDeclaredFields();
        Class<?> C = Car.class.getSuperclass();

        while (C != null) {
            System.out.println(C.getName());
            C = C.getSuperclass();
        }

        for (Constructor<?> c : allConstructors) {
            if (c.getParameterCount() != 0)
                System.out.println(c);
        }

        for (Method m : allMethods) {
            if (!Modifier.isPrivate(m.getModifiers()))
                System.out.println(m);
        }

        for (Field f : allFields) {
            System.out.println(f);
        }

        for (Field f : superFields) {
            System.out.println(f);
        }


        System.out.println();

        Car car = new Car("S", "Jan");
        car.setOwner("Kuba");


    }
}
