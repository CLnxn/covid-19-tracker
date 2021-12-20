package me.lnzt.covtracker.ui;

import me.lnzt.covtracker.storables.GlobalData;
import me.lnzt.covtracker.storables.CountryNode;
import me.lnzt.covtracker.ui.wrappers.LayeredPaneW;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class LabelHandler {


    private Dimension defDim;
    private LinkedList<JLabel> lbls;
    private  LayeredPaneW lPane;

    public LabelHandler(GlobalData mainData, LayeredPaneW lPane){
        lbls = new LinkedList<>();
        defDim = new Dimension(24,18);
        this.lPane = lPane;
        for(String cty : mainData.COUNTRIES.keySet()){
              CountryNode cNode = mainData.COUNTRIES.get(cty);
                if(cNode.CENTER != null){
                    lPane.add(initDefLbl(cNode.CENTER, cty));
                }

        }
    }

    /**
     * initialise all default labels. Called first in this class's constructor from the parent LayeredPanel class. Customisations to all labels can be made here.
     * @param location location of where the country label is.
     * @param ctyname name of the country label in ISO-3166-2 Country Code.
     * @return a JLabel handle.
     */
    public JLabel initDefLbl(Point location, String ctyname){
        JLabel lbl = new JLabel(ctyname);
        lbl.setAlignmentX(0.5f);
        lbl.setPreferredSize(defDim);
        lbl.setBounds(new Rectangle(location,lbl.getPreferredSize()));
        lbl.setText(ctyname);
        lbl.setBorder(BorderFactory.createLineBorder(Color.CYAN));
        lbl.setForeground(Color.MAGENTA);
        lbls.addLast(lbl);
        return lbl;
    }





    public void updateLabel(String ctyname, Point newPosition){
        if(lbls == null){
            throw new NullPointerException("null list.");
        }
        int i =0;
        for(JLabel lbl : lbls){

            if(ctyname.equals(lbl.getText())){
                double dx = -((double) lbl.getPreferredSize().width) / 2;
                double dy = -((double) lbl.getPreferredSize().height) / 2;
                newPosition.translate((int) Math.round(dx), (int) Math.round(dy));

                lbl.setBorder(BorderFactory.createLineBorder(Color.cyan));
                lbl.setBounds(new Rectangle(newPosition, lbl.getPreferredSize()));
                break;
            }
            i++;
        }
        // case where label is newly added
        if(i == lbls.size()){

            JLabel newlbl = initDefLbl(newPosition, ctyname);


            newlbl.setBorder(BorderFactory.createLineBorder(Color.cyan));
            double dx = -((double)newlbl.getPreferredSize().width)/2;
            double dy = -((double)newlbl.getPreferredSize().height)/2;
           // System.out.println("dx = "+(int )Math.round(dx) +",dy= "+ (int) Math.round(dy));
            newPosition.translate((int )Math.round(dx),(int) Math.round(dy));
            newlbl.setBounds(new Rectangle(newPosition, newlbl.getPreferredSize()));

            lPane.add(newlbl);

        }
    }

    public void updateAll(){



    }
    public void setDefDim(Dimension defDim) {
        this.defDim = defDim;
    }
}
