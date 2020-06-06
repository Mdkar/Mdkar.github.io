import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class GamePiece implements Drawable {
	private int row, col;
	private Image img;
	
	public GamePiece(int row, int col, Image img) {
		this.row = row;
		this.col = col;
		this.img = img;
	}

	public abstract void kill();
	
	public boolean collision(GamePiece a){
		
		if(Math.abs(a.getRow() - this.getRow())<=a.img.getHeight()/2 && Math.abs(a.getCol() - this.getCol())<=a.img.getWidth()/2)
			return true;
		return false;
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.drawImage(img, col - img.getWidth()/2, row - img.getHeight()/2);
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
