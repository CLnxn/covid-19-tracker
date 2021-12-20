package me.lnzt.covtracker.pixelprocess;

import me.lnzt.covtracker.CovTracker;

import java.awt.*;
import java.util.Collection;
import java.util.LinkedList;

public class Shape {

    /**
     *
     * contains point location data of every black pixel outlining the shape.
     */
    private Collection<Coordinate> BORDER_PATH;
    private LinkedList<Coordinate> ENCLOSED_AREA;



    public Shape(Collection<Coordinate> Outline){
        BORDER_PATH = Outline;



    }

    public Shape(){
        BORDER_PATH = new LinkedList<>();

    }

    public Collection<Point> getBORDER_PATH(){
        LinkedList<Point> points = new LinkedList<>();
        for(Coordinate c : BORDER_PATH){
            points.addLast(c.getPoint());
        }
        return points;
    }

    /**
     * Highlights the outline of the shape with the selected Colour param
     * @param Colour
     */
    public void highlight(Color Colour){

        int rgb = Colour.getRGB();

        for(Coordinate c : BORDER_PATH){

        }




    }


}
