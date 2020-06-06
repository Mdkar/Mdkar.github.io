import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.Animation.Status;

public class SnakeMain extends Application {

	final static int WIN_WIDTH = 600;
	final static int WIN_HEIGHT = 600;
	final static int NUM_ROWS = WIN_HEIGHT / GamePiece.PIECE_LENGTH;
	final static int NUM_COLS = WIN_WIDTH / GamePiece.PIECE_LENGTH;
	final static int INITIAL_LENGTH = 4;
	private double frameRate = 10; // frames per second
	private GraphicsContext gc;
	private ArrayList<SnakeSegment> snake;
	private static Scene scene;
	private Timeline loop;
	static boolean gameOver;
	private Food food;

	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage stage) {
		// javafx setup
		Group root = new Group();
		Canvas canvas = new Canvas(WIN_WIDTH, WIN_HEIGHT);
		root.getChildren().add(canvas);		
		gc = canvas.getGraphicsContext2D();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Snake");
		stage.show();

		setup();

		loop = new Timeline();
		loop.setCycleCount(Timeline.INDEFINITE);
		KeyFrame kf = new KeyFrame(Duration.seconds(1.0 / frameRate), new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				run();
			}
		});
		loop.getKeyFrames().add(kf);
		loop.play();
	}

	public void setup() {
		//set up key handlers
		scene.setOnKeyPressed(new KeyPressHandler());
		gameOver = false;
		//create snake
		snake = new ArrayList<SnakeSegment>();
		int startCol = NUM_COLS/2;
		int startRow = NUM_ROWS/2;
		snake.add(new Head(startRow, startCol));
		for(int i = 1; i < INITIAL_LENGTH; i++)
			snake.add(new Body(startRow-i, startCol, Direction.down, snake.get(i-1)));
		food = new Food();
		
	}
	
	public void run(){
		if(!gameOver){
			//update
			int x, y;
			do{
				y = (int)(Math.random()*SnakeMain.NUM_ROWS);
				x = (int)(Math.random()*SnakeMain.NUM_COLS); 
			}while(!foodSpace(x,y));
			int s = snake.size()-1;
			if(((Head)snake.get(0)).update(food)){
				food.respawn(x, y);
				if(snake.get(s).getDir().equals(Direction.down))
					snake.add(new Body(snake.get(s).getRow()-1, snake.get(s).getCol(), Direction.down, snake.get(s)));
				if(snake.get(s).getDir().equals(Direction.up))
					snake.add(new Body(snake.get(s).getRow()+1, snake.get(s).getCol(), Direction.up, snake.get(s)));
				if(snake.get(s).getDir().equals(Direction.left))
					snake.add(new Body(snake.get(s).getRow(), snake.get(s).getCol()+1, Direction.left, snake.get(s)));
				if(snake.get(s).getDir().equals(Direction.right))
					snake.add(new Body(snake.get(s).getRow(), snake.get(s).getCol()-1, Direction.right, snake.get(s)));
				//snake.get(s+1).update();
			}
			//if(snake.get(0).getCol() < 0 ||snake.get(0).getCol() >= NUM_COLS||snake.get(0).getRow() < 0 ||snake.get(0).getRow() >= NUM_ROWS)
				//gameOver = true;
			for(int i = snake.size()-1; i > 0; i--)
				snake.get(i).update();
			//draw
			//clear screen
			
			
			gc.clearRect(0, 0, WIN_WIDTH, WIN_HEIGHT);
			
			for(SnakeSegment sn: snake){
				if(sn.getCol()<0)
					sn.setCol(NUM_COLS-1);
				if(sn.getRow()<0)
					sn.setRow(NUM_ROWS-1);
				if(sn.getCol()>=NUM_COLS)
					sn.setCol(0);
				if(sn.getRow()>=NUM_ROWS)
					sn.setRow(0);
				sn.draw(gc);
				if(!(sn instanceof Head)){
					if(sn.collision(snake.get(0)))
						gameOver = true;
				}	
			}
			snake.get(0).draw(gc);
			food.draw(gc);
			gc.fillText("Score: " + (snake.size() - 4), 20, 20,60);
			
		}	
	}
	
	public boolean foodSpace(int x, int y){
		for(SnakeSegment sn: snake){
			if(sn.getCol() == x && sn.getRow() == y)
				return false;
		}
		return true;
	}
	
	
	
	public class KeyPressHandler implements EventHandler<KeyEvent>{
		public void handle(KeyEvent event){
			String key = event.getCode().toString();
			if((!snake.get(0).getDir().equals(Direction.down) && key.equals("UP"))||
					(!snake.get(0).getDir().equals(Direction.up) && key.equals("DOWN"))||
					(!snake.get(0).getDir().equals(Direction.left) && key.equals("RIGHT"))||
					(!snake.get(0).getDir().equals(Direction.right) && key.equals("LEFT")))
				((Head) snake.get(0)).setDirection(key);
			
			if(key.equals("R"))
				setup();
			if(key.equals("SPACE"))
				if(loop.getStatus().equals(Status.RUNNING))
					loop.pause();
				else
					loop.play();
				
			
		}
	}

}