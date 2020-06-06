import javafx.scene.image.Image;

public class Food extends GamePiece {
	
	private static Image food = new Image("food.png");
	public Food() {
		super((int)(Math.random()*SnakeMain.NUM_ROWS), (int)(Math.random()*SnakeMain.NUM_COLS), food);
		//System.out.println(this.getRow() + "," + this.getCol());
	}
	
	public void respawn(int x, int y){
		setRow(y);
		setCol(x);
		
	}

}
