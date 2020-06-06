import javafx.scene.image.Image;

public abstract class SnakeSegment extends GamePiece {
	private Direction dir;
	
	public SnakeSegment(int row, int col, Direction dir, Image img) {
		super(row, col, img);
		this.dir = dir;
	}
	
	public Direction getDir(){
		return dir;
	}
	
	public void setDir(Direction dir){
		this.dir = dir;
	}
	
	public void update(){
		if(dir.equals(Direction.up))
			setRow(getRow()-1);
		else if(dir.equals(Direction.down))
			setRow(getRow()+1);
		else if(dir.equals(Direction.right))
			setCol(getCol()+1);
		else
			setCol(getCol()-1);
	}
}
