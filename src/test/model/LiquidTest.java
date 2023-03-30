package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LiquidTest {

    Item liquidItemTest;


    @BeforeEach
    public void setUp() {
        liquidItemTest = new Liquid("Banana Milk", 15, 9, 2023, 500);
    }

    @Test
    public void LiquidTest() {
        assertEquals("Banana Milk", liquidItemTest.getItemName());
        assertEquals("Banana Milk (Expiration Date: SEPTEMBER 15, 2023)", liquidItemTest.getItemNameWithExpirationDate());
        assertEquals("Expiration Date: SEPTEMBER 15, 2023", liquidItemTest.getExpirationDate());
        assertEquals("500mL", liquidItemTest.getQuantity());
    }

    @Test
    public void getDaysLeftExpirationDatePassedTest() {
        LocalDate date1 = LocalDate.of(2023, 10, 22);
        assertEquals("The Item has Expired", liquidItemTest.getDaysLeft(date1));
    }

    @Test
    public void getDaysLeftSameDateTest() {
        LocalDate date1 = LocalDate.of(2023, 9, 15);
        assertEquals("The Item has Expired", liquidItemTest.getDaysLeft(date1));
    }

    @Test
    public void getDaysLeftExpirationDateNotPassedTest() {
        LocalDate date1 = LocalDate.of(2023, 9, 14);
        assertEquals("1 days", liquidItemTest.getDaysLeft(date1));
    }

}
