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

    public long getParkingLotOccupation() {
        return this.getParkingLot().stream().filter(entry -> !entry.isFree()).count();
    }

    public long getFreeCells() {
        return this.getParkingLot().stream().filter(Cell::isFree).count();
    }
}
