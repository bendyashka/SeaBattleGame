import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        char[][] theGround = new char[6][6];
        int vertical = 1;
        int horizontal = 2;
        gameboard(theGround);
        putShips(theGround);
        for (int i= 0; i < 6; i++){
            for (int j =0; j<6;j++){
                System.out.print(theGround[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void putShips(char[][] theGround) {
        Random random = new Random();
        int vertical = 1;
        int horizontal = 2;
        int minimumSizeOfBigShip = 0;
        int maximumSizeOfBigShip = 6;
        int randomNum = random.nextInt(maximumSizeOfBigShip - minimumSizeOfBigShip + 1) + minimumSizeOfBigShip;
        int randomNum2 = random.nextInt(maximumSizeOfBigShip - minimumSizeOfBigShip + 1) + minimumSizeOfBigShip;
        int directionOfBigShip = random.nextInt(horizontal - vertical + 1) + vertical;
        System.out.println(randomNum + " " + randomNum2 + " " + directionOfBigShip);
        char ship = 'S';
        theGround[randomNum][randomNum2] = ship;
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