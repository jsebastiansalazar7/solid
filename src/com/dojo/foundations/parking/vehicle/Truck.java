package com.dojo.foundations.parking.vehicle;

import com.dojo.foundations.parking.cell.RateEnum;

public class Truck extends Vehicle {

    private final double HIGH_TON_OVERCHARGE = 1.35;

    public double ton;

    public Truck(String plate, double ton) {
        super(plate);
        this.ton = ton;
    }

    public double getTon() {
        return ton;
    }

    public void setTon(double ton) {
        this.ton = ton;
    }

    @Override
    public double calculateBilling(double hoursToCharge) {
        return (this.getTon() < 4.0) ?
                hoursToCharge * RateEnum.TRUCK.getRate() :
                hoursToCharge * RateEnum.TRUCK.getRate() * HIGH_TON_OVERCHARGE;
    }
}
