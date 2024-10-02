package com.api;

public class Employee {
    private String name;
    private long id;


    public Employee(String name, long id) {
        this.name = name;
        this.id = id;

    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }


}
