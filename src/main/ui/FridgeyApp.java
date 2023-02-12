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

    Date myDate = new Date();
    SimpleDateFormat dateFormat = new SimpleDateFormat("M/dd/Y");

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
        System.out.println();
    }

    private void doAddItem() {
        String commandName;
        int commandDay;
        int commandMonth;
        int commandYear;
        int commandQuantity;
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

    // EFFECTS: returns the name received from the user
    private String getItemName() {
        String commandName;
        System.out.println("Item Name:");
        commandName = input.next();
        commandName = commandName.toLowerCase();
        return commandName;
    }

    private int getItemExpirationDay() {
        int commandDay;
        System.out.println("Item Expiration Day (DD):");
        commandDay = input.nextInt();
        return commandDay;
    }

    private int getItemExpirationMonth() {
        int commandMonth;
        System.out.println("Item Expiration Month (MM):");
        commandMonth = input.nextInt();
        return commandMonth;
    }

    private int getItemExpirationYear() {
        int commandYear;
        System.out.println("Item Expiration Year (YYYY):");
        commandYear = input.nextInt();
        return commandYear;

    }

    private int getItemQuantity() {
        int commandQuantity;
        System.out.println("Item Quantity:");
        commandQuantity = input.nextInt();
        return commandQuantity;
    }

    private void doRemoveItem() {
        String commandName;
        System.out.println("Name of the item wanting to remove: ");
        commandName = input.next();

        Item i = myFridgey.searchItem(commandName);
        myFridgey.removeItem(i);
        System.out.println(commandName + " has been removed");
    }

    private void doGetAllItems() {
        myFridgey.getAllItems();
    }
}
