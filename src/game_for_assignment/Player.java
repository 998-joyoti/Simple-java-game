package game_for_assignment;


import java.util.ArrayList;
//import java.util.Arrays;
import java.util.Random;



public class Player implements Runnable, Message {
	
	

	private int id;							// player id [0 or 1]
	private GameData gameData;				// shared object	
	private int totalNumbersFound;    		// total numbers found
	private final static int Max = 10;      //Max cards that can be displayed
	int flag = 1; //to ensure first player who gets hold of the shared variable wins in case of a tie
	
	
	
	ArrayList<Integer> ticket  = new ArrayList<Integer>(10); //Used ArrayList to benefit from 'Generics'
			
	/************************************ DO NOT MODIFY *********************************/
	public Player(int id) { this.id = id;}
	
	public Player(GameData gameData, int id) { 
		
		this.id = id; 		
		this.gameData = gameData;	
		this.totalNumbersFound = 0;
		
		// randomly generate 10 numbers and store them in the player Ticket
		for(int i = 0; i < Max; i++) {
			int p = randInt(0,50);
			ticket.add(p);
		}
		
		try{
			Thread.sleep(1000);}
		catch(InterruptedException e) {
			System.out.println("Interrupted!!");
		}
		System.out.println("Player" + (id + 1) + ":" + ticket);
	}
	
	/************************************ DO NOT MODIFY *********************************/
	private static int randInt(int min, int max) {	//method to generate random numbers
	    Random rand = new Random();
	    int randomNum = rand.nextInt((max - min) + 1) + min;
	    return randomNum;
	}
	
	public void startMessage(){
		
		System.out.println("I am Player " + this.id);
		
	}
	
	/************************************ WRITE CODE FOR THIS METHOD ********************/
	public void run() {
				
		// take a lock on the instance of SharedData using lock1
		
		  synchronized (gameData.lock1) {
	            // All players execute while the game is not complete
	            while (gameData.gameCompleteFlag == false) {

	                // Wait till moderator announces number
	                while (gameData.noAnnouncedFlag = false || gameData.playerChanceFlag[id] == true) {
	                	//System.out.println("i am stuck in player" + id);
	                    try {
	                        gameData.lock1.wait();
	                        //System.out.println("i am stuck in player");
	                    } catch (InterruptedException e) {
	                        // TODO Auto-generated catch block
	                        e.printStackTrace();
	                        
	                    }
	                }

	                // Its important to check this condition again because it is possible that
	                // one player may have found all the numbers when the other was waiting
	                if (!gameData.gameCompleteFlag) {

	                   //Checking if announced number matches in players' tickets
	                    for (int i = 0; i < ticket.size(); i++) {
	                        if (ticket.get(i) == gameData.maintix.get(gameData.maintix.size()-1)) {
	                            this.totalNumbersFound++;
	                            this.ticket.remove(i);
	                            
	                            System.out.println("Matched with Player:" + (id + 1));
	                            break;
	                        }

	                    }

	                   //Check if some one wins and change flag so that there is no tie
	                    if (totalNumbersFound == 3 && flag == 1) {
	                        gameData.playerSuccessFlag[id] = true;
	                        flag = 0;
	                        
	                        
	                       
	                    }


	                    // player sets its chance flag
	                    gameData.playerChanceFlag[id] = true;

	                    
	                    gameData.lock1.notifyAll();
	                }
	            }
	        }
	    }
	}