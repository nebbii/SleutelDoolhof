package Sleuteldoolhof;

import java.awt.*;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;
import java.io.*;
import javax.sound.sampled.*;

public class Speelveld {
    static int vlakbreedte;
    static int vlakhoogte;
    JFrame veldframe;
    static JLabel[][] vlakjlabel;
    static Vlak[][] vlakGrid;
    static int huidigePuzzel;
    
    public static void main(String[] args) {
        int vlakbreedte = 10;
        int vlakhoogte = 10;

        /* Jframe (grafisch) create begin */
            JFrame veldframe = new JFrame("Sleutel Doolhof");
            JPanel panel = new JPanel();
            
            // pad maken voor de afbeeldingen
            File filepath = new File("src/Sleuteldoolhof");
            String dir = filepath.getAbsolutePath();
            String imgpath = dir+"/Images/";

            // load images
            ImageIcon ImgBarricade  = new ImageIcon(imgpath+"Barricade.png");
            ImageIcon ImgBroken     = new ImageIcon(imgpath+"Broken.png");
            ImageIcon ImgVasteMuur  = new ImageIcon(imgpath+"VasteMuur.png");
            ImageIcon ImgSleutel    = new ImageIcon(imgpath+"Sleutel.png");
            ImageIcon ImgLeegvlak   = new ImageIcon(imgpath+"LeegVlak.png");
            ImageIcon ImgSpeler     = new ImageIcon(imgpath+"Speler.png");
            ImageIcon ImgEindveld   = new ImageIcon(imgpath+"Eindveld.png");
            
            panel.setLayout(new GridLayout(vlakbreedte, vlakhoogte+2, 5, 5));
            panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        
            // create jlabel array
            JLabel vlakjlabel[][] = new JLabel[10][10];

            // place jlabels in grid
            for(int i=0;i < vlakbreedte; i++) {
                for(int j=0; j < vlakhoogte; j++) {
                    vlakjlabel[j][i] = new JLabel(ImgVasteMuur);
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
        for(int i=0;i < vlakbreedte; i++) {
                    for(int j=0; j < vlakhoogte; j++) {
                        int waarde = 0; 
                        switch(vlakGrid[i][j].returnTopContent()) {
                            case 'V':
//                                vlakjlabel[j][i].setText("V");
                                vlakjlabel[j][i].setText(null);
                                vlakjlabel[j][i].setIcon(ImgVasteMuur);
                                break;
                            case 'B':
                                for(VlakObject vlakobj : vlakGrid[i][j].objects) {
                                    if(vlakobj instanceof Barricade) {
                                        waarde = ((Barricade) vlakobj).getSlotwaarde();
                                    }
                                }
                                vlakjlabel[j][i].setIcon(ImgBarricade);
                                vlakjlabel[j][i].setText("<html><font color='white'>"+waarde+"</font></html>");
                                vlakjlabel[j][i].setHorizontalTextPosition(JLabel.CENTER);
                                break;
                            case 'b':
                                vlakjlabel[j][i].setText(null);
                                vlakjlabel[j][i].setIcon(ImgBroken);
                                break;
                            case 'S':
                                vlakjlabel[j][i].setText(null);
                                vlakjlabel[j][i].setIcon(ImgSpeler);
                                if(speler.getHuidigeSleutel() > 0) {
                                    vlakjlabel[j][i].setText("<html><font color='white'>"+speler.getHuidigeSleutel()+"</font></html>");
                                    vlakjlabel[j][i].setHorizontalTextPosition(JLabel.CENTER);
                                }
                                break;
                            case 's':
                                for(VlakObject vlakobj : vlakGrid[i][j].objects) {
                                    if(vlakobj instanceof Sleutel) {
                                        waarde = ((Sleutel) vlakobj).getWaarde();
                                    }
                                }
                                vlakjlabel[j][i].setIcon(ImgSleutel);
                                vlakjlabel[j][i].setText("<html><font color='white'>"+waarde+"</font></html>");
                                vlakjlabel[j][i].setHorizontalTextPosition(JLabel.CENTER);
                                break;
                            case 'E':
                                vlakjlabel[j][i].setText(null);
                                vlakjlabel[j][i].setIcon(ImgEindveld);
                                break;
                            case '.':
                                vlakjlabel[j][i].setText(null);
                                vlakjlabel[j][i].setIcon(ImgLeegvlak);
                                break;
                            default:
                        }
                    }
                }
        JOptionPane.showMessageDialog(veldframe, "<html>"
                + "<h1>Welkom bij het Sleutel Doolhof!</h1>"
                + "<b>Verplaats door het doolhof met de pijltoetsen.<br>"
                + "Er zijn twee levels, deze kan je laden met de 1 en 2 toets.<br>"
                + "Je kan opnieuw beginnen met de R toets!<br>"
                + "Succes!<br>"
                + "Gemaakt door Ben Wolthuis"
                + "</html>");
        
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
                        Speelveld.loadPuzzle2(speler);
                        break;
                    case 51: // 3
                        Speelveld.loadPuzzle3(speler);
                        break;
                    case 65: // A
                        System.out.println(speler.getXpos()+""+speler.getYpos());
                        break;
                    case 82: // R
                        reset(speler);
                        break;
                    default:
                }
                // check voor sleutel
                checkSleutelOppakken(speler);
                
                // check voor win condities
                if(checkWin(speler)) {
                    System.out.println("Doolhof gehaald!!!");
                    JOptionPane.showMessageDialog(veldframe, "Doolhof gehaald!");
                    if(huidigePuzzel >= 3) {
                        huidigePuzzel=1;
                        JOptionPane.showMessageDialog(veldframe, "<html>Je hebt het spel uitgespeeld. Gefeliciteerd!<br>Bedankt voor het spelen van mijn spel.</html>");
                    } else {
                        huidigePuzzel++;
                    }
                    reset(speler);
                }
                renderCmd(vlakGrid);
                for(int i=0;i < vlakbreedte; i++) {
                    for(int j=0; j < vlakhoogte; j++) {
                        int waarde = 0; 
                        switch(vlakGrid[i][j].returnTopContent()) {
                            case 'V':
//                                vlakjlabel[j][i].setText("V");
                                vlakjlabel[j][i].setText(null);
                                vlakjlabel[j][i].setIcon(ImgVasteMuur);
                                break;
                            case 'B':
                                for(VlakObject vlakobj : vlakGrid[i][j].objects) {
                                    if(vlakobj instanceof Barricade) {
                                        waarde = ((Barricade) vlakobj).getSlotwaarde();
                                    }
                                }
                                vlakjlabel[j][i].setIcon(ImgBarricade);
                                vlakjlabel[j][i].setText("<html><font color='white'>"+waarde+"</font></html>");
                                vlakjlabel[j][i].setHorizontalTextPosition(JLabel.CENTER);
                                break;
                            case 'b':
                                vlakjlabel[j][i].setText(null);
                                vlakjlabel[j][i].setIcon(ImgBroken);
                                break;
                            case 'S':
                                vlakjlabel[j][i].setText(null);
                                vlakjlabel[j][i].setIcon(ImgSpeler);
                                if(speler.getHuidigeSleutel() > 0) {
                                    vlakjlabel[j][i].setText("<html><font color='white'>"+speler.getHuidigeSleutel()+"</font></html>");
                                    vlakjlabel[j][i].setHorizontalTextPosition(JLabel.CENTER);
                                }
                                break;
                            case 's':
                                for(VlakObject vlakobj : vlakGrid[i][j].objects) {
                                    if(vlakobj instanceof Sleutel) {
                                        waarde = ((Sleutel) vlakobj).getWaarde();
                                    }
                                }
                                vlakjlabel[j][i].setIcon(ImgSleutel);
                                vlakjlabel[j][i].setText("<html><font color='white'>"+waarde+"</font></html>");
                                vlakjlabel[j][i].setHorizontalTextPosition(JLabel.CENTER);
                                break;
                            case 'E':
                                vlakjlabel[j][i].setText(null);
                                vlakjlabel[j][i].setIcon(ImgEindveld);
                                break;
                            case '.':
                                vlakjlabel[j][i].setText(null);
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
        
        // start jframe
        veldframe.add(panel);
        
        veldframe.setVisible(true);
        veldframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        veldframe.setSize(700, 700);
        veldframe.setResizable(false);
   }  

    /**
     * Clear alles
     */
    public static void reset(Speler speler) {
        System.out.println("Game Reset!");
        switch(huidigePuzzel) {
            case 1:
                Speelveld.loadPuzzle1(speler);
                break;
            case 2:
                Speelveld.loadPuzzle2(speler);
                break;
            case 3:
                Speelveld.loadPuzzle3(speler);
                break;
            default:
                Speelveld.loadPuzzle1(speler);
        }
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

        huidigePuzzel = 1;
        
        for(int i=0;i<10;i++) {     
            for(int j=0;j<10;j++) {
                grid[i][j] = new Vlak(i, j);
            }
        }

        speler.setXpos(0); speler.setYpos(0); speler.setHuidigeSleutel(0);

        grid[speler.getXpos()][speler.getYpos()].objects.add(speler);

        // Creating!
        grid[1][0].objects.add(new VasteMuur());
        grid[2][0].objects.add(new Barricade(100, true));

        for(int i=3;i<10;i++) {
            grid[1][i].objects.add(new VasteMuur());
        }
        for(int i=2;i<10;i++) {
            grid[2][i].objects.add(new Barricade(300, true));
        }

        grid[3][6].objects.add(new VasteMuur());
        grid[4][6].objects.add(new VasteMuur());
        grid[5][6].objects.add(new VasteMuur());
        
        grid[5][7].objects.add(new VasteMuur());
        grid[5][8].objects.add(new VasteMuur());
        grid[5][9].objects.add(new VasteMuur());

        grid[3][4].objects.add(new Barricade(300, true));

        grid[7][0].objects.add(new Barricade(100, true));
        grid[8][0].objects.add(new Barricade(100, true));

        for(int i=1;i<5;i++) {
            grid[7][i].objects.add(new Barricade(500, true));
            grid[8][i].objects.add(new Barricade(500, true));
        }

        grid[9][0].objects.add(new Barricade(100, true));
        grid[9][1].objects.add(new Barricade(100, true));
        grid[9][3].objects.add(new VasteMuur());
        grid[9][4].objects.add(new VasteMuur());

        grid[4][4].objects.add(new VasteMuur());
        grid[5][4].objects.add(new VasteMuur());
        grid[6][4].objects.add(new VasteMuur());
        grid[6][5].objects.add(new VasteMuur());
        grid[6][6].objects.add(new VasteMuur());
        grid[7][6].objects.add(new VasteMuur());

        grid[4][1].objects.add(new Sleutel(4, 1, 100));
        grid[5][5].objects.add(new Sleutel(5, 1, 500));
        grid[9][2].objects.add(new Sleutel(9, 2, 300));

        grid[9][9].objects.add(new Eindveld(9,9));

        Speelveld.vlakGrid = grid;
    }
    
    public static void loadPuzzle2(Speler speler) {
        Vlak[][] grid = new Vlak[10][10]; 

        huidigePuzzel = 2;
        
        for(int i=0;i<10;i++) {     
            for(int j=0;j<10;j++) {
                grid[i][j] = new Vlak(i, j);
            }
        }

        speler.setXpos(0); speler.setYpos(1); speler.setHuidigeSleutel(0);
        
        grid[speler.getXpos()][speler.getYpos()].objects.add(speler);

        for(int i=0;i<10;i++) {
            grid[i][0].objects.add(new VasteMuur());
            if((i!=1) && (i!=6)) {
                grid[i][2].objects.add(new VasteMuur());
            }
        }
        grid[8][1].objects.add(new Barricade(1000, true));
        
        grid[1][2].objects.add(new Barricade(100, true));
        grid[6][2].objects.add(new Barricade(300, true));
        
        for(int i=3;i<10;i++) {
            grid[0][i].objects.add(new VasteMuur());
        }
        
        for(int i=1;i<8;i++) {
            if(i!=6) grid[i][6].objects.add(new VasteMuur());
            if(i!=6) grid[i][8].objects.add(new VasteMuur());
        }
        
        grid[1][8].objects.remove(0);
        grid[6][8].objects.add(new VasteMuur());
        
        grid[7][6].objects.add(new VasteMuur());
        grid[7][7].objects.add(new VasteMuur());
        grid[7][8].objects.add(new VasteMuur());
        
        grid[8][6].objects.add(new VasteMuur());
        grid[8][7].objects.add(new VasteMuur());
        grid[8][8].objects.add(new VasteMuur());
        
        grid[7][1].objects.add(new Sleutel(7, 1, 100));
        
        grid[4][3].objects.add(new Sleutel(4, 3, 300));
        
        grid[9][3].objects.add(new Sleutel(4, 3, 1000));
        
        for(int i=3;i<6;i++) {
            grid[5][i].objects.add(new VasteMuur());
            
            grid[7][i].objects.add(new VasteMuur());
            grid[8][i].objects.add(new VasteMuur());
        }
        
        grid[6][4].objects.add(new Barricade(300, true));
        grid[6][6].objects.add(new Barricade(300, true));

        grid[9][1].objects.add(new Eindveld(9,1));

        Speelveld.vlakGrid = grid;
    }
    
    public static void loadPuzzle3(Speler speler) {
        Vlak[][] grid = new Vlak[10][10]; 

        huidigePuzzel = 3;
        
        for(int i=0;i<10;i++) {     
            for(int j=0;j<10;j++) {
                grid[i][j] = new Vlak(i, j);
            }
        }
        
        speler.setXpos(0); speler.setYpos(0); speler.setHuidigeSleutel(0);
        grid[speler.getXpos()][speler.getYpos()].objects.add(speler);
        
        for(int i=0;i<10;i++) {     
            for(int j=0;j<10;j++) {
                if(i<3 && j<3) {
                    continue;
                }
                if(i==9 && j==9) {
                    continue;
                }
                int item = (int)(Math.random() * 3);
                int slot = (int)(Math.random() * 1000);
                switch(item) {
                    case 0:
                        grid[i][j].objects.add(new VasteMuur());
                        break;
                    case 1:
                        grid[i][j].objects.add(new Barricade(slot, true));
                        break;
                    case 2:
                        grid[i][j].objects.add(new Sleutel(i, j, slot));
                        break;
                    default:
                        break;
                }
            }
        }
        
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
                vlakGrid[speler_x][speler_y].objects.remove(0);
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
            
            // als toegestaan, verplaats de speler
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
