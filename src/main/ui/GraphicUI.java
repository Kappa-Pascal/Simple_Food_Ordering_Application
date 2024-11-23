package ui;

import javax.swing.*;

import model.AllItems;
import model.Event;
import model.EventLog;
import model.Item;

import persistence.JsonReaderAllItems;
import persistence.JsonWriterAllItems;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

// Represent the graphic UI of the project
// Part of the code is modelled from AlarmSystem
// Citation: “Build software better, together,” GitHub. https://github.students.cs.ubc.ca/CPSC210/AlarmSystem 
// The loadImage method is modelled from TrafficLight, the lecture lab of C3
// Citation: “Build software better, together,” GitHub. https://github.students.cs.ubc.ca/CPSC210/C3-LectureLabSolution
public class GraphicUI extends JPanel implements ActionListener {
    private AllItems allItems;
    private JDesktopPane desktop;
    private JInternalFrame controlPanel;
    private JFrame frame;
    private JPanel textPanel;
    private JPanel imagePanel;
    private JTextArea textArea;
    private ImageIcon backgroundImage;
    private static final int WIDTH = 500;
    private static final int HEIGHT = 700;
    private static final String PATH = "./data/savedData.json";

    // EFFECTS: Display the graphic UI of the project
    public GraphicUI() {
        this.allItems = new AllItems();
        textPanel = new JPanel();
        imagePanel = new JPanel();
        frame = new JFrame("Food Ordering Application");

        setBlankFrame();
        addButtonPanel();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        controlPanel.setVisible(true);
        desktop.add(controlPanel);
        controlPanel.pack();

        initializeText();
        setVisible(true);

        imagePanel.add(loadImage());
        frame.add(imagePanel);
        frame.pack();
        frame.addWindowListener(new WindowsAction());
    }

    // Represent a inner class that is used for windows event handling
    private class WindowsAction extends WindowAdapter {

        // EFFECT: Print the log when the user closes the window
        @Override
        public void windowClosing(WindowEvent e) {
            printLog();
        }
    }

    // MODIFIES: EventLog, Event
    // EFFECTS: Print the log in the console
    public void printLog() {
        Iterator<Event> itb = EventLog.getInstance().iterator();
        while (itb.hasNext()) {
            System.out.println(itb.next().toString());
        }
    }

    // MODIFIES: this
    // EFFECTS: Display a blank frame
    public void setBlankFrame() {
        desktop = new JDesktopPane();
        desktop.setLayout(null);
        desktop.addMouseListener(new DesktopFocusAction());
        controlPanel = new JInternalFrame("Graphic UI", false, false, false, false);
        controlPanel.setLayout(new BorderLayout());
        frame.add(desktop);
        frame.add(controlPanel);
        setSize(WIDTH, HEIGHT);
        setLocation(WIDTH, 0);

    }

