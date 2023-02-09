package model;

public class Item {

    String name;
    int daysLeft;
    boolean freezer;

    // EFFECTS: constructs a new item with a name, due date,
    //          and whether it will be stored in the freezer (true means it's in the freezer)
    public Item(String itemName, int daysLeft, boolean freezer) {
        this.name = itemName;
        this.daysLeft = daysLeft;
        this.freezer = freezer;

    }

    public String getItemName() {
        return name;
    }
}
