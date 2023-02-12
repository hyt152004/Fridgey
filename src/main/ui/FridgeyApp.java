package ui;

import model.Item;
import model.Liquid;
import model.Refrigerator;
import model.Solid;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

// This class is utilized to launch the application

public class FridgeyApp {

    private Scanner input;
    private Refrigerator myFridgey;

    // MODIFIES: this
    // EFFECTS: processes user input
    public void runFridgey() {
        boolean keepGoing = true;
        String command = null;

        Date myDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/Y");
        System.out.println("\n" + "Today's Date: " + dateFormat.format(myDate));

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("a")) {
            doAddItem();
        } else if (command.equals("b")) {
            doGetAllItems();
        } else if (command.equals("c")) {
            doRemoveItem();
        } else if (command.equals("d")) {
            doSearchAnItem();
        } else {
            System.out.println("Selection not valid...");
        }

    }


    // MODIFIES: this
    // EFFECTS: initializes accounts
    private void init() {
        myFridgey = new Refrigerator();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> add an item");
        System.out.println("\tb -> view all items");
        System.out.println("\tc -> remove an item");
        System.out.println("\td -> search for an item");
        System.out.println();
    }

    // EFFECTS: performs the addition of a new item
    private void doAddItem() {
        boolean liquidState;
        Item i;

        System.out.println("Is the item in liquid state? (true/false):");
        liquidState = input.nextBoolean();

        if (liquidState) {
            i = new Liquid(getItemName(), getItemExpirationDay(),
                    getItemExpirationMonth(), getItemExpirationYear(), getItemQuantity());
        } else {
            i = new Solid(getItemName(), getItemExpirationDay(),
                    getItemExpirationMonth(), getItemExpirationYear(), getItemQuantity());
        }

        myFridgey.addItem(i);

    }

    // EFFECTS: preforms the task of removing an item
    private void doRemoveItem() {
        String commandName;
        System.out.println("Name of the item wanting to remove: ");
        commandName = input.next();

        Item i = myFridgey.searchItem(commandName);
        myFridgey.removeItem(i);
        System.out.println(commandName + " has been removed");
    }

    // EFFECTS: preforms the task of presenting all the items
    private void doGetAllItems() {
        myFridgey.getAllItems();
    }

    //EFFECTS: preforms the searching of an item
    private void doSearchAnItem() {
        String commandName;
        System.out.println("Searching item name: ");
        commandName = input.next();
        Item i = myFridgey.searchItem(commandName);
        System.out.println(i.getItemNameWithExpirationDate() + "\nQuantity: " + i.getQuantity());
    }


    // EFFECTS: returns the Name received from the user
    private String getItemName() {
        String commandName;
        System.out.println("Item Name:");
        commandName = input.next();
        commandName = commandName.toLowerCase();
        return commandName;
    }

    // EFFECTS: returns the ExpirationDay received from the user
    private int getItemExpirationDay() {
        int commandDay;
        System.out.println("Item Expiration Day (DD):");
        commandDay = input.nextInt();
        return commandDay;
    }

    // EFFECTS: returns the ExpirationMonth received from the user
    private int getItemExpirationMonth() {
        int commandMonth;
        System.out.println("Item Expiration Month (MM):");
        commandMonth = input.nextInt();
        return commandMonth;
    }

    // EFFECTS: returns the ExpirationYear received from the user
    private int getItemExpirationYear() {
        int commandYear;
        System.out.println("Item Expiration Year (YYYY):");
        commandYear = input.nextInt();
        return commandYear;

    }

    // EFFECTS: returns the Quantity received from the user
    private int getItemQuantity() {
        int commandQuantity;
        System.out.println("Item Quantity:");
        commandQuantity = input.nextInt();
        return commandQuantity;
    }


}
