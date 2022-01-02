package me.lnzt.covtracker.ui.listeners;

import me.lnzt.covtracker.storables.GlobalData;
import me.lnzt.covtracker.ui.ButtonHandler;
import me.lnzt.covtracker.ui.ConfigDialog;
import me.lnzt.covtracker.ui.ControlPanel;
import me.lnzt.covtracker.ui.wrappers.JButtonW;
import me.lnzt.covtracker.ui.wrappers.JCustomPane;

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
                    case "deselect":
                       JPanel jPane = (JPanel) button.getHandler().getParent(ButtonHandler.PARENT_TYPE.JPanel);
                       if(jPane instanceof ControlPanel){
                           ControlPanel controlPanel = (ControlPanel) jPane;
                           for(Component jComponent : controlPanel.getParentPane().getComponents()){
                               if(jComponent instanceof JCustomPane){
                                   JCustomPane jcp = (JCustomPane) jComponent;
                                   if(jcp.getIsHighlighted()){
                                      jcp.highlight(false);
                                   }
                               }
                           }
                       }

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
