package ui;

import model.Item;
import model.Liquid;
import model.Refrigerator;
import model.Solid;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

// This class is utilized to launch the application

public class FridgeyApp {

    private static final String JSON_STORE = "./data/fridgey.json";
    private Scanner input;
    private Refrigerator myFridgey;
    private LocalDate today = LocalDate.now();
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // MODIFIES: this
    // EFFECTS: processes user input
    public void runFridgey() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        boolean keepGoing = true;
        String command = null;


        System.out.println("\n" + "Today's Date: " + today.getMonth()
                + " " + today.getDayOfMonth() + ", " + today.getYear());

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
        } else if (command.equals("s")) {
            saveWorkRoom();
        } else if (command.equals("r")) {
            loadWorkRoom();
        } else {
            System.out.println("Selection not valid...");
        }

    }

    // MODIFIES: this
    // EFFECTS: initializes refrigerator
    private void init() {
        myFridgey = new Refrigerator();
        input = new Scanner(System.in);
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> add an item");
        System.out.println("\tb -> view all items");
        System.out.println("\tc -> remove an item");
        System.out.println("\td -> search for an item");
        System.out.println("\ts -> to save all the items");
        System.out.println("\tr -> retrieve all the items");
        System.out.println("\tq -> quit");
        System.out.println();
    }

    // EFFECTS: performs the addition of a new item
    private void doAddItem() {
        String liquidOrSolid;
        Boolean state;

        Item i;

        System.out.println("Item's state? (l (liquid)/ s (solid)):");
        liquidOrSolid = input.next();
        liquidOrSolid = liquidOrSolid.toLowerCase();

        // true means liquid, false means solid
        if (liquidOrSolid.equals("s")) {
            state = true;
        } else if (liquidOrSolid.equals("l")) {
            state = false;
        } else {
            return;
        }

        if (state) {
            i = new Solid(getStringInfo("Item Name: "), getIntInfo("Item Expiration Day (DD):"),
                    getIntInfo("Item Expiration Month (MM):"),
                    getIntInfo("Item Expiration Year (YYYY):"), getIntInfo("Item Quantity:"));
        } else {
            i = new Liquid(getStringInfo("Item Name: "), getIntInfo("Item Expiration Day (DD):"),
                    getIntInfo("Item Expiration Month (MM):"),
                    getIntInfo("Item Expiration Year (YYYY):"), getIntInfo("Item Quantity (in mL):"));
        }
        myFridgey.addItem(i);
    }

    // EFFECTS: preforms the task of removing an item
    private void doRemoveItem() {
        String commandName;
        System.out.println("Name of the item wanting to remove: ");
        commandName = input.next();
        commandName = commandName.toLowerCase();

        Item i = myFridgey.searchItem(commandName);
        if (myFridgey.removeItem(i)) {
            myFridgey.removeItem(i);
            System.out.println(commandName + " has been removed");
        } else {
            System.out.println(commandName + " has been found");
        }

    }

    // EFFECTS: preforms the task of presenting all the items
    private void doGetAllItems() {
        for (String s : myFridgey.getAllItemsNameWithExpirationDate()) {
            System.out.println(s);
        }
    }

    //EFFECTS: preforms the searching of an item
    private void doSearchAnItem() {
        String commandName;
        System.out.println("Searching item name: ");
        commandName = input.next();
        commandName = commandName.toLowerCase();
        Item i = myFridgey.searchItem(commandName);
        System.out.println(i.getItemNameWithExpirationDate() + "\nQuantity: " + i.getQuantity()
                + "\nDays Left Until Expiration Date: " + i.getDaysLeft(today));
    }


    // EFFECTS: saves the workroom to file
    private void saveWorkRoom() {
        try {
            jsonWriter.open();
            jsonWriter.write(myFridgey);
            jsonWriter.close();
            System.out.println("Saved to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadWorkRoom() {
        try {
            myFridgey = jsonReader.read();
            System.out.println("Loaded from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }



    // EFFECTS: returns the info asked from the user
    private int getIntInfo(String command) {
        int commandName;
        System.out.println(command);
        commandName = input.nextInt();
        return commandName;
    }

    // EFFECTS: returns the info asked from the user
    private String getStringInfo(String command) {
        String commandName;
        System.out.println(command);
        commandName = input.next();
        commandName = commandName.toLowerCase();
        return commandName;
    }
}
