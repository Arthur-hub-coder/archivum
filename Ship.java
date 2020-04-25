import java.util.Random;

public class Ship {
    int size;
    int[][] array;
    int health;
    boolean direct;
    int x;
    int y;
    public boolean bordercheck;

    public Ship(int size) {
        this.size = size;
        this.health = size;
        this.array = new int[size][2];
        boolean noncreated = true;
        while (noncreated) {
            int c = 0;
            Random rand = new Random();
            direct = rand.nextBoolean();
            int nx = 0;
            int ny = 0;
            if (direct)
                nx = size - 1;
            else
                ny = size - 1;
            x = rand.nextInt(10 - nx);
            y = rand.nextInt(10 - ny);
            if (direct) {
                for (int i = y - 1; i <= y + 1; i++) {
                    for (int j = x - 1; j <= x + size; j++) {
                        if ((i >= 0) && (i <= 9) && (j >= 0) && (j <= 9)) {
                            c = c + GUI.enemyShips[j][i];
                        }
                    }

                }
            } else {
                for (int i = y - 1; i <= y + size; i++) {
                    for (int j = x - 1; j <= x + 1; j++) {
                        if ((i >= 0) && (i <= 9) && (j >= 0) && (j <= 9)) {
                            c = c + GUI.enemyShips[j][i];
                        }
                    }
                }
            }
            if (c == 0) {
                if (direct) {
                    int count = 0;
                    for (int i = x; i <= x + size - 1; i++) {
                        GUI.enemyShips[i][y] = 1;
                        this.array[count][0] = i;
                        this.array[count][1] = y;
                        count++;
                    }
                } else {
                    int count = 0;
                    for (int i = y; i <= y + size - 1; i++) {
                        GUI.enemyShips[x][i] = 1;
                        this.array[count][0] = x;
                        this.array[count][1] = i;
                        count++;
                    }
                }
                noncreated = false;
            }
        }
    }

    public Ship(int size, int x, int y, boolean direct) {
        this.size = size;
        this.health = size;
        this.array = new int[size][2];
        this.direct = direct;
        this.x = x;
        this.y = y;
        int c = 0;
        int nx = 0;
        int ny = 0;
        if (direct)
            nx = size - 1;
        else
            ny = size - 1;
        if (direct) {
            for (int i = y - 1; i <= y + 1; i++) {
                for (int j = x - 1; j <= x + size; j++) {
                    if ((i >= 0) && (i <= 9) && (j >= 0) && (j <= 9)) {
                        c = c + GUI.userShips[j][i];
                    }
                }

            }
        } else {
            for (int i = y - 1; i <= y + size; i++) {
                for (int j = x - 1; j <= x + 1; j++) {
                    if ((i >= 0) && (i <= 9) && (j >= 0) && (j <= 9)) {
                        c = c + GUI.userShips[j][i];
                    }
                }
            }
        }
        if (c == 0) {
            if ((direct) && (x + size < 11)) {
                int count = 0;
                for (int i = x; i <= x + size - 1; i++) {
                    GUI.userShips[i][y] = 1;
                    this.array[count][0] = i;
                    this.array[count][1] = y;
                    count++;
                }
            } else if ((!direct) && (y + size < 11)) {
                int count = 0;
                for (int i = y; i <= y + size - 1; i++) {
                    GUI.userShips[x][i] = 1;
                    this.array[count][0] = x;
                    this.array[count][1] = i;
                    count++;
                }
            }
            if (((!direct) && (y + size < 11))||((direct) && (x + size < 11)))
            bordercheck = true;
        } else {
            bordercheck = false;
        }

    }


}

