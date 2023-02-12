package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RefrigeratorTest {

    Refrigerator fridgey;
    Item i1;
    Item i2;

    @BeforeEach
    public void setUp() {
        fridgey = new Refrigerator();
        i1 = new Liquid("Milk", 15, 9, 2023, 500);
        i2 = new Solid("Apple", 4, 10, 2002, 2);

    }

    @Test
    public void addItemTest() {
        assertTrue(checkEmpty(fridgey));
        fridgey.addItem(i1);
        assertEquals(1, fridgey.getSize());
        fridgey.addItem(i2);
        assertEquals(2, fridgey.getSize());

    }

    @Test
    public void removeItemTest() {
        fridgey.addItem(i1);
        fridgey.removeItem(i2);
        assertEquals(1, fridgey.getSize());
        fridgey.addItem(i2);
        fridgey.removeItem(i2);
        assertEquals(1, fridgey.getSize());
    }

    @Test
    public void searchItemTest() {
        fridgey.addItem(i1);
        assertEquals(null, fridgey.searchItem("Apple"));
        assertEquals(i1, fridgey.searchItem("Milk"));
    }

    public boolean checkEmpty(Refrigerator f) {
        return (f.getSize() == 0);
    }
}
