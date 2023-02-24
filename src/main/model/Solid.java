package model;

// This class represents all items that are in solid form

public class Solid extends Item {

    protected int quantity;

    // REQUIRES: quantity > 0
    // EFFECTS: constructs a new solid item
    public Solid(String itemName, int expirationDay, int expirationMonth,
                 int expirationYear, int quantity) {
        super(itemName, expirationDay, expirationMonth, expirationYear);
        this.quantity = quantity;
    }

    @Override
    public String getQuantity() {
        return String.valueOf(quantity);
    }

}
