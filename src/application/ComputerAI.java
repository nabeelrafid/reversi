package application;

import java.util.ArrayList;
import java.util.Random;

public class ComputerAI extends GameLogic{
	//Minimax tree search algorithms will be used
	
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
		//wait(99999999);
		return moveCoordinates;
		
	}
	public static void wait(int time){
		long count = 0;
		while (count < time * 999999999){
			count ++;
		}
	}
	

}
