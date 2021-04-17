package com.dojo.foundations.restaurant.team;

import com.dojo.foundations.restaurant.meals.Order;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Chef extends Employee implements Cooker {

    public Chef(String name, Double salary) {
        super(name, salary);
    }

    @Override
    public void prepareRoom() {
        try {
            System.out.println("Preparing kitchen");
            Thread.sleep(1L);
        } catch (InterruptedException e) {
            System.out.println("Accident preparing kitchen");
            e.printStackTrace();
        }
    }

    @Override
    public Map<Order, Boolean> prepareMeal(List<Order> orders) {
        System.out.println("*** Preparing food ***");
        Map<Order, Boolean> readyOrders = new HashMap<>();
        prepareRoom();
        orders.stream().forEach((order) -> {
            cook(order.getName(), order.getWaitTime());
            readyOrders.put(order, true);
        });
        cleanRoom();
        System.out.println("- Food ready");
        return readyOrders;
    }

    @Override
    public void cleanRoom() {
        try {
            System.out.println("Cleaning kitchen");
            Thread.sleep(1L);
        } catch (InterruptedException e) {
            System.out.println("Accident cleaning kitchen");
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
