package model;

// This class is an abstract class for all the different states of items that will be stored within the refrigerator

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Item {

    protected String name;
    protected LocalDate expirationDate;

    // REQUIRES: expirationDay must be [1, [28-31]] depending on the month,
    //              expirationMonth must be [1, 12], and expirationYear > 0
    // EFFECTS: constructs a new item with a name and due date
    public Item(String itemName, int expirationDay, int expirationMonth, int expirationYear) {
        this.name = itemName;
        expirationDate = LocalDate.of(expirationYear, expirationMonth, expirationDay);
    }

    // EFFECTS: returns the name of the item
    public String getItemName() {
        return this.name;
    }

    // EFFECTS: returns the combination of name and the expiration date
    public String getItemNameWithExpirationDate() {
        return this.name + " (" + this.getExpirationDate() + ")";
    }

    // Effects: returns the date of expiration in the form (MONTH) (DAY), (YEAR)
    public String getExpirationDate() {
        return ("Expiration Date: " + expirationDate.getMonth() + " "
                + expirationDate.getDayOfMonth() + ", " + expirationDate.getYear());
    }

    // EFFECTS: if the expiration date didn't pass, returns the number of days left until expiration date.
    //            Otherwise, it informs that the expiration date has passed.
    //            Today and expirationDate being on the same day is considered passed
    public String getDaysLeft(LocalDate today) {
        if (expirationDate.isAfter(today)) {
            List<LocalDate> days = today.datesUntil(expirationDate).collect(Collectors.toList());
            Integer numOfDaysLeft = days.size();
            return numOfDaysLeft.toString() + " days";
        } else {
            return "The expiration date has passed";
        }
    }

    // EFFECTS: returns the quantity
    public abstract String getQuantity();


}
