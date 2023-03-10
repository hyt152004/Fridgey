package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// This class represents the fridge where all the items will be stored

public class Refrigerator implements Writable {

    protected ArrayList<Item> myItems;


    // EFFECTS: constructs a new empty ArrayList of Items for the refrigerator
    public Refrigerator() {
        myItems = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds the given item to myItems
    public void addItem(Item i) {
        myItems.add(i);
    }

    // REQUIRES: myItems.size() > 0, no item duplicates
    // MODIFIES: this
    // EFFECTS: if the given item can be found within myItems, the item is removed and
    //            returns true. Otherwise, return false
    public boolean removeItem(Item i) {
        if (myItems.contains(i)) {
            myItems.remove(i);
            return true;
        }
        return false;
    }

    // REQUIRES: must be no duplicate names within myItems
    // EFFECTS: returns the item that matches the given name. If no item exists, return null
    public Item searchItem(String name) {
        String newName = name.toLowerCase();
        for (Item i : myItems) {
            if (i.getItemName().equals(newName)) {
                return i;
            }
        }
        return null;
    }

    // EFFECTS: returns all the item's name along with its expiration date
    public List<String> getAllItems() {
        List<String> items = new LinkedList<>();
        if (myItems.size() == 0) {
            items.add("The fridge is empty...");
        } else {
            for (Item i : myItems) {
                items.add(i.getItemNameWithExpirationDate());
            }
        }
        return items;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Items", thingiesToJson());
        return json;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray thingiesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Item t : myItems) {
            jsonArray.put(t.toJson());
        }

        return jsonArray;
    }


    // EFFECTS: returns the number of items in the fridge
    public int getSize() {
        return myItems.size();
    }


}
