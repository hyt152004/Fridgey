package ui;

import model.Item;
import model.Liquid;
import model.Refrigerator;
import model.Solid;
import exceptions.BooleanCheckException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;

// GUI class for the Fridgey App
public class FridgeyAppGUI extends FridgeyApp implements ActionListener {

    private static int addItemTextFieldHorizontalPosition = 200;
    private LocalDate today = LocalDate.now();

    private static JPanel panel;
    private static JFrame frame;

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

    public static void main(String[] args) {
        FridgeyAppGUI startUp = new FridgeyAppGUI();
        startUp.homeScreen();
    }

    public void homeScreen() {
        frame = new JFrame();
        panel = new JPanel();

        frame.add(panel);

        // to make the window pop out
        frame.setSize(1000, 800); // sets the dimensions of the frame
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit out of application
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


        itemsDisplay = new DefaultListModel();
        items = new Refrigerator();
        try {
            BufferedImage myPicture = ImageIO.read(new File("images/fridgey.png"));
            JLabel picLabel = new JLabel(new ImageIcon(myPicture));
            picLabel.setBounds(0, -10, 1000, 800);
            panel.add(picLabel);
        } catch (IOException e) {
            System.out.println("IOException caught");
        }


        displayItems = new JList(itemsDisplay);
        displayItems.setBounds(400, 480, 300, 200);
        displayItems.setSelectedIndex(6);
        panel.add(displayItems);
        frame.setVisible(true);
    }

    // EFFECTS: creates the label, text box, and button for adding items
    public void doAddItemName() {

        // this is for adding an item
        JLabel addItemLabel = new JLabel("Item Name To Add:");
        addItemLabel.setBounds(20, 30, 140, 30);
        panel.add(addItemLabel);


        addItemNameText = new JTextField(20);
        addItemNameText.setBounds(addItemTextFieldHorizontalPosition, 30, 100, 30);
        panel.add(addItemNameText);

        addButton = new JButton("Add Item");
        addButton.setBounds(addItemTextFieldHorizontalPosition - 83, 330, 100, 30);
        addButton.addActionListener(new FridgeyAppGUI());
        panel.add(addButton);

        errorLabel = new JLabel("");
        errorLabel.setBounds(addItemTextFieldHorizontalPosition - 123, 300, 260, 30);
        panel.add(errorLabel);

    }

    // EFFECTS: Sets up the expiration label and its three text boxes
    public void doAddItemExpirationDate() {
        // this is for adding an item

        JLabel addItemExpirationLabel = new JLabel("Item Expiration Date:");
        addItemExpirationLabel.setBounds(20, 90, 180, 30);
        panel.add(addItemExpirationLabel);

        addExpirationDayText = new JTextField(20);
        addExpirationDayText.setBounds(addItemTextFieldHorizontalPosition, 90, 100, 30);
        panel.add(addExpirationDayText);

        JLabel addExpirationDayLabel = new JLabel("Day");
        addExpirationDayLabel.setBounds(addItemTextFieldHorizontalPosition + 110, 90, 180, 30);
        panel.add(addExpirationDayLabel);

        addExpirationMonthText = new JTextField(20);
        addExpirationMonthText.setBounds(addItemTextFieldHorizontalPosition, 120, 100, 30);
        panel.add(addExpirationMonthText);

        JLabel addExpirationMonthLabel = new JLabel("Month");
        addExpirationMonthLabel.setBounds(addItemTextFieldHorizontalPosition + 110, 120, 180, 30);
        panel.add(addExpirationMonthLabel);

        addExpirationYearText = new JTextField(20);
        addExpirationYearText.setBounds(addItemTextFieldHorizontalPosition, 150, 100, 30);
        panel.add(addExpirationYearText);

        JLabel addExpirationYearLabel = new JLabel("Year");
        addExpirationYearLabel.setBounds(addItemTextFieldHorizontalPosition + 110, 150, 180, 30);
        panel.add(addExpirationYearLabel);


    }

