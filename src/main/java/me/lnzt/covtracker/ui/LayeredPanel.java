package me.lnzt.covtracker.ui;



import me.lnzt.covtracker.CovTracker;
import me.lnzt.covtracker.ui.listeners.LpaneListener;
import me.lnzt.covtracker.ui.wrappers.LayeredPaneW;


import javax.swing.*;
import java.awt.*;

public class LayeredPanel {

    private LayeredPaneW layeredPane;
    private LabelHandler labelHandler;
    private ControlPanel cPanel;
    private int width, height;


            public LayeredPanel(int width, int height, LayoutManager lmgr, CovTracker parentF){
                this.width = width;
                this.height = height;



                this.layeredPane = new LayeredPaneW(parentF);
                layeredPane.setLayout(lmgr);
                layeredPane.setPreferredSize(new Dimension(width,height));
                //setBorder("layeredP");
                this.layeredPane.addMouseListener(new LpaneListener());

                this.labelHandler = new LabelHandler(parentF.mainData, layeredPane);
                addControlPanel();

            }


            private void setBorder(String title){
                if(title !=null){
                    layeredPane.setBorder(BorderFactory.createTitledBorder("title"));
                }
            }





            public void addControlPanel() {
                this.cPanel = new ControlPanel(new FlowLayout(), 100, height, new Dimension(width, height), ControlPanel.Side.LEFT);
                this.layeredPane.add(cPanel);

            }

            public void scaleComponents(double xScale, double yScale){
                int i = 0;
                for(Component c : this.layeredPane.getComponents()){
                    int newX = (int) Math.round(((double)c.getBounds().x)*xScale);
                    int newY = (int) Math.round(((double)c.getBounds().y)*yScale);
                    if(i == 3) {
                        System.out.println("xnew: " + newX + ", ynew:" + newY);
                    }

                    if(c instanceof JLabel){
                        JLabel lbl = (JLabel) c;
                        CovTracker pFrame = (CovTracker) this.layeredPane.getParentFrame();
                        pFrame.mainData.COUNTRIES.get(lbl.getText()).CENTER = new Point(newX,newY); //updating the map
                    }
                    c.setBounds(newX,newY,c.getBounds().width,c.getBounds().height);

                    i++;
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
        public LabelHandler getLabelHandler(){
            return this.labelHandler;
        }


}
