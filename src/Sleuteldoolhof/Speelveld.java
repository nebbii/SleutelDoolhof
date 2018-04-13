package Sleuteldoolhof;

import java.awt.*;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

public class Speelveld {
    int vlakbreedte;
    int vlakhoogte;
    JFrame veldframe;
    JLabel[][] vlakjlabel;

    public static void main(String[] args) {
        int vlakbreedte = 10;
        int vlakhoogte = 10;

        /* Jframe (grafisch) create begin */
            JFrame veldframe = new JFrame("Sleutel Doolhof");
            JPanel panel = new JPanel();
            
            // pad maken voor de afbeeldingen
            File filepath = new File("src/sleuteldoolhof");
            String dir = filepath.getAbsolutePath();
            String imgpath = dir+"/Images/";

            // load images
            // Image
            ImageIcon ImgVasteMuur  = new ImageIcon(dir+"/Images/VasteMuur.png");
            ImageIcon ImgLeegvlak   = new ImageIcon(dir+"/Images/LeegVlak.png");
            ImageIcon ImgSpeler     = new ImageIcon(dir+"/Images/speler.png");
            ImageIcon ImgEindvlak   = new ImageIcon(dir+"/Images/Eindvlak.png");
            
            panel.setLayout(new GridLayout(vlakhoogte+2, vlakbreedte, 5, 5));
            panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        /* jframe (grafisch) create einde */
        Speler speler = new Speler(0,0);
        
        Vlak[][] vlakGrid = loadPuzzle1();
        
        vlakGrid[1][2].objects.add(new Speler(1,2));
        
        veldframe.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                /**
                 * left=37
                 * up=38
                 * right=39
                 * down=40
                 */
                switch(e.getKeyCode()) {
                    case 37: // left
                        System.out.println("Left pressed");
                        break;
                    case 38: // up
                        System.out.println("Up Pressed");
                        break;
                    case 39: // right
                        System.out.println("Right pressed");
                        break;
                    case 40: // down
                        System.out.println("Down pressed");
                        break;
                    default:
                }
                renderCmd(vlakGrid);
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        
        // maak vlakken
        
        // start jframe
        veldframe.add(panel);
        
        veldframe.setVisible(true);
        veldframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        veldframe.setSize(500, 650);
   }  

   public static void renderCmd(Vlak[][] vlakGrid) {
       for(int i=0;i<10;i++) {     
           for(int j=0;j<10;j++) {
               System.out.print(vlakGrid[i][j].returnTopContent());
           }
           System.out.println("|");
       } 
   }
    
   public static Vlak[][] loadPuzzle1() {
       Vlak[][] grid = new Vlak[10][10]; 

       for(int i=0;i<10;i++) {     
           for(int j=0;j<10;j++) {
               grid[i][j] = new Vlak(i, j);
               
           }
       }
       
       grid[0][0].objects.add(new Barricade(10,true));
       grid[0][1].objects.add(new Barricade(10,true));
       grid[0][2].objects.add(new Barricade(10,true));
       grid[0][3].objects.add(new Barricade(10,true));
       
       return grid;
   }
}
