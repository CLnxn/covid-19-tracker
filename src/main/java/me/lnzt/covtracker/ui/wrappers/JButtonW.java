package me.lnzt.covtracker.ui.wrappers;

import me.lnzt.covtracker.ui.ButtonHandler;

import javax.swing.*;

public class JButtonW extends JButton {

    private ButtonHandler handler;
    /**
     * identifiers are loose terms for users to separate out their own buttons. Defaults to text.
     */
    private String identifier;
    private JDialog parent;



        public JButtonW(ButtonHandler handler){
            super();
            this.handler = handler;


        }
        public JButtonW(ButtonHandler handler, String text){
            super(text);
            this.identifier = text;
            this.handler = handler;

        }
        public JButtonW(ButtonHandler handler, String text, ImageIcon ic){
            super(text,ic);
            this.identifier = text;
            this.handler = handler;

        }
        public JButtonW(JDialog parent){
            super();
            this.parent = parent;
        }


        public ButtonHandler getHandler(){
            return handler;

        }

        public void setIdentifier(String id){
            this.identifier = id;
        }

        public String getIdentifier(){
            return  identifier;
        }



}
