package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.input.*;


public class Main extends Application {
	
	@Override
	public void start(Stage window) throws Exception {


		Board board = new Board();
		board.display();
		
		
		
	}
	

	public static void main(String[] args) {
		launch(args);
	}
}
