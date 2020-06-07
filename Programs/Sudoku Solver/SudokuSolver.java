import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class SudokuSolver {
	private int[][] numbers;
	private ArrayList<int[][]> moves = new ArrayList<int[][]>();
	
	/**
	 * creates a SudokuSolver with board values coming from the given filePath
	 * @param filePath
	 */
	public SudokuSolver(String filePath) {
		File f = new File(filePath);
		try {
			numbers = new int[9][9];
			Scanner sc = new Scanner(f);
			for(int i = 0; i<9; i++)
				for(int j = 0; j<9; j++)
					if(sc.hasNextInt())
						numbers[i][j] = sc.nextInt();
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}

	}
	/**
	 * solves the sudoku puzzle recursively, starting at the given row and column (0,0) to solve the whole puzzle
	 * @param row
	 * @param col
	 * @return whether the puzzle is solved
	 */
	public boolean solve(int row, int col) {
		if (row == numbers.length){
			row = 0;
			if (++col == numbers[row].length){
				return true;
			}
		}
		if (numbers[row][col] != 0) {
			return solve(row+1,col);
		}
		for (int k = 1; k <= numbers.length; k++) {
			if (isPossibleDigit(k,row,col)) {
				numbers[row][col] = k;
				storeMove();
				if (solve(row+1,col) == true) {
					return true;
				}
			}
		}
		numbers[row][col] = 0;
		return false;
	}
	/**
	 * add current board state to the list of board states
	 */
	private void storeMove() {
		int[][] board = new int[9][9];
		for(int r = 0; r < 9; r++)
			for(int c = 0; c < 9; c++)
				board[r][c] = numbers[r][c];
		moves.add(board);
	}

	/**
	 * 
	 * @param val
	 * @param row
	 * @param col
	 * @return whether the given value can be placed at the given row and column
	 */
	public boolean isPossibleDigit(int val, int row, int col) {
		if(isInRow(val,row) || isInColumn(val,col) || isInSquare(val,row,col))
			return false;
		else
			return true;
	}
	
	/**
	 * 
	 * @param val
	 * @param row
	 * @return whether the given value is already in the given row
	 */
	public boolean isInRow(int val, int row) {
		for(int v: numbers[row])
			if(v == val)
				return true;
		return false;
	}
	/**
	 * 
	 * @param val
	 * @param col
	 * @return whether the given value is already in the given column
	 */
	public boolean isInColumn(int val, int col) {
		for(int r = 0; r < numbers.length; r++)
			if(numbers[r][col] == val)
				return true;
		return false;
	}
	/**
	 * 
	 * @param val
	 * @param row
	 * @param col
	 * @return whether the given value is already in the 3x3 square containing the given row and column
	 */
	public boolean isInSquare(int val, int row, int col) {
		int[][] square = getSquare(row,col);
		for(int r = 0; r < 3; r++)
			for(int v: square[r])
				if(v == val)
					return true;
		return false;
	}
	/**
	 * 
	 * @param row
	 * @param col
	 * @return the 3x3 square containing the given row and column
	 */
	public int[][] getSquare(int row, int col) {
		int sqRow = 3*((int)(row/3));
		int sqCol = 3*((int)(col/3));
		int[][] square = new int[3][3];
		for(int r = 0; r < 3; r++)
			for(int c = 0; c < 3; c++)
				square[r][c] = numbers[sqRow + r][sqCol + c];
			return square;
		
	}
	/**
	 * displays the puzzle in console
	 */
	public void showPuzzle() {
		for(int r = 0; r < numbers.length; r++){
			for(int c = 0; c < numbers[r].length; c++)
				System.out.print(numbers[r][c] + " ");
			System.out.println();
		}		
	}

	/**
	 * 
	 * @return array of numbers on the board
	 */
	public int[][] getNumbers() {
		return numbers;
	}
	/**
	 * 
	 * @return list of board states completed
	 */
	public ArrayList<int[][]> getMoves(){
		return moves;
	}

}









