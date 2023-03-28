package ui;

import model.Item;
import model.Refrigerator;
import model.Solid;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

// GUI class for the Fridgey App
public class FridgeyAppGUI extends FridgeyApp implements ActionListener {

    private static int addItemTextFieldHorizontalPosition = 200;

    private static JPanel panel;
    private static JFrame frame;

    private static JLabel addItemLabel;
    private static JTextField addText;
    private static JButton addButton;

    private static JLabel addItemExpirationLabel;
    private static JTextField addExpirationMonthText;
    private static JTextField addExpirationDayText;
    private static JTextField addExpirationYearText;

    private static JTextField addItemQuantity;
    private static JTextField addItemState;

    private static JLabel removeItemLabel;
    private static JTextField removeText;
    private static JButton removeButton;

    private static JList displayItems;
    private static DefaultListModel itemsDisplay;
    private static Refrigerator items;

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

        doAddItemName();

        doRemoveItem();

        doAddItemExpirationDate();

        doAddItemQuantity();

        doAddItemState();


        itemsDisplay = new DefaultListModel();
        items = new Refrigerator();
        try {
            BufferedImage myPicture = ImageIO.read(new File("images/fridgey.png"));
            JLabel picLabel = new JLabel(new ImageIcon(myPicture));
            picLabel.setBounds(0, 0, 1000, 800);
            panel.add(picLabel);
        } catch (IOException e) {
            System.out.println("IOException caught");
        }


        displayItems = new JList(itemsDisplay);
        displayItems.setBounds(700, 400, 100, 200);
        displayItems.setSelectedIndex(3);
        panel.add(displayItems);
        frame.setVisible(true);
    }

    // EFFECTS: creates the label, text box, and button for adding items
    public void doAddItemName() {

        // this is for adding an item
        addItemLabel = new JLabel("Item Name To Add:");
        addItemLabel.setBounds(20, 30, 140, 30);
        panel.add(addItemLabel);

        addText = new JTextField(20);
        addText.setBounds(addItemTextFieldHorizontalPosition, 30, 100, 30);
        panel.add(addText);

        addButton = new JButton("Add Item");
        addButton.setBounds(addItemTextFieldHorizontalPosition - 83, 330, 100, 30);
        addButton.addActionListener(new FridgeyAppGUI());
        panel.add(addButton);

    }

    // EFFECTS: Sets up the expiration label and its three text boxes
    public void doAddItemExpirationDate() {
        // this is for adding an item

        addItemExpirationLabel = new JLabel("Item Expiration Date:");
        addItemExpirationLabel.setBounds(20, 90, 180, 30);
        panel.add(addItemExpirationLabel);

        addExpirationDayText = new JTextField(20);
        addExpirationDayText.setBounds(addItemTextFieldHorizontalPosition, 90, 100, 30);
        panel.add(addExpirationDayText);

        addExpirationMonthText = new JTextField(20);
        addExpirationMonthText.setBounds(addItemTextFieldHorizontalPosition, 120, 100, 30);
        panel.add(addExpirationMonthText);

        addExpirationYearText = new JTextField(20);
        addExpirationYearText.setBounds(addItemTextFieldHorizontalPosition, 150, 100, 30);
        panel.add(addExpirationYearText);
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

        addItemQuantity = new JTextField(20);
        addItemQuantity.setBounds(addItemTextFieldHorizontalPosition, 210, 100, 30);
        panel.add(addItemQuantity);
    }

    // EFFECTS: Sets up the expiration label and its three text boxes
    public void doAddItemState() {
        // this is for adding an item

        JLabel addItemStateLabel;

        addItemStateLabel = new JLabel("Solid (true)/ Liquid (false):");
        addItemStateLabel.setBounds(20, 270, 180, 30);
        panel.add(addItemStateLabel);

        addItemState = new JTextField(20);
        addItemState.setBounds(addItemTextFieldHorizontalPosition, 270, 100, 30);
        panel.add(addItemState);
    }



    // EFFECTS: creates the label, text box, and button for removing items
    public void doRemoveItem() {
        // This is for removing an item
        removeItemLabel = new JLabel("Item Name:");
        removeItemLabel.setBounds(500, 200, 100, 30);
        panel.add(removeItemLabel);

        removeText = new JTextField(20);
        removeText.setBounds(600, 200, 100, 30);
        panel.add(removeText);

        removeButton = new JButton("Remove Item");
        removeButton.setBounds(800, 200, 100, 30);
        removeButton.addActionListener(new FridgeyAppGUI());
        panel.add(removeButton);

    }


    // EFFECTS: performs the action of addButton and removeButton
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == addButton) {
            String name = addText.getText();
            items.addItem(new Solid(name, 20, 10, 2023,20));
            itemsDisplay.addElement(name);

        } else if (e.getSource() == removeButton) {
            String name = removeText.getText();
            Item tempItem = items.searchItem(name);
            items.removeItem(tempItem);
            itemsDisplay.removeElement(name);
        }


    }

}
