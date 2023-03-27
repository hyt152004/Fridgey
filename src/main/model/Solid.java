package model;

// This class represents all items that are in solid form

import org.json.JSONObject;

import java.time.LocalDate;

public class Solid extends Item {

    protected int quantity;
    protected LocalDate today = java.time.LocalDate.now();

    // REQUIRES: quantity > 0
    // EFFECTS: constructs a new solid item
    public Solid(String itemName, int expirationDay, int expirationMonth,
                 int expirationYear, int quantity) {
        super(itemName, expirationDay, expirationMonth, expirationYear, true);
        this.quantity = quantity;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("expirationDay", expirationDate.getDayOfMonth());
        json.put("expirationMonth", expirationDate.getMonthValue());
        json.put("expirationYear", expirationDate.getYear());
        json.put("quantity", this.quantity);
        json.put("daysLeft", getDaysLeft(today));
        json.put("state", getState());
        return json;
    }

    @Override
    public String toString() {
        return name + ", " + getExpirationDate();
    }


    @Override
    public String getQuantity() {
        return String.valueOf(quantity);
    }


}
