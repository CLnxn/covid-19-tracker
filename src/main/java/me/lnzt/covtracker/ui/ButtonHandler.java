package me.lnzt.covtracker.ui;

import me.lnzt.covtracker.ui.listeners.ButtonListener;
import me.lnzt.covtracker.ui.wrappers.JButtonW;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class ButtonHandler {

    private JButtonW configButton;
    private ArrayList<Component> buttons;
    private JFrame parentF;
    private JPanel parentP;
    private JDialog parentD;
    ButtonListener bList;


    public Dimension defDim;

    public enum PARENT_TYPE{
        JFrame,JPanel, JDialog

    }
    public ButtonHandler(Dimension bDim, boolean initAll){
        bList = new ButtonListener();
        buttons = new ArrayList<>();
        this.defDim = bDim;
        if(initAll){
            initButtons();
        }


    }




    public void setParent(JFrame parent){
        this.parentF = parent;
    }
    public void setParent(JDialog parent){
        this.parentD = parent;
    }
    public void setParent(JPanel parent){
        this.parentP = parent;


    }
    public Object getParent(PARENT_TYPE parentType) throws EnumConstantNotPresentException{
            switch (parentType){
                case JFrame:
                    return parentF;
                case JPanel:
                    return parentP;
                case JDialog:
                    return parentD;

            }
            throw new EnumConstantNotPresentException(PARENT_TYPE.class, parentType.toString());

    }



    //TODO: zoom in/zoom out of region buttons
    private void initButtons(){
        initShowHideButton();
        initConfigButton();
        initdeselectButton();


    }
    public JButtonW initSubmitButton(){
        JButtonW submit = initDefButton("submit");

        buttons.add(submit);
        return submit;
    }
    public JButtonW initShowHideButton(){
        JButtonW sh = initDefButton("Hide All");


        sh.setIdentifier("sh");
        buttons.add(sh);
        return sh;
    }
    public JButtonW initConfigButton(){


        this.configButton = initDefButton("Config");

        buttons.add(configButton);
        return configButton;
    }

    private void initdeselectButton(){

       JButton deselect = initDefButton("deselect");
        buttons.add(deselect);


    }

    public JButtonW initDefButton(String text, ImageIcon ic){
        JButtonW jb;
        jb = new JButtonW(this, text);
        if(ic != null){
            jb = new JButtonW(this, text, ic);
        }else {
            jb = new JButtonW(this, text);
        }

        jb.setPreferredSize(defDim);

        jb.addMouseListener(bList);
        jb.addKeyListener(bList);
        jb.setPreferredSize(defDim);
        jb.setBackground(Color.gray);
        jb.setOpaque(false);
        return jb;
    }


    public JButtonW initDefButton(String text){
        return initDefButton(text, null);
    }


    public List<Component> getButtons (){

        return buttons;
    }





}
