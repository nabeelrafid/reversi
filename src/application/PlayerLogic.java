package application;

import java.util.ArrayList;

public class PlayerLogic {
	
	public ArrayList validMove(int[][] board, int playerPiece, int indexY, int indexX){
		ArrayList piecesToFlip = new ArrayList();
		boolean validDirection;
		int opponentPiece;
		
		if (board[indexY][indexX] != 0) //If tile is not blank than it is an invalid move
			return piecesToFlip;
		
		if (playerPiece == 1)//Setting the opponent piece as opposite to the player piece
			opponentPiece = 2;
		else
			opponentPiece = 1;
		
		//Right
		try{
			if (board[indexY][indexX+1] == opponentPiece){
				for (int i = indexX + 2; i <= 7; i++){
					
				}
			}
		}
		
		
		
		
		
	}
	

}
