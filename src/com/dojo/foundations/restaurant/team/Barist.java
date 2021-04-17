package com.dojo.foundations.restaurant.team;

import com.dojo.foundations.restaurant.meals.Order;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Barist extends Employee implements Cooker {

    public Barist(String name, Double salary) {
        super(name, salary);
    }

    @Override
    public void prepareRoom() {
        try {
            System.out.println("Preparing bar");
            Thread.sleep(1L);
        } catch (InterruptedException e) {
            System.out.println("Accident preparing the bar");
            e.printStackTrace();
        }
    }

    @Override
    public Map<Order, Boolean> prepareMeal(List<Order> orders) {
        System.out.println("*** Preparing drinks ***");
        Map<Order, Boolean> readyOrders = new HashMap<>();
        prepareRoom();
        orders.stream().forEach((order) -> {
            cook(order.getName(), order.getWaitTime());
            readyOrders.put(order, false);
        });
        cleanRoom();
        System.out.println("- Drinks ready");
        return readyOrders;
    }

    @Override
    public void cleanRoom() {
        try {
            System.out.println("Cleaning bar");
            Thread.sleep(1L);
        } catch (InterruptedException e) {
            System.out.println("Accident cleaning the bar");
            e.printStackTrace();
        }
    }

    private void cook(String orderName, Long waitTime) {
        try {
            System.out.println("Preparing " + orderName);
            Thread.sleep(waitTime);
        } catch (InterruptedException e) {
            System.out.println("Accident cooking " + orderName);
            e.printStackTrace();
        }
    }
}
