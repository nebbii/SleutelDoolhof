
package Sleuteldoolhof;

public class Speler extends VlakObject {
    int huidigeSleutel;

    public Speler(int x, int y) {
        this.setXpos(x);
        this.setYpos(y);
        this.setHuidigeSleutel(0);
    }
 
    public int getHuidigeSleutel() {
        return huidigeSleutel;
    }

    public void setHuidigeSleutel(int huidigeSleutel) {
        this.huidigeSleutel = huidigeSleutel;
    }
    
}
