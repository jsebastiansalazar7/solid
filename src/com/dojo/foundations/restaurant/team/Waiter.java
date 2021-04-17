package com.dojo.foundations.restaurant.team;

import com.dojo.foundations.restaurant.customer.CustomerTable;
import com.dojo.foundations.restaurant.meals.Order;
import com.dojo.foundations.restaurant.customer.CustomerRequestEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Waiter extends Employee {

    private final Chef chef;
    private final Barist barist;

    public Waiter(String name, Double salary, Chef chef, Barist barist) {
        super(name, salary);
        this.chef = chef;
        this.barist = barist;
    }

    public void answerCustomerCall(CustomerRequestEnum request, CustomerTable customerTable, Map<Order, Boolean> orderMap) {
        switch (request) {
            case QUESTION:
                break;
            case NEW_ORDER:
                System.out.println("*** Getting new order from the table " + customerTable.getId() + "***");
                takeOrder(customerTable, orderMap, chef, barist);
                break;
            case CHANGE:
                break;
            case COMPLAIN:
                break;
            case BILL:
                System.out.println("Bringing customer the bill");
                Double totalToPay = generateBill(orderMap);
                bringBillToTable(totalToPay, customerTable);
                break;
        }
    }

    public void takeOrder(CustomerTable customerTable, Map<Order, Boolean> orders, Chef chef, Barist barist) {
        orders.keySet().forEach(order -> System.out.println("* " + order.getName()));
        System.out.println("\n");

        List<Order> foodOrders = new ArrayList<>();
        List<Order> drinkOrders = new ArrayList<>();
        orders.forEach((order, isFood) -> {
            if (isFood) {
                foodOrders.add(order);
            } else {
                drinkOrders.add(order);
            }
        });

        Map<Order, Boolean> readyFoodOrders = chef.prepareMeal(foodOrders);
        Map<Order, Boolean> readyBeverageOrders = barist.prepareMeal(drinkOrders);
        readyFoodOrders.putAll(readyBeverageOrders);
        System.out.println("\n");

        bringOrderToTable(customerTable, readyFoodOrders, this);
    }

    public void bringOrderToTable(CustomerTable customerTable, Map<Order, Boolean> orders, Waiter waiter) {
        customerTable.eat(orders, waiter);
    }

    private double generateBill(Map<Order, Boolean> orders) {
        return orders.keySet().stream().mapToDouble(order -> order.getPrice())
                .reduce(0, (subTotal, order) -> subTotal + order);

    }

    private void bringBillToTable(Double totalToPay, CustomerTable customerTable) {
        customerTable.payBill(totalToPay);
    }
}
