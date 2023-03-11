package persistence;

import model.Item;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkThingy(String name, Integer expD,
                               Integer expM, Integer expY, Integer quantity,
                               String daysLeft, boolean state, Item item) {
        LocalDate expirationDate = LocalDate.of(expY, expM, expD);
        LocalDate today = LocalDate.of(2023, 3, 10);

        assertEquals(name, item.getItemName());
        assertEquals(("Expiration Date:" + " " + expirationDate.getMonth() + " "
                + expirationDate.getDayOfMonth() + ", " + expirationDate.getYear()), item.getExpirationDate());
        assertEquals(daysLeft, item.getDaysLeft(today));
        assertEquals(state, item.getState());
        if (item.getState()) {
            assertEquals(quantity.toString(), item.getQuantity());
        } else {
            assertEquals(quantity + "mL", item.getQuantity());
        }
    }
}
