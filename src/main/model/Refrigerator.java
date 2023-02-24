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

    // REQUIRES: myItems should not be empty
    // MODIFIES: this
    // EFFECTS: removes the given item from myItems
    public void removeItem(Item i) {
        if (myItems.contains(i)) {
            myItems.remove(i);
        }
        // TODO 1: make an action if it doesn't exist
    }

    // REQUIRES: must be no duplicate names within myItems
    // EFFECTS: returns the item that matches the given name. If no item exists, return null
    public Item searchItem(String name) {
        for (Item i : myItems) {
            if (i.getItemName().equals(name)) {
                return i;
            }
        }
        // TODO 2:  make an action if it doesn't exist
        return null;
    }

    // EFFECTS: returns all the item's name along with it's expiration date
    public List<String> getAllItems() {
        List<String> items = new LinkedList<>();
        if (myItems.size() == 0) {
            items.add("The fridge is empty...");
        } else {
            for (Item i : myItems) {
                // TODO: display the quantity?
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
