import java.util.ArrayList;


public class Account {
	private String username;
	private String password;
	ArrayList<Email> mailbox;
	
	public Account(String username,String password) {
		this.username = username;
		this.password = password;
		mailbox = new ArrayList<Email>();
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public ArrayList<Email> getMailbox() {
		return mailbox;
	}

	public void addMail(Email email) {
		mailbox.add(email);
	}
}
