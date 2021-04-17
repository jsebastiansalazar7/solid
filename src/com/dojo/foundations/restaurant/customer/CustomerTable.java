package com.dojo.foundations.restaurant.customer;

import com.dojo.foundations.restaurant.meals.Order;
import com.dojo.foundations.restaurant.team.Waiter;

import java.util.Map;

public class CustomerTable {

    private long id;


    public CustomerTable(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void eat(Map<Order, Boolean> orders, Waiter waiter) {
        System.out.println("*** Customers of the table " + id + " are eating their order ***");
        orders.keySet().forEach(order -> System.out.println("* " + order.getName()));
        System.out.println("\n");
        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            System.out.println("Problem with the food ");
            e.printStackTrace();
        }

        callWaiter(CustomerRequestEnum.BILL, orders, waiter);
    }

    public void callWaiter(CustomerRequestEnum request, Map<Order, Boolean> orderMap, Waiter waiter) {
        waiter.answerCustomerCall(request, this, orderMap);
    }

    public void payBill(Double totalToPay) {
        System.out.println("*** Customers is paying $" + totalToPay + " ***");
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            System.out.println("Problem with the payment");
            e.printStackTrace();
        }
        System.out.println("Payment completed.  Customer leaving");
    }
}
