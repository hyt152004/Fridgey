package model;

// This class represents all items that are in liquid form

import org.json.JSONObject;

import java.time.LocalDate;

public class Liquid extends Item {

    protected int quantity;
    protected LocalDate today = LocalDate.now();

    // REQUIRES: quantity > 0 and is in ml
    // EFFECTS: constructs a new liquid item
    public Liquid(String itemName, int expirationDay, int expirationMonth,
                  int expirationYear, int quantity) {
        super(itemName, expirationDay, expirationMonth, expirationYear, false);
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
    public String getQuantity() {
        return (quantity + "mL");
    }
}
