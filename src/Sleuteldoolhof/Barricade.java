package Sleuteldoolhof;

public class Barricade extends VlakObject {
    int slotwaarde;
    boolean staat;
    
    public Barricade(int sw, boolean st) {
        this.slotwaarde = sw;
        this.staat = st;
    }
}
