import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        char[][] theGround = new char[7][7];
        int vertical = 1;
        int horizontal = 2;
        //  gameboard(theGround);
        putShips(theGround);
        putMediumShips(theGround);
        putMediumShips(theGround);

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(theGround[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int[] randomizecoord(int[] data) {
        Random random = new Random();
        int vertical = 1;
        int horizontal = 2;
        int minimumSizeOfBigShip = 0;
        int maximumSizeOfBigShip = 6;

        int randomNum = random.nextInt(maximumSizeOfBigShip - minimumSizeOfBigShip + 1) + minimumSizeOfBigShip;
        int randomNum2 = random.nextInt(maximumSizeOfBigShip - minimumSizeOfBigShip + 1) + minimumSizeOfBigShip;
        int directionOfBigShip = random.nextInt(horizontal - vertical + 1) + vertical;
        int[] result = {randomNum, randomNum2, directionOfBigShip};
        return result;
    }

    private static void putMediumShips(char[][] theGround) {
        Random random = new Random();
        int vertical = 1;
        int horizontal = 2;
        int minimumSizeOfBigShip = 0;
        int maximumSizeOfBigShip = 6;

        int randomNum = random.nextInt(maximumSizeOfBigShip - minimumSizeOfBigShip + 1) + minimumSizeOfBigShip;
        int randomNum2 = random.nextInt(maximumSizeOfBigShip - minimumSizeOfBigShip + 1) + minimumSizeOfBigShip;
        int directionOfBigShip = random.nextInt(horizontal - vertical + 1) + vertical;
        int[] dataArray = {randomNum, randomNum2, directionOfBigShip};
        System.out.println("2: " + randomNum + " " + randomNum2 + " " + directionOfBigShip);
        char ship = 'M';

        if (randomNum >= 0 && randomNum < 7 && randomNum2 >= 0 && randomNum2 < 7) {
            if(theGround[randomNum][randomNum2] == 'S' || theGround[randomNum][randomNum2] == 'M' ){
                randomizecoord(dataArray);
            }
            else {

            theGround[randomNum][randomNum2] = ship;
        }

            if (directionOfBigShip == vertical) {
                for (int i = 1; i < 2 && randomNum + i < 7; i++) {
                    if (randomNum + i >= 0 && randomNum + i < 7) {
                        theGround[randomNum + i][randomNum2] = ship;
                    }
                }
            } else {
                for (int i = 1; i < 2 && randomNum2 + i < 7; i++) {
                    if (randomNum2 + i >= 0 && randomNum2 + i < 7) {
                        theGround[randomNum][randomNum2 + i] = ship;
                    }
                }
            }
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

        if (randomNum >= 0 && randomNum < 7 && randomNum2 >= 0 && randomNum2 < 7) {
            theGround[randomNum][randomNum2] = ship;

            if (directionOfBigShip == vertical) {
                for (int i = 1; i < 3 && randomNum + i < 7; i++) {
                    if (randomNum + i >= 0 && randomNum + i < 7) {
                        theGround[randomNum + i][randomNum2] = ship;
                    }
                }
            } else {
                for (int i = 1; i < 3 && randomNum2 + i < 7; i++) {
                    if (randomNum2 + i >= 0 && randomNum2 + i < 7) {
                        theGround[randomNum][randomNum2 + i] = ship;
                    }
                }
            }
        }
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

