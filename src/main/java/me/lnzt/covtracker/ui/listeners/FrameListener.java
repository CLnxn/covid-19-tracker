package me.lnzt.covtracker.ui.listeners;

import me.lnzt.covtracker.CovTracker;
import me.lnzt.covtracker.fileio.FileIO;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class FrameListener implements WindowListener, WindowFocusListener, WindowStateListener, MouseListener {

    public CovTracker parentFrame;
    public FrameListener(CovTracker parentJFrame){
        parentFrame = parentJFrame;


    }

    @Override
    public void windowOpened(WindowEvent e) {
    /*
                double xScale = (double)e.getWindow().getWidth()/(double)parentFrame.mainData.configDimension.width;
                double yScale = (double)e.getWindow().getHeight()/(double)parentFrame.mainData.configDimension.height;


                parentFrame.bgPanel.LPHandler.scaleComponents(xScale,yScale);



     */

        System.out.println("opened window");

    }

    @Override
    public void windowClosing(WindowEvent e) {

        FileIO fio = new FileIO();
        try {
            fio.writeToFile(parentFrame.mainData.Countries);
            System.out.println("written.");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    @Override
    public void windowClosed(WindowEvent e) {
        System.out.println("closed");
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

    @Override
    public void windowGainedFocus(WindowEvent e) {

    }

    @Override
    public void windowLostFocus(WindowEvent e) {

    }

    @Override
    public void windowStateChanged(WindowEvent e) {
        //0 is default, 6 is maximised, 1 is minimised

        if(e.getNewState() == 0 || e.getNewState() == 6) {
            System.out.println("scaling");


            parentFrame.bgPanel.LPHandler.scaleComponents(new Dimension(e.getWindow().getWidth(),e.getWindow().getHeight()));


            parentFrame.bgPanel.setHeight(e.getWindow().getHeight());
            parentFrame.bgPanel.setWidth(e.getWindow().getWidth());
            parentFrame.setContentPane(parentFrame.bgPanel);



        }
    }
    @Deprecated
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

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
