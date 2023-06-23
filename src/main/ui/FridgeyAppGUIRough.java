package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;

// GUI class for the Fridgey App
public class FridgeyAppGUIRough implements ActionListener {

    private static final String JSON_STORE = "./data/fridgeyGUI.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private static final int ADD_ITEM_TEXT_FIELD_HORIZONTAL_POSITION = 200;
    private LocalDate today = LocalDate.now();

    private static JPanel panel;
    private JFrame frame;

    private static JTextField addItemNameText;
    private static JButton addButton;

    private static JTextField addExpirationMonthText;
    private static JTextField addExpirationDayText;
    private static JTextField addExpirationYearText;

    private static JTextField addItemQuantityText;
    private static JTextField addItemStateText;

    private static JTextField removeText;
    private static JButton removeButton;

    private static JTextField searchText;
    private static JButton searchButton;

    private static JList displayItems;
    private static DefaultListModel itemsDisplay;
    private static Refrigerator items;

    private static JLabel errorLabel;
    private static JLabel searchItemInfoLabel;

    private static JButton saveButton;
    private static JLabel savedLabel;
    private static JButton loadButton;


    public static void main(String[] args) {
        FridgeyAppGUIRough startUp = new FridgeyAppGUIRough();
        startUp.homeScreen();

    }


    // MODIFIES: this
    // EFFECTS: preforms the entire operation of building all the physical attributes
    public void homeScreen() {
        frame = new JFrame();
        panel = new JPanel();
        itemsDisplay = new DefaultListModel();
        items = new Refrigerator();

        frame.addWindowListener(new CloseTheWindow());

        frame.add(panel);

        // to make the window pop out
        frame.setSize(480, 650); // sets the dimensions of the frame
        frame.setResizable(false);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Fridgey"); /// sets title to frame

        panel.setLayout(null);

        JLabel todayDateText = new JLabel("Today's Date: " + today.getMonth()
                + " " + today.getDayOfMonth() + ", " + today.getYear());
        todayDateText.setBounds(780, 2, 300, 30);
        panel.add(todayDateText);

        doAddItemName();

        doRemoveItem();

        doAddItemExpirationDate();

        doAddItemQuantity();

        doAddItemState();

        doSearchItem();

        doPersistence();

//        doAddBackground();

        doDisplayItems();


    }

    // MODIFIES: this
    // EFFECTS: constructs a new background image to display the logo
    private void doAddBackground() {
        try {
            BufferedImage myPicture = ImageIO.read(new File("images/fridgey.png"));
            JLabel picLabel = new JLabel(new ImageIcon(myPicture));
            picLabel.setBounds(0, -10, 1000, 800);
            panel.add(picLabel);
        } catch (IOException e) {
            System.out.println("IOException caught");
        }
    }

