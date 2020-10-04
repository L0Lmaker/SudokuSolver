public class SudokuSolver {
    public static void main(String[] args) {
        //0 represents empty cell
        int[][] grid = {
                { 8, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 3, 6, 0, 0, 0, 0, 0 },
                { 0, 7, 0, 0, 9, 0, 2, 0, 0 },
                { 0, 5, 0, 0, 0, 7, 0, 0, 0 },
                { 0, 0, 0, 0, 4, 5, 7, 0, 0 },
                { 0, 0, 0, 1, 0, 0, 0, 3, 0 },
                { 0, 0, 1, 0, 0, 0, 0, 6, 8 },
                { 0, 0, 8, 5, 0, 0, 0, 1, 0 },
                { 0, 9, 0, 0, 0, 0, 4, 0, 0 }
        };

        System.out.println("Input grid");
        printBoard(grid);
        System.out.println();
        if(solve(grid)) {
            System.out.println("Solved grid: ");
            printBoard(grid);
        }else{
            System.out.println("This board has no solution!: ");
        }

    }


    //Requires: 2d array of sudoku grid
    //Modifies: Sudoku grid
    //Effects: Solves the sudoku grid with a valid solution
    public static boolean solve(int[][] grid){
        for(int row=0; row<9; row++){
            for(int col=0; col<9; col++){
                if(grid[row][col]==0){
                    for(int val=1; val<=9; val++){
                        grid[row][col]=val;
                        if(isValid(grid, row, col) && solve(grid))
                            return true;
                        grid[row][col] = 0;
                    }
                    return false;
                }

            }
        }
        return true;
    }

    //Requires: sudoku grid and co ordinates of last modified cell
    //Effects: checks if the cell modification is acceptable
    public static boolean isValid(int[][] grid, int row, int col){
        return (rowValid(grid, row, col) && colValid(grid, row, col) && sectionValid(grid, row, col));
    }

    //Requires: sudoku grid and number
    //Effects: checks if the number already exists in the row
    public static boolean rowValid(int[][] grid, int row, int col){
        int count=0;
        for(int elem: grid[row]){
            if (elem==grid[row][col])
                count++;
        }
        return count<=1;
    }

    //Requires: sudoku grid and number
    //Effects: checks if the number already exists in the column
    public static boolean colValid(int[][] grid, int row, int col){
        int[] colElem = new int[9];
        for(int i=0; i<9; i++){
            colElem[i] = grid[i][col];
        }
        int count=0;
        for(int elem:colElem){
            if(elem==grid[row][col]){
                count++;
            }
        }
        return count<=1;
    }

    //Requires: sudoku grid and number
    //Effects: checks if the number already exists in the column
    public static boolean sectionValid(int[][] grid, int row, int col){
        int sRow = ((int)(row/3))*3;
        int sCol = ((int)(col/3))*3;
        int[] elems = new int[9];
        int count=0;
        for(int i=sRow; i<=sRow+2; i++){
            for(int j=sCol; j<=sCol+2; j++){
                elems[count] = grid[i][j];
                count++;
            }
        }

        count=0;
        for(int elem:elems){
            if(elem==grid[row][col]){
                count++;
            }
        }
        return count<=1;
    }

    //Requires: sudoku grid as 2d array
    //Effects: prints out grid on console
    public static void printBoard(int[][] grid){
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                System.out.print(grid[i][j]+" ");
            }
            System.out.println();
        }
    }


}
