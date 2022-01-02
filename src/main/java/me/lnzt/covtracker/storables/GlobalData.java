package me.lnzt.covtracker.storables;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import me.lnzt.covtracker.CovTracker;
import me.lnzt.covtracker.countryconfig.Country;
import me.lnzt.covtracker.countryconfig.CovData;
import me.lnzt.covtracker.fileio.FileIO;

import java.awt.*;
import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class GlobalData {

    public CovData WORLD;
    public LinkedList<Country> Countries; // contains country: WORLD.
    public Dimension configDimension;

    private CovTracker parentFrame;

    public GlobalData(CovTracker parentFrame){
        this.parentFrame = parentFrame;
        this.configDimension = new Dimension(parentFrame.width, parentFrame.height);
    }
    /**
     * to be called once only at the start of the program
     */

    public void initCountries(String newPath){


        FileIO fio = new FileIO();
        boolean foundWorld = false;
        try {
           Countries = fio.readFromFile(newPath);

        //init scaled coordinates for all of the countries.
        for(Country c : Countries){
            if(c.data == null){
                c.data=  new CovData("", "", "","","");
            }

           if(!c.countryName.equalsIgnoreCase("world")) {
                c.getLocation().mercatorProjection();
                c.getLocation().scaleXY(configDimension);
           } else{
               foundWorld = true;
               this.WORLD = c.data;
           }




        }

        if(!foundWorld){
            this.WORLD = new CovData("1","1","1","?","1");
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
    //code will stop here upon caught exception in reading from file input (null, io etc).

    }


    public void updateCountries(List<JsonObject> content){
        if(content == null){
            throw new NullPointerException("null params");

        }
        LinkedList<String> notFound = new LinkedList<>();

        System.out.println("size = "+ content.size());
        try {
            for (JsonObject obj : content) {
                //System.out.println(obj.toString());
                //System.out.println( obj.get("Country_text").toString());

                List<JsonElement> elements = new LinkedList<>();
                List<String> copyTo = new LinkedList<>();


                elements.add(obj.get("Country_text")); //0
                elements.add(obj.get("Active Cases_text")); //1
                elements.add(obj.get("Total Cases_text")); //2
                elements.add(obj.get("Total Deaths_text")); //3
                elements.add(obj.get("Total Recovered_text")); //4
                elements.add(obj.get("New Deaths_text")); //5

                for(JsonElement element : elements){
                    if(element == null){
                       copyTo.add("");
                    } else{
                        copyTo.add(element.toString().replace("\"", "").replace("N/A", ""));
                    }
                }

                boolean found = false;
                for (int j = 0; j < Countries.size(); j++) {
                    Country c = Countries.get(j);

                    if (c.countryName.equalsIgnoreCase(copyTo.get(0))) {
                        //System.out.println(CountryName);
                        try {
                                c.data = new CovData(copyTo.get(1), copyTo.get(2), copyTo.get(3), "?", copyTo.get(4));

                                if(c.countryName.equalsIgnoreCase("world")) {
                                    this.WORLD = c.data;
                                }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        Countries.remove(j);
                        Countries.add(j, c);
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    notFound.add(copyTo.get(0));
                    //System.out.println(copyTo.get(0));
                    if(copyTo.get(0).equalsIgnoreCase("world")){
                        System.out.println("world not found");
                        Country c = new Country("world");
                        c.data = new CovData(copyTo.get(1), copyTo.get(2), copyTo.get(3), "?", copyTo.get(4));
                        this.Countries.add(c);

                        if(c.countryName.equalsIgnoreCase("world")) {
                            this.WORLD = c.data;
                        }
                    }

                }


            }
            this.parentFrame.bgPanel.LPHandler.getLabelHandler().updateLabel(Countries, WORLD);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("done iterating");







    }



}
