package ui;

import javax.swing.SwingUtilities;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FridgeyAppGUI {

    JFrame frame = new JFrame("Fridgey");
    JPanel panelCont = new JPanel();
    JPanel addPanel = new JPanel();
    JPanel removePanel = new JPanel();
    JButton addButton = new JButton("Add (+)");
    JButton removeButton = new JButton("Remove (-)");
    JButton homeButton = new JButton("Home ⌂");
    JButton saveButton = new JButton("Save");
    JButton loadButton = new JButton("Load");
    JTextField addItemNameText = new JTextField();
    CardLayout cl = new CardLayout();

    public FridgeyAppGUI() {
        panelCont.setLayout(cl);

        panelCont.add(addPanel, "1");
        panelCont.add(removePanel, "2");

        cl.show(panelCont, "1");

        setHomePanel();
        setRemovePanel();

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(panelCont, "2");
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(panelCont, "1");
            }
        });

        frame.add(panelCont);
        frame.setSize(386, 500);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    private void setHomePanel() {
        addPanel.setLayout(null);

        addButton.setBounds(-3, 400, 83, 75);
        addPanel.add(addButton);

        removeButton.setBounds(74, 400, 83, 75);
        addPanel.add(removeButton);

        homeButton.setBounds(151, 400, 83, 75);
        addPanel.add(homeButton);

        saveButton.setBounds(228, 400, 83, 75);
        addPanel.add(saveButton);

        loadButton.setBounds(305, 400, 83, 75);
        addPanel.add(loadButton);

        addItemNameText.setBounds(90, 20, 200, 40);
        addPanel.add(addItemNameText);
    }

    private void setRemovePanel() {
        removePanel.setLayout(null);
//        removeButton.setBounds(96, 500, 96, 100);
//        removePanel.add(removeButton);
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
