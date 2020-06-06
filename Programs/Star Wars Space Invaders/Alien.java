import javafx.scene.image.Image;

public class Alien extends GamePiece {
	static Image img = new Image("tiefighter.png");
	private boolean alive;

	public Alien(int row, int col) {
		super(row, col, img);
		alive = true;
	}

	public boolean isAlive() {
		return alive;
	}

	public void kill() {
		alive = false;
	}
	
	public void moveX(int d){
		setCol(getCol()+d);
	}
	
	public void moveY(){
		setRow(getRow()+10);
	}

}
