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
    static Vlak[][] vlakGrid;
    
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
        Speler speler = new Speler(1,1);
        
        loadPuzzle1(speler);
        
        veldframe.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                /**
                 * https://stackoverflow.com/a/31637206
                 * left=37
                 * up=38
                 * right=39
                 * down=40
                 */
                int x = speler.getXpos();
                int y = speler.getYpos();
                int[] arrows = new int[4];
                arrows[0] = 37; arrows[1] = 38; arrows[2] = 39; arrows[3] = 40;
                
                switch(e.getKeyCode()) {
                    case 37: // left
                        System.out.println("Left pressed");
                        if(checkInBounds(x-1,y, vlakbreedte, vlakhoogte)) {
                            if(vlakGrid[x-1][y].returnTopContent()=='B') {
                                System.out.println("Boop!");
                            } else {
                                speler.setXpos(x-1);
                                vlakGrid[x][y].objects.remove(speler);
                                vlakGrid[x-1][y].objects.add(speler);
                            }
                        } else {
                            System.out.println("oob!");
                        }
                        break;
                    case 38: // up
                        System.out.println("Up pressed");
                        if(checkInBounds(x,y-1, vlakbreedte, vlakhoogte)) {
                            if(vlakGrid[x][y-1].returnTopContent()=='B') {
                                System.out.println("Boop!");
                            } else {
                                speler.setYpos(y-1);
                                vlakGrid[x][y].objects.remove(speler);
                                vlakGrid[x][y-1].objects.add(speler);
                            }
                        } else {
                            System.out.println("oob!");
                        }
                        break;
                    case 39: // right
                        System.out.println("Right pressed");
                        if(checkInBounds(x+1,y, vlakbreedte, vlakhoogte)) {
                            if(vlakGrid[x+1][y].returnTopContent()=='B') {
                                System.out.println("Boop!");
                            } else {
                                speler.setXpos(x+1);
                                vlakGrid[x][y].objects.remove(speler);
                                vlakGrid[x+1][y].objects.add(speler);
                            }
                        } else {
                            System.out.println("oob!");
                        }
                        break;
                    case 40: // down
                        System.out.println("Down pressed");
                        if(checkInBounds(x,y+1, vlakbreedte, vlakhoogte)) {
                            if(vlakGrid[x][y+1].returnTopContent()=='B') {
                                System.out.println("Boop!");
                            } else {
                                speler.setYpos(y+1);
                                vlakGrid[x][y].objects.remove(speler);
                                vlakGrid[x][y+1].objects.add(speler);
                            }
                        } else {
                            System.out.println("oob!");
                        }
                        break;
                    case 49: // 1
                        Speelveld.loadPuzzle1(speler);
                        break;
                    case 50: // 2
                        
                        break;
                    case 51: // 3
                        
                        break;
                    case 65: // A
                        System.out.println(speler.getXpos()+""+speler.getYpos());
                        break;
                    case 82: // R
                        System.out.println("Game Reset!");
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
               System.out.print(vlakGrid[j][i].returnTopContent());
           }
           System.out.println("|");
       } 
   }
    
   public static void loadPuzzle1(Speler speler) {
       Vlak[][] grid = new Vlak[10][10]; 

       for(int i=0;i<10;i++) {     
           for(int j=0;j<10;j++) {
               grid[i][j] = new Vlak(i, j);
           }
       }
       
       speler.setXpos(1); speler.setYpos(2);
       
       grid[speler.getXpos()][speler.getYpos()].objects.add(speler);
       
       grid[0][0].objects.add(new Barricade(10,true));
       grid[0][1].objects.add(new Barricade(10,true));
       grid[0][2].objects.add(new Barricade(10,true));
       grid[0][3].objects.add(new Barricade(10,true));
       grid[0][4].objects.add(new Barricade(10,true));
       
       grid[9][9].objects.add(new Eindveld());
       
       Speelveld.vlakGrid = grid;
   }
   
    /**
     * Returns true als speler en eindvlak op dezelfde plek staan!
     * 
     * @param eind
     * @param speler
     * @return 
     */
    public static boolean checkWin(Eindveld eind, Speler speler) {
        return ((eind.getXpos()==speler.getXpos()) && (eind.getYpos()==speler.getYpos()));
    }
   
    /**
     * Check if new value is within bounds!
     * 
     * @param x
     * @param y
     * @return 
     */
    public static boolean checkInBounds(int x, int y, int vb, int vh) {
        boolean check = false;
       
        // check horizontal
        if((x >= 0) && (x < vb)) {
            System.out.println("horizontally good!");
            
            // check vertical
            if((y >= 0) && (y < vh)) {
                System.out.println("vertically good!");
                 check = true;
            } else {
                check = false;
            }
                
        } else {
            check = false;
        }

       
       return check;
   }
    
}
