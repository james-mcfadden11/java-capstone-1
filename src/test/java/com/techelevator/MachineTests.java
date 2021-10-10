package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.crypto.Mac;
import java.util.ArrayList;
import java.util.List;

public class MachineTests {
    private Machine testMachine;
    private Item testItem;
    private List<Item> inventory = new ArrayList<>();

    @Before
    public void setup() {
        testMachine = new Machine();
        testItem = new Item("pop", 0.75, "B5", "Drink");
        testMachine.addItemToInventory(testItem);
    }

    @Test
    public void add_unlisted_amount_of_money() {
        // Arrange
        // Act
        double actual = testMachine.addToBalance(-3);
        double expected = -1.0;
        // Assert
        Assert.assertEquals(expected, actual, 2);
        }

    @Test
    public void deduct_from_balance() {
        // Arrange
        testMachine.addToBalance(50);
        // Act
        double actual = testMachine.deductFromBalance(20.55);
        double expected = 29.45;
        // Assert
        Assert.assertEquals(expected, actual, 2);
        }

    @Test
    public void test_for_change() {
        // Arrange
        testMachine.addToBalance(5);
        testMachine.deductFromBalance(2.45);
        // Act
        String actual = testMachine.change();
        String expected = "Your change is 10 quarters, 0 dimes, and 1 nickels";
        // Assert
        Assert.assertEquals(expected, actual);

    }

    @Test
    public void deduct_price_from_balance() {
        // Arrange
        testMachine.addToBalance(5);
        // Act
        double actual = testMachine.deductFromBalance(2.05);
        double expected = 2.95;
        // Assert
        Assert.assertEquals(expected, actual, 2);
    }

    @Test
    public void unknown_item_selected() {
        testMachine.addToBalance(5);
        String actual =testMachine.sellItem("A1");
        String expected = "Invalid product code! Please try again.";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void item_sold_out() {
        testMachine.addToBalance(20);
        testMachine.sellItem("B5");
        testMachine.sellItem("B5");
        testMachine.sellItem("B5");
        testMachine.sellItem("B5");
        testMachine.sellItem("B5");
        String actual = testMachine.sellItem("B5");
        String expected = "Sold out!";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void need_more_money() {
        String actual = testMachine.sellItem("B5");
        String expected = "Please add more money for this item";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void expected_outcome() {
        testMachine.addToBalance(20);
        String outputString = "";
        outputString += "Dispensing item: " + testItem.getName() + "\n";
        outputString += "Item price: $" + String.format("%.2f", testItem.getPrice()) + "\n";
        outputString += "Money remaining: $" + testMachine.getCurrentBalance() + "\n";
        testItem.setMessage();
        outputString += testItem.getMessage();

        String actual = testMachine.sellItem("B5");
        String expected = outputString;

        Assert.assertEquals(expected, actual);
    }
}