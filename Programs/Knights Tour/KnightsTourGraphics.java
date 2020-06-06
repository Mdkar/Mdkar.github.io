import java.util.ArrayList;
import java.util.Random;

import ddf.minim.AudioOutput;
import ddf.minim.Minim;
import ddf.minim.ugens.FilePlayer;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PShape;

public class KnightsTourGraphics extends PApplet{
	private final int SPEED = 30;
	private int N = 8, i;
	private float width1, height1, currX, currY, nextX, nextY;
	private Random randy = new Random();
	private PImage knight;
	private PShape s;
	private ArrayList<Square> answer;
	private Knight m;
	Minim minim;
	FilePlayer filePlayer;
	AudioOutput out;
	public static void main(String[] args) {
		PApplet.main("KnightsTourGraphics");
	
	}
	
	public void settings(){
		size(700,700,P3D);
	}
	
	public void setup(){
		//simulate 600 x 600 screen
		width1 = 600;
		height1 = 600;
		m = new Knight(new Square(randy.nextInt(N),randy.nextInt(N),0),N,N);
		answer = new ArrayList<Square>();
		answer = m.solve();
		if(N%2 == 0)
			answer.add(m.getStartingSquare()); //closed tour if N even
		background(255);
		imageMode(CENTER);
		//image and 3d model setup
		knight = loadImage("knight.png");
		s = loadShape("scad_chess_knight.obj");
		s.scale(16/N);
		//music player
		minim = new Minim(this);
		filePlayer = new FilePlayer( minim.loadFileStream("naylor-abacaba-full.mp3",1,true));
		filePlayer.loop();
		out = minim.getLineOut();
		filePlayer.patch(out);
		i = 0; //iterator
		draw(); //makes switching sizes less buggy for some reason
		
	}
	public void draw(){
		lights();	//for 3D knight to look good
		background(255);
		image(knight,width/2,-100, knight.width/2, knight.height/2);
		drawBoard();
		//display numbers
		textSize((width1/(N*2)));
		textAlign(CENTER,CENTER);
		fill(128);
		for(int j = i; j >= 0; j--) {
			if(j<N*N)
				text(j+1, answer.get(j).getRow()*height1/N + width1/(N*2)+50, answer.get(j).getColumn()*width1/N + height/(N*2)+50,1);
		}
		//sets position of piece and draws at that position of first half of every 30 frames
		//frameRate() does not work for some reason, and using frameCount allows for finer animation
		if(i < answer.size()) {
			currX = (answer.get(i).getRow())*height1/N + width1/(N*2)+50;
			currY = (answer.get(i).getColumn())*width1/N + height1/(N*2)+50;
			if((frameCount)%SPEED <= (SPEED/2))
				shape(s,currX, currY);
		}
		//runs for the last half of every 30 frames
		if((i < answer.size() - 1) && (frameCount)%SPEED > (SPEED/2)) {
			nextX = (answer.get(i+1).getRow())*height1/N + width1/(N*2)+50;
			nextY = (answer.get(i+1).getColumn())*width1/N + height1/(N*2)+50;
			float dX = (nextX-currX)*(frameCount%(SPEED/2))/(SPEED/2);
			float dY = (nextY-currY)*(frameCount%(SPEED/2))/(SPEED/2);
			shape(s,currX+dX,currY+dY);
			if(frameCount%SPEED >= SPEED-1)
				i++; //next move after frame 29/30
		}
		camera(width/2, height, (height/2) / tan(PI/6), width/2, height/2, 0, 0, 1, 0); //shifts camera to give 3D perspective
		
	}
	/**
	 * draws grid of black and white squares
	 */
	public void drawBoard(){
		for(int r = 0; r < N; r++){
			for(int c = 0; c < N; c++){
				fill((r+c+1)%2 * 255);
				rect(r*(width1/N)+50,c*(height1/N)+50,(width1/N),(height1/N));
			}
		}
	}
	public void keyPressed() {
		//increases/decreases size, pauses audio, prints size to console, and reruns setup()
		if (key == CODED) {
			if (keyCode == UP) {
				N++;
				filePlayer.pause();
				System.out.print(N);
				setup();
			}
			if (keyCode == DOWN && N > 6) {
				N--;
				filePlayer.pause();
				System.out.print(N);
				setup();
			}
		}
	}

}
