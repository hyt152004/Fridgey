package persistence;

import model.Item;
import model.Liquid;
import model.Refrigerator;
import model.Solid;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Refrigerator read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseWorkRoom(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private Refrigerator parseWorkRoom(JSONObject jsonObject) {
        Refrigerator fridgey = new Refrigerator();
        addItems(fridgey, jsonObject);
        return fridgey;
    }

    // MODIFIES: fridgey
    // EFFECTS: parses items from JSON object and adds them to fridgey
    private void addItems(Refrigerator fridgey, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Items");
        for (Object json : jsonArray) {
            JSONObject nextThingy = (JSONObject) json;
            addItem(fridgey, nextThingy);
        }
    }

    // MODIFIES: fridgey
    // EFFECTS: parses item from JSON object and adds it to fridgey
    private void addItem(Refrigerator fridgey, JSONObject jsonObject) {
        Item item;
        String name = jsonObject.getString("name");
        Integer expirationDay = jsonObject.getInt("expirationDay");
        Integer expirationMonth = jsonObject.getInt("expirationMonth");
        Integer expirationYear = jsonObject.getInt("expirationYear");
        Integer quantity = jsonObject.getInt("quantity");
        Boolean state = jsonObject.getBoolean("state");
        if (state) {
            item = new Solid(name, expirationDay,expirationMonth,expirationYear, quantity);
        } else {
            item = new Liquid(name, expirationDay,expirationMonth,expirationYear, quantity);
        }
        fridgey.addItem(item);
    }
}
