package Sleuteldoolhof;

import java.awt.*;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;

public class Speelveld {
    int vlakbreedte;
    int vlakhoogte;
    JFrame veldframe;
    static JLabel[][] vlakjlabel;
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
            
            System.out.println(imgpath);

            // load images
            // Image
            ImageIcon ImgVasteMuur  = new ImageIcon(imgpath+"VasteMuur.png");
            ImageIcon ImgSleutel  = new ImageIcon(imgpath+"Sleutel.png");
            ImageIcon ImgLeegvlak   = new ImageIcon(imgpath+"LeegVlak.png");
            ImageIcon ImgSpeler     = new ImageIcon(imgpath+"Speler.png");
            ImageIcon ImgEindveld   = new ImageIcon(imgpath+"Eindveld.png");
            
            System.out.println(ImgVasteMuur.toString());
            
            panel.setLayout(new GridLayout(vlakbreedte, vlakhoogte+2, 5, 5));
            panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        
            // create jlabel array
            JLabel vlakjlabel[][] = new JLabel[10][10];

            // place jlabels in grid
            for(int i=0;i < vlakbreedte; i++) {
                for(int j=0; j < vlakhoogte; j++) {
                    vlakjlabel[j][i] = new JLabel(i+"-"+j);
                }
            }
            
            for(int i=0;i < vlakbreedte; i++) {
                for(int j=0; j < vlakhoogte; j++) {
                    panel.add(vlakjlabel[i][j]);
                }
            }
            
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
                        moveSpeler(speler, "left", vlakbreedte, vlakhoogte);
                        break;
                    case 38: // up
                        System.out.println("Up pressed");
                        moveSpeler(speler, "up", vlakbreedte, vlakhoogte);
                        break;
                    case 39: // right
                        System.out.println("Right pressed");
                        moveSpeler(speler, "right", vlakbreedte, vlakhoogte);
                        break;
                    case 40: // down
                        System.out.println("Down pressed");
                        moveSpeler(speler, "down", vlakbreedte, vlakhoogte);
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
                // check voor sleutel
                checkSleutelOppakken(speler);
                
                // check voor win condities
                if(checkWin(speler)) {
                    System.out.println("Doolhof gehaald!!!");
                    loadPuzzle1(speler);
                }
                renderCmd(vlakGrid);
                for(int i=0;i < vlakbreedte; i++) {
                    for(int j=0; j < vlakhoogte; j++) {
                         switch(vlakGrid[i][j].returnTopContent()) {
                            case 'V':
                                vlakjlabel[j][i].setText("V");
                                vlakjlabel[j][i].setIcon(ImgVasteMuur);
                                break;
                            case 'B':
                                vlakjlabel[j][i].setText("B");
                                vlakjlabel[j][i].setIcon(ImgVasteMuur);
                                break;
                            case 'b':
                                vlakjlabel[j][i].setText("b");
                                vlakjlabel[j][i].setIcon(ImgVasteMuur);
                                break;
                            case 'S':
                                vlakjlabel[j][i].setText("S");
                                vlakjlabel[j][i].setIcon(ImgSleutel);
                                break;
                            case 'E':
                                vlakjlabel[j][i].setText("E");
                                vlakjlabel[j][i].setIcon(ImgEindveld);
                                break;
                            case '.':
                                vlakjlabel[j][i].setText(".");
                                vlakjlabel[j][i].setIcon(ImgLeegvlak);
                                break;
                            default:
                        }
                    }
                }
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
   
   /*public static void renderFrame(int vb, int vh) {
       
   }*/
    
