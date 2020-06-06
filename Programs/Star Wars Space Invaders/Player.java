import javafx.scene.image.Image;

public class Player extends GamePiece {
	
	private int lives;
	static Image img = new Image("X-wing.png");
	public Player() {
		super(SpaceMain.NUM_ROWS, SpaceMain.NUM_COLS/2, img);
		lives = 3;
	}
	public Player(int row, int col, int n) {
		super(row, col, img);
		lives = n;
	}
	
	public int getLives(){
		return lives;
	}
	
	public void moveLeft(){
		if(getCol()>20)
			setCol(getCol()-5);
	}
	
	public void moveRight(){
		if(getCol()<SpaceMain.NUM_COLS-20)
			setCol(getCol()+5);
	}
	
	public void kill(){
		lives--;
	}
	public void addLife() {
		lives++;
		
	}
	

}
