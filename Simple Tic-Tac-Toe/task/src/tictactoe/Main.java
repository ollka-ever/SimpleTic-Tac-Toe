package tictactoe;

import java.util.Scanner;

public class Main {
    //checks if empty cells are present
    public static boolean emptyCellsChecker(String[][] matrix){
        boolean containsEmpty = false;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j].equals(" ")) {
                    containsEmpty = true;
                }
            }
        }
        return containsEmpty;
    }

    //shows matrix with its content
    public static void showMatrix(String[][] matrix){
        System.out.println("---------");
        for (int i = 0; i < matrix.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    //actions when it is X turn
    public static void xTurnDetails(boolean xStepApproved, String[][] matrix){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the coordinates: ");
        String coordinate1 = scanner.next();
        String coordinate2 = scanner.next();
        int c1 = 0;
        int c2 = 0;
        while (!xStepApproved) {
            try {
                c1 = Integer.parseInt(coordinate1);
                c2 = Integer.parseInt(coordinate2);
                if (c1 > 3 || c1 < 1 || c2 > 3 || c2 < 1) {
                    System.out.println("Coordinates should be from 1 to 3!");
                } else if (!matrix[c1-1][c2-1].equals(" ")) {
                    System.out.println("This cell is occupied! Choose another one!");
                } else {
                    xStepApproved = true;
                    break;
                }
            } catch (Exception e) {
                System.out.println("You should enter numbers!");
            }

            System.out.print("Enter the coordinates: ");
            coordinate1 = scanner.next();
            coordinate2 = scanner.next();
        }

        if (xStepApproved) {
            matrix[c1-1][c2-1] = "X";
            showMatrix(matrix);
        }
    }

    //actions when it is O turn
    public static void oTurnDetails(boolean oStepApproved, String[][] matrix){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the coordinates: ");
        String coordinate1 = scanner.next();
        String coordinate2 = scanner.next();
        int c1 = 0;
        int c2 = 0;
        while (!oStepApproved) {
            try {
                c1 = Integer.parseInt(coordinate1);
                c2 = Integer.parseInt(coordinate2);
                if (c1 > 3 || c1 < 1 || c2 > 3 || c2 < 1) {
                    System.out.println("Coordinates should be from 1 to 3!");
                } else if (!matrix[c1-1][c2-1].equals(" ")) {
                    System.out.println("This cell is occupied! Choose another one!");
                } else {
                    oStepApproved = true;
                    break;
                }
            } catch (Exception e) {
                System.out.println("You should enter numbers!");
            }

            System.out.print("Enter the coordinates: ");
            coordinate1 = scanner.next();
            coordinate2 = scanner.next();
        }

        if (oStepApproved) {
            matrix[c1-1][c2-1] = "O";
            showMatrix(matrix);
        }
    }

    //check if game is finished
    public static boolean isGameFinishedCheck(String[][] matrix){
        int xThreeInRow = 0;
        int oThreeInRow = 0;
        boolean containsEmpty = emptyCellsChecker(matrix);
        boolean isGameFinished = false;

        //checking three in horizontal row
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][0].equals("O") && matrix[i][1].equals("O") && matrix[i][2].equals("O")) {
                oThreeInRow++;
            } else if (matrix[i][0].equals("X") && matrix[i][1].equals("X") && matrix[i][2].equals("X")) {
                xThreeInRow++;
            }
        }

        //checking three in vertical row
        for (int i = 0; i < 3; i++) {
            if (matrix[0][i].equals("O") && matrix[1][i].equals("O") && matrix[2][i].equals("O")) {
                oThreeInRow++;
            } else if (matrix[0][i].equals("X") && matrix[1][i].equals("X") && matrix[2][i].equals("X")) {
                xThreeInRow++;
            }
        }

        //checking three in diagonal row one way
        if (matrix[0][0].equals("O") && matrix[1][1].equals("O") && matrix[2][2].equals("O")) {
            oThreeInRow++;
        } else if (matrix[0][0].equals("X") && matrix[1][1].equals("X") && matrix[2][2].equals("X")) {
            xThreeInRow++;
        }

        //checking three in diagonal row another way
        if (matrix[0][2].equals("O") && matrix[1][1].equals("O") && matrix[2][0].equals("O")) {
            oThreeInRow++;
        } else if (matrix[0][2].equals("X") && matrix[1][1].equals("X") && matrix[2][0].equals("X")) {
            xThreeInRow++;
        }

        if (oThreeInRow == xThreeInRow && oThreeInRow == 0 && containsEmpty == false) {
            System.out.println("Draw");
            isGameFinished = true;
        } else if (xThreeInRow == 1) {
            System.out.println("X wins");
            isGameFinished = true;
        } else if (oThreeInRow == 1) {
            System.out.println("O wins");
            isGameFinished = true;
        }
        return isGameFinished;
    }



    public static void main(String[] args) {
        // write your code here
        boolean xTurn = true;
        boolean oTurn = false;
        boolean xStepApproved = false;
        boolean oStepApproved = false;
        boolean isFinished = false;

        // empty matrix is created
        String[][] matrix = new String[3][3];
        for(int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = " ";
            }
        }

        showMatrix(matrix);

        while (!isFinished) {
            if (xTurn) {
                xTurnDetails(xStepApproved, matrix);
                isFinished = isGameFinishedCheck(matrix);
                if (isFinished == false) {
                    xTurn = false;
                    oTurn = true;
                } else {
                    break;
                }
            } else if (oTurn) {
                oTurnDetails(oStepApproved, matrix);
                isFinished = isGameFinishedCheck(matrix);
                if (isFinished == false) {
                    oTurn = false;
                    xTurn = true;
                } else {
                    break;
                }
            }
        }
    }
}
