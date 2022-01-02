package me.lnzt.covtracker.ui;

import me.lnzt.covtracker.countryconfig.Country;
import me.lnzt.covtracker.countryconfig.CovData;
import me.lnzt.covtracker.countryconfig.Location;
import me.lnzt.covtracker.storables.GlobalData;

import me.lnzt.covtracker.ui.listeners.CustomPaneListener;
import me.lnzt.covtracker.ui.wrappers.JCustomPane;
import me.lnzt.covtracker.ui.wrappers.LayeredPaneW;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class CustomPaneHandler {


    private Dimension defDim;
    private Dimension outerDefDim;
    private LinkedList<JCustomPane> lbls;
    private  LayeredPaneW lPane;
    private CustomPaneListener LblList;

    public CustomPaneHandler(GlobalData mainData, LayeredPaneW lPane){
        lbls = new LinkedList<JCustomPane>();
        defDim = new Dimension(24,18);
        outerDefDim = new Dimension(defDim.width+30, defDim.height+30);
        this.lPane = lPane;
        this.LblList = new CustomPaneListener(this);


        for(Country c : mainData.Countries){
            if(c.getLocation() != null ){
                Location loc = c.getLocation();
                Point location = new Point(loc.getScaledXasInt(),loc.getScaledYasInt());
                lPane.add(initCompleteLbl(c.ISO31662TAG,location,c.data));
            }
        }

    }





    /**
     * initialise a custom JCustomPane label. Called first in this class's constructor from the parent LayeredPanel class. Customisations to all labels can be made here.
     * @param tag name of the country label in ISO-3166-2 Country Code.
     * @param location location of the top left point of this component relative to the parent window.
     * @param data the data to be held by this component for further processing.
     * @return a JCustomPane reference.
     */
    public JCustomPane initCompleteLbl(String tag, Point location, CovData data){
        JCustomPane mainPane = new JCustomPane(location, tag, data, new JLabel(), lPane.getParentFrame().mainData.WORLD);
        mainPane.setParentDim(new Dimension(lPane.getParentFrame().width,lPane.getParentFrame().height));
        mainPane.addMouseListener(this.LblList);

        lbls.addLast(mainPane);
        return mainPane;
    }





    public void updateLabel(LinkedList<Country> updatedCountries, CovData WORLD){
        try {
          for(Country c : updatedCountries){
              if(c.countryName.equalsIgnoreCase("world")){
                  continue;
              }
                for(int i =0; i < lbls.size(); i++){
                    JCustomPane jcp = lbls.get(i);
                  if(jcp.getTag().equalsIgnoreCase(c.ISO31662TAG)){
                     jcp.updateData(c.data,WORLD);

                  }

              }


          }

        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public void updateAll(){



    }
    public void setDefDim(Dimension defDim) {
        this.defDim = defDim;
    }

    public LayeredPaneW getlPane(){
        return this.lPane;
    }
}
