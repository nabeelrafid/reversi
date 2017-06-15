package application;
import java.io.IOException;
import java.util.ArrayList;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class Board {
	
	//Creating the game board (2d array)
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

	
	int turn = 1; // setting the turn to white
	int skips = 0; // initializing the number of skips to zero
	int computerY; // y coordinate of the computer move
	int computerX; // x coordinate of the computer move
	
	
	
	public void display(Stage window) throws Exception {
		
		//Creating a blank board
		Group root = new Group();
        Canvas canvas = new Canvas(722,722);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        final ImageView imv = new ImageView();
        final Image image2 = new Image(Main.class.getResourceAsStream("board.jpg"));
        imv.setImage(image2);
        root.getChildren().addAll(imv, canvas);
        window.setScene(new Scene(root));
        
        //Window settings
        window.setTitle("Reversi"); // Set window title
        window.setResizable(false); // Disable resizing the window
        window.sizeToScene(); // Bug fix: Setting resizable as false puts a small white border 
        window.show();  // Displays the window
        
        try{ // Load previous game positions
        	FileController.loadGame(board);
        	FileController.loadTurn(turn);
        }catch(Exception e){}//NoOp
        
        
       
		drawBoard(board, gc);
		
		
		window.setOnCloseRequest(e -> {
			e.consume(); // Prevents the game window from closing even if user picked no
			boolean confirm = ConfirmBox.display("Close", "Are you sure you want to close?"); //Chow confirm box
			if (confirm){
				try { // Saving the game upon closing
					FileController.saveGame(board);
					FileController.saveTurn(turn);
				} catch (IOException e1) {} // NoOp
				
				window.close(); // Closes the window
			}
		});
		
		root.setOnMouseClicked(e -> { //Checks for a mouse click on the screen
			int tileX = getXTile(e.getSceneX()); // Returns a x index in 2d array based on click location
			int tileY = getYTile(e.getSceneY()); // Returns a y index in 2d array based on click location
			ArrayList piecesToFlip = GameLogic.validMove(board, turn, tileY, tileX); // Returns an array of pieces to flip
			ArrayList computerMove;
			
			
			//Main program
			if (skips <= 2){ //Checks for double skips
				
				if (turn == 1){ // Checks to see if its white's turn
					if (GameLogic.possibleMoves(board, turn).size() > 0){ // Checks to see if there are any valid moves
						skips = 0; //Resets the number of skips if there are valid moves
						if (piecesToFlip.size() > 0){ // Checks to see if player entered a valid move
							GameLogic.flipPieces(board, piecesToFlip, turn); // Flips the pieces to white
							turn = 2; // Changes the turn to black
							drawBoard(board, gc);
						}else{} //NoOp wait for valid input
					}else{ // Enters this else clause if there are no valid moves
						System.out.println("Human is stuck!");
						skips ++; // Increments skip by one
						turn = 2; // Changes the turn to black
					}
				}if (turn == 2){ // turn for black
					if (GameLogic.possibleMoves(board, turn).size() > 0){ // Checks to see if there are any valid moves
						skips = 0; //Resets the number of skips if there are valid moves
						try{
							computerMove = ComputerAI.thinkingAI(board, turn); // Computer AI chooses a move
						}catch (Exception e1){
							computerMove = ComputerAI.randomAlgorithm(board, turn);
						}
						computerY = (int) computerMove.get(0); 
						computerX = (int) computerMove.get(1);
						
						GameLogic.flipPieces(board, GameLogic.validMove(board, turn, computerY, computerX), turn);
						turn = 1; // Change turn back to white
			
						drawBoard(board, gc);

					}else{
						System.out.println("Computer is stuck!");
						skips ++; //Increments skip by one
						turn = 1; //Changes the turn to white
					}
				}
			}else{ //If there are two consecutive skips
				ArrayList winner = GameLogic.winnerChecker(board); // Fetches winner information
				boardReset(); // Resets the board
				
				try { // Resets the text files to default board position
					FileController.saveGame(board);
					FileController.saveTurn(1);
				} catch (Exception e1) {} //NoOp
				
				WinnerBox.display(window, winner.get(0),winner.get(1), winner.get(2)); // Displays the winner information

			}
			
		});

	}	
	public static int getXTile(double coord){ // Returns x index of a 2d array based on click location
		int tileX = 0;
			for (int i = 90; i <= 720; i += 90.25){ // Each tile is 90.25 x 90.25 pixels 
				if (coord < i){
					break;
				}
				tileX++;
			}
		
		return tileX;
	}
	public static int getYTile(double coord){ // Returns y index of a 2d array based on click location
		int tileY = 0;
		for (int i = 90; i <= 720; i += 90.25){ // Each tile is 90.25 x 90.25 pixels
			if (coord < i){
				break;
			}
			tileY++;
		}
	
	return tileY;
	}
	/*public void printBoard(){ // for testing purposes
		for (int y = 0; y <= 7; y++){
			System.out.println("");
			for (int x = 0; x <= 7; x++){
				System.out.print(board[y][x]);
				System.out.print(",");
			}
		}
	}*/
	public static void drawBoard(int[][] board, GraphicsContext gc){ // Loops through the entire board and places pieces
		for (int y = 0; y <= 7; y++){
			for (int x = 0; x <= 7; x++){
				if (board[y][x] == 1){
					drawPiece(1, gc, getXCoord(x), getYCoord(y));
				}else if (board[y][x] == 2){
					drawPiece(2, gc, getXCoord(x), getYCoord(y));
				}
			}
		}
	}
	public static void drawPiece(int pieceColor, GraphicsContext gc, double coordinateX, double coordinateY){ // Draws actual piece
		if (pieceColor == 1){
			gc.setFill(Color.ALICEBLUE);
		}else if (pieceColor == 2){
			gc.setFill(Color.BLACK);
		}
        
        gc.fillOval(coordinateX, coordinateY, 89, 89);
	}
	public static double getXCoord(int tileX){ // Returns the x coordinate of where to place the piece
		return (tileX  * 90);
	}
	public static double getYCoord(int tileY){ // Returns the x coordinate of where to place the piece
		return (tileY  * 90);
	}
	public void boardReset(){ // Changes the board back into the default starting position
		board = new int[][]{ // 0 = empty,1 = white, 2 = black
			{0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0},
			{0,0,0,1,2,0,0,0},
			{0,0,0,2,1,0,0,0},
			{0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0},
		};
	}
}
