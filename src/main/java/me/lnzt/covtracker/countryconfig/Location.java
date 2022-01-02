package me.lnzt.covtracker.countryconfig;

import me.lnzt.covtracker.exceptions.CoordinateOutOfBoundsException;

import java.awt.*;

public class Location {
    /**
     * scaledX, scaledY are null unless scaleXY is called to calculate their onScreen coordinates.
     * rawX, rawY are the raw XY coordinates of a mercator projection of latitudes and longitudes. Origin is centered in the middle of the rectangular plane.
     * maxrY is the maximum lattitude that will be displayed on a projection, to avoid numerical overflow due to a large projected rawY value when latitude is 90deg.
     *
     */
    double latitude,longitude,rawX,rawY, scaledX, scaledY;
    double eRadius = 6371000; //m
    double maxlY = 85.05113; //deg

    /**
     *  stores the location of a coordinate on earth. Can be converted to pix coords onScreen w.r.t top left origin (0,0) by calling scaleXY.
     * @param latitude latitude in degrees.
     * @param longitude latitude in degrees.
     */
    public Location(double latitude, double longitude) throws CoordinateOutOfBoundsException {
        if(latitude <= maxlY && latitude >= -maxlY && longitude <= 180 && longitude >= -180) {
            this.latitude = latitude;
            this.longitude = longitude;
        } else{
            throw new CoordinateOutOfBoundsException();
        }


    }
    //latitude truncation at +-maxlY, projects earth onto 2D rectangular surface.
    public void mercatorProjection(){
        rawX = Math.toRadians(longitude);
        rawY = Math.log(Math.tan(Math.PI/4+Math.toRadians(latitude)/2));

    }

    public void millerProjection(){
        rawX = Math.toRadians(longitude);
        rawY = 1.25*Math.log(Math.tan(Math.PI*0.25 + 0.4*Math.toRadians(latitude)));


    }

    /**
     * scales the rawX and rawY coordinates based on the window dimensions, w.r.t. top left corner as the origin.
     * @param winDim dimension of the window to be scaled to.
     */
    public void scaleXY(Dimension winDim){
        double projHeight = 2*Math.log(Math.tan(Math.PI/4+Math.toRadians(maxlY)/2));
        double projWidth = 2*Math.PI;
        double width = winDim.width;
        double height = winDim.height;
        //System.out.println("ph:"+ projHeight+ ", pw:" +projWidth+", w:"+width+ ", h:"+height);

        //scaledcoord = scaleratio * rawcoord corrected for origin (from middle to top left)
        scaledX = (width/projWidth)*(projWidth/2 + rawX);
        scaledY = (height/projHeight)*(projHeight/2 - rawY);

    }



    public double getScaledX() {
        return scaledX;
    }


    public double getScaledY() {
        return scaledY;
    }

    public int getScaledXasInt(){
        return (int) Math.round(scaledX);
    }
    public int getScaledYasInt(){
        return (int) Math.round(scaledY);
    }
}
