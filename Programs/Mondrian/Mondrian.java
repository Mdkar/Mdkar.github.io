import processing.core.PApplet;

public class Mondrian extends PApplet {

	public static void main(String[] args) {
		PApplet.main("Mondrian");

	}
	public void settings(){
		fullScreen();
	}
	public void setup(){
		background(255);
		monRekt(9, 0, 0, width, height);
	}
	public void draw(){
		
	}
	private void monRekt(int depth, float x, float y, float w, float h) {
		
		if(depth == 0){
			int color = (int)random(6);
			if(color < 2)
				fill(255);
			else if(color == 3)
				fill(255,0,0);
			else if(color == 4)
				fill(255,255,0);
			else if(color == 5)
				fill(0,0,255);
			else
				fill(0);
			rect(x, y, w, h);
		}
		else{
			float newX = random(x, x+w);
			float newY = random(y, y+h);
			monRekt(depth-1,x,y,newX-x,newY-y);
			monRekt(depth-1,x,newY,newX-x,h-(newY-y));
			monRekt(depth-1,newX,y,w-(newX-x),newY-y);
			monRekt(depth-1,newX,newY,w-(newX-x),h-(newY-y));
			
			
		}
	}
	public void mousePressed(){
		setup();
	}
	

}
