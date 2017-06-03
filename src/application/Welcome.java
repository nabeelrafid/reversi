package application;
import java.util.ArrayList;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Welcome {
	Parent root;
	Stage window;
	//t
	
	public void display() throws Exception {

		
		// setting up the board
		window = new Stage();
		root = FXMLLoader.load(getClass().getResource("Board.fxml"));
		System.out.println(root.getChildrenUnmodifiable());
		
		
		Scene scene = new Scene(root);
		window.setTitle("Reversi");
		window.setResizable(false);
		window.setScene(scene);
		
		
		window.show();
	}
	


}
