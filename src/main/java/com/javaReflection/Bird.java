package com.javaReflection;

public class Bird extends Animal {

    private boolean walks;

    @Override
    protected String getSound() {
        return null;
    }

    @Override
    public String eats() {
        return null;
    }

    public Bird() {
        super("bird");
    }

    public Bird(String name, boolean walks) {
        super(name);
        setWalks(walks);
    }

    public Bird(String name) {
        super(name);
    }

    public boolean walks() {
        return walks;
    }

    public boolean isWalks() {
        return walks;
    }

    public void setWalks(boolean walks) {
        this.walks = walks;
    }
}
