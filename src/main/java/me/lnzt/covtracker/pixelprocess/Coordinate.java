package me.lnzt.covtracker.pixelprocess;

import java.awt.*;

public class Coordinate {
    public int x;
    public int y;
    public Coordinate(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Point getPoint(){
        return new Point(this.x,this.y);

    }

}
