package com.dojo.foundations.parking.vehicle;

import com.dojo.foundations.parking.cell.RateEnum;

public class Car extends Vehicle {

    public Car (String plate) {
        super(plate);
    }

    @Override
    public double calculateBilling(double hoursToCharge) {
        return hoursToCharge * RateEnum.CAR.getRate();
    }

}
