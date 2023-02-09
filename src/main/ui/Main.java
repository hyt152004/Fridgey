package ui;

import model.Item;
import model.Refrigerator;

public class Main {
    public static void main(String[] args) {

        Item i1 = new Item("Milk", 100, false);
        Item i2 = new Item("Apple Juice", 50, false);



        Refrigerator fridge = new Refrigerator();

        fridge.addItem(i1);
        fridge.addItem(i2);

        fridge.getAllItems();

    }
}
