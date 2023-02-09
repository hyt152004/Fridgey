package model;

import java.util.ArrayList;

public class Refrigerator {

    ArrayList<Item> myItems;

    // EFFECTS: constructs a new ArrayList of Items for your refrigerator
    public Refrigerator() {
        myItems = new ArrayList<Item>();
    }

    public void addItem(Item i) {
        myItems.add(i);
    }

    public void getAllItems() {
        for (Item i : myItems) {
            System.out.println(i.getItemName());
        }
    }














}
