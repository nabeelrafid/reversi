package application;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class Controller {

	@FXML
	private Canvas canvas;

	public void createGrid(){
	    GraphicsContext g = canvas.getGraphicsContext2D();

	    g.rect(100,100,300,300);
	    g.
	}
}
