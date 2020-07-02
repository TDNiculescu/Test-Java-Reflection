package com.javaReflection;

public class Goat extends Animal implements Locomotion {
    private String name;

    @Override
    protected String getSound() {
        return "bleat";
    }

    @Override
    public String getLocomotion() {
        return "walks";
    }

    @Override
    public String eats() {
        return "grass";
    }

    public Goat(String name) {
        this.name = name;
    }
}
