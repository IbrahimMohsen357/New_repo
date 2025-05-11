package com.example.map;

import javafx.scene.paint.Color;

public class UserLocation {
    private String name;
    private double x;
    private double y;
    private Color color;

    public UserLocation(String name, double x, double y, Color color) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.color = color;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Getter for x
    public double getX() {
        return x;
    }

    // Setter for x
    public void setX(double x) {
        this.x = x;
    }

    // Getter for y
    public double getY() {
        return y;
    }

    // Setter for y
    public void setY(double y) {
        this.y = y;
    }

    // Getter for color
    public Color getColor() {
        return color;
    }
}
