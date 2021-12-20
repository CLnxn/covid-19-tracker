package me.lnzt.covtracker.fileio;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import me.lnzt.covtracker.storables.ConfigPayload;
import me.lnzt.covtracker.storables.CountryNode;

import java.io.*;
import java.lang.reflect.Type;
import java.util.HashMap;

public class FileIO {

    Type type;
    String DATA = "src/main/resources/datap.json";
    String DATA_ORIGINAL = "src/main/resources/origin.json"; // contains all the raw country data (location, shape, etc.).

    public FileIO(){
        type = new TypeToken<ConfigPayload>(){}.getType();
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

    public void writeToFile(ConfigPayload cfgP) throws Exception {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FileWriter fw = new FileWriter(DATA);

        gson.toJson(cfgP, type, fw);
        fw.close();

    }


    public ConfigPayload readFromFile() throws Exception {
        Gson gson = new Gson();
        FileReader fr = new FileReader(DATA);
        ConfigPayload deserialised = gson.fromJson(fr,type);
        return deserialised;


    }




}
