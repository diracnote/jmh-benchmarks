package com.diracnote.reflection;

public class ImmutablePerson implements Person {

    private final String name;

    public ImmutablePerson(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
