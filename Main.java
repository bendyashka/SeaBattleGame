import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        char[][] theGround = new char[7][7];
        gameboard(theGround);
        putShips();
    }

    private static void putShips() {
        Random random = new Random();
        int vertical = 1;
        int horizontal = 2;
        int miniumSizeofBigShip = 1;
        int maxiumSizeofBigShip = 7;
        int randomNum = random.nextInt(maxiumSizeofBigShip - miniumSizeofBigShip + 1) + miniumSizeofBigShip;
        int randomNum2 = random.nextInt(maxiumSizeofBigShip - miniumSizeofBigShip + 1) + miniumSizeofBigShip;
        int directionOfBigShip = random.nextInt(horizontal - vertical + 1) + vertical;
        System.out.println(randomNum + " " + randomNum2 + " " + directionOfBigShip);
    }

    public static void gameboard(char[][] theGround) {
        for (int i = 0; i < theGround.length; i++) {
            for (int j = 0; j < theGround[i].length; j++) {
                theGround[i][j] = '&';
            }
        }

        for (int g = 0; g < theGround.length; g++) {
            for (int j = 0; j < theGround[g].length; j++) {
                System.out.print(theGround[g][j] + " ");
            }
            System.out.println();
        }
    }
}