package me.lnzt.covtracker.ui;

import me.lnzt.covtracker.CovTracker;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ContentPanel extends JPanel {

    private final String IMAGE_PATH = "src/main/resources/mercator.jpg";

    public BufferedImage backgroundImage;
    public LayeredPanel LPHandler;
    public ButtonHandler BHandler;
    private Graphics graphics;
    private CovTracker ParentJframe;





    private int width, height;


    public ContentPanel(int width, int height, CovTracker ParentJframe) throws IOException {
        try {
            this.setLayout(new BorderLayout());
            this.width = width;
            this.height = height;
            this.ParentJframe = ParentJframe;

            //BHandler = new ButtonHandler(new Dimension(100,25));
            //List<Component> buttons = BHandler.getButtons();
            LPHandler = new LayeredPanel(width, height, null, ParentJframe);
            JLayeredPane layeredPane = LPHandler.getLayeredPane();


            backgroundImage = ImageIO.read(new File(IMAGE_PATH));

            this.add(layeredPane, BorderLayout.CENTER);
        }catch (Exception e){
            e.printStackTrace();
        }

    }









    public void paintComponent(Graphics g){
           super.paintComponent(g);

            boolean isFinished = g.drawImage(backgroundImage,0,0,width,height, this);
           // System.out.println(isFinished);

    }




    public void setWidth(int width) {
        this.width = width;
    }
    public int getCurrentWidth(){
        return this.width;
    }

    public void setHeight(int height){
        this.height = height;
    }
    public int getCurrentHeight(){
        return this.height;
    }
    public CovTracker getParentJframe() {
        return ParentJframe;
    }
}
