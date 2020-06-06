import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Random;

public class Knight {
	private Square currentSquare;
	private Square startingSquare;
	private boolean[][] board;


	/**
	 * Creates a Knight with board of size rows x columns.
	 * Sets the value of board to true in the Square represented
	 * by s. Sets all other board values to false.
	 * Sets currentSquare and startingSquare to s.
	 * @param s the starting Square for this Knight
	 * @param rows the number of rows in this Knight's board
	 * @param cols the number of columns in this Knight's board
	 */
	public Knight(Square s, int rows, int cols) {
		board = new boolean[rows][cols];
		startingSquare = s;
		currentSquare = s;
		board[s.getRow()][s.getColumn()] = true;
	}
	
	/**
	 * Returns this Knight's current Square.
	 * @return this Knight's current Square.
	 */
	public Square getCurrentSquare() {
		return currentSquare;
	}

	/**
	 * Returns this Knight's starting Square.
	 * @return this Knight's starting Square.
	 */
	public Square getStartingSquare() {
		return startingSquare;
	}

	/**
	 * Returns this Knight's board.
	 * @return this Knight's board.
	 */
	public boolean[][] getBoard() {
		return board;
	}

	/**
	 * Returns a list of Squares representing a Knights Tour for this Knight, making it a closed tour if possible
	 * @return a list of Squares representing a Knights Tour for this Knight
	 */
	public ArrayList<Square> solve() {
		ArrayList<Square> sequence = new ArrayList<Square>();
		
		int pos = 1;
		sequence.add(currentSquare);
		do {
			board[currentSquare.getRow()][currentSquare.getColumn()] = true;
			ArrayList<Square> possible = getPossibleSquares();
			if (possible.isEmpty()) {
				sequence.clear();
				sequence.add(startingSquare);
				pos = 1;
				currentSquare = startingSquare;
				clearBoard();
			}
			else {
				Square best = getBestSquare(possible);
				sequence.add(best);
				currentSquare = best;
				pos++;
			}			
		} while (pos < board.length*board[0].length || ((board.length*board[0].length)%2 == 0 && pos >= board.length*board[0].length && !startIsReachableFromCurrent()));		
		System.out.println(currentSquare + "," + startingSquare);
		return sequence;
	}

	/**
	 * Determines if starting Square is reachable from current Square.
	 * @return true if starting Square is reachable from current Square, false otherwise
	 */
	public boolean startIsReachableFromCurrent() {
		int sr = startingSquare.getRow();
		int sc = startingSquare.getColumn();
		int cr = currentSquare.getRow();
		int cc = currentSquare.getColumn();
		return Math.abs(sr-cr) + Math.abs(sc-cc) == 3 && Math.abs(sr-cr) * Math.abs(sc-cc) > 0;
	}
	
	/**
	 * Returns a Square with the smallest score in possible.
	 * If several Squares in possible have the same lowest score,
	 * one of these Squares is selected at random and returned.
	 * @param possible the list of Squares
	 * @return a Square with the smallest score in possible
	 */
	public Square getBestSquare(ArrayList<Square> possible) {
		int min = possible.get(0).getScore();
		ArrayList<Integer> index = new ArrayList<Integer>();
		for(int i = 0; i < possible.size(); i++) {
			if(possible.get(i).getScore() < min){
				min = possible.get(i).getScore();
				index = new ArrayList<Integer>();
				index.add(i);
			}
			else if(possible.get(i).getScore() == min)
				index.add(i);
		}
		return possible.get(index.get((int) (Math.random()*index.size())));
	}

	/**
	 * Sets all values in this Knight's board to false.
	 */
	public void clearBoard() {
		for(int r = 0; r < board.length; r++)
			for(int c = 0; c < board[0].length; c++)
				board[r][c] = false;
	}

	/**
	 * Returns a list of all Squares that are within one knight move of
	 * this Knight's current Square.
	 * Each Square in the returned list has been given a score representing
	 * the number of unvisited Squares that can be reached (with a knight move) from that Square.
	 * @return a list of all Squares that are within one knight move of
	 * this Knight's current Square
	 */
	public ArrayList<Square> getPossibleSquares() {
		ArrayList<Square> possible = new ArrayList<Square>();
		int cr = currentSquare.getRow();
		int cc = currentSquare.getColumn();
		for(int r = cr-2; r <= cr + 2; r++)
			for(int c = cc-2; c <= cc+2; c++)
				if(isValid(r,c) && Math.abs(r-cr) + Math.abs(c-cc) == 3 && Math.abs(r-cr) * Math.abs(c-cc) > 0 && !board[r][c]){
					Square sq = new Square(r,c,getScoreOfSquare(r,c));
					possible.add(sq);
				}
		return possible;
	}

	/**
	 * Returns the number of unvisited Squares that can be reached (with a knight move) from the Square at row, col.
	 * @param row the row
	 * @param col the column
	 * @return the number of unvisited Squares that can be reached (with a knight move) from the Square at row, col
	 */
	public int getScoreOfSquare(int row, int col) {
		int score = 0;
		for(int r = row-2; r <= row + 2; r++)
			for(int c = col-2; c <= col+2; c++)
				if(isValid(r,c) && Math.abs(r-row) + Math.abs(c-col) == 3 && Math.abs(r-row) * Math.abs(c-col) > 0 && !board[r][c])
						score++;
		return score;
	}

	/**
	 * Returns true if the square at row r, column c is in this Knight's board; returns false otherwise.
	 * @param r the row
	 * @param c the column
	 * @return true if the square at row r, column c is in this Knight's board; false otherwise
	 */
	public boolean isValid(int r, int c) {
		return r < board.length && r >= 0 && c < board[0].length && c >= 0;
	}	
}
