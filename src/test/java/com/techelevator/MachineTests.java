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









}
