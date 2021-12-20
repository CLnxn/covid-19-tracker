package me.lnzt.covtracker.exceptions;

import me.lnzt.covtracker.pixelprocess.Coordinate;

import java.util.LinkedList;

public class AlternativePathFoundException extends Exception{

    public LinkedList<? extends Coordinate> Path;

    public AlternativePathFoundException(LinkedList<? extends Coordinate> path, String message){
        super(message);
        this.Path = path;

    }
    public AlternativePathFoundException(LinkedList<? extends  Coordinate> path){
        this.Path = path;

    }
    public AlternativePathFoundException(){
        super();

    }

}
