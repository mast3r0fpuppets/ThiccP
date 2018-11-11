public class User {
	private String IP;
	private String Porto;
	
	
	
	public User(String iP, String porto) {
		IP = iP;
		Porto = porto;
	}



	@Override
	public String toString() {
		return IP +" "+ Porto;
	}
	
	
	
}
