package game_for_assignment;


import java.util.*;

public class Moderator implements Runnable,Message{
	
	public static int winner = 0;
	
    //public static boolean complete = false;
	//public static int winner;
	private GameData gameData; //shared data 
    //private int numberAnnounced = 0; //it is set when a button on GUI is pressed
    
    ArrayList<Integer> list= new ArrayList<Integer>();
    int count = 0;
    
    public Moderator() {}
    
	public Moderator(GameData gameData) {
		this.gameData = gameData;
		GameData.count++;
		try{
			Thread.sleep(1000);}
		catch(InterruptedException e) {
			System.out.println("Interrupted!!");
		}
		System.out.println("I have started the game!");
		
		
		}
	
public void startMessage(){
		
		System.out.println("I am the Moderator");
		
	}
		
				

	public static int counter = 0;
   public void run() {
	  
	   
    //lock shared data for moderator
		
		synchronized(gameData.lock1) {	
			 
			   
			   
			
			//specify condition for player1,player2,player3 
			// moderator executes until either (or all) players sets their playerSuccessFlag OR total cards exceed 10
			
			while (!gameData.playerSuccessFlag[0] && !gameData.playerSuccessFlag[1] && !gameData.playerSuccessFlag[2] && counter<10) {
				
				// set number announced flag to false before announcing the number
				gameData.noAnnouncedFlag = false;
				
				// set checked flag of both players as false before the number is announced
				gameData.playerChanceFlag[0] = false;
				gameData.playerChanceFlag[1] = false;
				gameData.playerChanceFlag[2] = false;
		
				
				try{Thread.sleep(1000); 
                
                }  catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				Random rand = new Random();
	                int randomNum = rand.nextInt((50-1) + 1) + 1;
	                
                    gameData.maintix.add(randomNum);
	                System.out.println("Announced number: "+ randomNum);
	                counter++;
	                
				
				
				gameData.noAnnouncedFlag = true;
				
				//Notify players so that they can check 
				
				gameData.lock1.notifyAll();
				
											
				// wait while the players haven't checked the numbers
				
				while(gameData.playerChanceFlag[0] == false || gameData.playerChanceFlag[1] == false || gameData.playerChanceFlag[2] == false )
                {  //System.out.println("i am stuck in moderator");
                    try {
                        gameData.lock1.wait();
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }			
			}
			
			//Ensure playerChanceFlag is false to prevent deadlock in player wait
			
			if(gameData.playerSuccessFlag[0]== true || gameData.playerSuccessFlag[1]== true || gameData.playerSuccessFlag[2]== true) {
				gameData.playerChanceFlag[0] = false;
                gameData.playerChanceFlag[1] = false;
                gameData.playerChanceFlag[2] = false;
			}
			
			
			 // Check if Player1 has won
            if (gameData.playerSuccessFlag[0]== true && gameData.playerSuccessFlag[1]== false && gameData.playerSuccessFlag[2]== false)
            {
                
            	Singleton.getInstance().getWinner(1);
            	winner = 1;
            	
                 
            }
            // Check if Player2 has won
            if(gameData.playerSuccessFlag[1]== true && gameData.playerSuccessFlag[0]== false && gameData.playerSuccessFlag[2]== false)
            {
                
                
            	Singleton.getInstance().getWinner(2);
            	winner = 2;
            	
                
            }
            //Check if player3 won
            if(gameData.playerSuccessFlag[2]== true && gameData.playerSuccessFlag[0]== false && gameData.playerSuccessFlag[1]== false)
            {
                
            	Singleton.getInstance().getWinner(3);
            	winner = 3;
            	
            }
            
          
            
            if(counter==10 && gameData.playerSuccessFlag[0]== false && gameData.playerSuccessFlag[1]== false && gameData.playerSuccessFlag[2]== false) {
            	System.out.println("Nobody won");
            	gameData.playerChanceFlag[0] = false;
                gameData.playerChanceFlag[1] = false;
                gameData.playerChanceFlag[2] = false;
            	
            	
            }

            gameData.gameCompleteFlag = true; // Set the complete flag to true
            gameData.lock1.notifyAll(); 

				
		} 		
	}

	
   
}




