package Sleuteldoolhof;

import java.util.*;

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
           if(vlakobj instanceof Barricade) {
               top = 'B';
           }
           else if(vlakobj instanceof Speler) {
                top = 'S';
           } else {
               top = '.';
           }
       }
       return top;
    }
}
