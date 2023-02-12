package model;

import java.util.ArrayList;

public class Refrigerator {

    protected ArrayList<Item> myItems;

    // EFFECTS: constructs a new ArrayList of Items for your refrigerator
    public Refrigerator() {
        myItems = new ArrayList<Item>();
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
    }

    // REQUIRES: must be no duplicate names within myItems
    // EFFECTS: returns the item that matches the given name. If no item exists, return null
    public Item searchItem(String name) {
        for (Item i : myItems) {
            if (i.getItemName().equals(name)) {
                return i;
            }
        }
        return null;
    }

    // EFFECTS: prints out all the item's name
    public void getAllItems() {
        if (myItems.size() == 0) {
            System.out.println("The fridge is empty...");
        } else {
            for (Item i : myItems) {
                System.out.println(i.getItemNameWithExpirationDate());
            }
        }
    }

    // EFFECTS: returns the number of items in the fridge
    public int getSize() {
        return myItems.size();
    }


}
