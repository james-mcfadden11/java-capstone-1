package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

import javax.crypto.Mac;

public class MachineTests {
    public static void main(String[] args) {

    }

    Item testItem = new Item("pop", 0.75, "B5", "Drink");

@Test
    public void add_unlisted_amount_of_money() {
    // Arrange
    Machine testMachine = new Machine();
    // Act
    double actual = testMachine.addToBalance(-3);
    double expected = -1.0;

    // Assert
    Assert.assertEquals(expected, actual, 2);

}

@Test
    public void deduct_from_balance() {
    // Arrange
    Machine testMachine = new Machine();
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
    Machine testMachine = new Machine();
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
    Machine testMachine = new Machine();
    testMachine.addToBalance(5);

    // Act
    double actual = testMachine.deductFromBalance(2.05);
    double expected = 2.95;

    // Assert
    Assert.assertEquals(expected, actual, 2);


}







}
