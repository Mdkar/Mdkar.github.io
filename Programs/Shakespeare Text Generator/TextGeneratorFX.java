import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.control.TextArea;
 
@SuppressWarnings("restriction")
public class TextGeneratorFX extends Application {
	    
    @Override
    public void start(Stage primaryStage) {
  
    	TextGenerator tg = new TextGenerator();    	
    	 
        //Text Area to write generated text and read user text
    	TextArea name = new TextArea();
        name.setPromptText("");
        name.setPrefRowCount(10);
        name.setWrapText(true);
        
        //Button to generate random text
        Button rand = new Button();
        rand.setAlignment(Pos.BOTTOM_RIGHT);
        rand.setText("Generate Random Sentence");
        rand.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
            	name.setText(tg.generateSentence());
            }
        });
        
        //Button to generate text from user provided seed
        Button usr = new Button();
        usr.setText("Generate Sentence From Text");
        usr.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
            	try{
            		name.setText(tg.generateSentence(name.getText()));
            	}
            	catch(NullPointerException e){
            		name.setText("[No sentence found]");
            	}
            }
        });
        
        //Align buttons next to each other
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.add(rand,0,1);
        grid.add(usr,1,1);
        
        //align buttons and text area next to each other
        GridPane root = new GridPane();
        root.setAlignment(Pos.TOP_CENTER);
        root.setVgap(10);
        root.setPadding(new Insets(25, 25, 25, 25));
        root.add(grid,0,0);
        root.add(name,0,1);
        
        //creates the scene
        Scene scene = new Scene(root, 500, 250);
        primaryStage.setTitle("Shakespear Text Generator by Mihir Dhamankar");
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();
    }
    
    public static void main(String[] args) {
    	launch(args);
   	}
}

