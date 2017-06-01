package application;

import java.util.ArrayList;

public class PlayerLogic {
	
	public ArrayList validMove(int[][] board, int playerPiece, int indexY, int indexX){
		ArrayList piecesToFlip = new ArrayList();
		boolean validDirection = false;
		int opponentPiece;
		
		if (board[indexY][indexX] != 0) //If tile is not blank than it is an invalid move
			return piecesToFlip;
		
		if (playerPiece == 1)//Setting the opponent piece as opposite to the player piece
			opponentPiece = 2;
		else
			opponentPiece = 1;
		
		//Right
		try{

			
			if (board[indexY][indexX+1] == opponentPiece){//Checks to see if piece right to the chosen tile is an opponent piece
				int index = 0;
				for (int i = indexX + 2; i <= 7; i++){
					if (board[indexY][i] == 0){
						validDirection = false;
						break;
					}else if (board[indexY][i] == playerPiece){
						validDirection = true;
						index = i;
						break;
					}else if (i == 7){
						validDirection = false;
						break;
					}
				}
				System.out.println(validDirection);
				if (validDirection){
					System.out.println("success");
					for (int i = indexX + 1; i < index; i++){
						System.out.println("loop entered");
						piecesToFlip.add(indexY);
						piecesToFlip.add(i);
					}
					//validDirection = false;
				}
			}
		}catch(Exception e){System.out.println(e);}//NoOp
		
		return piecesToFlip;
		
		
		
		
	}
	

}
