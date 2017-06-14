package application;
//Import Statements
import java.io.*;




public class FileController {

	
	public static void saveGame(int[][]board) throws IOException{

		File file = new File ("game.txt");
		file.createNewFile ();
		
		
	    FileWriter writer = new FileWriter (file);      // creates a FileWriter Object
	    for (int y = 0; y <= 7; y++){
	    	for (int x = 0; x <= 7; x++){
	    		writer.write(Integer.toString(board[y][x]));
	    		writer.write("\r\n");
	    	}
	    	
	    }

	    writer.flush ();
	    writer.close ();
	}
	
	public static void loadGame(int[][]board){
		String tempStr = null;
		int gameLoad[] = new int[64]; 
		int count = 0;
		try{
		    // fileReader is an object used to identify the file that we want to read.
		    FileReader fileReader = new FileReader ("game.txt");

		    // Always wrap FileReader in BufferedReader.
		    @SuppressWarnings("resource")
			BufferedReader bufferedReader = new BufferedReader (fileReader);

		    while ((tempStr = bufferedReader.readLine ()) != null){
		    	gameLoad[count] = Integer.parseInt(tempStr);
		    	count ++;
		    } 
		}
		catch (Exception e)	{}
		count = 0;
		for (int y = 0; y <= 7; y++){
			for (int x = 0; x <= 7; x++){
				board[y][x] = gameLoad[count];
				count ++;
			}
		}


	    
		
	}
	public static void saveTurn(int turn) throws IOException{

		File file = new File ("turn.txt");
		file.createNewFile ();
		
		
	    FileWriter writer = new FileWriter (file);      // creates a FileWriter Object
	    writer.write(Integer.toString(turn));

	    writer.flush ();
	    writer.close ();
	}
	
	public static void loadTurn(int turn) throws NumberFormatException, IOException{
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
