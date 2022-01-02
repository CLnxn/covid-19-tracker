package me.lnzt.covtracker.async;

import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import me.lnzt.covtracker.CovTracker;
import me.lnzt.covtracker.countryconfig.CountrydataParser;
import me.lnzt.covtracker.fileio.FileIO;
import me.lnzt.covtracker.storables.GlobalData;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import javax.swing.*;
import java.io.File;
import java.util.List;

public class BackgroundWorker extends SwingWorker {

    private boolean isRunning = false;
    private CovTracker parentJFrame;

    public BackgroundWorker(CovTracker parentJFrame){
        isRunning = true;
        this.parentJFrame = parentJFrame;

    }

    @Override
    protected String doInBackground() throws Exception {
      //sleep for a constant unit time and then retrieve api req
        int i =0;
        while (isRunning){
            Thread.sleep(2*1000);
            i+= 2;
            System.out.println("doing");
            OkHttpClient client = new OkHttpClient().newBuilder().build();
            Request req = new Request.Builder().url("https://covid-19.dataflowkit.com/v1").method("GET",null).build();
            Response res = client.newCall(req).execute();

            String content = res.body().string();
            FileIO fileio = new FileIO(new TypeToken<List<JsonObject>>(){}.getType());

            Object parsed = fileio.parseString(content);
            List<JsonObject> resBody = null;
            if(parsed instanceof List){
                resBody = (List<JsonObject>) parsed;
            }
            parentJFrame.mainData.updateCountries(resBody);
            System.out.println("done doing");

            if(i == 60*5){
                i = 0;
                FileIO autoSave = new FileIO();
                if(parentJFrame.mainData.Countries != null && parentJFrame.mainData.Countries.size() != 0) {
                    autoSave.writeToFile(parentJFrame.mainData.Countries);
                } else{
                    throw new NullPointerException("Unable to autoSave data.");

                }
                System.out.println("auto saved.");
            }

            //isRunning = false; //runs once only
        }
        //also need to schedule a task for periodically writing data to file for saving in case of sudden application exit.

        return null;
    }


    @Override
    protected void done() {
       //update gui


    }
    public void setCancelled(boolean cancelled){
        this.isRunning = cancelled;
    }
}
