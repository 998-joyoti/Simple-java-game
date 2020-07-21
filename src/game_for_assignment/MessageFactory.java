package game_for_assignment;

public class MessageFactory {
	
	public Message getMessage(String type, int id) {
		
		if (type == null) return null;
		
		if (type.contentEquals("player")) return new Player(id);
		
		if (type.contentEquals("moderator")) return new Moderator();
		
		return null;
		
		
	}

}
