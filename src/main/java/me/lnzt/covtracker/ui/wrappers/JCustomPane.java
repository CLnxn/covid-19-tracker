package me.lnzt.covtracker.ui.wrappers;

import me.lnzt.covtracker.countryconfig.CovData;

import javax.swing.*;
import java.awt.*;

public class JCustomPane extends JPanel {


    public boolean paint = true;
    private boolean isHighlighted = false;
    private Point location;
    private String tag;
    private CovData data, worldData;
    private Dimension defDim;
    private Dimension parentDim;

    private int maxlength, minlength;
    private JLabel label;

    public JCustomPane(Point loc, String tag, CovData localdata, JLabel ctyLbl, CovData worldData){

        this.setLayout(new GridBagLayout());
        this.location = loc;
        this.tag = tag;
        this.data = localdata;
        this.worldData = worldData;
        this.label = ctyLbl;
        this.minlength = 20;
        this.maxlength = 100;
        //label.setForeground(Color.cyan);
        this.add(ctyLbl);
        this.defDim = new Dimension(minlength, minlength); //default, should be a square
        this.setPreferredSize(defDim);
        this.setBounds(new Rectangle(new Point((int) Math.round((double) loc.x - ((double) defDim.width) / 2), (int) Math.round((double) loc.y - ((double) defDim.height) / 2)), this.getPreferredSize()));
        this.setOpaque(false);
        //this.setBorder(BorderFactory.createLineBorder(Color.black));
        setLiveLocation(true);

    }

    public void updateData(CovData newdata, CovData worldData){
        this.data = newdata;
        this.worldData = worldData;
        setLiveLocation(false);

    }

    public JLabel getLabel(){
        return this.label;
    }
    public String getTag(){
        return this.tag;
    }
    public boolean getIsHighlighted(){ return this.isHighlighted;}
    public void setLocs(Point newLocation){
        this.location = newLocation;
    }
    public void setMaxlength(int maxlength){
        this.maxlength = maxlength;
    }
    public void setMinlength(int minlength){
        this.minlength = minlength;
    }
    public void setParentDim(Dimension parentDim) { this.parentDim = parentDim; }

    public void setLiveLocation(boolean initial){
        String infections = data.TOTAL_INFECTIONS.replace(",", "");
        double infected = (infections.equals("")) ? 1 : Double.valueOf(infections);
        if(infected < 1){
            infected = 1; //prevents log10 from returning undefined.
        }

        double scalelength = ((double)(maxlength+minlength)*0.5 + ((double) (maxlength-minlength)/Math.PI)*Math.atan(0.05*(infected- worldData.getAverageWCases(195))));
        //denoted the length of the sides of the square box which the circle is inscribed in.

        int newX = (int) Math.round(location.x - 0.5*scalelength); // center location.x
        int newY = (int) Math.round(location.y - 0.5*scalelength);// center location.y



       //this.setSize(new Dimension((int) scalelength, (int) scalelength)); // not needed as paintComponent will be called again in setLocation() which will call setSize()
        this.setLocation(new Point(newX,newY));

    }


    public void highlight(boolean highlight){
        this.isHighlighted = highlight;
        this.repaint();
    }
    @Override
    public void paintComponent(Graphics g){
        try {
        super.paintComponent(g);


        if(paint) {

            String infections = data.TOTAL_INFECTIONS.replace(",", "");
            double infected = (infections.equals("")) ? 1 : Double.valueOf(infections);
            if(infected < 1){
                infected = 1; //prevents log10 from returning undefined.
            }

               //double scalelength = (double)maxlength - (1/Math.cbrt(0.000625*infected))*((double) (maxlength-minlength));
                double scalelength = ((double)(maxlength+minlength)*0.5 + ((double) (maxlength-minlength)/Math.PI)*Math.atan(0.000001*(infected- worldData.getAverageWCases(195))));



                this.setSize(new Dimension((int) Math.round(scalelength),(int) Math.round(scalelength)));

                double uDiam = scalelength;

                float maxDarkness = .1f;
                float r = (float) (((1f - maxDarkness) / ((double) (minlength - maxlength))) * (scalelength - minlength) + 1f);
                g.setColor(new Color(r, .0f, .0f, 0.5f));
                g.fillOval(0,0, (int) uDiam,(int) uDiam);

                if(isHighlighted){ //condition is set to true before graphics is painted.
                   Graphics highlighter = g.create();
                    highlighter.setColor(Color.WHITE);
                   highlighter.drawOval(0,0, (int) uDiam,(int) uDiam);
                }
        }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }




}
