public class sudoku {
    /*
    The sudoku class contains a 2-dimensional array which stores the values of the 9x9 sudoku board
    The purpose of the class is to contain sudoku grids and solve them.
     */

    int[][] grid;
    int boardLength = 9;

    public sudoku() {
        grid = new int[boardLength][boardLength];
        for (int i = 0; i < boardLength; i++) {
            for (int j = 0; j < boardLength; j++) {
                grid[i][j] = 0;
            }
        }
    }

    //Checks for integer conflicts in rows, columns, and 3x3 grids and returns a boolean
    private boolean isSafe(int row, int col, int num) {

        //Split the row and col check in case they vary (affects range)

        //Check if num is already in the row
        for (int i = 0; i < boardLength; i++) {
            if (grid[row][i] == num) {
                return false;
            }
        }

        //Check if num is already in column
        for (int i = 0; i < boardLength; i++) {
            if (grid[i][col] == num) {
                return false;
            }
        }

        //Check for a conflict in a 3x3
        //Partitions convert 0-2 to 0, 3-5 to 3, 6-8 to 6
        int rowPartition = row - row % 3;
        int colPartition = col - col % 3;
        for (int i = rowPartition; i < rowPartition + 3; i++) {
            for (int j = colPartition; j < colPartition + 3; j++) {
                if (grid[i][j] == num) {
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

    //Solves the sudoku board by going through rows and columns using recursive backtracking in a nested loop
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
                    return false;
                }
            }
        }

        //Base case is a filled board; the board is filled with no errors
        return true;
    }

    //toString stores the board in a multi-line string
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
        //Test an empty board
        sudoku empty = new sudoku();
        System.out.println(empty);
        empty.solveOutput();
        System.out.println(empty);

        //Test a full board
        sudoku full = new sudoku();
        full.grid = new int[][] {
                {5,3,4,6,7,8,9,1,2},
                {6,7,2,1,9,5,3,4,8},
                {1,9,8,3,4,2,5,6,7},
                {8,5,9,7,6,1,4,2,3},
                {4,2,6,8,5,3,7,9,1},
                {7,1,3,9,2,4,8,5,6},
                {9,6,1,5,3,7,2,8,4},
                {2,8,7,4,1,9,6,3,5},
                {3,4,5,2,8,6,1,7,9}
        };
        System.out.println(full);
        full.solveOutput();
        System.out.println(full);

        //Successfully solves an Easy board
        sudoku easy = new sudoku();
        easy.grid = new int[][] {
                {4,9,0,1,5,7,0,0,0},
                {0,1,8,0,9,0,0,0,0},
                {7,5,0,2,8,4,1,0,6},
                {0,6,0,4,1,5,0,7,0},
                {1,0,0,7,0,0,4,0,0},
                {0,0,0,9,0,8,0,6,1},
                {0,0,7,5,0,0,0,1,3},
                {6,4,0,0,0,0,2,0,0},
                {5,0,1,0,7,0,0,8,0}
        };
        easy.solveOutput();
        System.out.println("Easy: \n" + easy);

        //Successfully solves an Expert board
        sudoku expert = new sudoku();
        expert.grid = new int[][]{
                {0,0,5,0,7,0,2,0,6},
                {0,0,3,0,2,0,5,0,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,6,0,4,5,0,0,0},
                {0,0,0,0,0,0,0,0,9},
                {0,0,0,1,0,0,3,7,0},
                {7,0,0,9,0,0,0,0,0},
                {0,8,0,2,0,0,0,6,0},
                {4,0,0,0,0,3,0,8,0}
        };
        expert.solveOutput();
        System.out.println("Expert: \n" + expert);
    }
}
