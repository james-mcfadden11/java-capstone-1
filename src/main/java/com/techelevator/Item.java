package com.techelevator;

public class Item {
    private String name;
    private double price;
    private String location;
    private String type;
    private int quantity;
    private String message;

    public Item(String name, double price, String location, String type) {
        this.name = name;
        this.price = price;
        this.location = location;
        this.type = type;
        this.quantity = 5;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getLocation() {
        return location;
    }

    public String getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
