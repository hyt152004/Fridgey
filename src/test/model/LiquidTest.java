package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LiquidTest {

    Item liquidItemTest;


    @BeforeEach
    private void setUp() {
        liquidItemTest = new Liquid("Banana Milk", 9, 15, 2023, 500);
    }

}
