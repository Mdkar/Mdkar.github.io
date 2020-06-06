import processing.core.PApplet;
import processing.event.MouseEvent;
/*
 * Scrolling up and down with a mouse changes the scale
 * Left arrow key decreases precision (larger bucket size)
 * Right arrow key increases precision (smaller bucket size)
 * The Numerical Summary box is movable and collapsible
 */
public class GradeDisplay extends PApplet{
	private float x;
	private float y;
	private boolean areValuesDisplayed = true;
	private float scale;
	private double[][] values;
	private String[] valueTypes = {"Mean: ", "Median: ", "Modes: ", "Variance: ", "Standard Deviation: "};
	private Grade grades;
	private int bucketSize;
	public static void main(String[] args) {
		PApplet.main("GradeDisplay");
	}
	public void settings(){
		fullScreen();
	}
	public void setup(){
		background(255);
		textSize(16);
		grades = new Grade("scores.txt");
		values = new double[5][];
		x = 20;
		y = 20;
		scale = 20;
		bucketSize = 10;
		for(int i = 0; i < values.length; i++){
			if(i != 2)
				values[i] = new double[1];
			else
				values[2] = new double[grades.mode().length];
		}
		getValues();

	}

	public void draw(){
		background(255);
		drawHistogram();
		drawValueBox();
		if(areValuesDisplayed)
			drawValues();
		if(mouseX >= x && mouseX <= x + 40 && mouseY >= y && mouseY <= y + 30){
			fill(255, 0, 0);
			stroke(255);
			drawButton();
		}

	}

	private void drawHistogram() {
		textAlign(CENTER);
		stroke(0);
		line(50, height - 125, width - 55, height - 125);
		float dx = (width - 100)/(100/bucketSize);
		int[] histogram = grades.histogram(bucketSize , 1, 100);
		for(int i = 0; i <= histogram.length; i++){
			line( 50 + i * dx, height - 120, 50 + i * dx, height - 130);
			text(bucketSize * i, 50 + i * dx, height - 100);
		}
		text("Scores", width/2, height - 50);
		rectMode(CORNERS);
		for(int i = 0; i < histogram.length; i++)
			if(histogram[i] != 0){
				fill(100/bucketSize * histogram[i]);
				rect(50 + i * dx, height - 125, 50 + (i + 1) * dx, height - 125 - scale * histogram[i]);
				fill(0);
				text(histogram[i], 50 + i * dx + dx/2, height - 130 - scale * histogram[i] );
			}
		rectMode(CORNER);

	}

	private void drawValueBox(){
		textAlign(LEFT);
		stroke(0);
		fill(123);
		rect(x, y, 300, 30, 10, 10, 0, 0);
		line(x, y + 30, x + 300, y + 30);
		drawButton();
		fill(0);
		text("Numerical Summary", x + 90, y + 20);
	}

	private void drawValues() {
		fill(255);
		rect(x, y + 30, 300, 170, 0, 0, 10, 10);
		fill(0);
		for(int i = 0; i < 5; i++){
			text(valueTypes[i], x + 20, 30 + y + 30 * (i + 1));
			for(int j = 0; j < values[i].length; j++){
				text(String.format("%.2f",(float)values[i][j]), textWidth(valueTypes[i]) + x + 25 + 60 * ((j)),30 + y + 30 * (i + 1));
			}
		}
	}

	private void drawButton(){
		rect(x, y, 40, 30, 10, 0, 0, 0);
		strokeWeight(3);
		line( x + 15, y + 15, x + 25, y + 15);
		strokeWeight(1);
		stroke(0);
		noFill();
		rect(x, y, 40, 30, 10, 0, 0, 0);
		fill(0);

	}

	private void getValues() {
		values[0][0] = grades.mean();
		values[1][0] = grades.median();
		for(int i = 0; i < values[2].length; i++)
			values[2][i] = grades.mode()[i];
		values[3][0] = grades.variance();
		values[4][0] = grades.standardDeviation();
	}

	public void mouseDragged(){
		if(pmouseX >= x && pmouseX <= x + 300 && pmouseY >= y && pmouseY <= y + 200 ){
			x += mouseX - pmouseX;
			y += mouseY - pmouseY;
		}
	}

	public void mouseClicked(){
		if(mouseX >= x && mouseX <= x + 40 && mouseY >= y && mouseY <= y + 30)
			areValuesDisplayed = ! areValuesDisplayed;
	}

	public void mouseWheel(MouseEvent event){
		scale+=event.getCount();

	}

	public void keyPressed(){
		if (key == CODED) {
			if (keyCode == LEFT && bucketSize<100){
				do{
					bucketSize++;
				}while(100%bucketSize != 0);
			}
			if (keyCode == RIGHT && bucketSize>1){
				do{
					bucketSize--;
				}while(100%bucketSize != 0);
			}

		}
	}

}