    // MODIFIES: this
    // EFFECTS: initialize the panel that displays all added items
    public void initializeText() {
        textPanel.setLayout(new BorderLayout());

        textArea = new JTextArea(10, 20);
        textArea.setText("");
        textArea.setEditable(false); // Make it read-only

        textPanel.add(textArea);
        frame.add(textPanel);
        frame.setVisible(true);
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
    // @Override
    public void actionPerformed(ActionEvent e) {
        GraphicUI.this.requestFocusInWindow();
    }

    // MODIFIES: this
    // EFFECTS: Add buttons in the graphic UI
    private void addButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 2));
        buttonPanel.add(new JButton(new AddSingleItemAction()));
        buttonPanel.add(new JButton(new AddMultipleItemsAction()));
        buttonPanel.add(new JButton(new ViewItemAction()));
        buttonPanel.add(new JButton(new RemoveItemAction()));
        buttonPanel.add(new JButton(new SaveAction()));
        buttonPanel.add(new JButton(new LoadAction()));
        buttonPanel.add(new JButton(new MutateItemAction()));
        buttonPanel.add(new JButton(new AddStockAction()));
        frame.add(buttonPanel, BorderLayout.WEST);
    }

    // EFFECTS: produce true if the item with the given name exists in AllItems,
    // otherwise produce false
    public boolean checkExistanceAllItems(String name) {
        if (allItems.findItem(name) == null) {
            return false;
        } else {
            return true;
        }
    }

    // MODIFIES: this, Item, AllItems
    // EFFECTS: read the name of the item from user's input
    public static String readItemName() {
        String name = JOptionPane.showInputDialog(null,
                "Enter Item Name:",
                "Item Name?",
                JOptionPane.QUESTION_MESSAGE);
        return name;
    }

    // REQUIRES: input >= 0.0
    // MODIFIES: this, Item, AllItems
    // EFFECTS: read the price of the item from user's input
    public static Double readItemPrice() throws NumberFormatException {
        double res = 0.0;
        String price = JOptionPane.showInputDialog(null,
                "Enter Item Price: (non-negative number)",
                "Item Price?",
                JOptionPane.QUESTION_MESSAGE);
        try {
            res = Double.valueOf(price);
            return res;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e.getMessage() + "\nInvalid Input", "Invalid Input",
                    JOptionPane.ERROR_MESSAGE);
            throw e;
        }

    }

    // REQUIRES: input >= 0
    // MODIFIES: this, Item, AllItems
    // EFFECTS: read the stock of the item from user's input
    public static int readItemStock() throws NumberFormatException {
        int res = 0;
        String stock = JOptionPane.showInputDialog(null,
                "Enter Item Stock (non-negative integer):",
                "Item Stock?",
                JOptionPane.QUESTION_MESSAGE);
        try {
            res = Integer.valueOf(stock);
            return res;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e.getMessage() + "\nInvalid Input", "Invalid Input",
                    JOptionPane.ERROR_MESSAGE);
            throw e;
        }
    }

    // REQUIRES: input >= 0
    // MODIFIES: this, Item, AllItems
    // EFFECTS: read the stock of the item from user's input
    public static int readItemAddedStock() throws NumberFormatException {
        int res = 0;
        String stock = JOptionPane.showInputDialog(null,
                "Enter Added Item Stock (non-negative integer):",
                "Added amount of Stock?",
                JOptionPane.QUESTION_MESSAGE);
        try {
            res = Integer.valueOf(stock);
            return res;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e.getMessage() + "\nInvalid Input", "Invalid Input",
                    JOptionPane.ERROR_MESSAGE);
            throw e;
        }
    }

    // REQUIRES: input >= 0
    // MODIFIES: this, Item, AllItems
    // EFFECTS: read the number of the items the user want to add from user's input
    public static int readReps() throws NumberFormatException {
        int res = 0;
        String reps = JOptionPane.showInputDialog(null,
                "Enter the Number of Items (non-negative integer):",
                "Number of Items?",
                JOptionPane.QUESTION_MESSAGE);
        try {
            res = Integer.valueOf(reps);
            return res;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e.getMessage() + "\nInvalid Input", "Invalid Input",
                    JOptionPane.ERROR_MESSAGE);
            throw e;
        }
    }

    // Represents the functionality of Add Single Item botton
    private class AddSingleItemAction extends AbstractAction {

        // EFFECTS: Display the name of a botton
        AddSingleItemAction() {
            super("Add a single Item");
        }

        // MODIFIES: this, AllItems
        // EFFECTS: Add a single item when the "Add a single Item" botton is clicked
        @Override
        public void actionPerformed(ActionEvent evt) {
            String name = readItemName();
            double price = readItemPrice();
            int stock = readItemStock();
            allItems.addItem(new Item(name, price, stock));
            updateText(allItems.printItems());
        }
    }

    // Represents the functionality of Add Multiple Items botton
    private class AddMultipleItemsAction extends AbstractAction {

        // EFFECTS: Display the name of a botton
        AddMultipleItemsAction() {
            super("Add multiple items");
        }

        // MODIFIES: this, AllItems
        // EFFECTS: Add multiple items when the "Add a single Item" botton is clicked
        @Override
        public void actionPerformed(ActionEvent evt) {
            int reps = readReps();
            AddSingleItemAction asi = new AddSingleItemAction();
            for (int i = 0; i < reps; i++) {
                asi.actionPerformed(evt);
            }
            updateText(allItems.printItems());
        }

    }

    // Represents the functionality of View Items botton
    private class ViewItemAction extends AbstractAction {

        // EFFECTS: Display the name of a botton
        ViewItemAction() {
            super("View all added items");
        }

        // MODIFIES: this
        // EFFECTS: Display the name and the price of items when the "View all added
        // items" botton is clicked
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null, "All added items are:\n"
                    + allItems.printItems());
        }

    }

    // MODIFIES: this
    // EFFECTS: Update text in the panel that displays all added items
    public void updateText(String text) {
        textArea.setText(text);
    }

    // MODIFIES: this
    // EFFECTS: Load the background image
    public JLabel loadImage() {
        String sep = System.getProperty("file.separator");
        backgroundImage = new ImageIcon(System.getProperty("user.dir") + sep
                + "image" + sep + "Background.jpg");
        JLabel imageAsLabel = new JLabel(backgroundImage);
        return imageAsLabel;
    }

    // Represents the functionality of Save botton
    private class SaveAction extends AbstractAction {

        // EFFECTS: Display the name of a botton
        SaveAction() {
            super("Save");
        }

        // MODIFIES: this
        // EFFECTS: Save all added items when the "Save" button is clicked
        @Override
        public void actionPerformed(ActionEvent e) {
            JsonWriterAllItems writer = new JsonWriterAllItems(PATH);
            try {
                writer.open();
            } catch (FileNotFoundException ec) {
                return;
            }
            writer.write(allItems);
            writer.close();
            JOptionPane.showMessageDialog(null, "All added items are saved");
        }

    }

    // Represents the functionality of Load botton
    private class LoadAction extends AbstractAction {

        // EFFECTS: Display the name of a botton
        LoadAction() {
            super("Load");
        }

        // MODIFIES: this, AllItems
        // EFFECTS: Load all saved items when the "Load" button is clicked
        @Override
        public void actionPerformed(ActionEvent e) {
            JsonReaderAllItems reader = new JsonReaderAllItems(PATH);
            try {
                allItems = reader.read();
                JOptionPane.showMessageDialog(null, "All Saved items are loaded");
            } catch (IOException ec) {
                return;
            }
            updateText(allItems.printItems());
        }

    }

    // Represents the functionality of Remove An item botton
    private class RemoveItemAction extends AbstractAction {

        // EFFECTS: Display the name of a botton
        RemoveItemAction() {
            super("Remove An Item");
        }

        // MODIFIES: this, AllItems
        // EFFECTS: Remove items whose name is equal to the user's input
        // if the name of the item does not exist, do nothing
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = JOptionPane.showInputDialog(null,
                    "Enter the name of the item\nyou want to remove",
                    "Item Name?",
                    JOptionPane.QUESTION_MESSAGE);
            if (checkExistanceAllItems(name)) {
                allItems.removeItem(allItems.findItem(name));
            }
            updateText(allItems.printItems());
        }

    }

    // Represents the functionality of Mutate An item botton
    private class MutateItemAction extends AbstractAction {

        // EFFECTS: Display the name of a botton
        MutateItemAction() {
            super("Mutate An Item");
        }

        // MODIFIES: this, AllItems
        // EFFECTS: Mutate items whose name is equal to the user's input to the new
        // name, price
        // if the name of the item does not exist, do nothing
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = JOptionPane.showInputDialog(null,
                    "Enter the name of the item\nyou want to mutate",
                    "Item name",
                    JOptionPane.QUESTION_MESSAGE);
            if (checkExistanceAllItems(name)) {
                String newName = readItemName();
                double price = readItemPrice();
                allItems.mutateItem(allItems.findItem(name), newName, price);
            }
            updateText(allItems.printItems());
        }

    }

    // Represents the functionality of Add Stock botton
    private class AddStockAction extends AbstractAction {

        // EFFECTS: Display the name of a botton
        AddStockAction() {
            super("Add Stock");
        }

        // MODIFIES: this, AllItems
        // EFFECTS: Add the input amount of stock to items whose name is equal to the
        // user's input and display the number of stock of item after the stock is
        // added.
        // if the name of the item does not exist, do nothing
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = JOptionPane.showInputDialog(null,
                    "Enter the name of the item\nyou want to add stock to",
                    "Item Name?",
                    JOptionPane.QUESTION_MESSAGE);
            if (checkExistanceAllItems(name)) {
                Item itm = allItems.findItem(name);
                int addedStock = readItemAddedStock();
                itm.addstock(addedStock);
                JOptionPane.showMessageDialog(null, "The stock of "
                        + itm.getName() + " is "
                        + Integer.toString(itm.getStockAmount()));

            }
            updateText(allItems.printItems());
        }

    }
}
