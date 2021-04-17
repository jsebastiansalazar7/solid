package com.dojo.foundations.parking.cell;

public enum RateEnum {

    MOTORCYCLE(5.0),
    CAR(10.5),
    TRUCK(18.5);

    private double rate;

    RateEnum(double rate) {
        this.rate = rate;
    }

    public double getRate() {
        return rate;
    }
}
