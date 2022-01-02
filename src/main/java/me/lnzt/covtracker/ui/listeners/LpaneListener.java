package me.lnzt.covtracker.ui.listeners;

import me.lnzt.covtracker.CovTracker;
import me.lnzt.covtracker.ui.ConfigDialog;
import me.lnzt.covtracker.ui.wrappers.JCustomPane;
import me.lnzt.covtracker.ui.wrappers.LayeredPaneW;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class LpaneListener implements MouseListener{

    JComponent parent;
    public LpaneListener(JComponent parent){
        this.parent = parent;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("clicked pane");
        if(parent != null){
            parent.grabFocus();
        }

        if(e.getSource() instanceof LayeredPaneW && CovTracker.inConfigMode && !ConfigDialog.isOpen) {
            ConfigDialog.isOpen = true;
            LayeredPaneW jLayeredPane = (LayeredPaneW) e.getSource();
            System.out.println(e.getPoint());
            JFrame parent =  jLayeredPane.getParentFrame();
            CovTracker ctParent;
            if(parent instanceof  CovTracker){
                ctParent = (CovTracker) parent;
            } else{
                throw new ClassCastException("JFrame is not an instance of CovTracker!");
            }
            new ConfigDialog(ctParent,e.getPoint());




        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
