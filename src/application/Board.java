package application;
import java.io.IOException;
import java.util.ArrayList;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Board {
	Parent root;
	Stage window;
	int[][] board = new int[][]{ // 0 = empty,1 = white, 2 = black
		{0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0},
		{0,0,0,1,2,0,0,0},
		{0,0,0,2,1,0,0,0},
		{0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0},
	};
	double coordX, coordY;
	int turn = 1; // setting the turn to white
	int skips = 0; // initializing the number of skips to zero
	int computerY;
	int computerX;
	
	public void display() throws Exception {
		GameLogic playLogic = new GameLogic();
		ComputerAI compAI = new ComputerAI();
		
		// setting up the board
		window = new Stage();
		root = FXMLLoader.load(getClass().getResource("Board.fxml"));
		System.out.println(root.getChildrenUnmodifiable());
		
		
		root.setOnMouseClicked(e -> {
			int tileX = getXTile(e.getSceneX());
			int tileY = getYTile(e.getSceneY());
			ArrayList piecesToFlip = playLogic.validMove(board, turn, tileY, tileX);
			ArrayList computerMove;
			
			/*System.out.println("");
			System.out.println("Random move: " + compAI.randomAlgorithm(board, turn));
			System.out.println("player move: " + Integer.toString(tileY) + "," + Integer.toString(tileX));
			System.out.println("tile element: " + Integer.toString(board[tileY][tileX]));
			System.out.println("pieces to flip: " + piecesToFlip);*/
			
			if (skips != 2){
				printBoard();
				if (turn == 1){
					if (playLogic.possibleMoves(board, turn).size() > 0){
						skips = 0; //Resets the number of skips if there are valid moves
						if (piecesToFlip.size() > 0){
							playLogic.flipPieces(board, piecesToFlip, turn);
							turn = 2;
							
						}else{} //NoOp wait for valid input
					}else{
						skips ++;
					}
				}else{ // turn for black
					if (playLogic.possibleMoves(board, turn).size() > 0){
						skips = 0; //Resets the number of skips if there are valid moves
						computerMove = compAI.randomAlgorithm(board, turn);
						computerY = (int) computerMove.get(0);
						computerX = (int) computerMove.get(1);
						if (playLogic.validMove(board, turn, computerY, computerX).size() > 0){
							playLogic.flipPieces(board, playLogic.validMove(board, turn, computerY, computerX), turn);
						}
					}else{
						skips ++;
					}
				}
			}else{
				System.out.println("Game over!");
			}
			
		
		});
		
		Scene scene = new Scene(root);
		window.setTitle("Reversi");
		window.setResizable(false);
		window.setScene(scene);
		
		
		window.show();
	}
	
	public static int getXTile(double coord){
		int minBoardCoord = 91;
		int maxBoardCoord = 587;
		int minTileCoord = 153;
		int maxTileCoord = 587;
		
		int tileX = 0;
		if (coord >= minBoardCoord && coord <= maxBoardCoord){
			for (int i = minTileCoord; i <= maxTileCoord; i += 62){
				if (coord < i){
					break;
				}
				tileX++;
			}
		} else {
			return -1;
		}
		return tileX;
	}
	
	public static int getYTile(double coord){
		int minBoardCoord = 117;
		int maxBoardCoord = 613;
		int minTileCoord = 179;
		int maxTileCoord = 613;
		
		int tileY = 0;
		if (coord >= minBoardCoord && maxBoardCoord <= 613){
			for (int i = minTileCoord; i <= maxTileCoord; i += 62){
				if (coord < i){
					break;
				}
				tileY++;
			}
		} else {
			return -1;
		}
		
		return tileY;
	}
	
	public void printBoard(){ // for testing purposes
		for (int y = 0; y <= 7; y++){
			System.out.println("");
			for (int x = 0; x <= 7; x++){
				System.out.print(board[y][x]);
				System.out.print(",");
			}
		}
	}

}
