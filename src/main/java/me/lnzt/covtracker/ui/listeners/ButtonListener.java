package me.lnzt.covtracker.ui.listeners;

import me.lnzt.covtracker.storables.GlobalData;
import me.lnzt.covtracker.ui.ButtonHandler;
import me.lnzt.covtracker.ui.ConfigDialog;
import me.lnzt.covtracker.ui.wrappers.JButtonW;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

import static me.lnzt.covtracker.CovTracker.inConfigMode;

public class ButtonListener implements MouseListener, KeyListener {


             boolean allShown = true;
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        //System.out.println("released");
        if(inConfigMode && e.getKeyCode() == KeyEvent.VK_ESCAPE){
            System.out.println("exiting config mode");
            inConfigMode = false;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
            //System.out.println("mouse clicked");


            if(e.getSource() instanceof JButtonW){
                JButtonW button = (JButtonW) e.getSource();



                switch (button.getIdentifier()){
                    case "Config":
                        if(!inConfigMode){
                            inConfigMode = true;
                            System.out.println("in config mode");
                        }


                        break;
                    case "sh":
                        if(!inConfigMode){
                            allShown = !allShown;
                            button.setText( allShown? "Hide All": "Show All");
                            ButtonHandler handler = button.getHandler();
                            List<Component> buttons = handler.getButtons();
                            for(Component c : buttons){
                                if(!((JButtonW) c).getIdentifier().equals("sh")){
                                    c.setVisible(allShown);
                                }
                            }
                        }
                        break;
                    case "submit":
                        JDialog parent = (JDialog) button.getHandler().getParent(ButtonHandler.PARENT_TYPE.JDialog);
                        if(parent instanceof ConfigDialog){
                            ConfigDialog proper = (ConfigDialog) parent;
                            String inputCCODE = proper.getTextInput();
                            Point loc = proper.getLocation();
                            System.out.println("input country code: "+ inputCCODE + "\n"+"location: "+ loc);
                            parent.dispose();

                            GlobalData ctys = proper.getOwner().mainData;


                                try {

                                        ctys.updateCountry(inputCCODE, loc);
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                    // if wrong, no updates will be done to any country
                                }

                        }
                   default:
                        break;
                }


            }


    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getSource() instanceof JButton){
            JButton button = (JButton) e.getSource();
                button.setBackground(Color.WHITE);

        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getSource() instanceof JButton){
            JButton button = (JButton) e.getSource();
            button.setBackground(Color.GRAY);

        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(e.getSource() instanceof JButton){
            //System.out.println("mouse entered");
            JButton button = (JButton) e.getSource();
            Border b = new LineBorder(Color.cyan,1);
           button.setBorder(b);

        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getSource() instanceof JButton){
           // System.out.println("mouse exited");
            JButton button = (JButton) e.getSource();
            Border b = new LineBorder(Color.GRAY,1);
            button.setBorder(b);

        }
    }




}
