package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Machine {

    private double currentBalance;
    private double changeDue;
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
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        if (transactionType.equals("deposit")) {
            printWriter.println(formatter.format(date) + " FEED MONEY: $" + (currentBalance - transactionAmount) + " $" + currentBalance);
        } else if (transactionType.equals("change")) {
            printWriter.println(formatter.format(date) + " GIVE CHANGE: $" + currentBalance + " $0.00");
            printWriter.close();
        }
    }

    public void logTransaction(Item item) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        printWriter.println(formatter.format(date) + " " + item.getName() + " " + item.getLocation() + " $" + currentBalance + " $" + (currentBalance - item.getPrice()));
    }


    public double addToBalance(int ammount) {
        if (ammount != 1 && ammount != 2 && ammount != 5 && ammount != 10 && ammount != 20 && ammount != 50 && ammount != 100) {
            System.out.println("Invalid bill size!");
            return -1;
        } else {
            this.currentBalance += ammount;
            this.logTransaction(ammount, "deposit");
            return currentBalance;
        }
    }

    public double deductFromBalance(double cost) {
        this.currentBalance -= cost;
        return currentBalance;
    }

    public double change() {
        changeDue = currentBalance;
        int quarters = 0;
        int dimes = 0;
        int nickels = 0;

        this.logTransaction(changeDue, "change");

        while (changeDue >= 0.05) {
            if (changeDue >= 0.25) {
                quarters += 1;
                changeDue -= 0.25;
            } else if (changeDue >= 0.10) {
                dimes += 1;
                changeDue -= 0.10;
            } else if (changeDue >= 0.05) {
                nickels += 1;
                changeDue -= 0.05;
            }
        }

        this.currentBalance = 0;


        System.out.println("Your change is " + quarters + " quarters, " + dimes + " dimes, and " + nickels + " nickels");

        return currentBalance = 0;
    }

    public void printInventory() {
        for (Item item : inventory) {
            System.out.println(item.getLocation() + " " + item.getName() + " Price: " + item.getPrice() + " Quantity: " + item.getQuantity());
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

    public void sellItem(String itemLocation) {
        for (Item item : inventory) {
            if (item.getLocation().equals(itemLocation)) {
                if (item.getQuantity() == 0) {
                    System.out.println("Sold out!");
                    return;
                }

                if (this.currentBalance >= item.getPrice()) {
                    this.logTransaction(item);
                    this.deductFromBalance(item.getPrice());
                    item.setQuantity(item.getQuantity() - 1);
                    item.setMessage();
                    System.out.println(item.getMessage());
                } else {
                    System.out.println("Please add more money for this item");
                }
            }
        }
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    /*
    display items
    - current items
        - qty on hand
        - location
        - name
        - price
        - type

    purchase >>
    - add money *
    - select product
    - finish transaction
    - balance on hand *

    feed money
    - add to balance (int only) *

    select product
    - list items (similar to display items)
    - enter code for product
        - sold out message
        - dispense message
            - item name
            - cost
            - remaining balance
            - product specific message
        - update balance

     finish transaction
     - give change (only in coins)
     - return to main menu

     exit
     - exit the program


     log
     - write all transactions to a txt file
        - time stamp
        - action taken / product bought
        - money added or deducted
        - balance


     */
}
