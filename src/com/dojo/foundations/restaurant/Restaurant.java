package com.dojo.foundations.restaurant;

import com.dojo.foundations.restaurant.customer.CustomerTable;
import com.dojo.foundations.restaurant.meals.Beverage;
import com.dojo.foundations.restaurant.meals.Food;
import com.dojo.foundations.restaurant.meals.Order;
import com.dojo.foundations.restaurant.team.Barist;
import com.dojo.foundations.restaurant.customer.CustomerRequestEnum;
import com.dojo.foundations.restaurant.team.Chef;
import com.dojo.foundations.restaurant.team.Waiter;

import java.util.HashMap;
import java.util.Map;

public class Restaurant {

    public static void main(String[] args) {

        Chef chef = new Chef("Jose", 1000.0);
        Barist barist = new Barist("Mateo", 800.0);
        Waiter waiter = new Waiter("Mariano", 500.0, chef, barist);

        CustomerTable customerTable = new CustomerTable(1L);

        Map<Order, Boolean> orderMap = new HashMap<>();
        orderMap.put(new Food("Roast Beef", 15.0, 2000L), true);
        orderMap.put(new Beverage("Vanilla Milkshake", 5.0, 1500L), false);
        orderMap.put(new Food("Napolitan Pizza", 24.5, 4000L), true);
        orderMap.put(new Beverage("Coca Cola 350mL", 4.0, 500L), false);
        orderMap.put(new Food("Chaufa Rice", 18.0, 3000L), true);
        orderMap.put(new Beverage("Water 250mL", 4.0, 500L), false);

        customerTable.callWaiter(CustomerRequestEnum.NEW_ORDER, orderMap, waiter);
    }
}
