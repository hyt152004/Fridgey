package model;

// This class represents all items that are in liquid form

public class Liquid extends Item {

    protected int quantity;

    // REQUIRES: quantity > 0 and is in ml
    // EFFECTS: constructs a new liquid item
    public Liquid(String itemName, int expirationDay, int expirationMonth,
                  int expirationYear, int quantity) {
        super(itemName, expirationDay, expirationMonth, expirationYear);
        this.quantity = quantity;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }
}