    // MODIFIES: this
    // EFFECTS: constructs a new JList to display all items
    private void doDisplayItems() {
        displayItems = new JList(itemsDisplay);
        displayItems.setBounds(20, 400, 290, 350);
        displayItems.setSelectedIndex(6);
        panel.add(displayItems);
        frame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: creates the label, text box, and button for adding items
    private void doAddItemName() {

        // this is for adding an item
        JLabel addItemLabel = new JLabel("Item Name To Add:");
        addItemLabel.setBounds(20, 30, 180, 30);
        panel.add(addItemLabel);


        addItemNameText = new JTextField(20);
        addItemNameText.setBounds(ADD_ITEM_TEXT_FIELD_HORIZONTAL_POSITION, 30, 100, 30);
        panel.add(addItemNameText);

        addButton = new JButton("Add Item");
        addButton.setBounds(ADD_ITEM_TEXT_FIELD_HORIZONTAL_POSITION - 83, 330, 100, 30);
        addButton.addActionListener(new FridgeyAppGUIRough());
        panel.add(addButton);

        errorLabel = new JLabel("");
        errorLabel.setBounds(ADD_ITEM_TEXT_FIELD_HORIZONTAL_POSITION - 123, 300, 260, 30);
        panel.add(errorLabel);

    }

    // MODIFIES: this
    // EFFECTS: Sets up the expiration label and its three text boxes
    private void doAddItemExpirationDate() {
        // this is for adding an item

        JLabel addItemExpirationLabel = new JLabel("Item Expiration Date:");
        addItemExpirationLabel.setBounds(20, 90, 180, 30);
        panel.add(addItemExpirationLabel);

        addExpirationDayText = new JTextField(20);
        addExpirationDayText.setBounds(ADD_ITEM_TEXT_FIELD_HORIZONTAL_POSITION, 90, 100, 30);
        panel.add(addExpirationDayText);

        JLabel addExpirationDayLabel = new JLabel("Day");
        addExpirationDayLabel.setBounds(ADD_ITEM_TEXT_FIELD_HORIZONTAL_POSITION + 110, 90, 180, 30);
        panel.add(addExpirationDayLabel);

        addExpirationMonthText = new JTextField(20);
        addExpirationMonthText.setBounds(ADD_ITEM_TEXT_FIELD_HORIZONTAL_POSITION, 120, 100, 30);
        panel.add(addExpirationMonthText);

        JLabel addExpirationMonthLabel = new JLabel("Month");
        addExpirationMonthLabel.setBounds(ADD_ITEM_TEXT_FIELD_HORIZONTAL_POSITION + 110, 120, 180, 30);
        panel.add(addExpirationMonthLabel);

        addExpirationYearText = new JTextField(20);
        addExpirationYearText.setBounds(ADD_ITEM_TEXT_FIELD_HORIZONTAL_POSITION, 150, 100, 30);
        panel.add(addExpirationYearText);

        JLabel addExpirationYearLabel = new JLabel("Year");
        addExpirationYearLabel.setBounds(ADD_ITEM_TEXT_FIELD_HORIZONTAL_POSITION + 110, 150, 180, 30);
        panel.add(addExpirationYearLabel);


    }

    // MODIFIES: this
    // EFFECTS: Sets up the expiration label and its three text boxes
    private void doAddItemQuantity() {
        // this is for adding an item

        JLabel addItemQuantityLabel;
        JLabel addItemQuantityMeasurementLabel;

        addItemQuantityLabel = new JLabel("Item Quantity:");
        addItemQuantityLabel.setBounds(20, 210, 180, 30);
        panel.add(addItemQuantityLabel);

        addItemQuantityMeasurementLabel = new JLabel("(mL for Liquids)");
        addItemQuantityMeasurementLabel.setBounds(ADD_ITEM_TEXT_FIELD_HORIZONTAL_POSITION + 110, 210, 180, 30);
        panel.add(addItemQuantityMeasurementLabel);

        addItemQuantityText = new JTextField(20);
        addItemQuantityText.setBounds(ADD_ITEM_TEXT_FIELD_HORIZONTAL_POSITION, 210, 100, 30);
        panel.add(addItemQuantityText);
    }

    // MODIFIES: this
    // EFFECTS: Sets up the expiration label and its three text boxes
    private void doAddItemState() {
        // this is for adding an item

        JLabel addItemStateLabel;

        addItemStateLabel = new JLabel("Solid (s)/ Liquid (l):");
        addItemStateLabel.setBounds(20, 270, 180, 30);
        panel.add(addItemStateLabel);

        addItemStateText = new JTextField(20);
        addItemStateText.setBounds(ADD_ITEM_TEXT_FIELD_HORIZONTAL_POSITION, 270, 100, 30);
        panel.add(addItemStateText);
    }


    // MODIFIES: this
    // EFFECTS: creates the label, text box, and button for removing items
    private void doRemoveItem() {
        // This is for removing an item
        JLabel removeItemLabel = new JLabel("Item Name To Remove:");
        removeItemLabel.setBounds(450, 20, 180, 30);
        panel.add(removeItemLabel);

        removeText = new JTextField(20);
        removeText.setBounds(600, 20, 100, 30);
        panel.add(removeText);

        removeButton = new JButton("Remove Item");
        removeButton.setBounds(515, 60, 120, 30);
        removeButton.addActionListener(new FridgeyAppGUIRough());
        panel.add(removeButton);

    }

    // MODIFIES: this
    // EFFECTS: creates the label, text box, and button for searching an item
    private void doSearchItem() {
        // This is for searching an item
        JLabel searchItemLabel = new JLabel("Item Name To Search:");
        searchItemLabel.setBounds(450, 120, 180, 30);
        panel.add(searchItemLabel);

        searchText = new JTextField(20);
        searchText.setBounds(600, 120, 100, 30);
        panel.add(searchText);

        searchButton = new JButton("Search Item");
        searchButton.setBounds(515, 160, 120, 30);
        searchButton.addActionListener(new FridgeyAppGUIRough());
        panel.add(searchButton);

        searchItemInfoLabel = new JLabel("");
        searchItemInfoLabel.setBounds(450, 175, 500, 130);
        panel.add(searchItemInfoLabel);

    }


    // MODIFIES: this
    // EFFECTS: creates the label, text box, and button for saving and loading items
    private void doPersistence() {
        saveButton = new JButton("Save Items");
        saveButton.setBounds(745, 720, 120, 30);
        saveButton.addActionListener(new FridgeyAppGUIRough());
        panel.add(saveButton);

        savedLabel = new JLabel("");
        savedLabel.setBounds(785, 743, 260, 30);
        panel.add(savedLabel);

        loadButton = new JButton("Load Items");
        loadButton.setBounds(867, 720, 120, 30);
        loadButton.addActionListener(new FridgeyAppGUIRough());
        panel.add(loadButton);
    }

    // MODIFIES: this
    // EFFECTS: performs the action of addButton and removeButton
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == addButton) {
            actionForAddButton();
            savedLabel.setText("");

        } else if (e.getSource() == removeButton) {
            String name = removeText.getText().toLowerCase();
            removeText.setText("");
            Item tempItem = items.searchItem(name);
            items.removeItem(tempItem);
            itemsDisplay.removeElement(tempItem.getItemNameWithExpirationDate());
            savedLabel.setText("");

        } else if (e.getSource() == searchButton) {
            searchButtonAction();
        } else if (e.getSource() == saveButton) {
            saveButtonAction();
        } else if (e.getSource() == loadButton) {
            loadButtonAction();
        }
    }

    // MODIFIES: this
    // EFFECTS: performs the action when the search button is pressed
    private void searchButtonAction() {
        String name = searchText.getText().toLowerCase();
        searchText.setText("");
        Item i = items.searchItem(name);
        searchItemInfoLabel.setText("<html>" + i.getItemNameWithExpirationDate() + "<br/>Quantity: " + i.getQuantity()
                + "<br/>Days Left Until Expiration Date: " + i.getDaysLeft(today) + "</html>");
    }

    // MODIFIES: this
    // EFFECTS: performs the action when the save button is pressed
    private void saveButtonAction() {
        try {
            jsonWriter = new JsonWriter(JSON_STORE);

            jsonWriter.open();
            jsonWriter.write(items);
            jsonWriter.close();
            savedLabel.setText("Saved!");

        } catch (FileNotFoundException exception) {
            savedLabel.setText("Unable to write to file");
        }
    }

    // MODIFIES: this
    // EFFECTS: performs the action when the load button is pressed
    private void loadButtonAction() {
        try {
            jsonReader = new JsonReader(JSON_STORE);
            items = jsonReader.read();
            for (Item i : items.getAllItems()) {
                itemsDisplay.addElement(i.getItemNameWithExpirationDate());
            }
        } catch (IOException exception) {
            System.exit(1);
        }
    }

    // MODIFIES: this
    // EFFECTS: handles the action when the add button is pressed
    private void actionForAddButton() throws NumberFormatException {
        Item currentItem;

        try {
            currentItem = constructItemWithGivenInfo();
            itemsDisplay.addElement(currentItem.getItemNameWithExpirationDate());

            addButtonActionResetAllTextFields();

        } catch (NumberFormatException exception) {
            errorLabel.setText("Invalid Input, Please Try Again");
        } catch (DateTimeException exception) {
            errorLabel.setText("Invalid Date, Please Try Again");
        }
    }

    // EFFECTS: constructs a new item with the info given from the Text fields
    private Item constructItemWithGivenInfo() {
        int expMonth;
        Item currentItem;
        int expYear;
        int quantity;
        int expDay;
        String name = addItemNameText.getText().toLowerCase();
        String state = addItemStateText.getText().toLowerCase();
        expDay = Integer.parseInt(addExpirationDayText.getText());
        expMonth = Integer.parseInt(addExpirationMonthText.getText());
        expYear = Integer.parseInt(addExpirationYearText.getText());
        quantity = Integer.parseInt(addItemQuantityText.getText());

        if (state.equals("s")) {
            currentItem = new Solid(name, expDay, expMonth, expYear, quantity);
        } else if (state.equals("l")) {
            currentItem = new Liquid(name, expDay, expMonth, expYear, quantity);
        } else {
            throw new NumberFormatException();
        }

        items.addItem(currentItem);
        return currentItem;
    }


    // MODIFIES: this
    // EFFECTS: sets all the text fields used when adding an item to blank
    private void addButtonActionResetAllTextFields() {
        addItemNameText.setText("");
        addItemStateText.setText("");
        addItemQuantityText.setText("");
        addExpirationDayText.setText("");
        addExpirationMonthText.setText("");
        addExpirationYearText.setText("");
        addItemQuantityText.setText("");
        errorLabel.setText("");
    }

    private class CloseTheWindow extends WindowAdapter {
        public void windowClosing(WindowEvent e) {
            for (Event event : EventLog.getInstance()) {
                System.out.println(event.getDescription());
            }

            System.exit(0);
        }
    }

}
