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
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.Animation.Status;
import javafx.scene.media.*;

public class SpaceMain extends Application {

	final static int WIN_WIDTH = 600;
	final static int WIN_HEIGHT = 600;
	final static int NUM_ROWS = WIN_HEIGHT;
	final static int NUM_COLS = WIN_WIDTH;
	private double frameRate = 30; // frames per second
	private int frame, frameHit;
	private int score;
	private GraphicsContext gc;
	private Player ship;
	private Enemy a;
	private Boss b;
	private Bullet player, enemy;
	private ArrayList<Wall> walls;
	private static Scene scene;
	private Timeline loop;
	private KeyFrame kf;
	static boolean gameOver, bossMode, laser;
	int startCol = NUM_COLS / 2;
	int startRow = NUM_ROWS - 35;

	public static void main(String[] args) {
		launch(args);
	}

	Media music, effect;
	MediaPlayer background, blaster;

	public void start(Stage stage) {
		// javafx setup
		Group root = new Group();
		Canvas canvas = new Canvas(WIN_WIDTH, WIN_HEIGHT);
		root.getChildren().add(canvas);
		gc = canvas.getGraphicsContext2D();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Space Invaders");
		stage.show();
		bossMode = false;
		laser = false;
		setup(startRow, startCol, 3, 0);

		loop = new Timeline();
		loop.setCycleCount(Timeline.INDEFINITE);
		kf = new KeyFrame(Duration.seconds(1.0 / frameRate), new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				run();
			}
		});
		loop.getKeyFrames().add(kf);
		loop.play();
	}

	public void setup(int row, int col, int life, int score) {
		String musicFile = "Soundtrack" + (1 + (int) (Math.random() * 3)) + ".mp3";
		music = new Media(this.getClass().getResource(musicFile).toString());
		background = new MediaPlayer(music);
		background.setOnEndOfMedia(new Runnable() {
			public void run() {
				background.seek(Duration.ZERO);
			}
		});
		background.play();
		this.score = score;
		// set up key handlers
		scene.setOnKeyPressed(new KeyPressHandler());
		gameOver = false;

		ship = new Player(row, col, life);
		player = new Bullet(ship, 50);

		walls = new ArrayList<Wall>();
		a = new Enemy(5, 4);
		b = new Boss(150, startCol);
		frame = 0;
		frameHit = -1;

		for (int i = 0; i < 4; i++) {
			walls.add(new Wall(NUM_ROWS - 80, 100 + i * (NUM_COLS - 80) / 4));
		}

		ArrayList<ArrayList<Alien>> array = a.getArray();
		Alien rand = array.get(array.size() - 1).get((int) Math.random() * array.get(array.size() - 1).size());
		enemy = new Bullet(rand, 10);

	}

	public void run() {
		if (!gameOver) {
			gc.fillRect(0, 0, WIN_WIDTH, WIN_HEIGHT);

			if (player.isVisible()) {
				player.draw(gc);
				player.update();
			}

			if (!bossMode) {
				if (enemy.isVisible()) {
					enemy.draw(gc);
					enemy.update();
				}

				if (enemy.collision(ship)) {
					if (ship.getLives() == 1) {
						kill(ship);
						gameOver = true;
					} else {
						ship.kill();
						effect = new Media(this.getClass().getResource("Hit.mp3").toString());
						blaster = new MediaPlayer(effect);
						blaster.play();
					}
					enemy.kill();

				}
				for (Wall w : walls)
					if (w.isActive()) {
						if (player.collision(w) && player.isVisible()) {
							w.kill();
							w.draw(gc);
							player.kill();
						}
						if (enemy.collision(w) && enemy.isVisible()) {
							w.kill();
							w.draw(gc);
							enemy.kill();
						}

					}

				for (ArrayList<Alien> a1 : a.getArray())
					for (Alien a2 : a1) {
						if (a2.isAlive() && player.isVisible() && player.collision(a2)) {
							// a2.kill();
							kill(a2);
							score += 100;
							player.kill();
						}
						if (a2.isAlive() && (ship.collision(a2) || a2.collision(ship))) {
							kill(a2);
							if (ship.getLives() == 1)
								kill(ship);
							else
								ship.kill();
						}
						if (a2.isAlive() && a2.getRow() >= 590) {
							kill(a2);
							kill(ship);
						}
						if (a2.isAlive()) {
							a2.draw(gc);
						}
					}
				a.update();
				if (a.getArray().isEmpty()) {
					background.stop();
					bossMode = true;
					setup(ship.getRow(), ship.getCol(), ship.getLives(), score);
				}

				if (!enemy.isVisible()) {
					ArrayList<ArrayList<Alien>> array = a.getArray();
					Alien rand = array.get(array.size() - 1)
							.get((int) (Math.random() * array.get(array.size() - 1).size()));
					enemy = new Bullet(rand, 10);
				}
				enemy.shoot();
			} else {
				if (b.getLives() > 0) {
					for (Wall w : walls)
						if (w.isActive()) {
							if (player.collision(w) && player.isVisible()) {
								w.kill();
								w.draw(gc);
								player.kill();
							}
						}
					if (player.isVisible() && player.collision(b)) {
						player.kill();
						if (b.getLives() == 1) {
							kill(b);
							score += 500;
							ship.addLife();
							bossMode = false;
						} else {
							b.kill();
							effect = new Media(this.getClass().getResource("Hit.mp3").toString());
							blaster = new MediaPlayer(effect);
							blaster.play();
						}

					}
					b.update();
					b.moveX(6);
					int length = 30;
					if (frame % 200 == 0) {
						laser = true;
						length = 20 + (int) (Math.random() * 10);
					}
					if (frame % 200 == length)
						laser = false;
					// laser=true;
					if (laser) {
						gc.setFill(Color.rgb(4 * (frame % 200), 255 - 4 * (frame % 200), 4 * (frame % 200)));
						// gc.setFill(Color.GREEN);
						fireLaser();
					}
					b.draw(gc);
					frame++;
				}
			}

			ship.draw(gc);
			for (Wall w : walls)
				if (w.isActive())
					w.draw(gc);
			gc.setFill(Color.WHITE);
			gc.setFont(Font.font(20));
			gc.setTextAlign(TextAlignment.LEFT);
			gc.fillText("Lives: " + ship.getLives(), 25, 35);
			gc.fillText("Score: " + score, 400, 35);
			if (bossMode)
				gc.fillText("Death Star Defences: " + b.getLives(), 25, 55);
			gc.setFill(Color.BLACK);

			if (ship.getLives() <= 0)
				gameOver = true;

		} else {
			String musicFile;
			if (Math.random() < 0.5)
				musicFile = "End.mp3";
			else
				musicFile = "End2.mp3";
			music = new Media(this.getClass().getResource(musicFile).toString());
			background.stop();
			background = new MediaPlayer(music);
			background.play();
			gc.setFill(Color.WHITE);
			gc.setFont(Font.font(60));
			gc.setTextAlign(TextAlignment.CENTER);
			gc.fillText("Game Over", 300, 300);
			gc.setFill(Color.BLACK);
			loop.pause();
		}

	}

	private void fireLaser() {

		gc.fillRect(b.getCol(), b.getRow(), 1, NUM_ROWS - 80 - b.getRow());

		Bullet laser = new Bullet(NUM_ROWS - 80, b.getCol());
		boolean hit = true;
		for (Wall w : walls)
			if (w.isActive())
				if (laser.collision(w))
					hit = false;
		if (hit) {
			gc.fillRect(laser.getCol(), laser.getRow(), 1, 200);
			laser.setRow(NUM_ROWS - 20);
			if (laser.collision(ship) && frame > frameHit + 42) {
				if (ship.getLives() == 1) {
					kill(ship);
					gameOver = true;
				} else {
					ship.kill();
					effect = new Media(this.getClass().getResource("Hit.mp3").toString());
					blaster = new MediaPlayer(effect);
					blaster.play();
					frameHit = frame;
				}
			}

		}

		gc.setFill(Color.BLACK);

	}

	public void kill(GamePiece piece) {
		piece.setImg(new Image("Explosion.png"));
		piece.draw(gc);
		piece.kill();
		if (piece instanceof Boss)
			effect = new Media(this.getClass().getResource("Destroyed.mp3").toString());
		else
			effect = new Media(this.getClass().getResource("explosion.mp3").toString());
		blaster = new MediaPlayer(effect);
		blaster.play();

	}

	public class KeyPressHandler implements EventHandler<KeyEvent> {
		public void handle(KeyEvent event) {
			String key = event.getCode().toString();

			if (key.equals("LEFT") || key.equals("A"))
				ship.moveLeft();
			if (key.equals("RIGHT") || key.equals("D"))
				ship.moveRight();
			if (key.equals("SPACE")) {
				if (!player.isVisible()) {
					player = new Bullet(ship, 10);
					effect = new Media(this.getClass().getResource("Star Wars Blaster sound effect.mp3").toString());
					blaster = new MediaPlayer(effect);
					blaster.play();
				}
				player.shoot();
			}

			if (key.equals("R")) {
				background.stop();
				loop.play();
				bossMode = false;
				setup(startRow, startCol, 3, 0);
			}
			if (key.equals("P"))
				if (loop.getStatus().equals(Status.RUNNING)) {
					loop.pause();
					background.pause();
				} else {
					background.play();
					loop.play();
				}
		}
	}

}