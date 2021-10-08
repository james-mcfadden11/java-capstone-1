package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ItemTests {
    private Item testItem;
    private Item invalidTestItem;
    private Machine testMachine;

    @Before
    public void setup() {
        testItem = new Item("pop", 0.75, "B5", "Drink");
        invalidTestItem = new Item("pop", 0.75, "B5", "invalid snack");
        testMachine = new Machine();
    }

    @Test
    public void set_message_get_message_for_item() {
        // Arrange
        testItem.setMessage();
        // Act
        String expected = testItem.getMessage();
        String actual = "Glug Glug, Yum!";

        // Assert
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void set_message_get_message_for_invalid_item() {
        // Arrange
        invalidTestItem.setMessage();
        // Act
        String expected = invalidTestItem.getMessage();
        String actual = "Item not found!";

        // Assert
        Assert.assertEquals(expected, actual);
    }


}
