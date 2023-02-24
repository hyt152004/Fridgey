package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Refrigerator {

    protected ArrayList<Item> myItems;

    // EFFECTS: constructs a new ArrayList of Items for your refrigerator
    public Refrigerator() {
        myItems = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds the given item to myItems
    public void addItem(Item i) {
        myItems.add(i);
    }

    // REQUIRES: myItems.size() > 0, no duplicates
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

    // EFFECTS: returns all the item's name along with it's expiration date
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

    // EFFECTS: returns the number of items in the fridge
    public int getSize() {
        return myItems.size();
    }


}
