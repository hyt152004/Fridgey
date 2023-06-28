package ui;

import model.Item;
import model.Liquid;
import model.Refrigerator;
import model.Solid;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.imageio.ImageIO;
import javax.swing.SwingUtilities;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;

// EFFECTS: GUI for Fridgey application
public class FridgeyAppGUI {

    private static final String JSON_STORE = "./data/fridgeyGUI.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private final CardLayout cl = new CardLayout();

    // home screen
    private final JFrame frame = new JFrame("Fridgey");
    private final JPanel panelCont = new JPanel();
    private final JPanel homePanel = new JPanel();
    private final JButton homeToMainButton = new JButton("Navigate to my Fridgey!");

    // main screen
    private final JPanel mainPanel = new JPanel();
    private final JButton addButton = new JButton("Add (+)");
    private final JButton removeButton = new JButton("Remove (-)");
    private final JButton homeButton = new JButton("Home ⌂");
    private final JButton saveButton = new JButton("Save");
    private final JButton loadButton = new JButton("Load");
    private final JTextField searchTextField = new JTextField();
    private final JLabel searchText = new JLabel("Search for an Item!");
    private final JButton searchButton = new JButton("✓");
    private final JLabel searchItemInfoLabel = new JLabel("");
    private DefaultListModel itemsDisplay = new DefaultListModel();
    private JList displayItems = new JList(itemsDisplay);
    private Refrigerator items = new Refrigerator();
    private final LocalDate today = LocalDate.now();
    private final JLabel todayDateText = new JLabel("Today's Date: " + today.getMonth()
            + " " + today.getDayOfMonth() + ", " + today.getYear());
    private final JLabel savedLabel = new JLabel("");

    // add screen
    private final JPanel addPanel = new JPanel();
    private final JLabel addingItemNameLabel = new JLabel("Item Label:");
    private final JLabel addingItemExpirationDateLabel = new JLabel("Item Expiration Date:");
    private final JLabel addingItemExpirationDayLabel = new JLabel("Day");
    private final JLabel addingItemExpirationMonthLabel = new JLabel("Month");
    private final JLabel addingItemExpirationYearLabel = new JLabel("Year");
    private final JLabel addingItemQuantityLabel = new JLabel("Item Quantity:");
    private final JLabel addingItemStateLabel = new JLabel("Solid (s) / Liquid(l):");
    private final JLabel addingItemStateMLLabel = new JLabel("(ml for Liquids)");
    private final JTextField addingItemNameTextField = new JTextField();
    private final JTextField addingItemExDayTextField = new JTextField();
    private final JTextField addingItemExMonthTextField = new JTextField();
    private final JTextField addingItemExYearTextField = new JTextField();
    private final JTextField addingItemQuantityTextField = new JTextField();
    private final JTextField addingItemStateTextField = new JTextField();

    private final JButton addItemButton = new JButton("Add (+)");
    private final JButton clearAllButton = new JButton("Clear All");
    private final JButton backButton = new JButton("Back");
    private final JLabel errorLabel = new JLabel("");

    // remove screen
    private final JPanel removePanel = new JPanel();
    private final JButton removePanelBackButton = new JButton("Back");
    private final JTextField removingItemName = new JTextField();
    private final JButton removingItemButton = new JButton("Remove");
    private final JLabel removingItemErrorLabel = new JLabel();
    private final JLabel removingItemHeaderLabel = new JLabel("Item to be removed");


