import javafx.scene.image.Image;

public class Body extends SnakeSegment {
	private SnakeSegment precedingSeg;
	private static Image snakeBody1 = new Image("snakeSegment1.png", GamePiece.PIECE_LENGTH, GamePiece.PIECE_LENGTH, false, false);
	private static Image snakeBody2 = new Image("snakeSegment2.png", GamePiece.PIECE_LENGTH, GamePiece.PIECE_LENGTH, false, false);
	public Body(int row, int col, Direction dir, SnakeSegment precedingSeg) {
		super(row, col, dir, snakeBody1);
		this.precedingSeg = precedingSeg;
		// TODO Auto-generated constructor stub
	}
	public void update(){
		super.update();//call snakeSegment update()
		setDir(precedingSeg.getDir());
		if(getDir().equals(Direction.down)||getDir().equals(Direction.up))
			setImg(snakeBody1);
		else
			setImg(snakeBody2);
		
		
	
	}
	
}
