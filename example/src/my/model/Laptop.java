package my.model;

import my.model.support.Brand;
import my.model.support.Color;

public class Laptop {
    private final Brand brand;
    private final Color color;

    public Laptop(Brand brand, Color color) {
        this.brand = brand;
        this.color = color;
    }

    public Brand getBrand() {
        return brand;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "brand=" + brand +
                ", color=" + color +
                '}';
    }
}
