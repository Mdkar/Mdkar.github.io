import processing.core.PApplet;
import processing.core.PFont;

public class SudokuSolverGraphics extends PApplet{

	private SudokuSolver s = new SudokuSolver("puzzle.txt"); 
	private boolean showSteps;
	PFont normal;
	PFont bold;
	private int[][] given = new int[9][9];
	public static void main(String[] args) {
		PApplet.main("SudokuSolverGraphics");

	}
	
	public void settings(){
		size(594,594);
	}
	
	public void setup(){
		background(255);
		for(int r = 0; r < s.getNumbers().length; r++)
			for(int c = 0; c < s.getNumbers()[r].length; c++)
				given[r][c] = s.getNumbers()[r][c];
		normal = createFont("Arial", 22);
		bold = createFont("Arial Bold", 22);
		s.solve(0, 0);
		System.out.println("Press any key to solve!");
	}

	int i = 0;
	public void draw(){
		background(255);
		textFont(bold);
		drawBoard(given);
		if(keyPressed == true)
			showSteps = true;
		if(showSteps == true){	
			textFont(normal);
			drawBoard(s.getMoves().get(i));
			textFont(bold);
			drawBoard(given);
			i++;
			if(i>s.getMoves().size()-1)
				noLoop();
			
		}
	}
	
	private void drawBoard(int[][] arr) {
		fill(0);
		textSize(22);
		textAlign(CENTER,CENTER);
		for(int i = 0; i < 9; i++)
			for(int j = 0; j<9; j++)
				if(arr[i][j] != 0){
					if(arr == given){
						noStroke();
						fill(255,200,0);
						rect(j*(height/9),i*(width/9),height/9,width/9);
						stroke(1);
					}
					fill(0);
					text(arr[i][j],j*(height/9)+(height/18),i*(width/9)+(width/18));
				}
		for(int i = 0; i <= 9; i++){
			strokeWeight(1);
			if(i%3 == 0)
				strokeWeight(2);
			line(i*(width/9),0,i*(width/9),height);
			line(0,i*(height/9),width,i*(height/9));
			
		}
		
					
					
	}
}