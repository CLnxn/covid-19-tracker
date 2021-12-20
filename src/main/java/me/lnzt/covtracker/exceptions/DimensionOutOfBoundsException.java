package me.lnzt.covtracker.exceptions;

public class DimensionOutOfBoundsException extends Exception{

    public DimensionOutOfBoundsException(String message){
        super(message);
    }

    public  DimensionOutOfBoundsException(){
        this("negative length is not permitted!");
    }


}
