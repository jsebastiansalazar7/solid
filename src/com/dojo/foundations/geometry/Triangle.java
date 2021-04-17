package com.dojo.foundations.geometry;

public class Triangle implements com.dojo.foundations.geometry.Shape {

    private final double base;
    private final double height;

    public Triangle(double base, double height) {
        this.base = base;
        this.height = height;
    }
    @Override
    public double calculateArea() {
        return base * height / 2;
    }

    @Override
    public double calculatePerimeter() {
        return 0;
    }

}
