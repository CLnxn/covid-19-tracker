package me.lnzt.covtracker.ui;

import me.lnzt.covtracker.CovTracker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;


public class ConfigDialog extends JDialog implements WindowListener {


    public static boolean isOpen = false;
    private JTextField C_CODE;
    private ButtonHandler b_Handler;
    private Point location;
    private CovTracker owner;

        public ConfigDialog(CovTracker owner, Point location){
            super(owner);
            this.owner = owner;
            this.location = location;
            this.setSize(300,200);
            this.setTitle("Enter your input:");

            C_CODE = new JTextField();


            b_Handler = new ButtonHandler(new Dimension(200,25),false);
            b_Handler.setParent(this);


            this.add(C_CODE, BorderLayout.NORTH);
            this.add(b_Handler.initSubmitButton(), BorderLayout.SOUTH);
            this.addWindowListener(this);


            this.setVisible(true);

        }

        public String getTextInput(){
            return C_CODE.getText();
        }
        public Point getLocation(){
            return this.location;
        }
        public CovTracker getOwner(){return this.owner;}


    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
            isOpen = false;
    }

    @Override
    public void windowClosed(WindowEvent e) {
        isOpen = false;
    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
