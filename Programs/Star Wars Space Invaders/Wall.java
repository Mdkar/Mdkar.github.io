import javafx.scene.image.Image;

public class Wall extends GamePiece {
	
	private int lives;
	private String[] walls = {"Wall1.png","Wall2.png","Wall3.png","Wall4.png", "Wall5.png"};
	private Image img;
	private boolean active;
	public Wall(int row, int col) {
		super(row, col, new Image("Wall5.png"));
		lives = 5;
		active = true;
	}
	public void kill(){
		//System.out.println(walls[lives-2]);
		lives--;
		if(lives > 0){
			setImg(new Image(walls[lives-1]));
		}
		else
			active = false;
	}
	public boolean isActive(){
		return active;
	}
	
	

}