   public static void loadPuzzle1(Speler speler) {
       Vlak[][] grid = new Vlak[10][10]; 

       for(int i=0;i<10;i++) {     
           for(int j=0;j<10;j++) {
               grid[i][j] = new Vlak(i, j);
           }
       }
       
       speler.setXpos(1); speler.setYpos(2);
       
       grid[speler.getXpos()][speler.getYpos()].objects.add(speler);
       
       grid[0][0].objects.add(new VasteMuur());
       grid[0][1].objects.add(new VasteMuur());
       grid[0][2].objects.add(new VasteMuur());
       grid[0][3].objects.add(new VasteMuur());
       grid[0][4].objects.add(new VasteMuur());
       
       for(int i=0;i<10;i++) {
           grid[4][i].objects.add(new Barricade(20,true));
       }
       
       grid[2][9].objects.add(new Sleutel(2,2, 20));
       grid[9][9].objects.add(new Eindveld(9,9));
       
       Speelveld.vlakGrid = grid;
   }
   
    /**
     * Returns true als speler en eindvlak op dezelfde plek staan!
     * 
     * @return 
     */
    public static boolean checkWin(Speler speler) {
        boolean win = false;
        
        int speler_x = speler.getXpos();
        int speler_y = speler.getYpos();
        
        for(VlakObject vlakobj : vlakGrid[speler_x][speler_y].objects) {
            if(vlakobj instanceof Eindveld) {
                win = true;
            }
        }
        
        return win;
    }
    
    /**
     * check of speler op een sleutel staat, pak op als dat zo is
     * 
     * @param speler 
     */
    public static void checkSleutelOppakken(Speler speler) {
        int speler_x = speler.getXpos();
        int speler_y = speler.getYpos();
        
        for(VlakObject vlakobj : vlakGrid[speler_x][speler_y].objects) {
            if(vlakobj instanceof Sleutel) {
                System.out.println("Sleutel opgepakt!");
                speler.setHuidigeSleutel(((Sleutel) vlakobj).getWaarde());
            }
        }
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
            
            // check vertical
            if((y >= 0) && (y < vh)) {
                 check = true;
            } else {
                check = false;
            }
                
        } else {
            check = false;
        }

       
       return check;
   }
    
    /**
     * Verplaats speler over speelveld
     * 
     * @param speler
     * @param richting
     * @param vb
     * @param vh 
     */
    public static void moveSpeler(Speler speler, String richting, int vb, int vh) {
        int x; int y; int nx; int ny;
        
        x = nx = speler.getXpos();
        y = ny = speler.getYpos();
        
        switch(richting) {
            case "left":
                nx--;
                break;
            case "up":
                ny--;
                break;
            case "right":
                nx++;
                break;
            case "down":
                ny++;
                break;
        }
        
        if(checkInBounds(nx,ny, vb, vh)) {
            ArrayList<VlakObject> vlakobjects = Speelveld.vlakGrid[nx][ny].getObjects();
            int indexBarricade = -1;
            int indexVasteMuur = -1;
            boolean allowMove = false;
            
            for(VlakObject vlakobj : vlakobjects) {
                if(vlakobj instanceof Barricade) {
                    indexBarricade = vlakobjects.indexOf(vlakobj);
                }
                if(vlakobj instanceof VasteMuur) {
                    indexVasteMuur = vlakobjects.indexOf(vlakobj);
                }
            }
            
            // Tegen barricade, als sleutel waarde klopt breek barricade
            if(indexVasteMuur != -1) {
                System.out.println("Bots tegen muur!");
            }
            else if(indexBarricade != -1) {
                System.out.println("Bots tegen Barricade!");
                
                // source: https://stackoverflow.com/a/10259625
                Barricade b = (Barricade) vlakobjects.get(indexBarricade);
                if(b.getSlotwaarde() <= speler.getHuidigeSleutel()) {
                    b.setStaat(false);
                    allowMove = true;
                } else if (!b.isStaat()) {
                    allowMove = true;
                }
            } else {
                allowMove = true;
            }
            
            // if allowed, move player to new spot
            if(allowMove) {
                speler.setXpos(nx);
                speler.setYpos(ny);
                vlakGrid[x][y].objects.remove(speler);
                vlakGrid[nx][ny].objects.add(speler);
            }
        } else {
            System.out.println("oob!");
        }
    }
    
}
