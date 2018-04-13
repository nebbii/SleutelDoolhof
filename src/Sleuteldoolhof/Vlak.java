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
}
