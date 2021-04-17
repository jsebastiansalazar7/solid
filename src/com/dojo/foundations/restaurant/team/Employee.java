package com.dojo.foundations.restaurant.team;

public abstract class Employee {
    private final String name;
    private final Double salary;

    public Employee(String name, Double salary) {
        this.name = name;
        this.salary = salary;
    }
}
