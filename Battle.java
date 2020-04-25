import javax.swing.*;
import java.util.Random;

public class Battle extends JFrame {

    public int HitX;
    public int HitY;
    private int dx;
    private int dy;
    private int sx = 0;
    private int sy;
    private int sn = 0;
    private boolean search1 = true;
    private boolean search2 = false;
    private boolean search3 = false;
    private boolean isDestroy = false;
    private boolean kill = false;
    private boolean up = true;
    private boolean down = true;
    private boolean left = true;
    private boolean right = true;
    private boolean markx = false;
    private boolean marky = false;
    private int count = 0;

    public void checkHealth(int x, int y) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < StartGame.EnemyShip[i].array.length; j++) {
                if ((StartGame.EnemyShip[i].array[j][0] == x) && (StartGame.EnemyShip[i].array[j][1] == y)){
                    StartGame.EnemyShip[i].health--;
                    GUI.enemyHP--;
                    if (GUI.enemyHP == 0){
                        JOptionPane.showMessageDialog(Battle.this, "Вы выиграли!");
                    }

                }
            }
            if (StartGame.EnemyShip[i].health == 0) {
                for (int j = 0; j < StartGame.EnemyShip[i].array.length; j++) {
                    GUI.enemyShips[StartGame.EnemyShip[i].array[j][0]][StartGame.EnemyShip[i].array[j][1]] = -3;
                }
            }

        }
    }

    public void bang() {
        if (!GUI.userturn) {
            if (isDestroy) {
                destroy(HitX, HitY);
            } else {
                search();
            }

        }
    }

    private void destroy(int x, int y) {
        if ((x==0)||(GUI.userShips[dx-1][dy]<0))
            left = false;
        if ((x==9)||(GUI.userShips[dx+1][dy]<0))
            right = false;
        if ((y==9)||(GUI.userShips[dx][dy+1]<0))
            down = false;
        if ((y==0)||(GUI.userShips[dx][dy-1]<0))
            up = false;
        if (left && !kill && !GUI.userturn) {
            dx--;
            if (GUI.userShips[dx][dy] == 0) {
                GUI.userturn = true;
                left = false;
                GUI.userShips[dx][dy] = -2;
                dx = x;
                if(markx){
                    dx = x;
                    dy = y;
                    left = false;
                    right = true;
                }

            } else {
                if (GUI.userShips[dx][dy] == 1) {
                    down = false;
                    up = false;
                    right = false;
                    GUI.userShips[dx][dy] = -1;
                    checkUserHealth(dx, dy);
                    markx = true;
                }
            }
            if (((dx <= 0) || (GUI.userShips[dx - 1][dy] < 0))&&(markx)) {
                    dx = x;
                    dy = y;
                    left = false;
                    right = true;
            }
        } else {
            if (right && !GUI.userturn && !kill) {
                dx++;
                if (GUI.userShips[dx][dy] == 0) {
                    GUI.userturn = true;
                    right = false;
                    GUI.userShips[dx][dy] = -2;
                    dx = x;
                    if (markx) {
                        dx = x;
                        dy = y;
                        left = true;
                        right = false;
                    }
                } else {
                    if (GUI.userShips[dx][dy] == 1) {
                        down = false;
                        up = false;
                        left = false;
                        GUI.userShips[dx][dy] = -1;
                        checkUserHealth(dx, dy);
                        markx = true;
                    }
                }
                if (((dx >= 9) || (GUI.userShips[dx + 1][dy] < 0)) && (markx)) {
                    dx = x;
                    dy = y;
                    left = true;
                    right = false;
                }
            } else {
                if (up && !GUI.userturn && !kill){
                    dy--;
                    if (GUI.userShips[dx][dy] == 0) {
                        GUI.userturn = true;
                        up = false;
                        GUI.userShips[dx][dy] = -2;
                        dy = y;
                        dx = x;
                        if (marky) {
                            dy = y;
                            dx = x;
                            down = true;
                            up = false;
                        }
                    } else {
                        if (GUI.userShips[dx][dy] == 1) {
                            down = false;
                            right = false;
                            left = false;
                            GUI.userShips[dx][dy] = -1;
                            checkUserHealth(dx, dy);
                            marky = true;
                        }
                    }
                    if (((dy <= 0) || (GUI.userShips[dx][dy-1] < 0)) && (marky)) {
                        dy = y;
                        dx = x;
                        down = true;
                        up = false;
                    }
                } else {
                    if (down && !GUI.userturn && !kill) {
                        dy++;
                        if (GUI.userShips[dx][dy] == 0) {
                            GUI.userturn = true;
                            down = false;
                            GUI.userShips[dx][dy] = -2;
                            dy = y;
                            if (marky) {
                                dy = y;
                                dx = x;
                                down = true;
                                up = false;
                            }
                        } else {
                            if (GUI.userShips[dx][dy] == 1) {
                                up = false;
                                right = false;
                                left = false;
                                GUI.userShips[dx][dy] = -1;
                                checkUserHealth(dx, dy);
                                marky = true;
                            }
                        }
                        if (((dy >= 9) || (GUI.userShips[dx][dy+1] < 0)) && (marky)) {
                            dy = y;
                            dx = x;
                            down = false;
                            up = true;
                        }
                    }
                }
            }
         }
        if (count<20){
            System.out.println("up = " + up + " right = " + right + " left = " + left + " down = " + down + " dx = " + dx + " dy = " + dy);
            count++;
        }

    }

    public void checkUserHealth(int x, int y) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < StartGame.UserShip[i].array.length; j++) {
                if ((StartGame.UserShip[i].array[j][0] == x) && (StartGame.UserShip[i].array[j][1] == y)){
                    StartGame.UserShip[i].health--;
                    GUI.userHP--;
                    if (GUI.userHP == 0){
                        JOptionPane.showMessageDialog(Battle.this, "Вы проиграли!");
                    }
                    if (StartGame.UserShip[i].health == 0){
                        kill = true;
                        isDestroy = false;
                        up = true;
                        down = true;
                        left = true;
                        right = true;
                        markx = false;
                        marky = false;
                        if (StartGame.UserShip[i].direct) {
                            for (int k = StartGame.UserShip[i].y - 1; k <= StartGame.UserShip[i].y + 1; k++) {
                                for (int l = StartGame.UserShip[i].x - 1; l <= StartGame.UserShip[i].x + StartGame.UserShip[i].size; l++) {
                                    if ((k >= 0) && (k <= 9) && (l >= 0) && (l <= 9)&&(GUI.userShips[l][k]!=-2)) {
                                        GUI.userShips[l][k]=-4;
                                    }
                                }

                            }
                        } else {
                            for (int k = StartGame.UserShip[i].y - 1; k <= StartGame.UserShip[i].y + StartGame.UserShip[i].size; k++) {
                                for (int l = StartGame.UserShip[i].x - 1; l <= StartGame.UserShip[i].x + 1; l++) {
                                    if ((k >= 0) && (k <= 9) && (l >= 0) && (l <= 9)&&(GUI.userShips[l][k]!=-2)) {
                                        GUI.userShips[l][k]=-4;
                                    }
                                }
                            }
                        }
                        for (int l = 0; l < StartGame.UserShip[i].array.length; l++) {
                            GUI.userShips[StartGame.UserShip[i].array[l][0]][StartGame.UserShip[i].array[l][1]] = -3;
                        }
                    } else {
                        kill=false;
                    }
                }


            }


        }

    }


    private void search() {
        kill = false;
        if (search1) {
            sy = (3 - sx % 4) + sn * 4;
            if (GUI.userShips[sx][sy] == 1) {

                GUI.userShips[sx][sy] = -1;
                checkUserHealth(sx, sy);
                if (!kill) {
                    HitX = sx;
                    HitY = sy;
                    dx=HitX;
                    dy=HitY;
                    isDestroy = true;

                }
            }
            if (GUI.userShips[sx][sy] == 0) {
                GUI.userShips[sx][sy] = -2;
                GUI.userturn = true;
            }
            sn++;
            if (((3 - sx % 4) + sn * 4) > 9) {
                sx++;
                sn = 0;
            }
            if (sx > 9) {
                search1 = false;
                search2 = true;
                sx = 0;
                sn = 0;
            }

        }
        if (search2) {
            sy = (3 - (sx + 2) % 4) + sn * 4;
            if (GUI.userShips[sx][sy] == 1) {

                GUI.userShips[sx][sy] = -1;
                checkUserHealth(sx, sy);
                if (!kill) {
                    HitX = sx;
                    HitY = sy;
                    dx=HitX;
                    dy=HitY;
                    isDestroy = true;
                }
            }
            if (GUI.userShips[sx][sy] == 0) {
                GUI.userShips[sx][sy] = -2;
                GUI.userturn = true;
            }
            sn++;
            if (((3 - (sx + 2) % 4) + sn * 4) > 9) {
                sx++;
                sn = 0;
            }
            if (sx > 9) {
                search2 = false;
                search3 = true;
                sx = 0;
                sn = 0;
            }
        }
        if (search3) {
            isDestroy = false;
            Random rand = new Random();
            int x = rand.nextInt(10);
            int y = rand.nextInt(10);
            if (GUI.userShips[x][y] == 1) {
                GUI.userShips[x][y] = -1;
                checkUserHealth(x, y);
            }
            if (GUI.userShips[x][y] == 0) {
                GUI.userShips[x][y] = -2;
                GUI.userturn = true;

            }
        }

    }
}




