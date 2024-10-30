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
    private Action addSingleItemAction;
    private Action addMultipleItemAction;
    private Action removeItemAction;
    private Action mutateItemAction;
    private Action saveItemAction;
    private Action loadItemAction;
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 800;

    // EFFECTS: Display the graphic UI of the project
    public GraphicUI() {
        this.allItems = new AllItems();
        this.orderList = new OrderList();
        this.addSingleItem = new JButton("Add single item");
        setBlankFrame();
        addSingleItem.addActionListener(actionListener);
        addButtonPanel();
        controlPanel.setVisible(true);
        desktop.add(controlPanel);
        controlPanel.pack();
        // JList<Item> displayList = new JList<>();
        // JScrollPane scrollPane = new JScrollPane(displayList);
        // getContentPane().add(scrollPane);
        // setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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
        GraphicUI.this.requestFocusInWindow();
    }

    // MODIFIES: this
    // EFFECTS: Add buttons in the graphic UI
    private void addButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 2));
        buttonPanel.add(new JButton(new AddSingleItemAction()));
        buttonPanel.add(new JButton(addMultipleItemAction));
        buttonPanel.add(new JButton(mutateItemAction));
        buttonPanel.add(new JButton(removeItemAction));
        buttonPanel.add(new JButton(saveItemAction));
        buttonPanel.add(new JButton(loadItemAction));
        controlPanel.add(buttonPanel, BorderLayout.WEST);
    }

    // MODIFIES: this, Item, AllItems
    // EFFECTS: read the name of the item from user's input
    public static String readItemName() {
        String name = JOptionPane.showInputDialog(null,
                "Item Name?",
                "Enter Ttem Name:",
                JOptionPane.QUESTION_MESSAGE);
        return name;
    }

    private class AddSingleItemAction extends AbstractAction {

        // EFFECTS: Display the name of a botton
        AddSingleItemAction() {
            super("Add a single Item");
        }

        // MODIFIES: this, AllItems
        // EFFECTS: Add a single item when the "Add a single Item" botton is clicked
        @Override
        public void actionPerformed(ActionEvent evt) {
            // The following code allows users to input a String.
            /*
             * String sensorLoc = JOptionPane.showInputDialog(null,
             * "Sensor location?",
             * "Enter sensor location",
             * JOptionPane.QUESTION_MESSAGE);
             */

            // The following code displays a String in a new window
            // JOptionPane.showMessageDialog(null, String);

            // AlarmCode alarmCode = new AlarmCode(kp.getCode());
            // kp.clearCode();
            // try {
            // ac.addCode(alarmCode);
            // } catch (NotValidCodeException e) {
            // JOptionPane.showMessageDialog(null, e.getMessage(), "System Error",
            // JOptionPane.ERROR_MESSAGE);
            // }
            //System.out.println("success");
            readItemName();
        }
    }
}
