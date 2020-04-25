import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.*;

public class GUI extends JFrame {
    static public int userHP = 20;
    static public int enemyHP = 20;
    public int nx = -100;
    public int ny = -100;
    public int x = -100;
    public int y = -100;
    public int mx = -100;
    public int my = -100;
    public int counter = 0;
    public int size = 0;
    static public int[][] enemyShips = new int[10][10];
    static public int[][] userShips = new int[10][10];
    public boolean prepared = false;
    static public Battle battle = new Battle();
    static public boolean userturn = true;

    public GUI() {
        this.setTitle("Battleship");
        this.setSize(1000, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
        StartGame.createEnemyShips();
        Board userBoard = new Board();
        userBoard.user=true;
        userBoard.place=0;
        userBoard.ships=userShips;
        Board enemyBoard = new Board();
        enemyBoard.user=false;
        enemyBoard.place=400;
        enemyBoard.ships=enemyShips;
        this.getContentPane().add(userBoard);
        this.revalidate();
        this.repaint();
        this.getContentPane().add(enemyBoard);
        this.revalidate();
        this.repaint();
        Move move = new Move();
        this.addMouseMotionListener(move);
        Click click = new Click();
        this.addMouseListener(click);
        System.out.println("Hi!");


    }

    public class Board extends JPanel {
        public int[][] ships;
        public boolean user;
        public int place;

        public void paintComponent(Graphics g) {


            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    g.setColor(Color.LIGHT_GRAY);
                    if ((this.ships[i][j] == 1) && (this.user))
                        g.setColor(Color.BLUE);
                    if (this.ships[i][j] == -1)
                        g.setColor(Color.ORANGE);
                    if (this.ships[i][j] == -2)
                        g.setColor(Color.cyan);
                    if (this.ships[i][j] == -3)
                        g.setColor(Color.RED);
                    if ((nx >= (98 + this.place) + i * 22) && (nx <= (118 + this.place) + i * 22) && (ny >= 121 + j * 22) && (ny <= 141 + j * 22)) {
                        g.setColor(Color.BLACK);
                        x = i;
                        y = j;
                        if (!user){
                            g.setColor(Color.RED);
                            mx = i;
                            my = j;
                        }
                    }
                    g.fillRect((90 + this.place) + i * 22, 90 + j * 22, 20, 20);
                    }
                }
            }
        }




    public class Move implements MouseMotionListener {

        @Override
        public void mouseDragged(MouseEvent e) {

        }

        @Override
        public void mouseMoved(MouseEvent e) {
            nx = e.getX();
            ny = e.getY();

        }
    }

    public class Click implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (!prepared) {
                if (counter == 0) {
                    size = 4;
                }
                if ((counter >= 1) && (counter <= 2)) {
                    size = 3;
                }
                if ((counter >= 3) && (counter <= 5)) {
                    size = 2;
                }
                if ((counter >= 6) && (counter <= 9)) {
                    size = 1;
                }
                StartGame.UserShip[counter] = new Ship(size, x, y, SwingUtilities.isRightMouseButton(e));
                if (StartGame.UserShip[counter].bordercheck)
                    counter++;
                prepared = (counter == 10);
            } else {
                if ((userturn)&&(nx>400)){
                    if (enemyShips[mx][my] == 1) {
                        enemyShips[mx][my] = -1;
                        battle.checkHealth(mx, my);
                    }
                    if (enemyShips[mx][my] == 0) {
                        enemyShips[mx][my] = -2;
                        userturn = false;
                    }
                }
            }
        }
            @Override
            public void mousePressed (MouseEvent e){

            }

            @Override
            public void mouseReleased (MouseEvent e){

            }

            @Override
            public void mouseEntered (MouseEvent e){

            }

            @Override
            public void mouseExited (MouseEvent e){

            }

        }
    }







