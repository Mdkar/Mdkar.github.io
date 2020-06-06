import javafx.scene.image.Image;

public class Boss extends GamePiece {
	
	public int lives, direction;
	static Image img = new Image("death star.png");
	
	public Boss(int row, int col) {
		super(row, col, img);
		lives = 3;
		direction = 1;
	}

	public void kill() {
		lives--;
		
	}

	public int getLives() {
		return lives;
	}

	public void update() {
		if(getCol() >= SpaceMain.WIN_WIDTH-50 || getCol()<=50)
			direction = -1*direction;
		
	}
	
	public void moveX(int d){
		setCol(getCol()+(direction*d));
	}

}
