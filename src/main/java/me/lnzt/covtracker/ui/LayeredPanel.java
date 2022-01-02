package me.lnzt.covtracker.ui;



import me.lnzt.covtracker.CovTracker;
import me.lnzt.covtracker.countryconfig.Country;
import me.lnzt.covtracker.countryconfig.Location;
import me.lnzt.covtracker.ui.listeners.LpaneListener;
import me.lnzt.covtracker.ui.wrappers.JCustomPane;
import me.lnzt.covtracker.ui.wrappers.LayeredPaneW;
import javax.swing.*;
import java.awt.*;

public class LayeredPanel {

    private LayeredPaneW layeredPane;
    private CustomPaneHandler customPaneHandler;
    private ControlPanel cPanel;
    private int width, height;


            public LayeredPanel(int width, int height, LayoutManager lmgr, CovTracker parentF){
                this.width = width;
                this.height = height;



                this.layeredPane = new LayeredPaneW(parentF, this);
                layeredPane.setLayout(lmgr);
                layeredPane.setPreferredSize(new Dimension(width,height));
                //setBorder("layeredP");
                this.layeredPane.addMouseListener(new LpaneListener(this.layeredPane));

                this.customPaneHandler = new CustomPaneHandler(parentF.mainData, layeredPane);
                addControlPanel();

            }


            private void setBorder(String title){
                if(title !=null){
                    layeredPane.setBorder(BorderFactory.createTitledBorder("title"));
                }
            }





            public void addControlPanel() {
                this.cPanel = new ControlPanel(new FlowLayout(), 100, height, this.layeredPane, ControlPanel.Side.LEFT);
                this.layeredPane.add(cPanel);

            }

            public void scaleComponents(Dimension newWinDim){
                try {
                    int i = 0;
                    for (Component c : this.layeredPane.getComponents()) {


                        if (c instanceof JCustomPane) {
                            JCustomPane lbl = (JCustomPane) c;
                            CovTracker pFrame = this.layeredPane.getParentFrame();
                            for (Country cty : pFrame.mainData.Countries) {
                                if (cty.ISO31662TAG.equalsIgnoreCase(lbl.getTag())) {
                                    Location loc = cty.getLocation();
                                    loc.scaleXY(newWinDim);
                                    lbl.setLocs(new Point(loc.getScaledXasInt(), loc.getScaledYasInt()));
                                    lbl.setLiveLocation(false);
                                    break;
                                }
                            }
                        }

                        i++;
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }





    /**
     * to be called after the constructor/ after all customisations to layeredpane to return JLayeredPane. Else null is returned.
     */
        public JLayeredPane getLayeredPane(){
            return this.layeredPane;
        }

        public ControlPanel getControl(){
            return this.cPanel;
        }
        public CustomPaneHandler getLabelHandler(){
            return this.customPaneHandler;
        }


}
