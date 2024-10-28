package ui;

import javax.swing.*;

import model.AllItems;
import model.Item;
import model.Order;
import model.OrderList;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

// Represent the graphic UI of the project
// Part of the code is modelled from AlarmSystem
// Citation: “Build software better, together,” GitHub. https://github.students.cs.ubc.ca/CPSC210/AlarmSystem 
public class GraphicUI extends JFrame implements ActionListener {
    private AllItems allItems;
    private OrderList orderList;
    private Order order;
    private JDesktopPane desktop;
    private JInternalFrame controlPanel;
    private JButton addSingleItem;
    private ActionListener actionListener;
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 800;

    // EFFECTS: Display the graphic UI of the project
    public GraphicUI() {
        this.allItems = new AllItems();
        this.orderList = new OrderList();
        this.addSingleItem = new JButton("Add single item");
        setBlankFrame();
        addSingleItem.addActionListener(actionListener);
        // JList<Item> displayList = new JList<>();
        // JScrollPane scrollPane = new JScrollPane(displayList);
        // getContentPane().add(scrollPane);
        // setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: Display a blank frame
    public void setBlankFrame() {
        desktop = new JDesktopPane();
        desktop.addMouseListener(new DesktopFocusAction());
        controlPanel = new JInternalFrame("Graphic UI", false, false, false, false);
        controlPanel.setLayout(new BorderLayout());
        setContentPane(desktop);
        setTitle("Food Ordering Application");
        setSize(WIDTH, HEIGHT);
    }

    // Represent a inner class that is used for key handling
    private class DesktopFocusAction extends MouseAdapter {

        // EFFECTS: Handle the MouseEvent
        @Override
        public void mouseClicked(MouseEvent e) {
            GraphicUI.this.requestFocusInWindow();
        }
    }

    // MODIFIES: this
    // EFFECTS: Detect a MouseEvent
    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
}
