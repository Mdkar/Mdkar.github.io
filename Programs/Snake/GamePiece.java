import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class GamePiece implements Drawable {
	private int row, col;
	private Image img;
	final static int PIECE_LENGTH = 20;
	
	public GamePiece(int row, int col, Image img) {
		this.row = row;
		this.col = col;
		this.img = img;
	}

	
	@Override
	public void update() {

	}
	
	public boolean collision(GamePiece a){
		if(a.getRow() == this.getRow() && this.getCol() == a.getCol())
			return true;
		return false;
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.drawImage(img, col*PIECE_LENGTH, row*PIECE_LENGTH, PIECE_LENGTH, PIECE_LENGTH);
	}

	public void setRow(int row) {
		this.row = row;
	}


	public void setCol(int col) {
		this.col = col;
	}


	public void setImg(Image img) {
		this.img = img;
	}


	public int getRow() {
		return row;
	}


	public int getCol() {
		return col;
	}

}
