/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sleuteldoolhof;

/**
 *
 * @author nebbii
 */
public class Sleutel extends VlakObject {
    int waarde;
    
    public Sleutel(int x, int y, int waarde) {
        this.setXpos(x);
        this.setYpos(y);
        this.waarde = waarde;
    }
    
    public int getWaarde() {
        return waarde;
    }

    public void setWaarde(int waarde) {
        this.waarde = waarde;
    }
}
