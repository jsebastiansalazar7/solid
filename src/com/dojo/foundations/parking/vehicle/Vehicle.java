package com.dojo.foundations.parking.vehicle;

public abstract class Vehicle implements Billing {

    public String plate;

    public Vehicle (String plate) {
        this.plate = plate;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

}
