package me.lnzt.covtracker.countryconfig;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.Scanner;

public class CountrydataParser {


    public LinkedList<Country> countries;
    File f = new File("src/test/resources/countries.json");
    Type t;

    public CountrydataParser(File input) throws Exception {
            t = new TypeToken<LinkedList<Country>>() {
            }.getType();
            if (!f.exists()) {
                f.createNewFile();
            }
            if(!input.getPath().endsWith(".txt")){
                throw new CountryParserException("parsed file is not of extension type '.txt'!");
            }
            countries = new LinkedList<>();
            Scanner scanner = new Scanner(input);

            while (scanner.hasNextLine()){

                String datum = scanner.nextLine();

                if (datum.startsWith("EOF")){
                    scanner.close();
                    break;
                }
                String[] arr = datum.split("\\s+");


                String tag = arr[0];
                try {
                    double latitude = Double.valueOf(arr[1]);
                    double longitude = Double.valueOf(arr[2]);
                    String ctyName = "";
                    for (int i = 3; i < arr.length; i++) {
                        if(i > 3){
                            ctyName = ctyName.concat(" ");
                        }
                        ctyName = ctyName.concat(arr[i]);
                    }

                    countries.addLast(new Country(tag, latitude, longitude, ctyName));
                } catch (NumberFormatException e) {
                    // simply means lattitude/longitude is not a number.

                    continue;
                }


            }
            System.out.println(countries.size());
            toJson();

        }



    private void toJson() throws Exception {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            FileWriter fw = new FileWriter(f);

            gson.toJson(countries,t,fw);
            fw.close();
    }




    }