    // EFFECTS: Sets up the expiration label and its three text boxes
    public void doAddItemQuantity() {
        // this is for adding an item

        JLabel addItemQuantityLabel;
        JLabel addItemQuantityMeasurementLabel;

        addItemQuantityLabel = new JLabel("Item Quantity:");
        addItemQuantityLabel.setBounds(20, 210, 180, 30);
        panel.add(addItemQuantityLabel);

        addItemQuantityMeasurementLabel = new JLabel("(mL for Liquids)");
        addItemQuantityMeasurementLabel.setBounds(addItemTextFieldHorizontalPosition + 110, 210, 180, 30);
        panel.add(addItemQuantityMeasurementLabel);

        addItemQuantityText = new JTextField(20);
        addItemQuantityText.setBounds(addItemTextFieldHorizontalPosition, 210, 100, 30);
        panel.add(addItemQuantityText);
    }

    // EFFECTS: Sets up the expiration label and its three text boxes
    public void doAddItemState() {
        // this is for adding an item

        JLabel addItemStateLabel;

        addItemStateLabel = new JLabel("Solid (s)/ Liquid (l):");
        addItemStateLabel.setBounds(20, 270, 180, 30);
        panel.add(addItemStateLabel);

        addItemStateText = new JTextField(20);
        addItemStateText.setBounds(addItemTextFieldHorizontalPosition, 270, 100, 30);
        panel.add(addItemStateText);
    }


    // EFFECTS: creates the label, text box, and button for removing items
    public void doRemoveItem() {
        // This is for removing an item
        JLabel removeItemLabel = new JLabel("Item Name To Remove:");
        removeItemLabel.setBounds(450, 20, 180, 30);
        panel.add(removeItemLabel);

        removeText = new JTextField(20);
        removeText.setBounds(600, 20, 100, 30);
        panel.add(removeText);

        removeButton = new JButton("Remove Item");
        removeButton.setBounds(515, 60, 120, 30);
        removeButton.addActionListener(new FridgeyAppGUI());
        panel.add(removeButton);

    }

    // EFFECTS: creates the label, text box, and button for searching an item
    public void doSearchItem() {
        // This is for searching an item
        JLabel searchItemLabel = new JLabel("Item Name To Search:");
        searchItemLabel.setBounds(450, 120, 180, 30);
        panel.add(searchItemLabel);

        searchText = new JTextField(20);
        searchText.setBounds(600, 120, 100, 30);
        panel.add(searchText);

        searchButton = new JButton("Search Item");
        searchButton.setBounds(515, 160, 120, 30);
        searchButton.addActionListener(new FridgeyAppGUI());
        panel.add(searchButton);

        searchItemInfoLabel = new JLabel("");
        searchItemInfoLabel.setBounds(450, 175, 500, 130);
        panel.add(searchItemInfoLabel);

    }




    // EFFECTS: performs the action of addButton and removeButton
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == addButton) {
            try {
                actionForAddButton();
            } catch (BooleanCheckException exception) {
                System.exit(1);
            }

        } else if (e.getSource() == removeButton) {
            String name = removeText.getText().toLowerCase();
            removeText.setText("");
            Item tempItem = items.searchItem(name);
            items.removeItem(tempItem);
            itemsDisplay.removeElement(tempItem.getItemNameWithExpirationDate());

        } else if (e.getSource() == searchButton) {
            String name = searchText.getText().toLowerCase();
            searchText.setText("");
            Item i = items.searchItem(name);
            searchItemInfoLabel.setText("<html>" + i.getItemNameWithExpirationDate() + "<br/>Quantity: " + i.getQuantity()
                    + "<br/>Days Left Until Expiration Date: " + i.getDaysLeft(today) + "</html>");
        }
    }

    // EFFECTS: handles the action when the add button is pressed
    public void actionForAddButton() throws BooleanCheckException {
        int expDay;
        int expMonth;
        int expYear;
        int quantity;
        Item currentItem;

        try {
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
                throw new BooleanCheckException();
            }

            items.addItem(currentItem);
            itemsDisplay.addElement(currentItem.getItemNameWithExpirationDate());

            addItemNameText.setText("");
            addItemStateText.setText("");
            addItemQuantityText.setText("");
            addExpirationDayText.setText("");
            addExpirationMonthText.setText("");
            addExpirationYearText.setText("");
            addItemQuantityText.setText("");
            errorLabel.setText("");

        } catch (NumberFormatException exception) {
            errorLabel.setText("Invalid Input, Please Try Again");
        } catch (BooleanCheckException exception) {
            errorLabel.setText("Invalid State, Please Try Again");
        } catch (DateTimeException exception) {
            errorLabel.setText("Invalid Date, Please Try Again");
        }
    }

}
