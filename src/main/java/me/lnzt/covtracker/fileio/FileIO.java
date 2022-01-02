package me.lnzt.covtracker.fileio;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import me.lnzt.covtracker.countryconfig.Country;

import java.io.*;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.LinkedList;

public class FileIO {

    Type type;
    String DATA = "src/main/resources/countries.json";
    String DATA_ORIGINAL = "src/main/resources/origin.json"; // contains all the raw country data (location, shape, etc.).

    public FileIO(){
        type = new TypeToken<LinkedList<Country>>(){}.getType();
        if(type == null){
            throw new NullPointerException("t is null");
        }
        File data = new File(DATA);
        if(!data.exists()){
            try {
                data.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


    public FileIO(Type t){
        type = t;
        if(type == null){
            throw new NullPointerException("t is null");
        }

    }


    public synchronized void writeToFile(LinkedList<Country> countries) throws IOException {
        if(countries != null) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

            FileWriter fw = new FileWriter(DATA);
            gson.toJson(countries, type, fw);
            fw.close();

        } else{
            throw new NullPointerException("Unable to write to file due to null deserialisation.");
        }


    }



    public LinkedList<Country> readFromFile(String filePath) throws Exception{

        Gson gson = new Gson();
        FileReader fr = new FileReader(filePath);
        LinkedList<Country> deserialised = gson.fromJson(fr, type);
    if(deserialised == null){
        throw new Exception("Deserialised value is null.");
    } else{
        return deserialised;
    }



    }

    public Object parseString(String s){
        Object parsedObj = new Gson().fromJson(s, type);
        return parsedObj;
    }






}
