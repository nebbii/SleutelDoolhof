package Sleuteldoolhof;

public class Barricade extends VlakObject {
    
    int slotwaarde;
    boolean staat; // true is heel, false is gebroken
    
    public Barricade(int sw, boolean st) {
        this.slotwaarde = sw;
        this.staat = st;
    }
    
    public int getSlotwaarde() {
        return slotwaarde;
    }

    public void setSlotwaarde(int slotwaarde) {
        this.slotwaarde = slotwaarde;
    }

    public boolean isStaat() {
        return staat;
    }

    public void setStaat(boolean staat) {
        this.staat = staat;
    }
}
