package me.lnzt.covtracker.ui.listeners;

import me.lnzt.covtracker.ui.CustomPaneHandler;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class PopupExitListener implements MouseMotionListener {


    JComponent jComponent;
    CustomPaneHandler lHandle;
    public PopupExitListener(JComponent jComponent, CustomPaneHandler lHandle){
        this.lHandle = lHandle;
        this.jComponent = jComponent;


    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //System.out.println("moving");
        if(!jComponent.contains(e.getPoint())){
            //System.out.println("removing");
            lHandle.getlPane().popup.hide();
            this.lHandle.getlPane().removeMouseMotionListener(this);
        }


    }
}
