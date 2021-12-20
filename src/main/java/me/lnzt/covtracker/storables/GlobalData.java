package me.lnzt.covtracker.storables;

import me.lnzt.covtracker.CovTracker;
import me.lnzt.covtracker.fileio.FileIO;

import java.awt.*;
import java.util.HashMap;
import java.util.Locale;

public class GlobalData {

    public ConfigPayload configPayload;
    public HashMap<String, CountryNode> COUNTRIES;
    public Dimension configDimension;

    private CovTracker parentFrame;

    public GlobalData(CovTracker parentFrame){
        this.parentFrame = parentFrame;
    }
    /**
     * to be called once only at the start of the program
     */
    public void initCountries() {


        String[] ISO_3166_2 = Locale.getISOCountries();

        FileIO fileHandler = new FileIO();
        try {
           configPayload = fileHandler.readFromFile();
           COUNTRIES = configPayload.COUNTRIES;
           configDimension = configPayload.configDimension;
            if(COUNTRIES == null){
                throw new Exception();
            }
        } catch (Exception e) {
            //e.printStackTrace();
            COUNTRIES = new HashMap<>();

        }

        if (COUNTRIES.isEmpty()) {
            System.out.println("countries is empty");
        for (String C_CODE : ISO_3166_2) {
            //Locale countryLocale = new Locale("", C_CODE);
            COUNTRIES.put(C_CODE, new CountryNode(C_CODE));


        }
    }



    }

    public boolean find(String countryCode){
        countryCode = countryCode.toUpperCase();
      if(!COUNTRIES.containsKey(countryCode)){
          return false;
      }

          return true;

    }

    public void updateCountry(String countryCode, Point location) throws IllegalAccessException{
        countryCode = countryCode.toUpperCase();
        if(!find(countryCode)){
            throw new IllegalAccessException("no such country with code '" + countryCode +"' !");
        }
        CountryNode country = COUNTRIES.get(countryCode);
        country.CENTER = location;
        COUNTRIES.replace(countryCode, country);
        parentFrame.bgPanel.LPHandler.getLabelHandler().updateLabel(countryCode, location);
    }

}
