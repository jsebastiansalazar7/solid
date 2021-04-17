package com.dojo.foundations.geometry;

public class Square implements Shape {

    private final double side;

    public Square(double side) {
        this.side = side;
    }
    @Override
    public double calculateArea() {
        return side * side;
    }

    @Override
    public double calculatePerimeter() {
        return 0;
    }

}
