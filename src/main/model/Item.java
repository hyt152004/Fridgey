package model;

// This class is an abstract class for all the items that will be stored within the refrigerator

import java.time.LocalDate;

public abstract class Item {

    protected String name;
    protected LocalDate expirationDate;

    // REQUIRES: expirationDay must be [1, (30)] and expirationMonth must be [1, 12]
    // EFFECTS: constructs a new item with a name, due date,
    //          and whether it will be stored in the freezer (true means it's in the freezer)
    public Item(String itemName, int expirationDay, int expirationMonth, int expirationYear) {
        this.name = itemName;
        expirationDate = LocalDate.of(expirationYear, expirationMonth, expirationDay);
    }

    // EFFECTS: returns the name of the item
    public String getItemName() {
        return this.name;
    }

    // EFFECTS: returns the name of the item and the expiration date
    public String getItemNameWithExpirationDate() {
        return this.name + " (" + this.getExpirationDate() + ")";
    }

    // Effects: returns the date of expiration
    public String getExpirationDate() {
        return ("Expiration Date: " + expirationDate.getMonth() + " "
                + expirationDate.getDayOfMonth() + ", " + expirationDate.getYear());
    }

    // EFFECTS: returns the number of days left until expiration date
    // TODO: implementation (use the example from testing)
    public int getDaysLeft() {
        return 0; //stub
    }

    // returns the quantity
    public abstract String getQuantity();


}
