import javafx.scene.image.Image;

public class Head extends SnakeSegment {
	private static Image img1 = new Image("snakeHeadUp.png");
	private static Image img2 = new Image("snakeHeadDown.png");
	private static Image img3 = new Image("snakeHeadRight.png");
	private static Image img4 = new Image("snakeHeadLeft.png");
	public Head(int row, int col) {
		super(row, col, Direction.down, img2);
	}
	
	public void setDirection(String dir){
		if(dir.equals("RIGHT")){
			super.setDir(Direction.right);
			super.setImg(img3);
		}
		else if(dir.equals("LEFT")){
			super.setDir(Direction.left);
			super.setImg(img4);
		}
		else if(dir.equals("DOWN")){
			super.setDir(Direction.down);
			super.setImg(img2);
		}
		else if(dir.equals("UP")){
			super.setDir(Direction.up);
			super.setImg(img1);
		}
	}
	
	public boolean update(Food f){
		update();
		if(this.collision(f)){
			return true;
		}
		return false;
	}
}
