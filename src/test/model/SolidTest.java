package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolidTest {

    Solid solidItemTest;

    @BeforeEach
    public void setUp() {
        solidItemTest = new Solid("egg", 15, 9, 2023, 20);
    }

    @Test
    public void LiquidTest() {
        assertEquals("egg", solidItemTest.getItemName());
        assertEquals("egg (Expiration Date: SEPTEMBER 15, 2023)", solidItemTest.getItemNameWithExpirationDate());
        assertEquals("Expiration Date: SEPTEMBER 15, 2023", solidItemTest.getExpirationDate());
        assertEquals("20", solidItemTest.getQuantity());
    }

}
