package me.lnzt.covtracker.pixelprocess;

import me.lnzt.covtracker.exceptions.AlternativePathFoundException;
import me.lnzt.covtracker.exceptions.NoSuchPathsException;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

public class OutlineProcessing {



    private ArrayList<Coordinate> bPixels = new ArrayList<>();
    private LinkedList<Coordinate> sorted_Pixels = new LinkedList<>();




    private BufferedImage bgImg;

    public OutlineProcessing(BufferedImage bgImg){
        this.bgImg = bgImg;
        processPixels();


    }


    private void processPixels(){

        //ArrayList<ArrayList<Coordinate>> bitMap = new ArrayList<>();
        int run = 0; //breakpoint setter


        for(int y = 0; y < bgImg.getHeight(); y++){
            for(int x = 0; x< bgImg.getWidth();x++){
                int pix = bgImg.getRGB(x,y);

                int a= (pix >> 24) & 0xff;
                int r= (pix >> 16) & 0xff;
                int g= (pix >> 8) & 0xff;
                int b = pix & 0xff;

                if(a > 200 && (r< 10 && g <10 && b <10)) {
                    // System.out.print("[ " + a + ", " + r + ", " + g + ", " + b + " ], ");
                    bPixels.add(new Coordinate(x,y));
                }
            }
        }



        for(int y = 0; y < bgImg.getHeight(); y++){

            ArrayList<Coordinate> bucket = new ArrayList<>();
            for(int x =0; x < bgImg.getWidth(); x++ ){
                //rowy,colx

                    int pix = bgImg.getRGB(x,y);
                    int a= (pix >> 24) & 0xff;
                    int r= (pix >> 16) & 0xff;
                    int g= (pix >> 8) & 0xff;
                    int b = pix & 0xff;
                    //select only reasonably black pixels
                    if(a > 200 && (r< 10 && g <10 && b <10)) {
                       // System.out.print("[ " + a + ", " + r + ", " + g + ", " + b + " ], ");
                        bucket.add(new Coordinate(x,y));

                    }
            }
            //smallest to largest from left(0) to right(size-1)
            ArrayList<Coordinate> xsorted_list = x_bSort(bucket, String.valueOf(bgImg.getWidth()).length());
            for(int i =0; i < xsorted_list.size()-1; i++){
                Coordinate c1 = xsorted_list.get(i);
                Coordinate c2 = xsorted_list.get(i+1);

                if(Math.abs(c1.x-c2.x) > 500 && run <1 ){
                    try {

                        Shape outline = findAllPaths(c1,c2);

                        outline.highlight(Color.CYAN);
                        run++;
                    } catch (NoSuchPathsException e) {
                        //e.printStackTrace();
                        //System.err.println("no path linking c1("+c1.x+", "+ c1.y+" ) to c2("+ c2.x+", "+c2.y +" ).");
                    }
                }
            }

          //  bitMap.add((xsorted_list));
            /*
            System.out.print("[");
            for(int i =0; i < sorted_list.size(); i++){

                System.out.print(sorted_list.get(i).x+", ");

            }
            System.out.print("]");
            */

        }
        System.out.println("run ="+ run) ;
        System.out.println("bpixel size: "+bPixels.size());
        System.out.println("height: "+ bgImg.getHeight() + ", width: "+ bgImg.getWidth());

       //Shape s = new Shape(bPixels);
       //s.highlight(Color.CYAN);
    }
        // in context of row major access, maxPlace = String.valueOf(bgImg.getWidth()).length;
    private ArrayList<Coordinate> x_bSort(ArrayList<Coordinate> coordinates, int maxPlace){
        ArrayList<Queue<Coordinate>> buckets = new ArrayList<>();

        for(int i =0; i < 10; i++){
            buckets.add(new LinkedList<>());
        }

        for(int i = 1; i <= maxPlace; i++ ) {

            for (int j = 0; j < coordinates.size(); j++) {
                String number = String.valueOf(coordinates.get(j).x);
                if(number.length() >= i){
                   int index = Integer.parseInt(String.valueOf(number.charAt(number.length()-i)));

                    buckets.get(index).add(coordinates.get(j));
                    coordinates.remove(j);
                    j--;
                }
            }
                    for (int j = 0; j < buckets.size(); j++) {
                        for (int k = 0; k < buckets.get(j).size(); k++) {

                            coordinates.add(buckets.get(j).poll());
                            k--;
                        }
                }
        }
        return coordinates;
    }



