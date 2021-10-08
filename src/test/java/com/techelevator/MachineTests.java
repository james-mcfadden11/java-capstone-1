package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.crypto.Mac;

public class MachineTests {
    private Item testItem;
    private Machine testMachine;

    @Before
    public void setup() {
        testItem = new Item("pop", 0.75, "B5", "Drink");
        testMachine = new Machine();
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
    testMachine.addToBalance(5);

    // Act
    double actual = testMachine.deductFromBalance(2.05);
    double expected = 2.95;

    // Assert
    Assert.assertEquals(expected, actual, 2);
    }

    // sell item test cases:
    // invalid input or doesn't exist in inventory
    // need more money
    // sold out
    // normal purchase
    @Test
    public void sellItem_updates_balance_accordingly() {

    }


}
