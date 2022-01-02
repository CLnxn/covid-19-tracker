package me.lnzt.covtracker.ui.wrappers;


import me.lnzt.covtracker.countryconfig.Country;
import me.lnzt.covtracker.ui.ControlPanel;



import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;


public class SearchField extends JTextField implements KeyListener, MouseListener, FocusListener{

    private ArrayList<Country> toFind;
    private Popup resultsList = null;
    private ControlPanel parentPane;
    private String liveText ="";



    /**
     * creates a searchBar with size 0. setPreferredSize() can be called externally.
     * @param toFind the list to be searched by the component.
     */
        public SearchField(Collection<Country> toFind, ControlPanel parentPane){
            super();
            this.parentPane = parentPane;
            this.toFind = new ArrayList<>();
            this.toFind.addAll(toFind);
            this.addKeyListener(this);
            this.addFocusListener(this);

        }


        private LinkedList<Country> getResultBucket(String liveText){
            LinkedList<Country> resultsBucket = new LinkedList<>();

            for(Country c : toFind){
                if((c.ISO31662TAG.toLowerCase().contains(liveText) || c.countryName.toLowerCase().contains(liveText)) && !c.ISO31662TAG.equalsIgnoreCase("world")){
                    resultsBucket.add(c);
                }
            }
          return resultsBucket;

        }

        private void createPopUp(int maxResults, String liveText){
            try{
                LinkedList<Country> resultsBucket = getResultBucket(liveText);

                if (resultsList != null) {
                    resultsList.hide();
                }


                //configuring the searchResult query to be shown.

                maxResults = (maxResults <= resultsBucket.size()) ? maxResults : resultsBucket.size();
                JPanel contents = new JPanel();
                contents.setPreferredSize(new Dimension(this.getWidth(), this.getHeight() * (maxResults+1)));
                contents.setLayout(new FlowLayout());
                for (int i = 0; i < maxResults; i++) {
                    Country c = resultsBucket.get(i);

                    JLabelW result = new JLabelW(c.countryName + ", " + c.ISO31662TAG, c.ISO31662TAG);
                    result.setPreferredSize(this.getSize()); // result labels are of the same size as the SearchField.
                    result.addMouseListener(this);
                    contents.add(result);
                }
                if(contents.getComponents().length ==0){
                    JLabel noResultLabel = new JLabel("No results found.");
                    noResultLabel.setPreferredSize(this.getSize());
                    contents.add(noResultLabel);
                }
                PopupFactory pFactory = new PopupFactory();
                Point screenLocation = this.getLocationOnScreen();
                this.resultsList = pFactory.getPopup(null, contents, screenLocation.x, screenLocation.y + this.getHeight()); //owner location is dependent on setBounds() call.

                resultsList.show();
            }catch (Exception e){
                e.printStackTrace();
            }
        }



    @Override
    public void keyTyped(KeyEvent e) {

            try {



                if (e.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
                    System.out.println("action key");
                    liveText = this.getText().toLowerCase();
                } else {
                    liveText = (this.getText() + e.getKeyChar()).toLowerCase();
                }
                System.out.println("text typed: " + liveText);

                if (liveText.equalsIgnoreCase("")) {
                    if(resultsList !=null){
                        resultsList.hide();
                    }
                    return;
                }
                    createPopUp(5,liveText);

            }catch (Exception ex){
                ex.printStackTrace();
            }

    }
    @Override
    public void paintComponent(Graphics g){ super.paintComponent(g); }

    // triggered before keyTyped
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyChar() == KeyEvent.VK_ESCAPE){
            if(e.getSource() instanceof SearchField){
                SearchField sf = (SearchField) e.getSource();
                sf.parentPane.getParentPane().grabFocus();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }


    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("is clicked");
        if(e.getSource() instanceof JLabelW){
           JLabelW lbl = (JLabelW) e.getSource();
           for(Country c : toFind){
               if(lbl.getId().equalsIgnoreCase(c.ISO31662TAG)){
                   for(Component component : this.parentPane.getParentPane().getComponents()){
                       if(component instanceof JCustomPane && ((JCustomPane) component).getTag().equalsIgnoreCase(c.ISO31662TAG)){
                           JCustomPane jcp = (JCustomPane) component;
                           jcp.highlight(true);
                       }
                   }

                   break;
               }
           }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {


    }

    @Override
    public void focusGained(FocusEvent e) {
        createPopUp(5,liveText);
    }

    @Override
    public void focusLost(FocusEvent e) {
        if(e.getSource() instanceof SearchField){
            if(resultsList !=null){
                synchronized (resultsList){
                    this.resultsList.hide();
                }
            }


        }
    }
}



