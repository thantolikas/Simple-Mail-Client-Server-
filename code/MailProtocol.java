import java.io.IOException;
import java.util.List;

public class MailProtocol {
	private static final int BEGINNING = 0;
	private static final int WAITING = 1;
	private static final int LOGIN = 2;
	private static final int REGISTER = 3;
	private static final int LOGGEDIN = 4;
	private static final int NEWEMAIL = 5;
	private static final int READEMAIL = 6;
	private static final int DELETEMAIL = 7;

	private boolean flag = false, flag2 = false;
	private String temp, temp2;

	private int state = BEGINNING;

	private List<Account> accounts;
	private int index;

	public MailProtocol(List<Account> accounts) {
		this.accounts = accounts;

	}

	public String processInput(String input) throws IOException {
		
		if (state == BEGINNING) {
			state = WAITING;
			return "Welcome!  >LogIn  >Register  >Exit";
		} else if (state == WAITING) {
			if (input.equalsIgnoreCase("LogIn")) {
				state = LOGIN;
				return "Please Enter Username: ";
			} else if (input.equalsIgnoreCase("Register")) {
				state = REGISTER;
				return "Please Enter Username ";
			} else {
				return "Exit";
			}
		} else if (state == LOGIN) {
			if (!flag) {
				temp = input;
				flag = true;
				return "Please Enter Password: ";
			} else {
				for (Account acc : accounts) {
					if (acc.getUsername().equalsIgnoreCase(temp)
							&& acc.getPassword().contentEquals(input)) {
						index = accounts.indexOf(acc);
						state = LOGGEDIN;
						flag = false;
						return "Welcome back " + acc.getUsername() + showMenu();
					}

				}
				state = WAITING;
				flag = false;
				return "Incorrect username or password"
						+ " >LogIn  >Register  >Exit ";
			}
		} else if (state == REGISTER) {
			if (!flag) {
				for (Account acc : accounts) {
					if (acc.getUsername().equalsIgnoreCase(input)) {
						return "This username is already in use! Please enter a new one: ";
					}
				}
				temp = input;
				flag = true;
				return "Please Enter a Password:  ";
			} else {
				Account ta = new Account(temp, input);
				accounts.add(ta);
				state = WAITING;
				flag = false;
				return "Registration successful!   >LogIn  >Register  >Exit ";
			}
		} else if (state == LOGGEDIN) {
			if (input.equalsIgnoreCase("NewEmail")) {
				state = NEWEMAIL;
				return "Receiver: ";
			} else if (input.equalsIgnoreCase("ShowEmails")) {
				String emails = "";
				for (Email email : accounts.get(index).getMailbox()) {
					emails = emails
							+ (accounts.get(index).getMailbox().indexOf(email) + 1)
							+ " " + email.isNew() + " " + email.getSender()
							+ " " + email.getSubjetc();
				}
				return emails + showMenu();
			} else if (input.equalsIgnoreCase("ReadEmail")) {
				state = READEMAIL;
				return "Please give the ID of the Email: ";
			} else if (input.equalsIgnoreCase("DeleteEmail")) {
				state = DELETEMAIL;
				return "Please give the ID of the Email: ";
			} else if (input.equalsIgnoreCase("LogOut")) {
				state = WAITING;
				return "Bye! " + " >LogIn  >Register  >Exit ";
			} else if (input.equalsIgnoreCase("Exit")) {
				return "Exit ";
			} else {
				return "Incorrect Input! Try Again! ";
			}
		} else if (state == NEWEMAIL) {
			if (!flag) {
				temp = input;
				flag = true;
				return "Subject: ";
			} else if (!flag2) {
				temp2 = input;
				flag2 = true;
				return "Main Body: ";
			} else {
				Email email = new Email(accounts.get(index).getUsername(),
						temp, temp2, input);
				for (Account acc : accounts) {
					if (acc.getUsername().equalsIgnoreCase(temp)) {
						acc.addMail(email);
						state = LOGGEDIN;
						flag = false;
						flag2 = false;
						return "Email Sent Successfully! " + showMenu();
					}
				}
				flag = false;
				flag2 = false;
				state = LOGGEDIN;
				return "Receiver Not Found! " + showMenu();
			}
		} else if (state == READEMAIL) {
			accounts.get(index).getMailbox().get(Integer.parseInt(input)).setNew();
			state = LOGGEDIN;
			return "Sender: "
					+ accounts.get(index).getMailbox()
							.get(Integer.parseInt(input)).getSender()
					+ "Subject: "
					+ accounts.get(index).getMailbox()
							.get(Integer.parseInt(input)).getSubjetc()
					+ accounts.get(index).getMailbox()
							.get(Integer.parseInt(input)).getMainbody()
					+ showMenu();
		} else {
			state = LOGGEDIN;
			accounts.get(index).getMailbox().remove(Integer.parseInt(input));
			return "Email Deleted Successfully! " + showMenu();
		}

	}

	private String showMenu() {
		return " >NewEmail  >ShowEmails  >ReadEmail  >DeleteEmail  >Logout  >Exit";

	}
}