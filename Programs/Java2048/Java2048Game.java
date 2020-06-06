import java.util.ArrayList;

import processing.core.PApplet;

public class Java2048Game extends PApplet {

	private int score;
	private Java2048 m;
	public static void main(String[] args) {
		PApplet.main("Java2048Game");
		
	}
	
	public void settings(){
		size(500,600);
		
	}
	int run = 0;
	public void setup(){
		if(run == 0){
			height = height-100;
			run++;
		}
		score = 0;
		m = new Java2048();
		//background(255);
		//drawBoard();
	}
	
	public void draw(){
		background(255);
		drawBoard();
		if(!m.isMovePossible()){
			textSize(64);
			fill(255,0,0);
			textAlign(CENTER,CENTER);
			text("GAME OVER", width/2, height/2);
		}
	}
	
	public void drawBoard(){
		for(int i = 0; i <= 3; i++){
			for(int j = 0; j <= 3; j++){
				if(m.getBoard().get(i).get(j)==0)
					fill(255);
				else
					fill((int) 255/(1+log(m.getBoard().get(i).get(j))));
				rect(j*(width/4),i*(height/4),(width/4),(height/4));
				textSize(32);
				textAlign(CENTER,CENTER);
				fill(255);
				text(m.getBoard().get(i).get(j).toString(),j*(width/4) + width/8,i*(height/4)+height/8);
			}
		}
		
		textSize(24);
		fill(0);
		text("Score: " + score, width/2, height + 25);
		text("Press 'r' to restart", width/2, height + 50);
		textSize(12);
		text("Created by Mihir Dhamankar Dec 2017", width/2, height + 80);
	}
	int it = 0;
	public void keyPressed(){
		it++;
		//System.out.println("press" + it);
		if (key == CODED) {
			if (keyCode == LEFT && m.isMovePossible() && m.isSignificant("left")){
				score += m.left();
				m.nextTile();
			}
			if (keyCode == RIGHT && m.isMovePossible() && m.isSignificant("right")){
				score += m.right();
				m.nextTile();
			}
			if (keyCode == UP && m.isMovePossible() && m.isSignificant("up")){
				score += m.up();
				m.nextTile();
			}
			if (keyCode == DOWN && m.isMovePossible() && m.isSignificant("down")){
				score += m.down();
				m.nextTile();
			}
		}
		
		if(key == 'r')
			setup();
	}

}
