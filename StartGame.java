public class StartGame {
    public static Ship[] EnemyShip = new Ship[10];
    public static Ship[] UserShip = new Ship[10];
    public static void createEnemyShips(){
        int size = 4;
        int counter = 0;
        while (size > 0) {
            for (int i = 0; i <= 4 - size; i++) {
                EnemyShip[counter] = new Ship(size);
                counter++;
            }
            size = size - 1;
        }
    }

}
