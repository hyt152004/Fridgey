package model;

// This class is an abstract class for all the items that will be stored within the refrigerator

public abstract class Item {

    protected String name;
    protected int expirationDay;
    protected int expirationMonth;
    protected int expirationYear;

    // REQUIRES: expirationDay must be [1, 30] and expirationMonth must be [1, 12]
    // EFFECTS: constructs a new item with a name, due date,
    //          and whether it will be stored in the freezer (true means it's in the freezer)
    public Item(String itemName, int expirationDay, int expirationMonth, int expirationYear) {
        this.name = itemName;
        this.expirationDay = expirationDay;
        this.expirationMonth = expirationMonth;
        this.expirationYear = expirationYear;

    }

    // EFFECTS: returns the name of the item
    public String getItemName() {
        return this.name;
    }

    // EFFECTS: returns the name of the item and the expiration date
    public String getItemNameWithExpirationDate() {
        return this.name + " " + "(" + this.getExpirationDate() + ")";
    }

    // Effects: returns the date of expiration
    public String getExpirationDate() {
        return ("Expiration Date: " + expirationDay + "/" + expirationMonth + "/" + expirationYear);
    }


    // returns the quantity
    public abstract int getQuantity();



}
