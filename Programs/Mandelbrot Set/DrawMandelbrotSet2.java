import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;


@SuppressWarnings("restriction")
public class DrawMandelbrotSet2 extends Application{	 
	GraphicsContext gc;
	PixelWriter pw;
	int maxIter = 100;
	int maxMag = 100;
	double maxx,maxy,minx,miny;
	double width, height;
	double initX, initY;
	public static void main(String[] args) {
		launch(args);
	} 
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Mandelbrot Set - Mihir Dhamankar 2019");
	    Group root = new Group();
	    Canvas canvas = new Canvas(500,500);
	    gc = canvas.getGraphicsContext2D();
	    maxx = 2;
	    maxy = 2;
	    minx = -2;
	    miny = -2;
	    width = canvas.getWidth();
	    height = canvas.getHeight();
	    pw = gc.getPixelWriter();
	    drawSet(gc);
	    root.getChildren().add(canvas);
	    root.setOnDragDetected(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				initX = event.getX();
				initY = event.getY();
			}
	    	
	    });
	    root.setOnMouseClicked(new EventHandler<MouseEvent>() {
	        @Override
	        public void handle(MouseEvent event) {
	        		if(!event.isStillSincePress()) {
	        			zoom((width/2)+(initX - event.getX()), (height/2) + (initY - event.getY()), 1);
	        		}
	        		else if(event.getButton() == MouseButton.PRIMARY) {
	            		zoom(event.getX(), event.getY(), 2);
	            }
	        		else {
	            		zoom(event.getX(), event.getY(), 0.5);
	            }
	        }

	    });
	    root.setOnKeyPressed(new EventHandler<KeyEvent>() {	    		
			@Override
			public void handle(KeyEvent event) {
				if(event.isShiftDown()) {
					if(event.getCode() == KeyCode.UP)
						zoom(width/2,height/2-10,1);
					if(event.getCode() == KeyCode.DOWN)
						zoom(width/2,height/2+10,1);
					if(event.getCode() == KeyCode.RIGHT)
						zoom(width/2+10,height/2,1);
					if(event.getCode() == KeyCode.LEFT)
						zoom(width/2-10,height/2,1);
					if(event.getCode() == KeyCode.OPEN_BRACKET) {
						maxMag-=10;
						drawSet(gc);
					}
					if(event.getCode() == KeyCode.CLOSE_BRACKET) {
						maxMag+=10;
						drawSet(gc);
					}
				}
				else {
					if(event.getCode() == KeyCode.UP)
						zoom(width/2,height/2,1.1);
					if(event.getCode() == KeyCode.DOWN)
						zoom(width/2,height/2,1/1.1);
					if(event.getCode() == KeyCode.OPEN_BRACKET) {
						maxIter-=10;
						drawSet(gc);
					}
					if(event.getCode() == KeyCode.CLOSE_BRACKET) {
						maxIter+=10;
						drawSet(gc);
					}
				}
			}
	    	
	    });
	    primaryStage.setScene(new Scene(root));
	    primaryStage.show();
	    root.requestFocus();
	    
	}
	protected void zoom(double x, double y, double scale) {
		double newDist = (maxx-minx)/(2*scale);
		x = itransformX(x);
		y = itransformY(y);
		maxx = x + newDist;
		minx = x - newDist;
		maxy = y + newDist;
		miny = y - newDist;
		drawSet(gc);
	}
    private void drawSet(GraphicsContext gc) {
    		for(double i = minx; i<=maxx; i+=((maxx-minx)/width)) {
    			for(double j = miny; j<=maxy; j+=((maxy-miny)/height)) {
    				pw.setColor(transformX(i),transformY(j),getPoint(i,j));
        		}
    		}
    }
    private int transformX(double i) {
    		return (int) ((width) * (i - minx) / (maxx - minx));
	}
    private double itransformX(double x) {
		return (((maxx-minx) * x / width) + minx);
	}
    private double itransformY(double y) {
		return (((maxy-miny) * y / height) + miny);
    }
    private int transformY(double j) {
		return (int) ((height) * (j - miny) / (maxy - miny));
    }
	private Color getPoint(double i, double j) {
	    	ComplexNumber z = new ComplexNumber(0,0);
		ComplexNumber c = new ComplexNumber(i,j);
		double mag = 0;
		int n = 0;
		while(n<maxIter && mag<maxMag) {
			z = (z.times(z)).plus(c);
			mag = z.mag();
			n++;
		}
		if(n>maxIter-1)
			return(Color.BLACK);
		return(Color.hsb(360*n/maxIter, 1, 1));
    }

}
