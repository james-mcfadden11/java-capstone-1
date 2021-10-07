package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class Machine {

    private double currentBalance;
    private double changeDue;
    private List<Item> inventory;

    public double addToBalance(int ammount) {
        this.currentBalance += ammount;
        return currentBalance;
    }

    public double deductFromBalance(double cost) {
        this.currentBalance -= cost;
        return currentBalance;
    }

    public double change() {
        changeDue = currentBalance;
        return currentBalance = 0;
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
