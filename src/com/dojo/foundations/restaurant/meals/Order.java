package com.dojo.foundations.restaurant.meals;

public abstract class Order {
    private String name;
    private double price;
    private Long waitTime;

    public Order(String name, double price, Long waitTime) {
        this.name = name;
        this.price = price;
        this.waitTime = waitTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Long getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(Long waitTime) {
        this.waitTime = waitTime;
    }
}