    // EFFECTS: Sets the layout of all the panels
    public FridgeyAppGUI() {
        panelCont.setLayout(cl);

        panelCont.add(mainPanel, "1");
        panelCont.add(addPanel, "2");
        panelCont.add(removePanel, "3");
        panelCont.add(homePanel, "4");

        cl.show(panelCont, "4");

        setHomePanel();
        setMainPanel();
        setAddPanel();
        setRemovePanel();
        actionPerformed();

        frame.add(panelCont);
        frame.setSize(386, 500);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    // EFFECTS: sets the home screen
    private void setHomePanel() {
        homePanel.setLayout(null);
        homePanel.setBackground(new Color(232,139,103));

        homeToMainButton.setBounds(110, 175, 180, 50);
        homePanel.add(homeToMainButton);

        try {
            BufferedImage myPicture = ImageIO.read(new File("images/fridgey.png"));
            JLabel picLabel = new JLabel(new ImageIcon(myPicture));
            picLabel.setBounds(63, 60, 250, 100);
            homePanel.add(picLabel);
        } catch (IOException e) {
            System.out.println("IOException caught");
        }

//        try {
//            BufferedImage myPicture = ImageIO.read(new File("images/imageedit_3_4541337243.png"));
//            JLabel picLabel = new JLabel(new ImageIcon(myPicture));
//            picLabel.setBounds(-70, -200, 500, 800);
//            homePanel.add(picLabel);
//        } catch (IOException e) {
//            System.out.println("IOException caught");
//        }

    }

    // EFFECTS: sets the home screen
    private void setMainPanel() {
        mainPanel.setLayout(null);
        mainPanel.setBackground(new Color(232,139,103));

        addButton.setBounds(-3, 400, 83, 75);
        mainPanel.add(addButton);

        removeButton.setBounds(74, 400, 83, 75);
        mainPanel.add(removeButton);

        homeButton.setBounds(151, 400, 83, 75);
        mainPanel.add(homeButton);

        saveButton.setBounds(228, 400, 83, 75);
        mainPanel.add(saveButton);

        loadButton.setBounds(305, 400, 83, 75);
        mainPanel.add(loadButton);

        searchTextField.setBounds(90, 50, 200, 40);
        mainPanel.add(searchTextField);

        searchText.setBounds(130, 23, 200, 40);
        mainPanel.add(searchText);

        searchButton.setBounds(300, 55, 30,30);
        mainPanel.add(searchButton);

        searchItemInfoLabel.setBounds(44, 70, 400, 100);
        mainPanel.add(searchItemInfoLabel);

        displayItems.setBounds(40, 150, 300, 240);
        displayItems.setSelectedIndex(6);
        mainPanel.add(displayItems);

        todayDateText.setBounds(2, -2, 300, 30);
        mainPanel.add(todayDateText);

        savedLabel.setBounds(340, 2, 50, 25);
        mainPanel.add(savedLabel);

    }

    // EFFECTS: sets the add screen
    private void setAddPanel() {
        addPanel.setLayout(null);
        addPanel.setBackground(new Color(232,139,103));

        addingItemNameLabel.setBounds(30, 10, 100, 100);
        addingItemExpirationDateLabel.setBounds(30, 70, 200, 100);
        addingItemQuantityLabel.setBounds(30, 170, 100, 100);
        addingItemStateLabel.setBounds(30, 220, 250, 100);

        addingItemNameTextField.setBounds(180, 50, 100, 20);
        addingItemExDayTextField.setBounds(180, 110, 100, 20);
        addingItemExMonthTextField.setBounds(180, 135, 100, 20);
        addingItemExYearTextField.setBounds(180, 160, 100, 20);
        addingItemQuantityTextField.setBounds(180, 210, 100, 20);
        addingItemStateTextField.setBounds(180, 260, 100, 20);
        addingItemExpirationDayLabel.setBounds(290, 110, 100, 20);
        addingItemExpirationMonthLabel.setBounds(290, 135, 100, 20);
        addingItemExpirationYearLabel.setBounds(290, 160, 100, 20);
        addingItemStateMLLabel.setBounds(285, 210, 100, 20);

        addItemButton.setBounds(205, 330, 130, 70);
        clearAllButton.setBounds(50, 330, 130, 70);
        backButton.setBounds(10,420, 90,30);
        errorLabel.setBounds(102, 385, 220, 60);


        addPanel.add(addingItemNameLabel);
        addPanel.add(addingItemExpirationDateLabel);
        addPanel.add(addingItemQuantityLabel);
        addPanel.add(addingItemStateLabel);
        addPanel.add(addingItemNameTextField);
        addPanel.add(addingItemExDayTextField);
        addPanel.add(addingItemExMonthTextField);
        addPanel.add(addingItemExYearTextField);
        addPanel.add(addingItemQuantityTextField);
        addPanel.add(addingItemStateTextField);
        addPanel.add(addingItemExpirationDayLabel);
        addPanel.add(addingItemExpirationMonthLabel);
        addPanel.add(addingItemExpirationYearLabel);
        addPanel.add(addingItemStateMLLabel);
        addPanel.add(addItemButton);
        addPanel.add(clearAllButton);
        addPanel.add(backButton);
        addPanel.add(errorLabel);



    }

    // EFFECTS: sets the remove screen
    private void setRemovePanel() {
        removePanel.setLayout(null);
        removePanel.setBackground(new Color(232,139,103));

        removePanelBackButton.setBounds(10,420, 90,30);
        removePanel.add(removePanelBackButton);

        removingItemHeaderLabel.setBounds(135, 100, 150, 30);
        removePanel.add(removingItemHeaderLabel);

        removingItemName.setBounds(120,130,150,30);
        removePanel.add(removingItemName);

        removingItemButton.setBounds(145,170, 100,40);
        removePanel.add(removingItemButton);

        removingItemErrorLabel.setBounds(130, 210, 150, 30);
        removePanel.add(removingItemErrorLabel);

        try {
            BufferedImage myPicture = ImageIO.read(new File("images/itemRemoveTrashCanReal.png"));
            JLabel picLabel = new JLabel(new ImageIcon(myPicture));
            picLabel.setBounds(96, 220, 200, 200);
            removePanel.add(picLabel);
        } catch (IOException e) {
            System.out.println("IOException caught");
        }
    }

    // EFFECTS: responsible for performing all the button actions
    private void actionPerformed() {
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(panelCont, "2");
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(panelCont, "3");
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(panelCont, "1");
            }
        });

        removePanelBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(panelCont, "1");
            }
        });

        addItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
        });

        clearAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addButtonActionResetAllTextFields();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = searchTextField.getText().toLowerCase();
                searchTextField.setText("");
                Item i = items.searchItem(name);
                try {
                    searchItemInfoLabel.setText("<html>" + i.getItemNameWithExpirationDate() + "<br/>Quantity: " + i.getQuantity()
                            + "<br/>Days Left Until Expiration Date: " + i.getDaysLeft(today) + "</html>");
                } catch (NullPointerException ex) {
                    searchItemInfoLabel.setText("Item cannot be found");
                }

            }
        });

        removingItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = removingItemName.getText().toLowerCase();
                Item tempItem = items.searchItem(name);
                items.removeItem(tempItem);
                try {
                    itemsDisplay.removeElement(tempItem.getItemNameWithExpirationDate());
                    removingItemErrorLabel.setText(tempItem.getItemName() + " was removed");
                    savedLabel.setText("");
                    removingItemName.setText("");
                } catch (NullPointerException exp) {
                    removingItemErrorLabel.setText("Item was not found");
                }
            }
        });

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
        });

        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(panelCont, "4");
            }
        });

        homeToMainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(panelCont, "1");
            }
        });

    }

    // EFFECTS: constructs a new item with the info given from the Text fields
    private Item constructItemWithGivenInfo() {
        int expMonth;
        Item currentItem;
        int expYear;
        int quantity;
        int expDay;
        String name = addingItemNameTextField.getText().toLowerCase();
        String state = addingItemStateTextField.getText().toLowerCase();
        expDay = Integer.parseInt(addingItemExDayTextField.getText());
        expMonth = Integer.parseInt(addingItemExMonthTextField.getText());
        expYear = Integer.parseInt(addingItemExYearTextField.getText());
        quantity = Integer.parseInt(addingItemQuantityTextField.getText());

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
        addingItemNameTextField.setText("");
        addingItemStateTextField.setText("");
        addingItemNameTextField.setText("");
        addingItemExDayTextField.setText("");
        addingItemExMonthTextField.setText("");
        addingItemExYearTextField.setText("");
        addingItemQuantityTextField.setText("");
        errorLabel.setText("");
        savedLabel.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FridgeyAppGUI();
            }
        });
    }

}
