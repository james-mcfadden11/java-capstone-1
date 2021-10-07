package com.techelevator;

public class Machine {

    private double currentBalance;
    private double changeDue;

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
