package game_for_assignment;

import java.io.*;
//This is also acting like the factory pattern demo class
public class GameApp {

	public static void main(String[] args) throws IOException {
		
		
		
		MessageFactory messagefactory = new MessageFactory();
		final GameData game  = new GameData();
		Message msg1 = messagefactory.getMessage("moderator",0);
		msg1.startMessage();
		final Moderator moderator  = new Moderator(game);
		Message msg2 = messagefactory.getMessage("player",1);
		msg2.startMessage();
		final Player player1 = new Player(game, 0);
		Message msg3 = messagefactory.getMessage("player",2);
		msg3.startMessage();
		final Player player2 = new Player(game, 1);
		Message msg4 = messagefactory.getMessage("player",3);
		msg4.startMessage();
		final Player player3 = new Player(game, 2);
		
		Thread moderatorThread  = new Thread(moderator);
		Thread player1Thread = new Thread(player1);
		Thread player2Thread = new Thread(player2);
		Thread player3Thread = new Thread(player3);
		
		moderatorThread.start();
		player1Thread.start();
		player2Thread.start();
		player3Thread.start();
		
		//System.out.println("In main thread");
		try{Thread.sleep(12000); 
        
        }  catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//This will execute after winner is declared
		PrintWriter outStream = null;
	    try {
	       outStream = 
				new PrintWriter(new FileOutputStream("data.txt",true));
		 }
	    catch(FileNotFoundException e)
	    {
	       System.err.println("Error opening the file data.txt.");
	       System.exit(0);
	    }
	    if(Moderator.winner == 0)  outStream.println("Nobody Won");
	    
	    else outStream.println("The winner of this game is: Player " + Moderator.winner);
	    
	    //outStream.println("jumped over the lazy dog.");

	    outStream.close( );


		
		
    }
		
		
	}
	

	
	
	


