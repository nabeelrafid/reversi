package application;
	
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
	
	@Override // overrides the Application.start with our own custom code
			  // our custom code initializes the board
	public void start(Stage window) throws Exception {
		//Board board = new Board();
		//board.display();
		Board2 test = new Board2();
		test.display(window);
	}

	public static void main(String[] args) {
		launch(args);
	}
}
