package com.dojo.foundations.parking.cell;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

public class Cell {

    private final double WAIVE_TIME = 0.02;

    public String code;
    public CellTypeEnum type;
    public boolean isFree;
    public Optional<LocalDateTime> enterDateTime;

    public Cell(String code, CellTypeEnum type, boolean isFree, Optional<LocalDateTime> enterDateTime) {
        this.code = code;
        this.type = type;
        this.isFree = isFree;
    }

    public String getCode() {
        return code;
    }

    public CellTypeEnum getType() {
        return type;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        isFree = free;
    }

    public Optional<LocalDateTime> getEnterDateTime() {
        return enterDateTime;
    }

    public void setEnterDateTime(Optional<LocalDateTime> enterDateTime) {
        this.enterDateTime = enterDateTime;
    }

    public int obtainHoursToCharge() {
        double hours = (double) ChronoUnit.MINUTES.between(this.getEnterDateTime().get(), LocalDateTime.now()) / 60;
        double completeHours = Math.floor(hours);
        double incompleteHours = hours - completeHours;
        return incompleteHours <= WAIVE_TIME ? (int) completeHours: (int) Math.ceil(hours);
    }
}
