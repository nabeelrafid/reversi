package application;
	
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
	
	@Override // overrides the Application.start with our own custom code
			  // our custom code initializes the board
	public void start(Stage window) throws Exception {

		Board game = new Board(); // Create Board object
		game.display(window);
	}

	public static void main(String[] args) {
		launch(args); // Launch JavaFX application
	}
}
