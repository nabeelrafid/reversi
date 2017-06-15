package application;
//Import Statements
import java.io.*;




public class FileController {

	
	public static void saveGame(int[][]board) throws IOException{ // Saves the game
		
		// Creates or overwrites a game file
		File file = new File ("game.txt");
		file.createNewFile ();
	    FileWriter writer = new FileWriter (file);      // creates a FileWriter Object
	    
	    
	    for (int y = 0; y <= 7; y++){
	    	for (int x = 0; x <= 7; x++){ // Cycles through the entire board
	    		writer.write(Integer.toString(board[y][x])); // Prints out each individual value in a new line
	    		writer.write("\r\n");
	    	}
	    	
	    }

	    writer.flush (); // Flush and close the FileWriter
	    writer.close ();
	}
	
	public static void loadGame(int[][]board){
		String tempStr = null;
		int gameLoad[] = new int[64]; // This array will store all the values from the game file
		int count = 0;
		try{
		    // fileReader is an object used to identify the file that we want to read.
		    FileReader fileReader = new FileReader ("game.txt");

		    // Always wrap FileReader in BufferedReader.
		    @SuppressWarnings("resource")
			BufferedReader bufferedReader = new BufferedReader (fileReader);

		    // Fetches all the game data from the file and adds them to the array
		    while ((tempStr = bufferedReader.readLine ()) != null){ 
		    	gameLoad[count] = Integer.parseInt(tempStr);
		    	count ++;
		    } 
		}
		catch (Exception e)	{} //NoOp
		
		// Changes the board to the newest positions from the array
		count = 0;
		for (int y = 0; y <= 7; y++){
			for (int x = 0; x <= 7; x++){
				board[y][x] = gameLoad[count];
				count ++;
			}
		}


	    
		
	}
	public static void saveTurn(int turn) throws IOException{ // Records whose turn it is

		File file = new File ("turn.txt");
		file.createNewFile ();
		
		
	    FileWriter writer = new FileWriter (file);      // creates a FileWriter Object
	    writer.write(Integer.toString(turn));

	    writer.flush ();
	    writer.close ();
	}
	
	public static void loadTurn(int turn) throws NumberFormatException, IOException{ // Fetches whose turn it is
		String tempStr = null;


	    // fileReader is an object used to identify the file that we want to read.
	    FileReader fileReader = new FileReader ("turn.txt");

	    // Always wrap FileReader in BufferedReader.
	    @SuppressWarnings("resource")
		BufferedReader bufferedReader = new BufferedReader (fileReader);

	    while ((tempStr = bufferedReader.readLine ()) != null){
	    	turn = Integer.parseInt(tempStr);
	    	
	    } 





	    
		
	}
	
	
	

}
