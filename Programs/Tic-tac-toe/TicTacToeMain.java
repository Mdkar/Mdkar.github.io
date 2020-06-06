
public class TicTacToeMain {

	public static void main(String[] args) {
		TicTacToe t = new TicTacToe();
		while(t.isWinner() == 0){
			t.displayBoard();
			System.out.println("Enter number 1-9");
			t.makeMove();
		}
		t.displayBoard();
		if(t.isWinner() > 0)
			System.out.println("player " + t.isWinner() + " wins");
		else if(t.isWinner() == -1)
			System.out.println("Draw :(");
	}

}
