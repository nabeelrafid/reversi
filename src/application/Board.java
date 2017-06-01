package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.input.*;


public class Board {
	
	double coordX, coordY;
	Parent root;
	Stage window;
	int[][] boardArray = new int[][]{//1 = white, 2 = black, 0 = empty
		{0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0},
		{0,0,0,0,1,2,2,0},
		{0,0,0,2,2,2,0,0},
		{0,0,2,2,2,1,1,0},
		{0,0,0,2,2,0,0,0},
		{0,0,0,1,0,0,0,0},
		{0,0,0,0,0,0,0,0},
	};
	
	public void display() throws Exception {
		PlayerLogic playLogic = new PlayerLogic();
		int turn = 1;
		window = new Stage();

		root = FXMLLoader.load(getClass().getResource("Board.fxml"));
		System.out.println(root.getChildrenUnmodifiable());
		System.out.println(playLogic.possibleMoves(boardArray, turn, getYTile(), getXTile()));
		root.setOnMouseClicked(e -> {
			coordX = e.getSceneX();
			coordY = e.getSceneY();
			System.out.println(playLogic.validMove(boardArray, turn, getYTile(), getXTile()));
			

			System.out.println(Integer.toString(getYTile()) + ", " + Integer.toString(getXTile()));
			System.out.println(boardArray[getYTile()][getXTile()]);
			//System.out.println(Integer.toString((int) coordX) + ", " + Integer.toString((int) coordY));

			
		});
		Scene scene = new Scene(root);
		window.setTitle("Reversi");
		window.setResizable(false);
		window.setScene(scene);
		
		
		window.show();
	}
	
	public int getXTile(){
		int tileX = -1; //Value -1 to notify program if click is within the board range
		if (coordX >= 91 && coordX <= 587){
			tileX = 0;
			for(int i = 153; i <= 587; i += 62){
				if (coordX < i){
					return tileX;
				}
				tileX++;
			}
		}
		return tileX;
	}
	
	public int getYTile(){
		int tileY = -1; //Value -1 to notify program if click is within the board range
		if (coordY >= 117 && coordY <= 613){
			tileY = 0;
			for(int i = 179; i <= 613; i += 62){
				if (coordY < i){
					return tileY;
				}
				tileY++;
			}
		}
		return tileY;
	}

}
