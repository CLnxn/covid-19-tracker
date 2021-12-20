package me.lnzt.covtracker.ui.wrappers;

import javax.swing.*;

public class LayeredPaneW extends JLayeredPane {


    private JFrame parentFrame;
    public LayeredPaneW(JFrame parentFrame){
        super();
        this.parentFrame = parentFrame;
    }

    public JFrame getParentFrame(){
        return this.parentFrame;
    }
}
