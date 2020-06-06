import processing.core.PApplet;

public class TicTacToeGraphics extends PApplet{

	public static void main(String[] args) {
		PApplet.main("TicTacToeGraphics");
	}
	private TicTacToe t;
	private int[][] board;
	public void settings(){
		
		size(600,600);
	}
	
	public void setup(){
		background(255);
		t = new TicTacToe();
		board = t.getBoard();
		drawBoard();
	}
	public void draw(){
		
	}

	public void drawBoard(){
		line(0,200,600,200);
		line(0,400,600,400);
		line(200,0,200,600);
		line(400,0,400,600);
		for(int r = 0; r < board.length; r++){
			for(int c = 0; c < board[r].length; c++){
				if(board[r][c] == 1){
					line(r*200 + 50, c*200 +50, r*200 + 150, c*200+150);
					line(r*200 + 50, c*200 +150, r*200 + 150, c*200+50);
				}
				if(board[r][c] == 2){
					ellipse(r*200 + 100, c*200 + 100, 100,100);
					
				}
			}
		}
	}
	
	public void mousePressed(){
		int row = mouseX/200;
		int col = mouseY/200;
		if(t.isWinner() == 0)
			t.makeMove(row, col);
		drawBoard();
	}
	
}
