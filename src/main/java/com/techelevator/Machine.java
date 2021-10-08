package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Machine {
    private double currentBalance;
    private String changeDue;
    private List<Item> inventory = new ArrayList<>();
    private File file = new File("Log.txt");
    private PrintWriter printWriter;

    public Machine() {
        try {
            this.printWriter = new PrintWriter(this.file);
        } catch (FileNotFoundException e) {
            System.out.println("file was not found");
        }
    }

    public void logTransaction(double transactionAmount, String transactionType) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");
        Date date = new Date();
        if (transactionType.equals("deposit")) {
            printWriter.println(formatter.format(date) + " FEED MONEY: $" + String.format("%.2f", (currentBalance - transactionAmount)) + " $" + String.format("%.2f", currentBalance));
        } else if (transactionType.equals("change")) {
            printWriter.println(formatter.format(date) + " GIVE CHANGE: $" + String.format("%.2f", currentBalance) + " $0.00");
            printWriter.close();
        }
    }

    public void logTransaction(Item item) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");
        Date date = new Date();
        printWriter.println(formatter.format(date) + " " + item.getName() + " " + item.getLocation() + " $" + String.format("%.2f", currentBalance) + " $" + String.format("%.2f", (currentBalance - item.getPrice())));
    }

    public double addToBalance(int amount) {
        if (amount != 1 && amount != 2 && amount != 5 && amount != 10 && amount != 20 && amount != 50 && amount != 100) {
            System.out.println("Invalid bill size!");
            return -1.0;
        } else {
            this.currentBalance += amount;
            this.logTransaction(amount, "deposit");
            return currentBalance;
        }
    }

    public double deductFromBalance(double cost) {
        this.currentBalance = getCurrentBalance() - cost;
        return currentBalance;
    }

    public String change() {
        int quarters = 0;
        int dimes = 0;
        int nickels = 0;

        this.logTransaction(getCurrentBalance(), "change");
        String change = "";
        while (getCurrentBalance() >= 0.05) {
            if (getCurrentBalance() >= 0.25) {
                quarters += 1;
                currentBalance -= 0.25;
            } else if (getCurrentBalance() >= 0.10) {
                dimes += 1;
                currentBalance -= 0.10;
            } else if (getCurrentBalance() >= 0.05) {
                nickels += 1;
                currentBalance -= 0.05;
            }
        }
        this.currentBalance = 0;
        change = "Your change is " + quarters + " quarters, " + dimes + " dimes, and " + nickels + " nickels";
        return change;
    }

    public void printInventory() {
        for (Item item : inventory) {
            if (item.getQuantity() != 0) {
                System.out.println(item.getLocation() + " " + item.getName() + " Price: $" + item.getPrice() + " Quantity: " + item.getQuantity());
            } else {
                System.out.println(item.getLocation() + " " + item.getName() + " Price: $" + item.getPrice() + " Quantity: SOLD OUT");
            }
        }
    }

    public void addItemToInventory(Item item) {
        this.inventory.add(item);
    }

    public void restock() {
        File inventoryFile = new File("vendingmachine.csv");
        try (Scanner fileScanner = new Scanner(inventoryFile)) {
            // for each line in inventory file, save a new item object
            // then add it to the list of items in machine
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] splitLine = line.split("[//|]");
                this.addItemToInventory(new Item(splitLine[1], Double.parseDouble(splitLine[2]), splitLine[0], splitLine[3]));
                // format --> A1|Potato Crisps|3.05|Chip
            }
        } catch (FileNotFoundException e) {
            System.out.println("File was not found!");
        }
    }

    // return an Item for unit testing purposes?
    public String sellItem(String itemLocation) {
        for (Item item : inventory) {
            if (item.getLocation().equals(itemLocation)) {
                if (item.getQuantity() == 0) {
                    return "Sold out!";
                }
                if (getCurrentBalance() >= item.getPrice()) {
                    String outputString = "";
                    outputString += "Dispensing item: " + item.getName() + "\n";
                    outputString += "Item price: $" + String.format("%.2f", item.getPrice()) + "\n";
                    outputString += "Money remaining: $" + getCurrentBalance() + "\n";
                    item.setMessage();
                    outputString += item.getMessage();

                    this.logTransaction(item);
                    this.deductFromBalance(item.getPrice());
                    item.setQuantity(item.getQuantity() - 1);

                    return outputString;
                } else {
                    return "Please add more money for this item";
                }
            }
        }
        return "Invalid product code! Please try again.";
    }

    public double getCurrentBalance() {
        this.currentBalance = (double)Math.round(currentBalance * 100.0) / 100.0;
        return currentBalance;
    }

}
