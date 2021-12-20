package me.lnzt.covtracker;


import me.lnzt.covtracker.storables.GlobalData;
import me.lnzt.covtracker.ui.ContentPanel;
import me.lnzt.covtracker.ui.listeners.FrameListener;

import javax.swing.*;


public class CovTracker extends JFrame {

    private int width, height;
    public ContentPanel bgPanel;
    public FrameListener f_Handler;
    public GlobalData mainData;
    public static boolean inConfigMode;
    public boolean maximised= false;


    public CovTracker(int width, int height){



                //this.setLayout(null);
                this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               // this.setResizable(false);
                this.mainData = new GlobalData(this);
                mainData.initCountries();

                this.width = width;
                this.height = height;
                this.setTitle("Covid-19 Tracker");
                this.setSize(width, height);
                try {

                    bgPanel = new ContentPanel(width, height,this);
                    bgPanel.setOpaque(true);
                    f_Handler = new FrameListener(this);
                    this.setContentPane(bgPanel);
                    //bgPanel.processComponent();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }



                this.setVisible(true);

                addWindowListener(f_Handler);
                addWindowStateListener(f_Handler);
                addWindowFocusListener(f_Handler);
                addMouseListener(f_Handler);


            }



}
