package me.lnzt.covtracker.exceptions;

public class NoSuchPathsException extends Exception{


    public NoSuchPathsException(String message){
        super(message);
    }

    public  NoSuchPathsException(){
        this("No such paths found!");
    }


}
