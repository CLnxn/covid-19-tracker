package me.lnzt.covtracker.ui.listeners;

import me.lnzt.covtracker.CovTracker;
import me.lnzt.covtracker.fileio.FileIO;
import me.lnzt.covtracker.storables.ConfigPayload;
import me.lnzt.covtracker.storables.CountryNode;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class FrameListener implements WindowListener, WindowFocusListener, WindowStateListener, MouseListener {

    public CovTracker parentFrame;
    public FrameListener(CovTracker parentJFrame){
        parentFrame = parentJFrame;


    }

    @Override
    public void windowOpened(WindowEvent e) {

            double xScale = (double)e.getWindow().getWidth()/(double)parentFrame.mainData.configDimension.width;
            double yScale = (double)e.getWindow().getHeight()/(double)parentFrame.mainData.configDimension.height;


            parentFrame.bgPanel.LPHandler.scaleComponents(xScale,yScale);
System.out.println("opened window");




    }

    @Override
    public void windowClosing(WindowEvent e) {
        System.out.println("closingw");

        FileIO output = new FileIO();
        try {
            HashMap<String, CountryNode> ctys = parentFrame.mainData.COUNTRIES;
            Dimension configD = e.getWindow().getSize();
            ConfigPayload cp = new ConfigPayload(ctys,configD);
            if(ctys != null) {
               output.writeToFile(cp);
            } else{
                System.out.println("ctys is null");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        //write file io

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
            parentFrame.maximised = !parentFrame.maximised;


            double xScale = (double)e.getWindow().getWidth()/(double)parentFrame.bgPanel.getCurrentWidth();
            double yScale = (double)e.getWindow().getHeight()/(double)parentFrame.bgPanel.getCurrentHeight();


            parentFrame.bgPanel.LPHandler.scaleComponents(xScale,yScale);


            parentFrame.bgPanel.setHeight(e.getWindow().getHeight());
            parentFrame.bgPanel.setWidth(e.getWindow().getWidth());
            parentFrame.setContentPane(parentFrame.bgPanel);



        }
    }
    @Deprecated
    @Override
    public void mouseClicked(MouseEvent e) {
        /*
        System.out.println("clicked frame");
        if(e.getSource() instanceof CovidWatch && CovidWatch.inConfigMode) {
            CovidWatch frame = (CovidWatch) e.getSource();



            JLabel label = new JLabel();
            label.setText("a label");

           label.setPreferredSize(new Dimension(200,200));
            frame.add(label, "x "+e.getX()+", y "+e.getY());
                frame.setVisible(true);
            System.out.println(e.getPoint());
        }

         */

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
