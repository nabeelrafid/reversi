package application;

import java.util.ArrayList;
import java.util.Random;

public class ComputerAI extends GameLogic{

	public static ArrayList thinkingAI (int[][] board, int playerPiece){
		int index = 0;
		ArrayList moveCoordinates = new ArrayList();
		ArrayList movesY = unpackArrayY(possibleMoves(board, playerPiece));
		ArrayList movesX = unpackArrayX(possibleMoves(board, playerPiece));
		int flipAmount[] = new int[movesY.size()];
		
		// Checks for corner pieces
		for (int i = 0; i < movesY.size(); i++){
			if ((movesY.get(i).equals(0) && movesX.get(i).equals(0)) || (movesY.get(i).equals(0) && movesX.get(i).equals(7)) 
					|| (movesY.get(i).equals(7) && movesX.get(i).equals(0)) || (movesY.get(i).equals(7) && movesX.get(i).equals(7))){
				moveCoordinates.add(movesY.get(i));
				moveCoordinates.add(movesX.get(i));
				return moveCoordinates;
			}
		}
		
		// Checks for side pieces
		for (int i = 0; i < movesY.size(); i++){
			if (movesY.get(i).equals(0) || movesY.get(i).equals(7) || movesX.get(i).equals(0) || movesY.get(i).equals(7)){
				moveCoordinates.add(movesY.get(i));
				moveCoordinates.add(movesX.get(i));
				return moveCoordinates;
			}
		}
		
		// Creates an array that will store the amount of pieces that will be flipped with each move balcita
		for (int i = 0; i < flipAmount.length; i++){
			flipAmount[i] = validMove(board, playerPiece, i, i).size();
		}
		
		index = maxNum(flipAmount); // Chooses the move that will get the maximum number of pieces
		moveCoordinates.add(movesY.get(index));
		moveCoordinates.add(movesX.get(index));
		return moveCoordinates;
		
	}
	//Random algorithm
	public static ArrayList randomAlgorithm (int[][] board, int playerPiece){
		ArrayList moveCoordinates = new ArrayList();
		ArrayList movesY = unpackArrayY(possibleMoves(board, playerPiece));
		ArrayList movesX = unpackArrayX(possibleMoves(board, playerPiece));
		Random rand = new Random();
		int numberOfMoves = movesY.size();
		int random = rand.nextInt((numberOfMoves - 1) - 0 + 1) + 0; // this looks weird but dont change it
		moveCoordinates.add(movesY.get(random));
		moveCoordinates.add(movesX.get(random));
		return moveCoordinates;
		
	}
	
	
	public static int maxNum(int[] array){ //Finds the largest number in an array
		int max = array[0];
		int index = 0;
        
        for(int i = 1; i < array.length; i++){
                if(array[i] > max)
                        max = array[i];
        }
        
        for (int i = 0; i < array.length; i++){
        	if (array[i] == max){
        		index = i;
        		break;
        	}
        }
        return index;
	}
}
