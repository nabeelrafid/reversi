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

public class Board2 {
	
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
	
	
	
	public void display(Stage window) throws Exception {
		GameLogic playLogic = new GameLogic();
		ComputerAI compAI = new ComputerAI();
		
        
        Group root = new Group();
        Canvas canvas = new Canvas(722,722);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        final ImageView imv = new ImageView();
        final Image image2 = new Image(Main.class.getResourceAsStream("board.jpg"));
        imv.setImage(image2);
        
        root.getChildren().addAll(imv, canvas);
        window.setScene(new Scene(root));
        window.setTitle("Reversi");
        window.setResizable(false);
        window.sizeToScene(); // Bug fix: Setting resizable as false puts a small white border 
        window.show(); 
        
        try{
        	FileController.loadGame(board);
        	FileController.loadTurn(turn);
        	printBoard();
        }catch(Exception e){}
        
        
		drawBoard(board, gc);
	
		window.setOnCloseRequest(e -> {
			e.consume();
			boolean confirm = ConfirmBox.display("Close", "Are you sure you want to close?");
			if (confirm){
				try {
					FileController.saveGame(board);
					FileController.saveTurn(turn);
				} catch (IOException e1) {}
				
				window.close();
			}
		});
		
		root.setOnMouseClicked(e -> {
			int tileX = getXTile(e.getSceneX());
			int tileY = getYTile(e.getSceneY());
			ArrayList piecesToFlip = GameLogic.validMove(board, turn, tileY, tileX);
			ArrayList computerMove;
			
			
			//Main program
			if (skips <= 2){ //Checks for double skips
				
				if (turn == 1){ // Checks to see if its white's turn
					if (GameLogic.possibleMoves(board, turn).size() > 0){ // Checks to see if there are any valid moves
						skips = 0; //Resets the number of skips if there are valid moves
						if (piecesToFlip.size() > 0){ // Checks to see if player entered a valid move
							GameLogic.flipPieces(board, piecesToFlip, turn); // Flips the pieces to white
							turn = 2; // Changes the turn to black
							System.out.print("beforeeeeeeeeee  ");
							System.out.println(System.currentTimeMillis());
							drawBoard(board, gc);
							System.out.println(System.currentTimeMillis());

							//System.out.println("draw   boooooooooaaaard  finished");
							/*try {
								System.out.println("draw   boooooooooaaaard  finished");

								Thread.sleep(1000);
						
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}*/
							System.out.println(System.currentTimeMillis());
							
						}else{} //NoOp wait for valid input
					}else{ // Enters this else clause if there are no valid moves
						System.out.println("Human is stuck!");
						skips ++; // Increments skip by one
						turn = 2; // Changes the turn to black
					}
				}if (turn == 2){ // turn for black
					if (GameLogic.possibleMoves(board, turn).size() > 0){ // Checks to see if there are any valid moves
						skips = 0; //Resets the number of skips if there are valid moves
						computerMove = ComputerAI.randomAlgorithm(board, turn); // Computer AI chooses a move
						computerY = (int) computerMove.get(0); 
						computerX = (int) computerMove.get(1);
						if (GameLogic.validMove(board, turn, computerY, computerX).size() > 0){
							GameLogic.flipPieces(board, GameLogic.validMove(board, turn, computerY, computerX), turn);
							turn = 1;
				
							drawBoard(board, gc);
							System.out.println("aaaaaaaaaaaaaaaaaaaaaaaa");
							//printBoard(); // Print the board at the end of computers turn
							System.out.println("");
							

						}
					}else{
						System.out.println("Computer is stuck!");
						skips ++; //Increments skip by one
						turn = 1; //Changes the turn to white
					}
				}
			}else{ //If there are two consecutive skips
				ArrayList winner = GameLogic.winnerChecker(board);
				System.out.println("Game over!");

				System.out.println("Winner is: " + winner.get(0));
				System.out.println("");
				System.out.println("White: " + winner.get(1));
				System.out.println("Black: " + winner.get(2));
				boardReset();
				try {
					FileController.saveGame(board);
					FileController.saveTurn(1);
				} catch (IOException e1) {}
				WinnerBox.display(window, winner.get(0),winner.get(1), winner.get(2));
				

				 //White = 1, black = 2, tie = 3
			}
			
		});
		

	}
	
	public static int getXTile(double coord){
		
		int tileX = 0;
			for (int i = 90; i <= 720; i += 90.25){
				if (coord < i){
					break;
				}
				tileX++;
			}
		
		return tileX;
	}
	
	public static int getYTile(double coord){
		int tileY = 0;
		for (int i = 90; i <= 720; i += 90.25){
			if (coord < i){
				break;
			}
			tileY++;
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
	
	public static void drawBoard(int[][] board, GraphicsContext gc){
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
	public static void drawPiece(int pieceColor, GraphicsContext gc, double coordinateX, double coordinateY){
		if (pieceColor == 1){
			gc.setFill(Color.ALICEBLUE);
		}else if (pieceColor == 2){
			gc.setFill(Color.BLACK);
		}
        
        gc.fillOval(coordinateX, coordinateY, 89, 89);
	}
	public static double getXCoord(int tileX){
		return (tileX  * 90);
		
		
	}
	public static double getYCoord(int tileY){
		return (tileY  * 90);
	}
	public void boardReset(){
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
