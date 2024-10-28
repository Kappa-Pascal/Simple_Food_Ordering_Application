package ui;

import javax.swing.*;

import model.AllItems;
import model.Order;
import model.OrderList;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

// Represent the graphic UI of the project
public class GraphicUI extends JFrame {
    private AllItems allItems;
    private OrderList orderList;
    private Order order;
    private JDesktopPane desktop;
    private JInternalFrame controlPanel;
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 800;

    // EFFECTS: Display the graphic UI of the project
    public GraphicUI() {
        this.allItems = new AllItems();
        this.orderList = new OrderList();
        setBlankFrame();

        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: Display a blank frame
    public void setBlankFrame() {
        desktop = new JDesktopPane();
        // desktop.addMouseListener();
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
            
        }
    }

}
