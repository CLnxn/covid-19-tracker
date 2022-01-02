package me.lnzt.covtracker.ui.wrappers;

import me.lnzt.covtracker.CovTracker;
import me.lnzt.covtracker.countryconfig.Country;
import me.lnzt.covtracker.countryconfig.CovData;
import me.lnzt.covtracker.ui.LayeredPanel;
import me.lnzt.covtracker.ui.PopUpHandler;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.NoSuchElementException;

public class LayeredPaneW extends JLayeredPane {


    private CovTracker parentFrame;
    private LayeredPanel handle;
    public Popup popup;

    public LayeredPaneW(CovTracker parentFrame, LayeredPanel handle){
        super();
        this.parentFrame = parentFrame;
        this.handle = handle;



    }

    /**
     * creates a popup which displays itself relative to the source label.
     * @param tag countryTag identifying the label
     * @param x  screen x coordinates
     * @param y  screen y coordinates
     * @param lblSize size of the source label
     * @return reference to the created Popup.
     */
    public synchronized Popup initPopup(String tag, int x, int y, Dimension lblSize){

        Country dataFrom = null;
        boolean found = false;
        if(parentFrame != null){
            CovTracker mainFrame = parentFrame;

            for(Country c : mainFrame.mainData.Countries.toArray(new Country[mainFrame.mainData.Countries.size()])){
                if(c.ISO31662TAG.equalsIgnoreCase(tag)){

                    dataFrom = c;
                    found = true;
                    break;
                }
            }

        }
        if(!found){
            throw new NoSuchElementException("unable to find country with:"+tag);
        }

        CovData data = dataFrom.data;
        //data
        //designing the popup label via popuphandler
        PopUpHandler pH = new PopUpHandler(data);
        JPanel contents = pH.getInitContents();
        TitledBorder border = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.CYAN),dataFrom.countryName);
        border.setTitleColor(Color.CYAN);
        contents.setBorder(border);

        PopupFactory pFact = new PopupFactory();
     this.popup = pFact.getPopup(this, contents,x,y - contents.getPreferredSize().height-20);
     return this.popup;


    }



    public CovTracker getParentFrame(){
        return this.parentFrame;
    }
    public LayeredPanel getHandle(){
        return  this.handle;
    }

}
