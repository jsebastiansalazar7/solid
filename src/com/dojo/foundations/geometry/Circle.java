package com.dojo.foundations.geometry;

public class Circle implements com.dojo.foundations.geometry.Shape, CircularShape {

    private final double radio;

    public Circle(double radio) {
        this.radio = radio;
    }
    @Override
    public double calculateArea() {
        return radio * radio * Math.PI;
    }

    @Override
    public double calculatePerimeter() {
        return 0;
    }

    @Override
    public double calculateCircularSector() {
        return 0;
    }
}
