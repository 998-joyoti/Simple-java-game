package game_for_assignment;


public class Singleton{
	
	



	private static Singleton instance = null;
	
	public static synchronized Singleton  getInstance() 
	 {
		 if (instance ==null)
		 {
			 instance = new Singleton();
		 }
		 return instance; 
	 }
	
	public void getWinner(int winner) { 
		
		System.out.println("The winner is: Player " + winner);
		
		}
	
	}