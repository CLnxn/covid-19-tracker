package me.lnzt.covtracker.ui.listeners;

import me.lnzt.covtracker.ui.CustomPaneHandler;
import me.lnzt.covtracker.ui.wrappers.JCustomPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CustomPaneListener implements MouseListener, KeyListener {

    public CustomPaneHandler parentHandler;


    public CustomPaneListener(CustomPaneHandler parentHandler){
        this.parentHandler = parentHandler;

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //System.out.println("entered");
        Object obj = e.getSource();

        if(obj instanceof JCustomPane){

            JCustomPane cPane = (JCustomPane) obj;
            if(this.parentHandler.getlPane().popup != null) {
                this.parentHandler.getlPane().popup.hide(); //hide & dispose previous popups
            }
            Point screenLocs = cPane.getLocationOnScreen();
            Popup popup = this.parentHandler.getlPane().initPopup(cPane.getTag(),screenLocs.x,screenLocs.y,((JCustomPane) obj).getSize());

            popup.show();

        }


    }

    @Override
    public void mouseExited(MouseEvent e) {
        //System.out.println("exited");
        Object obj = e.getSource();

        if(obj instanceof JCustomPane ){
            JCustomPane cPane = (JCustomPane) obj;

                if(cPane.contains(e.getPoint())){
                    parentHandler.getlPane().addMouseMotionListener(new PopupExitListener(cPane, parentHandler));
                    return;

                } else{
                    this.parentHandler.getlPane().popup.hide();
                }


        }


    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
