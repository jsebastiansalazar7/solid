package com.dojo.foundations.parking.cell;

import java.util.List;

public class ParkingLot {

    protected List<Cell> parkingLot;

    public ParkingLot(List<Cell> parkingLot) {
        this.parkingLot = parkingLot;
    }

    public List<Cell> getParkingLot() {
        return parkingLot;
    }
}
