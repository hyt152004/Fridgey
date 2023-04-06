package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RefrigeratorTest {

    Refrigerator fridgey;
    Item i1;
    Item i2;

    @BeforeEach
    public void setUp() {
        fridgey = new Refrigerator();
        i1 = new Liquid("milk", 15, 9, 2023, 500);
        i2 = new Solid("apple", 4, 10, 2002, 2);

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
    public void removeItemNotContainTest() {
        fridgey.addItem(i1);
        assertFalse(fridgey.removeItem(i2));
        assertEquals(1, fridgey.getSize());
    }

    @Test
    public void removeItemContainTest() {
        fridgey.addItem(i1);
        fridgey.addItem(i2);
        assertTrue(fridgey.removeItem(i2));
        assertEquals(1, fridgey.getSize());
    }

    @Test
    public void searchItemTest() {
        fridgey.addItem(i1);
        assertEquals(null, fridgey.searchItem("Apple"));
        assertEquals(i1, fridgey.searchItem("Milk"));
        assertEquals(i1, fridgey.searchItem("milk"));
    }

    @Test
    public void searchItemNoLogEventTest() {
        fridgey.addItem(i1);
        assertEquals(null, fridgey.searchItemNoLogEvent("Apple"));
        assertEquals(i1, fridgey.searchItemNoLogEvent("Milk"));
        assertEquals(i1, fridgey.searchItemNoLogEvent("milk"));
    }


    @Test
    public void getAllItemsTestEmpty() {
        List<String> test = fridgey.getAllItemsNameWithExpirationDate();
        assertEquals(1, test.size());
        assertEquals("The fridge is empty...", test.get(0));
    }

    @Test
    public void getAllItemsTestNotEmpty() {
        fridgey.addItem(i1);
        fridgey.addItem(i2);
        List<String> test = fridgey.getAllItemsNameWithExpirationDate();
        assertEquals(2, test.size());
        assertEquals("milk (Expiration Date: SEPTEMBER 15, 2023)", test.get(0));
        assertEquals("apple (Expiration Date: OCTOBER 4, 2002)", test.get(1));
    }

    @Test
    public void getSizeEmpty() {
        assertTrue(checkEmpty(fridgey));
        assertEquals(0, fridgey.getSize());
    }

    @Test
    public void getSizeNotEmpty() {
        fridgey.addItem(i1);
        fridgey.addItem(i2);
        assertEquals(2, fridgey.getSize());
    }

    public boolean checkEmpty(Refrigerator f) {
        return (f.getSize() == 0);
    }


}
