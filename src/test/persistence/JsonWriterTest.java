package persistence;

import model.Item;
import model.Liquid;
import model.Refrigerator;
import model.Solid;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            Refrigerator fridgey = new Refrigerator();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyRefrigerator() {
        try {
            Refrigerator fridgey = new Refrigerator();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyRefrigerator.json");
            writer.open();
            writer.write(fridgey);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyRefrigerator.json");
            fridgey = reader.read();
            assertEquals(0, fridgey.getSize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralRefrigerator() {
        try {
            Refrigerator fridgey = new Refrigerator();
            fridgey.addItem(new Solid("egg", 20, 3, 2025, 20));
            fridgey.addItem(new Liquid("milk", 10, 6, 2025, 500));
            JsonWriter writer = new JsonWriter("./data/testWriterRefrigerator.json");
            writer.open();
            writer.write(fridgey);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterRefrigerator.json");
            fridgey = reader.read();
            List<Item> thingies = fridgey.getAllItems();
            assertEquals(2, thingies.size());
            checkThingy("egg", 20, 3, 2025, 20, "741 days", true, thingies.get(0));
            checkThingy("milk", 10, 6, 2025, 500, "823 days", false, thingies.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}