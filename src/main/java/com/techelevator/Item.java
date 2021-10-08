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

    public void setMessage() {
        if (this.type.equals("Chip")) {
            this.message = "Crunch Crunch, Yum!";
        } else if (this.type.equals("Candy")) {
            this.message = "Munch Munch, Yum!";
        } else if (this.type.equals("Drink")) {
            this.message = "Glug Glug, Yum!";
        } else if (this.type.equals("Gum")) {
            this.message = "Chew Chew, Yum!";
        }
    }

    public String getMessage() {
        return this.message;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
