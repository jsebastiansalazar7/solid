package com.dojo.foundations.parking.cell;

import java.time.LocalDateTime;
import java.util.Optional;

public class Cell {

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
}