    private Shape findAllPaths(Coordinate startPt, Coordinate target) throws NoSuchPathsException {
        LinkedList<Coordinate> totalTraversed = new LinkedList<>();
            while (true){
                try {

                    LinkedList<Coordinate> path = pathFinder(startPt, target, totalTraversed);
                    System.out.print("path: [");
                    for(int i =0; i < path.size(); i++){

                        System.out.print("( "+path.get(i).x+", "+ path.get(i).y+" )");

                    }
                    System.out.print("] \n");
                    for(Coordinate c : path){
                        boolean contains = false;
                        for(Coordinate traversed : totalTraversed){

                           if(traversed.x == c.x && traversed.y == c.y){
                               contains = true;
                               break;
                           }
                       }
                        if(!contains){
                            totalTraversed.add(c);
                        }
                   }


                }catch (IllegalAccessException e){
                    //no more paths between startPt and target

                    break;

                }

            }
            if(totalTraversed.size() == 0){
                throw new NoSuchPathsException();
            } else{

            System.out.print("totaltraversed: [");
            for(int i =0; i < totalTraversed.size(); i++){

                System.out.print("( "+totalTraversed.get(i).x+", "+ totalTraversed.get(i).y+" )");

            }
            System.out.print("] \n");

            }

        return new Shape(totalTraversed);
    }


    private LinkedList<Coordinate> pathFinder(Coordinate startPt, Coordinate target) throws IllegalAccessException{
        return pathFinder(startPt,target, null);
    }

    private LinkedList<Coordinate> pathFinder(Coordinate startPt, Coordinate target, LinkedList<Coordinate> traversedPath) throws IllegalAccessException {
        if(traversedPath == null) {
            traversedPath = new LinkedList<>();
        }
        int odd_square_len = 3;
        Coordinate queryPt = startPt; //current point variable
        int k =0;

        while (true){
            LinkedList<Coordinate> comparator = new LinkedList<>();

            for(int j = -(odd_square_len-1)/2; j <= (odd_square_len-1)/2; j++){
                for(int i = -(odd_square_len-1)/2; i <= (odd_square_len-1)/2; i++ ){

                    if(i ==0 && j ==0){
                        continue;
                    }
                    Coordinate c = new Coordinate(queryPt.x+i, queryPt.y+j);
                    if(isDark(bgImg.getRGB(c.x,c.y)) && !containsCoords(traversedPath, c)){
                        comparator.addLast(c);
                        //System.out.println("adding to comparator");

                    }

                }
            }
            //System.out.println("comp size= "+comparator.size());



            if(comparator.isEmpty()){
                throw new IllegalAccessException("Path not found.");

            }else{
                //System.out.println("comparator not null.");
            }


            Coordinate nextPt = null;

            try {
                nextPt = getClosest(comparator, target);
            } catch (AlternativePathFoundException e) {
                e.printStackTrace();
            }



            traversedPath.add(nextPt);      //add only the Pts after startPt to traversed

            if(nextPt.x == target.x && nextPt.y == target.y){
                break;            //ensures targetPt is not added to traversed
            }

            queryPt = nextPt;
            comparator.clear();
            k++;
        }

        System.out.println("Start: ("+startPt.x+", "+startPt.y+")");
        System.out.println("End: ("+target.x+", "+target.y+")");
        System.out.println("k = "+k);
            return traversedPath;

    }

    private boolean containsCoords(Collection<Coordinate> data, Coordinate c){
       for(Coordinate coord : data){
           if(coord.x == c.x && coord.y == c.y){
               return true;
           }
       }
       return false;

    }

    private boolean isDark(int pix){

        int a= (pix >> 24) & 0xff;
        int r= (pix >> 16) & 0xff;
        int g= (pix >> 8) & 0xff;
        int b = pix & 0xff;
        //select only reasonably black pixels
        if(a > 200 && (r< 10 && g <10 && b <10)) {
            // System.out.print("[ " + a + ", " + r + ", " + g + ", " + b + " ], ");
            return true;

        }else{ return false;}

    }

    private Coordinate getClosest(LinkedList<Coordinate> dataset, Coordinate target) throws AlternativePathFoundException {

        Coordinate closestPt = null;
        double closestDist = bgImg.getWidth();
        for(Coordinate pt : dataset){
            double dx_sqr = (pt.x- target.x)*(pt.x- target.x);
            double dy_sqr = (pt.y- target.y)*(pt.y- target.y);
            double dist = Math.sqrt(dy_sqr+dx_sqr);
            if(closestDist >= dist){
                closestDist = dist;
                closestPt = pt;
            }
            /*
            else if(closestDist == dist){
                System.out.println("same dist");
                //indeterminate which path is shorter, thus recursion is used.
                try{
                    LinkedList<Coordinate> alternativePath = pathFinder(pt,target, traversed);
                    throw new AlternativePathFoundException(alternativePath);


                }catch (IllegalAccessException e){
                    //means path ends without reaching the target, for e.g. a dead end.


                }
            }

             */
        }
        return closestPt;


    }


    private ArrayList<Coordinate> x_qSort(ArrayList<Coordinate> coordinates){


        return coordinates;
    }


}
