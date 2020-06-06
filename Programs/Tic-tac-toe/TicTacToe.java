import java.util.Scanner;

public class TicTacToe {
	
	private int[][] board;
	//board is filled with 0's
	//make X be 1 
	//make O be 2
	private boolean isPlayerOnesTurn;
		public TicTacToe(){
		board = new int[3][3];
		isPlayerOnesTurn = true;
	}
//  (0,0),(0,1),(0,2)
//  (1,0),(1,1),(1,2)
//  (2,0),(2,1),(2,2)


	public void makeMove(){
		Scanner sc = new Scanner(System.in);
		int indexX = -1;
		int indexY = -1;
		int choice = 0;
		while(choice < 1 || choice > 9)
			choice = sc.nextInt();
		indexX = (choice - 1)/3;
		indexY = (choice - 1)%3;
		if(board[indexX][indexY]==0){
			board[indexX][indexY] = (isPlayerOnesTurn) ? 1 : 2;
			isPlayerOnesTurn = !isPlayerOnesTurn;
			displayBoard();
		}
		else makeMove();
	}
	
	public void makeMove(int row, int col){
		if(board[row][col]==0){
			board[row][col] = (isPlayerOnesTurn) ? 1 : 2;
			isPlayerOnesTurn = !isPlayerOnesTurn;
		}
	}
		
	public int isWinner(){
		int check=1;
		boolean isEmpty = false;
		
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<3;j++)
				check=check*board[i][j];
			if(check==1)
				return 1;
			else if(check==8)
				return 2;
			if(check==0)
				isEmpty = true;
			check = 1;
		}
		if(!isEmpty)
			return -1;
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<3;j++)
				check=check*board[j][i];
			if(check==1)
				return 1;
			else if(check==8)
				return 2;
			check=1;
			
		}
		if(board[1][1]*board[2][2]*board[0][0]==1)
			return 1;
		if(board[1][1]*board[2][2]*board[0][0]==8)
			return 2;
		if(board[0][2]*board[1][1]*board[2][0]==1)
			return 1;
		if(board[0][2]*board[1][1]*board[2][0]==8)
			return 2;	
		 return 0;
		}

	
	public void displayBoard(){
		//if 0, leave blank, 1 - X, 2 - O
		for(int r = 0; r < board.length; r++){
			for(int c = 0; c < board[r].length; c++){
				if(board[r][c] == 1)
					System.out.print(" X ");
				else if(board[r][c] == 2)
					System.out.print(" O ");
				else
					System.out.print("   ");
				if(c != 2)
					System.out.print("|");
				else 
					System.out.println();
				
			}
			if(r != 2)
				System.out.println("-----------");
		}
	}


	public int[][] getBoard() {
		return board;
	}
}
