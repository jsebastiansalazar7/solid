package com.dojo.foundations.restaurant.team;

import com.dojo.foundations.restaurant.meals.Order;

import java.util.List;
import java.util.Map;

public interface Cooker {

    void prepareRoom();

    Map<Order, Boolean> prepareMeal(List<Order> ordersToPrepare);

    void cleanRoom();
}
