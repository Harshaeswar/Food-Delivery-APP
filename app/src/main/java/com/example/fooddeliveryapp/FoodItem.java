package com.example.fooddeliveryapp;

import java.io.Serializable;

public class FoodItem implements Serializable {
    private int id;
    private String name;
    private double price;
    private String image;

    public FoodItem(int id, String name, double price, String image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getImage() { return image; }
}