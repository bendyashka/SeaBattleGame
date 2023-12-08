import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        char[][] theGround = new char[7][7];
        int vertical = 1;
        int horizontal = 2;
        gameboard(theGround);
        putShips(theGround);
        putMediumShips(theGround);
        putMediumShips(theGround);
        putSingleShips(theGround);
        actualGameBoard(theGround);
        actualGameBoard(theGround);

    }

    private static void actualGameBoard(char[][] theGround) {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(theGround[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void putSingleShips(char[][] theGround) {
        Random random = new Random();
        int vertical = 1;
        int horizontal = 2;
        int minimumSizeOfShip = 1;
        int maximumSizeOfShip = 5;
        char ship = 'L';

        for (int k = 0; k < 4; k++) {
            boolean clearPlace = false;

            while (!clearPlace) {
                int randomNum = random.nextInt(7);
                int randomNum2 = random.nextInt(7);

                boolean isValidPlacement = true;

                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        int newRow = randomNum + i;
                        int newCol = randomNum2 + j;
                        if (newRow >= 0 && newRow < 7 && newCol >= 0 && newCol < 7) {
                            if (theGround[newRow][newCol] == 'S' || theGround[newRow][newCol] == 'M' || theGround[newRow][newCol] == 'L') {
                                isValidPlacement = false;
                                break;
                            }
                        }
                    }
                    if (!isValidPlacement) {
                        break;
                    }
                }

                if (isValidPlacement) {
                    theGround[randomNum][randomNum2] = ship;
                //    System.out.println("1: " + randomNum + " " + randomNum2);
                    clearPlace = true;
                }
            }
        }
    }


    public static int[] randomizecoord(int[] data) {
        Random random = new Random();
        int vertical = 1;
        int horizontal = 2;
        int minimumSizeOfBigShip = 1;
        int maximumSizeOfBigShip = 5;

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
        int minimumSizeOfBigShip = 2;
        int maximumSizeOfBigShip = 4;
        char ship1 = 'M';
        boolean clearplace = false;

        while (!clearplace) {
            int randomNum = random.nextInt(6);
            int randomNum2 = random.nextInt(6);
            int directionOfBigShip = random.nextInt(horizontal - vertical + 1) + vertical;

            boolean neigboorPlaceClear = true;

            if (randomNum >= 0 && randomNum < 6 && randomNum2 >= 0 && randomNum2 < 7 &&
                    theGround[randomNum][randomNum2] == '&' &&
                    theGround[randomNum + 1][randomNum2] == '&') {

                for (int i = -1; i <= 2; i++) {
                    for (int j = -1; j <= 2; j++) {
                        int newRow = randomNum + i;
                        int newCol = randomNum2 + j;
                        if (newRow >= 0 && newRow < 7 && newCol >= 0 && newCol < 7) {
                            if (theGround[newRow][newCol] == 'S') {
                                neigboorPlaceClear = false;
                                break;
                            }
                        }
                    }
                    if (!neigboorPlaceClear) {
                        break;
                    }
                }

                if (neigboorPlaceClear) {
                    for (int i = -1; i <= 2; i++) {
                        for (int j = -1; j <= 2; j++) {
                            int newRow = randomNum + i;
                            int newCol = randomNum2 + j;
                            if (newRow >= 0 && newRow < 7 && newCol >= 0 && newCol < 7) {
                                if (theGround[newRow][newCol] == 'M') {
                                    neigboorPlaceClear = false;
                                    break;
                                }
                            }
                        }
                        if (!neigboorPlaceClear) {
                            break;
                        }
                    }
                }

                if (neigboorPlaceClear) {
                    theGround[randomNum][randomNum2] = ship1;

                    if (directionOfBigShip == vertical) {
                        theGround[randomNum + 1][randomNum2] = ship1;
                    } else {
                        theGround[randomNum][randomNum2 + 1] = ship1;
                    }

                    // System.out.println("2: " + randomNum + " " + randomNum2);
                    clearplace = true;
                }
            }
        }
    }









    private static void putShips(char[][] theGround) {
        Random random = new Random();
        int vertical = 1;
        int horizontal = 2;
        int minimumSizeOfBigShip = 1;
        int maximumSizeOfBigShip = 5;

        int randomNum = random.nextInt(maximumSizeOfBigShip - minimumSizeOfBigShip + 1) + minimumSizeOfBigShip;
        int randomNum2 = random.nextInt(maximumSizeOfBigShip - minimumSizeOfBigShip + 1) + minimumSizeOfBigShip;
        int directionOfBigShip = random.nextInt(horizontal - vertical + 1) + vertical;
       // System.out.println(randomNum + " " + randomNum2 + " " + directionOfBigShip);
        char ship = 'S';

        if (randomNum >= 0 && randomNum < 7 && randomNum2 >= 0 && randomNum2 < 7) {
            theGround[randomNum][randomNum2] = ship;

            if (directionOfBigShip == vertical) {
                theGround[randomNum + 1][randomNum2] = ship;
                theGround[randomNum - 1][randomNum2] = ship;
            } else {
                theGround[randomNum][randomNum2 + 1] = ship;
                theGround[randomNum][randomNum2 - 1] = ship;
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
        System.out.println();
    }
}