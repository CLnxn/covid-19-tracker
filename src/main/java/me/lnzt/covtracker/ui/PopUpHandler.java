package me.lnzt.covtracker.ui;

import me.lnzt.covtracker.countryconfig.CovData;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class PopUpHandler {


    JPanel PopUpContents;
    Dimension KVDim;
    Dimension RDim;

    public PopUpHandler(CovData data){
        KVDim = new Dimension(100,25);
        RDim = new Dimension(KVDim.width*2,KVDim.height);
        if(data == null){
            data = new CovData("","","","","");
        }
try {
    PopUpContents = new JPanel();
    PopUpContents.setSize(new Dimension(RDim.width, RDim.height*data.getClass().getDeclaredFields().length));
    PopUpContents.setLayout(new GridLayout(5,2));


    List<JLabel> labels = new LinkedList<>();

    labels.add(new JLabel("Population: "));
    labels.add(new JLabel((data.POPULATION.equals(""))?"No data":data.POPULATION));

    labels.add(new JLabel("Cases: "));
    labels.add(new JLabel((data.TOTAL_INFECTIONS.equals(""))?"No data":data.TOTAL_INFECTIONS));

    labels.add(new JLabel("Active Cases: "));
    labels.add(new JLabel((data.ACTIVE_CASES.equals(""))?"No data":data.ACTIVE_CASES));

    labels.add(new JLabel("Deaths: "));
    labels.add(new JLabel((data.TOTAL_DEATHS.equals(""))?"No data":data.TOTAL_DEATHS));

    labels.add(new JLabel("Recovered: "));
    labels.add(new JLabel((data.RECOVERED.equals(""))?"No data":data.RECOVERED));

    for(JLabel lbl : labels){
        lbl.setForeground(Color.cyan);
       PopUpContents.add(lbl);
    }
    labels.clear();


    PopUpContents.setBackground(Color.BLACK);






}catch (Exception e){
    e.printStackTrace();


}


    }


public JPanel getInitContents(){
        return this.PopUpContents;
}



}
