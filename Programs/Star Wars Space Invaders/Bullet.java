import javafx.scene.image.Image;

public class Bullet extends GamePiece {

	private GamePiece source;
	private int direction;
	private int speed;
	private boolean visible;
	static Image img1 = new Image("bigBoomBoomPowWhoosh.png");
	static Image img2 = new Image("bigBoomBoomPowWhoosh2.png");
	
	public Bullet(int row, int col){
		super(row,col,img2);
	}
	
	public Bullet(GamePiece source, int speed) {
		super(source.getRow(), source.getCol(), img2);
		this.source = source;
		this.speed = speed;
		if(source instanceof Player){
			direction = -1;
			setImg(img1);
		}
		else direction = 1;
		visible = false;
	}
	public void shoot(){
		visible = true;
	}
	public void update(){
		if(getRow()>=0 && getRow()<=SpaceMain.NUM_ROWS)
			setRow(getRow()+(direction*speed));
		else
			visible = false;	
	}
	
	public void kill(){
		visible = false;
	}
	
	public boolean isVisible(){
		return visible;
	}
	
	public void setSpeed(int speed){
		this.speed = speed;
	}

}
