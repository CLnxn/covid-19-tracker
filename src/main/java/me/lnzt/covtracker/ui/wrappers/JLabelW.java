package me.lnzt.covtracker.ui.wrappers;

import javax.swing.*;

public class JLabelW extends JLabel {
    private String id;

    public JLabelW(){
        super();
    }
    public JLabelW(String text){
        super(text);
    }
    public JLabelW(String text, String id){
        super(text);
        this.id = id;
    }

    public String getId(){
        String copyid = id;
        return copyid;
    }


}
