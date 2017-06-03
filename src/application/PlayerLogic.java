package application;

import java.util.ArrayList;

public class PlayerLogic {
	public ArrayList validMove(int[][] board, int playerPiece, int indexY, int indexX){
		ArrayList piecesToFlip = new ArrayList();
		boolean validDirection = false;
		int opponentPiece;
		int indexYTemp = indexY;// Used to maintain the value of indexY when checking diagonals
		
		if (board[indexY][indexX] != 0)  // if tile is not blank than it is an invalid move
			return piecesToFlip; // return the array which at this point is empty

		
		if (playerPiece == 1) { // setting the opponent piece as opposite to the player piece
			opponentPiece = 2;
		} else {
			opponentPiece = 1;
		}
		
		// checks to the right of placed piece
		try{
			
			if (board[indexY][indexX+1] == opponentPiece) { // checks to see if piece right to the chosen tile is an opponent piece
				int index = 0;
				for (int i = indexX + 2; i <= 7; i++) {
					if (board[indexY][i] == 0){ // checks to see if there is a blank space b/t the intended capture pieces
						validDirection = false;
						break;
					} else if (i == 7){
						validDirection = false;
						break;
					} else if (board[indexY][i] == playerPiece) { // if there is another piece on the same line, that means two pieces surround captured pieces
						validDirection = true;
						index = i;
						break;
					}
				}
				
				System.out.println("direction is " + validDirection);
				
				if (validDirection) {
					for (int i = indexX + 1; i < index; i++){
						System.out.println("loop entered");
						piecesToFlip.add(indexY);
						piecesToFlip.add(i);
					}
					//validDirection = false;
				}
			}
		} catch(Exception e) {System.out.println(e);}//NoOp
		
		// checks to the left of place piece
		try{

			
			if (board[indexY][indexX-1] == opponentPiece){ // checks to see if piece right to the chosen tile is an opponent piece
				int index = 0;
				for (int i = indexX - 2; i >= 0; i--){
					if (board[indexY][i] == 0){
						validDirection = false;
						break;
					} else if (i == 0){
						validDirection = false;
						break;
					} else if (board[indexY][i] == playerPiece){
						validDirection = true;
						index = i;
						break;
					}
				}
				
				System.out.println("direction is " + validDirection);
				
				if (validDirection) {
					for (int i = indexX - 1; i > index; i--){
						piecesToFlip.add(indexY);
						piecesToFlip.add(i);
					}
					//validDirection = false;
				}
			}
		} catch(Exception e) {System.out.println(e);}//NoOp
		
		
		// checks downwards of placed piece
		try{
			
			if (board[indexY + 1][indexX] == opponentPiece){ // checks to see if piece downwards to the chosen tile is an opponent piece
				int index = 0;
				for (int i = indexY + 2; i <= 7; i++){
					if (board[i][indexX] == 0){
						validDirection = false;
						break;
					} else if (i == 7){
						validDirection = false;
						break;
					} else if (board[i][indexX] == playerPiece){
						validDirection = true;
						index = i;
						break;
					}
				}
				
				System.out.println("direction is" + validDirection);
				
				if (validDirection){
					for (int i = indexY + 1; i < index; i++) {
						piecesToFlip.add(i);
						piecesToFlip.add(indexX);
					}
					//validDirection = false;
				}
			}
		} catch(Exception e) {System.out.println(e);}//NoOp
		
		// checks upwards of placed piece
		try{
			if (board[indexY][indexX-1] == opponentPiece){ // checks to see if piece above to the chosen tile is an opponent piece
				int index = 0;
				for (int i = indexX - 2; i >= 0; i--){
					if (board[indexY][i] == 0){
						validDirection = false;
						break;
					}else if (board[indexY][i] == playerPiece){
						validDirection = true;
						index = i;
						break;
					}else if (i == 0){
						validDirection = false;
						break;
					}
				}
				System.out.println(validDirection);
				if (validDirection){
					System.out.println("success");
					for (int i = indexX - 1; i > index; i--){
						System.out.println("loop entered");
						piecesToFlip.add(indexY);
						piecesToFlip.add(i);
					}
					//validDirection = false;
				}
			}
		}catch(Exception e){System.out.println(e);}//NoOp
		
		
		//Down
		try{
			
			if (board[indexY - 1][indexX] == opponentPiece){//Checks to see if piece right to the chosen tile is an opponent piece
				int index = 0;
				for (int i = indexY - 2; i >= 0; i--){
					if (board[i][indexX] == 0){
						validDirection = false;
						break;
					}else if (board[i][indexX] == playerPiece){
						validDirection = true;
						index = i;
						break;
					}else if (i == 0){
						validDirection = false;
						break;
					}
				}
				System.out.println(validDirection);
				if (validDirection){
					System.out.println("success");
					for (int i = indexY - 1; i > index; i--){
						System.out.println("loop entered");
						piecesToFlip.add(i);
						piecesToFlip.add(indexX);
					}
					//validDirection = false;
				}
			}
		}catch(Exception e){}
		
		// checks diagonally up left of placed piece
		try{
			
			if (board[indexY - 1][indexX-1] == opponentPiece) { // checks to see if piece diagonally up left to the chosen tile is an opponent piece
				int finalIndexX = 0;
				int finalIndexY = 0;
				indexY -= 2; // -2 compensates for the -2 used in the for loop for indexX
				for (int i = indexX - 2; i >= 0; i--) {
					if (board[indexY][i] == 0){ // checks to see if there is a blank space b/t the intended capture pieces
						validDirection = false;
						break;
					} else if (board[indexY][i] == playerPiece) { // if there is another piece on the same line, that means two pieces surround captured pieces
						validDirection = true;
						finalIndexX = i;
						finalIndexY = indexY;
						break;
					}else if (i == 0 || indexY == 0){
						validDirection = false;
						break;
					}
					indexY --; 
				}
				
				System.out.println("diagonal up left is " + validDirection);
				
				indexY = indexYTemp; // resets the indexY value for collection of the pieces to flip
				indexY -= 1;
				if (validDirection) {
					for (int i = indexX - 1; i > finalIndexX && indexY > finalIndexY; i--){
						System.out.println("loop entered");
						piecesToFlip.add(indexY); 
						piecesToFlip.add(i);
						indexY --;
					}
					//validDirection = false;
				}
			}
		} catch(Exception e) {System.out.println(e);}//NoOp
		
		// checks diagonally up right of placed piece
		try{
			
			if (board[indexY - 1][indexX + 1] == opponentPiece) { // checks to see if piece diagonally up left to the chosen tile is an opponent piece
				int finalIndexX = 0;
				int finalIndexY = 0;
				indexY -= 2; // -2 compensates for the -2 used in the for loop for indexX
				for (int i = indexX + 2; i <= 7; i++) {
					if (board[indexY][i] == 0){ // checks to see if there is a blank space b/t the intended capture pieces
						validDirection = false;
						break;
					} else if (board[indexY][i] == playerPiece) { // if there is another piece on the same line, that means two pieces surround captured pieces
						validDirection = true;
						finalIndexX = i;
						finalIndexY = indexY;
						break;
					}else if (i == 7 || indexY == 0){
						validDirection = false;
						break;
					}
					indexY --; 
				}
				
				System.out.println("diagonal up right is " + validDirection);
				
				indexY = indexYTemp; // resets the indexY value for collection of the pieces to flip
				indexY -= 1;
				if (validDirection) {
					for (int i = indexX + 1; i < finalIndexX && indexY > finalIndexY; i++){
						System.out.println("loop entered");
						piecesToFlip.add(indexY); 
						piecesToFlip.add(i);
						indexY --;
					}
					//validDirection = false;
				}
			}
		} catch(Exception e) {System.out.println(e);}//NoOp
		
		indexY = indexYTemp; // resets value of indexY
		
		// checks diagonally down left of placed piece
		try{
			
			if (board[indexY + 1][indexX - 1] == opponentPiece) { // checks to see if piece diagonally up left to the chosen tile is an opponent piece
				int finalIndexX = 0;
				int finalIndexY = 0;
				indexY += 2; // -2 compensates for the -2 used in the for loop for indexX
				for (int i = indexX - 2; i >= 0; i--) {
					if (board[indexY][i] == 0){ // checks to see if there is a blank space b/t the intended capture pieces
						validDirection = false;
						break;
					} else if (board[indexY][i] == playerPiece) { // if there is another piece on the same line, that means two pieces surround captured pieces
						validDirection = true;
						finalIndexX = i;
						finalIndexY = indexY;
						break;
					}else if (i == 0 || indexY == 7){
						validDirection = false;
						break;
					}
					indexY ++; 
				}
				
				System.out.println("diagonal down left is " + validDirection);
				
				indexY = indexYTemp; // resets the indexY value for collection of the pieces to flip
				indexY += 1;
				if (validDirection) {
					System.out.println(Integer.toString(finalIndexY) + ", " + Integer.toString(finalIndexX));
					for (int i = indexX - 1; i > finalIndexX && indexY < finalIndexY; i--){
						System.out.println("loop entered");
						piecesToFlip.add(indexY); 
						piecesToFlip.add(i);
						indexY ++;
					}
					//validDirection = false;
				}
			}
		} catch(Exception e) {System.out.println(e);}//NoOp
		
		// checks diagonally down right of placed piece
		try{
			
			if (board[indexY + 1][indexX + 1] == opponentPiece) { // checks to see if piece diagonally up left to the chosen tile is an opponent piece
				int finalIndexX = 0;
				int finalIndexY = 0;
				indexY += 2; // -2 compensates for the -2 used in the for loop for indexX
				for (int i = indexX + 2; i <= 7; i++) {
					if (board[indexY][i] == 0){ // checks to see if there is a blank space b/t the intended capture pieces
						validDirection = false;
						break;
					} else if (board[indexY][i] == playerPiece) { // if there is another piece on the same line, that means two pieces surround captured pieces
						validDirection = true;
						finalIndexX = i;
						finalIndexY = indexY;
						break;
					}else if (i == 7 || indexY == 7){
						validDirection = false;
						break;
					}
					indexY ++; 
				}
				
				System.out.println("diagonal down left is " + validDirection);
				
				indexY = indexYTemp; // resets the indexY value for collection of the pieces to flip
				indexY += 1;
				if (validDirection) {
					System.out.println(Integer.toString(finalIndexY) + ", " + Integer.toString(finalIndexX));
					for (int i = indexX + 1; i < finalIndexX && indexY < finalIndexY; i++){
						System.out.println("loop entered");
						piecesToFlip.add(indexY); 
						piecesToFlip.add(i);
						indexY ++;
					}
					//validDirection = false;
				}
			}
		} catch(Exception e) {System.out.println(e);}//NoOp
		
				
		
		
		
		return piecesToFlip;
		
		
		
		
	}
	public ArrayList possibleMoves(int[][] board, int playerPiece, int indexY, int indexX){
		ArrayList possibleMoves = new ArrayList();
		ArrayList validMoveCheck;
		for (int y = 0; y <=7; y++){ //Goes through the rows
			for (int x = 0; x <= 7; x++){ //Goes through the column
				validMoveCheck = validMove(board, playerPiece, y, x); 
				if (validMoveCheck.size() > 0){
					possibleMoves.add(y);
					possibleMoves.add(x);
				}
			}
		}
		
		return possibleMoves;
		
	}
	

}
