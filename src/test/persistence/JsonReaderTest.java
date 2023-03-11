package persistence;

import model.Item;
import model.Refrigerator;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Refrigerator fridgey = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyRefrigerator() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyRefrigerator.json");
        try {
            Refrigerator fridgey = reader.read();
            assertEquals(0, fridgey.getSize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralRefrigerator() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralRefrigerator.json");
        try {
            Refrigerator fridgey = reader.read();
            List<Item> thingies = fridgey.getAllItems();
            assertEquals(2, thingies.size());
            checkThingy("egg", 20, 3, 2025, 20, "741 days", true, thingies.get(0));
            checkThingy("milk", 10, 6, 2025, 500, "823 days", false, thingies.get(1));

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}