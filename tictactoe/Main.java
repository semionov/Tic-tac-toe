package tictactoe;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static char[] cells = new char[9];
    static char[][] field = new char[3][3];
    static int iCord = 0;
    static int jCord = 0;
    static boolean xORo = true;

    public static void main(String[] args) {
        //System.out.print("Enter cells: ");
        //cells = scanner.nextLine().toCharArray();

        for (int i = 0; i < 9; i++) {
            cells[i] = ' ';
        }

        printField(cells);
        initializeField(cells);

        playGame(field);
    }

    private static void printField(char[] cells) {
        System.out.println("---------");
        for (int i = 0; i <= 6; i += 3) {
            System.out.println(String.format("| %c %c %c |", cells[i], cells[i + 1], cells[i + 2]));
        }
        System.out.println("---------");
    }

    private static void initializeField(char[] cells) {
        for (int i = 0; i <= 6; i += 3) {
            field[i / 3] = new char[]{cells[i], cells[i + 1], cells[i + 2]};
        }
    }

    private static void nextMove() {
        boolean invalidInput = true;
        while (invalidInput) {
            try {
                System.out.print("Enter the coordinates: ");
                iCord = scanner.nextInt() - 1;
                jCord = scanner.nextInt() - 1;

                char check = field[2 - jCord][iCord];

                if (check != 'X' && check != 'O') {
                    if (xORo) {
                        field[2 - jCord][iCord] = 'X';
                        xORo = !xORo;
                    } else {
                        field[2 - jCord][iCord] = 'O';
                        xORo = !xORo;
                    }

                    for (int i = 0; i <= 6; i += 3) {
                        System.arraycopy(field[i / 3], 0, cells, i, 3);
                    }

                    invalidInput = false;
                } else {
                    System.out.println("This cell is occupied! Choose another one!");
                }

            } catch (InputMismatchException ex) {
                System.out.println("You should enter numbers!");
            } catch (ArrayIndexOutOfBoundsException ex) {
                System.out.println("Coordinates should be from 1 to 3!");
            } finally {
                scanner.nextLine();
            }
        }
    }

    private static void playGame(char[][] field) {

        nextMove();

        char x = 'X';
        char o = 'O';
        int countX;
        int countO;
        int countEmpty = 0;

        int xWin = 0;
        int oWin = 0;

        for (int i = 0; i < 3; i++) {

            countO = 0;
            countX = 0;

            for (int j = 0; j < 3; j++) {

                if (field[i][j] == x) {
                    countX++;
                } else if (field[i][j] == o) {
                    countO++;
                }

                if (countX == 3) {
                    xWin++;
                } else if (countO == 3) {
                    oWin++;
                }
            }
        }

        for (int j = 0; j < 3; j++) {

            countO = 0;
            countX = 0;

            for (int i = 0; i < 3; i++) {

                if (field[i][j] == x) {
                    countX++;
                } else if (field[i][j] == o) {
                    countO++;
                }

                if (countX == 3) {
                    xWin++;
                } else if (countO == 3) {
                    oWin++;
                }
            }
        }

        countO = 0;
        countX = 0;

        for (int j = 0, i = 0; j < 3; j++, i++) {
            if (field[i][j] == x) {
                countX++;
            } else if (field[i][j] == o) {
                countO++;
            }

            if (countX == 3) {
                xWin++;
            } else if (countO == 3) {
                oWin++;
            }
        }

        if (field[2][0] == x && field[1][1] == x && field[0][2] == x) {
            xWin++;
        } else if (field[2][0] == o && field[1][1] == o && field[0][2] == o) {
            oWin++;
        }

        countO = 0;
        countX = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                if (field[i][j] == x) {
                    countX++;
                } else if (field[i][j] == o) {
                    countO++;
                } else {
                    countEmpty++;
                }
            }
        }

        printField(cells);

        if (Math.abs(countX - countO) >= 2 ) {
            System.out.println("Impossible");
        } else if (xWin >= 1 && oWin >= 1) {
            System.out.println("Impossible");
        } else if (xWin == 1) {
            System.out.println("X wins");
        } else if (oWin == 1) {
            System.out.println("O wins");
        } else if (countEmpty > 0) {
            //System.out.println("Game not finished");
            playGame(field);
        } else {
            System.out.println("Draw");
        }
    }
}
