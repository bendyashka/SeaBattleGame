import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        char[][] theGround = new char[7][7];
        char[][] hiddenGround = new char[7][7];
        int vertical = 1;
        int horizontal = 2;
        gameboard(theGround, hiddenGround);
        putShips(theGround, hiddenGround);
        putMediumShips(theGround, hiddenGround);
        putMediumShips(theGround, hiddenGround);
        putSingleShips(theGround, hiddenGround);
        actualGameBoard(theGround);


        while (true) {
            makeShot(theGround, hiddenGround, scanner);
            actualGameBoard(hiddenGround);

            if (isGameFinished(hiddenGround)) {
                System.out.println("All ships Destroyed!");
                break;
            }
        }
    }

    private static void makeShot(char[][] theGround, char[][] hiddenGround, Scanner scanner) {
        System.out.println("\033[H\033[2J");  // Очистка консоли
        actualGameBoard(hiddenGround);
        System.out.print("Input coordinates (example:  1:1): ");
        String input = scanner.nextLine();
        String[] coordinates = input.split(":");

        int row = Integer.parseInt(coordinates[0]) - 1;
        int col = Integer.parseInt(coordinates[1]) - 1;

        if (isValidShot(row, col) && hiddenGround[row][col] == '&') {
            char target = theGround[row][col];
            hiddenGround[row][col] = (target != '&') ? 'X' : '*';

            if (target != '&') {
                System.out.println("Hit!");
                char hit = 'h';
                hiddenGround[row][col] =hit;

                if (isShipDestroyed(theGround, hiddenGround, target)) {
                    System.out.println("Ship Destoyed!");
                    char destroyed = 'd';
                    hiddenGround[row][col] =destroyed;

                }
            } else {
                System.out.println("Miss!");
                char miss = 'm';
                hiddenGround[row][col] = miss;

            }
        } else {
            System.out.println("Некорректные координаты или вы уже стреляли в эту ячейку. Попробуйте еще раз.");
        }
    }


    private static boolean isValidShot(int row, int col) {
        return row >= 0 && row < 7 && col >= 0 && col < 7;
    }

    private static boolean isShipDestroyed(char[][] theGround, char[][] hiddenGround, char shipType) {
        for (int i = 0; i < theGround.length; i++) {
            for (int j = 0; j < theGround[i].length; j++) {
                if (theGround[i][j] == shipType && hiddenGround[i][j] != 'X') {
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean isGameFinished(char[][] theGround, char[][] hiddenGround) {
        for (int i = 0; i < theGround.length; i++) {
            for (int j = 0; j < theGround[i].length; j++) {
                char target = theGround[i][j];
                if ((target == 'S' || target == 'M' || target == 'L') && hiddenGround[i][j] == '&') {
                    return false;
                }
            }
        }
        return true;
    }



    private static void actualGameBoard(char[][] hiddenGround) {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(hiddenGround[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void putSingleShips(char[][] theGround, char[][] hiddenGround) {
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
                    hiddenGround[randomNum][randomNum2] = '&';
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

    private static void putMediumShips(char[][] theGround, char[][] hiddenGround) {
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
                    theGround[randomNum + 1][randomNum2] = ship1;
                    hiddenGround[randomNum][randomNum2] = '&';
                    hiddenGround[randomNum + 1][randomNum2] = '&';
                    clearplace = true;
                }
            }
        }
    }

    private static void putShips(char[][] theGround, char[][] hiddenGround) {
        Random random = new Random();
        int vertical = 1;
        int horizontal = 2;
        int minimumSizeOfBigShip = 1;
        int maximumSizeOfBigShip = 5;

        int randomNum = random.nextInt(maximumSizeOfBigShip - minimumSizeOfBigShip + 1) + minimumSizeOfBigShip;
        int randomNum2 = random.nextInt(maximumSizeOfBigShip - minimumSizeOfBigShip + 1) + minimumSizeOfBigShip;
        int directionOfBigShip = random.nextInt(horizontal - vertical + 1) + vertical;
        char ship = 'S';

        if (randomNum >= 0 && randomNum < 7 && randomNum2 >= 0 && randomNum2 < 7) {
            theGround[randomNum][randomNum2] = ship;
            theGround[randomNum + 1][randomNum2] = ship;
            theGround[randomNum - 1][randomNum2] = ship;
            hiddenGround[randomNum][randomNum2] = '&';
            hiddenGround[randomNum + 1][randomNum2] = '&';
            hiddenGround[randomNum - 1][randomNum2] = '&';
        }
    }

    public static void gameboard(char[][] theGround, char[][] hiddenGround) {
        for (int i = 0; i < theGround.length; i++) {
            for (int j = 0; j < theGround[i].length; j++) {
                theGround[i][j] = '&';
                hiddenGround[i][j] = '&';
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
