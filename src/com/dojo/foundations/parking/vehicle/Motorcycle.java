package com.dojo.foundations.parking.vehicle;

import com.dojo.foundations.parking.cell.RateEnum;

public class Motorcycle extends Vehicle {

    private final double HIGH_CC_OVERCHARGE = 1.2;

    public int cc;

    public Motorcycle(String plate, int cc) {
        super(plate);
        this.cc = cc;
    }

    public int getCc() {
        return cc;
    }

    public void setCc(int cc) {
        this.cc = cc;
    }

    @Override
    public double calculateBilling(double hoursToCharge) {
        return (this.getCc() < 200) ?
                hoursToCharge * RateEnum.MOTORCYCLE.getRate() :
                hoursToCharge * RateEnum.MOTORCYCLE.getRate() * HIGH_CC_OVERCHARGE;
    }
}
