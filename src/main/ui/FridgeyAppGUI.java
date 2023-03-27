package ui;

import model.Solid;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

// GUI class for the Fridgey App
public class FridgeyAppGUI extends FridgeyApp implements ActionListener {

    private static JPanel panel;
    private static JFrame frame;

    private static JLabel addItemLabel;
    private static JLabel showAddedItemLabel;
    private static JTextField addText;
    private static JButton addButton;

    private static JLabel removeItemLabel;
    private static JLabel showRemovedItemLabel;
    private static JTextField removeText;
    private static JButton removeButton;

    private static JList displayItems;
    private static DefaultListModel items;

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

        doAddItem();

        doRemoveItem();


        items = new DefaultListModel();
        try {
            BufferedImage myPicture = ImageIO.read(new File("images/fridgey.png"));
            JLabel picLabel = new JLabel(new ImageIcon(myPicture));
            picLabel.setBounds(0, 0, 1000, 800);
            panel.add(picLabel);
        } catch (IOException e) {
            System.out.println("IOException caught");
        }


        displayItems = new JList(items);
        displayItems.setBounds(700, 400, 100, 200);
        displayItems.setSelectedIndex(3);
        panel.add(displayItems);
        frame.setVisible(true);
    }

    // EFFECTS: creates the label, text box, and button for adding items
    public void doAddItem() {
        // this is for adding an item
        addItemLabel = new JLabel("Item Name:");
        addItemLabel.setBounds(100, 200, 100, 30);
        panel.add(addItemLabel);

        addText = new JTextField(20);
        addText.setBounds(200, 200, 100, 30);
        panel.add(addText);

        addButton = new JButton("Add Item");
        addButton.setBounds(400, 200, 100, 30);
        addButton.addActionListener(new FridgeyAppGUI());
        panel.add(addButton);

        showAddedItemLabel = new JLabel("N/A");
        showAddedItemLabel.setBounds(100, 400, 100, 30);
        panel.add(showAddedItemLabel);
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

        showRemovedItemLabel = new JLabel("N/A");
        showRemovedItemLabel.setBounds(500, 400, 100, 30);
        panel.add(showRemovedItemLabel);

    }


    // EFFECTS: performs the action of addButton and removeButton
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == addButton) {
            String name = addText.getText();
            showAddedItemLabel.setText(name);
            items.addElement(new Solid(name, 20, 10, 2023,20));

        } else if (e.getSource() == removeButton) {
            String name = removeText.getText();
            showRemovedItemLabel.setText(name);
        }


    }

}
