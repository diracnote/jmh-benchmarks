package com.diracnote.reflection;

public class MutablePerson implements Person {

    private String name;

    public MutablePerson(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    public MutablePerson setName(String name) {
        this.name = name;
        return this;
    }

}
