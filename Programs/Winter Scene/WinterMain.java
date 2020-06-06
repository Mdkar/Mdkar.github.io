import java.util.ArrayList;

import ddf.minim.AudioOutput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class WinterMain extends PApplet{

	Minim minim;
	AudioOutput out;
	float t;
	PImage cabin;
	ArrayList<PVector> snowflakes = new ArrayList<PVector>();
	ArrayList<Float> speed = new ArrayList<Float>();
	int snowN = 100;
	float[] snowX = new float[snowN];
	float[] snowY = new float[snowN];
	float[] speedY = new float[snowN];
	float speedX;
	
	public static void main(String[] args) {
		PApplet.main("WinterMain");
	}
	
	public void settings(){
		fullScreen();
	}
	
	public void setup(){
		cabin = loadImage("Cabin.png");
		
		//initializes audio player
		minim = new Minim(this);
		out = minim.getLineOut();
		//pauses player to first generate sequence
		out.pauseNotes();
		out.setNoteOffset((float) 0.25); //0.25 sec between notes (this may be redundant, but it ensures smoothness)
		t = 0; //start time
		abacaba(12); //creates sequence using 12 notes (should play for ~17 minutes)
		out.resumeNotes();//plays sequence
		
		textSize(16);
		textAlign(CENTER, CENTER);
		
		for(int i = 0; i < snowN; i++){
			snowX[i] = random(width);
			snowY[i] = random(height/2);
			speedY[i] = random(1,3);
			}
	}
	
	public void draw(){
		scenery();	
		
		if(frameCount%5 == 0){
			speedX = random(-3,3);
			float snowX = random(width); 
			snowflakes.add(new PVector(snowX,0)); //new koch snowflake at random x added to list every 5 frames
			speed.add(random(3,7));
			if(snowflakes.size()>=15){ //removes earliest koch snowflake after 15 on screen (reduces lag)
				snowflakes.remove(0);
				speed.remove(0);
			}
		}
		
		//background snow
		fill(255);
		noStroke();
		drawSnow(snowN-1);
		
		//koch snowflakes falling animation
		for(int i = 0; i < snowflakes.size(); i++){
			stroke(255);
			noFill();
			snowflake(4, 30, 90, new PVector(snowflakes.get(i).x+random(-1,1),snowflakes.get(i).y)); //draws each snowflake in list
			snowflakes.get(i).y+=speed.get(i); //moves y coordinate down 5
		}
		
		
		//draws center star with "happy holidays"
		stroke(255,200,0);
		fill(255,200,0);
		snowflake(frameCount%4+1,300,180*((frameCount/3)%2) + 90 , new PVector(width/2, height/2));
		fill(0);
		text("HAPPY HOLIDAYS\nFrom Mihir Dhamankar", width/2, height/2);
		
		//draws cabin with smoke
		image(cabin, 1000, 400, cabin.width/3, cabin.height/3);
		noStroke();
		smoke(10,1050,400);
		
		//draws 5 trees
		trees(5,50,height/2-50);
		trees(5,220,400);
		trees(5,125,550);
		trees(5,350,350);
		trees(5,365,630);
		
		
		
	}
	
	/**
	 * Creates a ABACABA pattern, lasts n^2 - 1 notes * 0.25 sec
	 * @param n notes
	 */
	public void abacaba(int n){
		if(n>=0){
			abacaba(n-1);
			out.playNote(t,(float) 0.25, toNote(n));
			t+=0.25;
			abacaba(n-1);
		}
	}
	
	/**
	 * Converts a note number to a name
	 * @param n (number of note from A3)
	 * @return note name as a String understood by minim
	 */
	public String toNote(int n){
		String[] notes = {"A", "B", "Db", "D", "E", "Gb", "Ab"}; //the notes in my pattern are modified to sound happy
		//String[] notes = {"A", "B", "C", "D", "E", "F", "G"}; //actual ABACABA pattern sounds too scary
		return notes[n%7] + (3+((n+5)/7)); //String is note letter and note octave e.g. "B5", the b is a flat
	}
	
	/**
	 * Draws plain background 
	 */
	public void scenery(){
		background(0,100,100);
		fill(255);
		noStroke();
		rect(0,height/2, width, height/2);
	}
	
	/**
	 * Draws my attempt at a koch snowflake
	 * @param n number of iterations
	 * @param side length
	 * @param angle of orientation, must be 90 or 270 (or equivalent)
	 * @param center as a  PVector: new PVector(x,y);
	 */
	public void snowflake(int n,float side, float angle, PVector center){
		if(n>0){
			//calculates vertices of equilateral triangle
			PVector top = PVector.fromAngle(radians(angle)).setMag((float) (side/Math.sqrt(3))).add(center);
			PVector base1 = PVector.fromAngle(radians(angle+120)).setMag((float) (side/Math.sqrt(3))).add(center);
			PVector base2 = PVector.fromAngle(radians(angle-120)).setMag((float) (side/Math.sqrt(3))).add(center);
			//draws triangle
			triangle(base1.x,base1.y,base2.x,base2.y,top.x,top.y);
			//next 6 triangles
			snowflake(n-1, side/3, -angle, PVector.fromAngle(radians(-angle)).setMag((float)(((side/2)+(side/6))/Math.sqrt(3))).add(center));
			snowflake(n-1, side/3, angle + 60, PVector.fromAngle(radians(angle+60)).setMag((float)(((side/2)+(side/6))/Math.sqrt(3))).add(center));
			snowflake(n-1, side/3, angle - 60, PVector.fromAngle(radians(angle-60)).setMag((float)(((side/2)+(side/6))/Math.sqrt(3))).add(center));
			snowflake(n-1, side/3, angle, PVector.fromAngle(radians(angle)).setMag((float)(((side/2)+(side/6))/Math.sqrt(3))).add(center));
			snowflake(n-1, side/3, angle + 120, PVector.fromAngle(radians(angle+120)).setMag((float)(((side/2)+(side/6))/Math.sqrt(3))).add(center));
			snowflake(n-1, side/3, angle - 120, PVector.fromAngle(radians(angle-120)).setMag((float)(((side/2)+(side/6))/Math.sqrt(3))).add(center));

		}
		
	}
	
	/**
	 * Draws a pine tree
	 * @param n layers in the tree + 1 (should not go over 7)
	 * @param x position of top
	 * @param y position of top
	 */
	public void trees(int n, float x, float y){
		if(n==0){
			strokeWeight(8);
			stroke(100,42,10);
			line(x,y,x,y+60);
		}
		if(n>0){
			trees(n-1, x, y+10);
		}
		strokeWeight(1);
		stroke(0,20*n+150,0);
		for(int i=210; i<330; i+=2){
			line(x,y,(float) (x + (50-(5*n))*Math.cos(Math.toRadians(i))),(float) (y - (50-(5*n))*Math.sin(Math.toRadians(i))));
		}
		
	}
	
	/**
	 * Draws smoke rising
	 * @param n amount of smoke particles
	 * @param x position of chimney
	 * @param y position of chimney
	 */
	public void smoke(int n, float x, float y){
		if(n >= 0){
			fill(random(50,150));
			float size = random(20);
			float offy = random(7);
			float offx = random(10);
			ellipse(x,y-offy,size, size);
			ellipse(x+offx,y,size, size);
			smoke(n-1,x + offx, y - offy);
		}
	}
	
	/**
	 * Draws snow in the background
	 * @param n number of snow flakes
	 */
	public void drawSnow(int n){
		if(n>=0){
			if(snowY[n]>=height/2){
				snowY[n]=0;
				snowX[n]=random(width);
				speedY[n]=random(1,3);
			}
			ellipse(snowX[n]+random(-1,1),snowY[n],5,5);
			snowY[n]+=speedY[n];
			snowX[n]+=speedX;
			drawSnow(n-1);
		}
	}
	
	public void keyPressed(){
		exit();
	}

}
