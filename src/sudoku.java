import java.util.Scanner;

public class sudoku {
    int[][] grid;
    final int boardLength = 9;

    //Constructor makes a 9x9 board filled with empty values
    public sudoku() {
        grid = new int[boardLength][boardLength];
        for (int i = 0; i < boardLength; i++) {
            for (int j = 0; j < boardLength; j++) {
                grid[i][j] = 0;
            }
        }
    }

    private boolean isSafe(int row, int col, int num) {
        //The if statements include i != row or col because it allows the isSafe function to validate the initial input
        //Avoids returning false when the value is compared to ITSELF

        //Split the row and col check in case they vary (affects range)

        //Check if num is already in the row
        for (int i = 0; i < boardLength; i++) {
            if (grid[row][i] == num && i != col) {
                return false;
            }
        }

        //Check if num is already in column
        for (int i = 0; i < boardLength; i++) {
            if (grid[i][col] == num && i != row) {
                return false;
            }
        }

        //Check for a conflict in a 3x3
        int rowPartition = row - row % 3;
        int colPartition = col - col % 3;
        for (int i = rowPartition; i < rowPartition + 3; i++) {
            for (int j = colPartition; j < colPartition + 3; j++) {
                if (grid[i][j] == num && i != row && j != col) {
                    return false;
                }
            }
        }

        return true;
    }

    //Used to evaluate the true/false output of the sudoku solver
    public void solveOutput(){
        boolean result = this.solver();

        if (result) {
            System.out.println("Successful solve!");
        }
        else if (!result) {
            System.out.println("Unable to solve the puzzle.");
        }
    }

    private boolean solver() {

        //Go to next empty space
        for (int row = 0; row < boardLength; row++) {
            for (int col = 0; col < boardLength; col++) {
                if (this.grid[row][col] == 0) {
                    //Backtracking, starts at 1, goes to 9 (boardLength)
                    for (int num = 1; num <= boardLength; num++) {
                        //If the number does not conflict
                        if (isSafe(row, col, num)) {
                            this.grid[row][col] = num;

                            //If the recursive attempts to it are valid, true
                            if (solver()) {
                                return true;
                            }
                            //Otherwise, replace it with 0
                            else {
                                this.grid[row][col] = 0;
                            }
                        }
                    }
                    return false; //Unable to find a value
                }
                else {
                    if (isSafe(row, col, this.grid[row][col]) == false){ //Used to validate if initial input is unsolveable
                        return false;
                    }
                }
            }
        }

        //Base case is a filled board; the board is filled with no errors
        return true;
    }

    //Creates a string representation of the board
    @Override
    public String toString() {
        String board = "";
        for (int i = 0; i < boardLength; i++) {
            for (int j = 0; j < boardLength; j++) {
                board += grid[i][j] + " ";
            }
            board += "\n";
        }
        return board;
    }

    public static void main(String args[]) {
        //Creates blank board for user input
        //Asks for a full row of values 9 times w/ while loop
        //Takes a character, converts it as a string, parses it as an individual value of a Sudoku space
        //Sets the value of the space to the current row and position in the string, prints board and solution after

        int inputRow = 0;
        sudoku userBoard = new sudoku();
        while (inputRow < 9) {
            Scanner scan = new Scanner(System.in);
            System.out.print("Enter 9 consecutive integers to represent a single row of the Sudoku board (trailing values automatically are 0): ");
            String rowVals = scan.next();
            for (int i = 0; i < rowVals.length(); i++) {
                userBoard.grid[inputRow][i] = Integer.parseInt(String.valueOf(rowVals.charAt(i)));
            }
            inputRow++;
        }
        System.out.println("The input board: \n" + userBoard);
        userBoard.solveOutput();
        System.out.println(userBoard);


//        I'm leaving in all of my test cases in these comments :)
//
//        sudoku empty = new sudoku();
//        System.out.println(empty);
//        empty.solveOutput();
//        System.out.println(empty);
//
//        sudoku full = new sudoku();
//        full.grid = new int[][] {
//                {5,3,4,6,7,8,9,1,2},
//                {6,7,2,1,9,5,3,4,8},
//                {1,9,8,3,4,2,5,6,7},
//                {8,5,9,7,6,1,4,2,3},
//                {4,2,6,8,5,3,7,9,1},
//                {7,1,3,9,2,4,8,5,6},
//                {9,6,1,5,3,7,2,8,4},
//                {2,8,7,4,1,9,6,3,5},
//                {3,4,5,2,8,6,1,7,9}
//        };
//        System.out.println(full);
//        full.solveOutput();
//        System.out.println(full);
//
//        //Successfully solves an Easy board
//        sudoku easy = new sudoku();
//        easy.grid = new int[][] {
//                {4,9,0,1,5,7,0,0,0},
//                {0,1,8,0,9,0,0,0,0},
//                {7,5,0,2,8,4,1,0,6},
//                {0,6,0,4,1,5,0,7,0},
//                {1,0,0,7,0,0,4,0,0},
//                {0,0,0,9,0,8,0,6,1},
//                {0,0,7,5,0,0,0,1,3},
//                {6,4,0,0,0,0,2,0,0},
//                {5,0,1,0,7,0,0,8,0}
//        };
//        System.out.println("Easy: \n" + easy);
//        easy.solveOutput();
//        System.out.println(easy);
//
//        //Successfully solves an Expert board
//        sudoku expert = new sudoku();
//        expert.grid = new int[][]{
//                {0,0,5,0,7,0,2,0,6},
//                {0,0,3,0,2,0,5,0,0},
//                {0,0,0,0,0,0,0,0,0},
//                {0,0,6,0,4,5,0,0,0},
//                {0,0,0,0,0,0,0,0,9},
//                {0,0,0,1,0,0,3,7,0},
//                {7,0,0,9,0,0,0,0,0},
//                {0,8,0,2,0,0,0,6,0},
//                {4,0,0,0,0,3,0,8,0}
//        };
//        System.out.println("Expert: \n" + expert);
//        expert.solveOutput();
//        System.out.println(expert);
//
//        sudoku fail = new sudoku();
//        fail.grid = new int[][] {
//                {1,2,3,4,5,6,7,8,9},
//                {1,2,3,4,5,6,7,8,9},
//                {0,0,0,0,0,0,0,0,0},
//                {0,0,0,0,0,0,0,0,0},
//                {0,0,0,0,0,0,0,0,0},
//                {0,0,0,0,0,0,0,0,0},
//                {0,0,0,0,0,0,0,0,0},
//                {0,0,0,0,0,0,0,0,0},
//                {0,0,0,0,0,0,0,0,0}
//        };
//        fail.solveOutput();
//        System.out.println(fail);

    }
}
