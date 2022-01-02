package me.lnzt.covtracker.ui;


import me.lnzt.covtracker.ui.wrappers.LayeredPaneW;
import me.lnzt.covtracker.ui.wrappers.SearchField;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class ControlPanel extends JPanel{

    private LayeredPaneW parentPane;
    private List<Component> controls;
    private SearchField searchBar;
    private Dimension componentDimension;
    private int width, height;

    enum Side {
        LEFT(0), RIGHT(1);

        private Side(int value) {
            this.value = value;
        }

        private int value;

        private int getValue() {
            return value;
        }

    }




    /**
     * create a ControlPanel formatted based on the params below and with components added from ButtonHandler class.
     * @param lmgr    layout manager to specify, preferably a flow layout.
     * @param width   to set this ControlPanel's width.
     * @param height  to set this ControlPanel's height.
     * @param pFrame  the Dimensions of the parent frame for reference. (immediate parent frame must allow for absolute positioning.)
     * @param anchor  the enum side to anchor this control panel to.
     */
    public ControlPanel(LayoutManager lmgr, int width, int height, LayeredPaneW pFrame, Side anchor) {
        super();
        this.controls = new LinkedList<>();

        this.setOpaque(false);
        this.setLayout(lmgr); //preferably flow layout
        this.width = width;
        this.height = height;
        this.componentDimension = new Dimension(width, 25);
        this.setPreferredSize(new Dimension(width, height));
        this.parentPane = pFrame;
        switch (anchor) {
            case LEFT:
                this.setBounds(new Rectangle(new Point(0, 0), this.getPreferredSize()));
                break;

        }

        ButtonHandler bHandler = new ButtonHandler(componentDimension,true);
        bHandler.setParent(this);
        this.searchBar = new SearchField(pFrame.getParentFrame().mainData.Countries, this);
        searchBar.setPreferredSize(componentDimension);
        controls.addAll(bHandler.getButtons());
        controls.add(searchBar);
        addComponents();
        //setBorder("control");

    }





    public void addComponents() {
        for (int i = 0; i < controls.size(); i++) {
            this.add(controls.get(i));

        }


    }
    public LayeredPaneW getParentPane(){
        return this.parentPane;
    }


    private void setBorder(String title){
        if(title !=null){
            this.setBorder(BorderFactory.createTitledBorder(title));
        }
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
    }

}
