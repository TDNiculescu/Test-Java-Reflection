package com.javaReflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args)
            throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {

        Object goat = new Goat( "goat" );
        Class<?> clazz = goat.getClass();

        // getting the name of an object from a class
        System.out.println( "Goat class simple name is: " + clazz.getSimpleName() );
        System.out.println( "Goat class canonical name is: " + clazz.getCanonicalName() );
        System.out.println( "Goat class getName method returns: " + clazz.getName() );
        System.out.println();

        // creating an object if we know the fully qualified name
        Class<?> clazz1 = Class.forName( "com.javaReflection.Goat" );

        // class modifiers
        Class<?> goatClass = Class.forName( "com.javaReflection.Goat" );
        Class<?> animalClass = Class.forName( "com.javaReflection.Animal" );
        int goatMods = goatClass.getModifiers();
        int animalMods = animalClass.getModifiers();

        System.out.println( "Goat Class is public: " + Modifier.isPublic( goatMods ) );
        System.out.println( "Animal Class is abstract: " + Modifier.isAbstract( animalMods ) );
        System.out.println( "Animal Class is public: " + Modifier.isPublic( animalMods ) );
        System.out.println();

        // get the package for the class
        Package pkg = goatClass.getPackage();

        System.out.println( "Goat Class package: " + pkg.getName() );
        System.out.println();

        // determine the super class
        String str = "any string";
        Class<?> goatSuperClass = goatClass.getSuperclass();

        System.out.println( "Super Class of Goat Class: " + goatSuperClass.getSimpleName() );
        System.out.println(
                "Simple Name Super Class of String Class: " + str.getClass().getSuperclass()
                        .getSimpleName() );
        System.out.println();

        // get the list of implemented interfaces
        Class<?>[] goatInterfaces = goatClass.getInterfaces();
        Class<?>[] animalInterfaces = animalClass.getInterfaces();

        System.out
                .println( "Number of Interfaces Goat Class implements: " + goatInterfaces.length );
        System.out.println(
                "Number of Interfaces Animal Class implements: " + animalInterfaces.length );
        System.out.println(
                "The simple name of the first and only interface implemented by Goat Class: "
                        + goatInterfaces[0].getSimpleName() );
        System.out.println(
                "The simple name of the first and only interface implemented by Animal Class: "
                        + animalInterfaces[0].getSimpleName() );
        System.out.println();

        // fields
        Field[] fields = animalClass.getDeclaredFields();
        List<String> actualFields = getFieldNames( fields );

        System.out.println( "The number of fields Animal Class has: " + actualFields.size() );
        System.out.println(
                "Check if the fields Animal Class has are name and CATEGORY: " + actualFields
                        .containsAll( Arrays.asList( "name", "CATEGORY" ) ) );
        System.out.println();

        // methods
        Method[] methods = animalClass.getDeclaredMethods();
        List<String> actualMethods = getMethodNames( methods );

        System.out.println( "The number of methods Animal Class has: " + actualMethods.size() );
        System.out.println(
                "Check if the methods Animal Class has are getName, setName and getSound: "
                        + actualMethods
                        .containsAll( Arrays.asList( "getName", "setName", "getSound" ) ) );
        System.out.println();

        // inspecting constructors
        Class<?> birdClass = Class.forName( "com.javaReflection.Bird" );
        Constructor<?>[] constructorsBirdClass = birdClass.getConstructors();

        System.out.println(
                "The number of constructors Bird Class has: " + constructorsBirdClass.length );
        Constructor<?> cons1 = birdClass.getConstructor();
        Constructor<?> cons2 = birdClass.getConstructor( String.class );
        Constructor<?> cons3 = birdClass.getConstructor( String.class, boolean.class );
        System.out.println(
                "The name of the no parameter constructor of Bird Class is: " + cons1.getName() );
        System.out.println(
                "The name of the String parameter constructor of Bird Class is: " + cons2
                        .getName() );
        System.out.println(
                "The name of the String and Boolean parameter constructor of Bird Class is: "
                        + cons3.getName() );
        System.out.println();

        // how to instantiate objects at runtime while supplying their parameters
        Bird bird1 = (Bird) cons1.newInstance();
        Bird bird2 = (Bird) cons2.newInstance( "Weaver bird" );
        Bird bird3 = (Bird) cons3.newInstance( "dove", true );

        System.out.println( "The name of the bird1 Bird is: " + bird1.getName() );
        System.out.println( "The name of the bird2 Bird is: " + bird2.getName() );
        System.out.println( "The name of the bird3 Bird is: " + bird3.getName() );
        System.out.println( "bird1 Bird boolean walks: " + bird1.walks() );
        System.out.println( "bird3 Bird boolean walks: " + bird3.walks() );
        System.out.println();

        // inspecting fields
        Field[] fieldsBirdClass = birdClass.getFields();

        System.out.println(
                "The number of public fields Bird Class has is: " + fieldsBirdClass.length );
        System.out.println(
                "The first public field of Bird Class is: " + fieldsBirdClass[0].getName() );

        Field field = birdClass.getField( "CATEGORY" );
        System.out.println( "The name of the CATEGORY field in Bird Class is: " + field.getName() );

        Field[] declaredFieldsBirdClass = birdClass.getDeclaredFields();
        Field declaredFieldBirdClass = birdClass.getDeclaredField( "walks" );
        Class<?> fieldClass = declaredFieldBirdClass.getType();

        System.out.println( "The number of private fields that Bird Class has is: "
                + declaredFieldsBirdClass.length );
        System.out.println(
                "The first private field of Bird Class is: " + declaredFieldsBirdClass[0]
                        .getName() );
        System.out.println(
                "The private field walks of Bird Class is: " + declaredFieldBirdClass.getName() );
        System.out.println( "The type of the walks private field of Bird Class is: " + fieldClass
                .getSimpleName() );
        System.out.println();

        // accessing and modifying field values
        Bird bird = (Bird) birdClass.newInstance();
        declaredFieldBirdClass.setAccessible( true );

        System.out.println( "bird Bird walks before modification: " + bird.walks() );

        declaredFieldBirdClass.set( bird, true );

        System.out.println( "bird Bird walks after modification: " + bird.walks() );
        System.out.println();

        Field fieldCATEGORY = birdClass.getField( "CATEGORY" );
        fieldCATEGORY.setAccessible( true );
        System.out.println(
                "Passing null to the public static field CATEGORY of bird Bird to ge the default value: "
                        + field.get( null ) );

        // inspecting methods
        Method[] methodsOfBirdClass = birdClass.getMethods();
        List<String> methodNames = getMethodNames( methodsOfBirdClass );
        // we can get public methods of the java.lang.Object class
        System.out.println(
                "We can get public methods of the java.lang.Object class from Bird class: "
                        + methodNames.containsAll( Arrays
                        .asList( "equals", "notifyAll", "hashCode",
                                "walks", "eats", "toString" ) ) );

        // to get only the public methods
        List<String> actualMethodNames
                = getMethodNames( birdClass.getDeclaredMethods() );

        List<String> expectedMethodNames = Arrays
                .asList( "setWalks", "walks", "getSound", "eats" );

        System.out.println(expectedMethodNames.size() == actualMethodNames.size());
        System.out.println(expectedMethodNames.containsAll( actualMethodNames ));
        System.out.println(actualMethodNames.containsAll( expectedMethodNames ));
        System.out.println();

        // returning a single method by name
        Method walksMethod = birdClass.getDeclaredMethod("walks");
        Method setWalksMethod = birdClass.getDeclaredMethod("setWalks", boolean.class);

        System.out.println("walks method from Bird class is accessible: " + walksMethod.isAccessible());
        System.out.println("setWalks method from Bird class is accessible: " + setWalksMethod.isAccessible());

        walksMethod.setAccessible(true);
        setWalksMethod.setAccessible(true);

        System.out.println("walks method from Bird class is accessible: " + walksMethod.isAccessible());
        System.out.println("setWalks method from Bird class is accessible: " + setWalksMethod.isAccessible());
        System.out.println();

        // invoking a method at runtime
        Bird birdForMethod = (Bird) birdClass.newInstance();
        boolean walks = (boolean) walksMethod.invoke(birdForMethod);

        System.out.println("walks for Bird class is: " + walks);
        System.out.println("walks method is invoked at object creation: " + birdForMethod.walks());

        setWalksMethod.invoke(birdForMethod, true);

        boolean walks2 = (boolean) walksMethod.invoke(birdForMethod);

        System.out.println("walks for Bird class is: " + walks2);
        System.out.println("walks method is invoked at object creation: " + birdForMethod.walks());
    }

    private static List<String> getMethodNames(Method[] methods) {
        List<String> methodNames = new ArrayList<>();
        for (Method method : methods) {
            methodNames.add( method.getName() );
        }
        return methodNames;
    }

    private static List<String> getFieldNames(Field[] fields) {
        List<String> fieldNames = new ArrayList<>();
        for (Field field : fields) {
            fieldNames.add( field.getName() );
        }
        return fieldNames;
    }
}
