package Sleuteldoolhof;

import java.util.*;

// source voor objects: https://docs.oracle.com/javase/7/docs/api/java/util/ArrayList.html
public class Vlak {
    int xpos;
    int ypos;
    public ArrayList<VlakObject> objects;

    public Vlak(int x, int y) {
        this.xpos = x;
        this.ypos = y; 
        objects = new ArrayList<>();
    }
    
    public char[][] renderPlayfieldCmd(Vlak[][] grid, int vb, int vh) {
        char[][] cmdreturn = new char[vb][vh];
        
        return cmdreturn;
   }
    
   /**
    * Geeft het bovenste object terug in char waarde (voor cmd)
    * 
    * @return 
    */
   public char returnTopContent() {
       char top = '.';
       for(VlakObject vlakobj : objects) {
           if(vlakobj instanceof Speler) {
                top = 'S';
           }
           if(vlakobj instanceof Barricade) {
               top = 'B';
           }
           if(vlakobj instanceof VasteMuur) {
                top = 'V';
           }
           if(vlakobj instanceof Eindveld) {
                top = 'E';
           }
           if(vlakobj instanceof Sleutel) {
                top = 's';
           }
           
       }
       return top;
    }
   
    public int getXpos() {
        return xpos;
    }

    public void setXpos(int xpos) {
        this.xpos = xpos;
    }

    public int getYpos() {
        return ypos;
    }

    public void setYpos(int ypos) {
        this.ypos = ypos;
    }

    public ArrayList<VlakObject> getObjects() {
        return objects;
    }

    public void setObjects(ArrayList<VlakObject> objects) {
        this.objects = objects;
    }
}
