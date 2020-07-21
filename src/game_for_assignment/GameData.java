package game_for_assignment;

import java.util.ArrayList;

public class GameData {
	//These variables have been named like in Lab-9 sheet
	
	public int announcedNumber = 0;	 
	public boolean gameCompleteFlag = false;	
	public boolean noAnnouncedFlag = false;
	public boolean[] playerSuccessFlag = new boolean[3];
	public boolean[] playerChanceFlag = new boolean[3];
	public ArrayList<Integer> maintix  = new ArrayList<Integer>(); //Shared ArrayList for accessing numbers announced
	public static int count = 0;
	public Object lock1 = new Object(); //To ensure locking of shared variables (Mutual exclusion) 
	}
