import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class MailServer {

	private static ServerSocket listenSocket;

	public static void main(String[] args) throws IOException {

		ArrayList<Account> accounts = new ArrayList<Account>();
		accounts = new ArrayList<Account>();

		Account john = new Account("john@john.gr", "john");
		Account costas = new Account("costas@costas.gr", "costas");
		Account thanasis = new Account("thanasis@thanasis.gr", "thanasis");

		accounts.add(john);
		accounts.add(costas);
		accounts.add(thanasis);

		john.addMail(new Email("costas@costas.gr", "john@john.gr", "Hello",
				"How are you?"));
		john.addMail(new Email("costas@costas.gr", "john@john.gr", "Hello",
				"How are you?"));

		costas.addMail(new Email("john@john.gr", "costas@costas.gr", "Hello",
				"How are you?"));
		costas.addMail(new Email("john@john.gr", "costas@costas.gr", "Hello",
				"How are you?"));

		thanasis.addMail(new Email("costas@costas.gr", "thanasis@thanasis.gr",
				"Hello", "How are you?"));
		thanasis.addMail(new Email("costas@costas.gr", "thanasis@thanasis.gr",
				"Hello", "How are you?"));

		if (args.length != 1) {
			System.err.println("Usage: java <name> <port number>");
			System.exit(1);
		}

		int portNumber = Integer.parseInt(args[0]);

		try {
			listenSocket = new ServerSocket(portNumber);
			while (true) {
				Socket clientSocket = listenSocket.accept();
				Thread t = new Thread(new MailThread(clientSocket, accounts));
				t.start();
			}
		} catch (IOException e) {
			System.out.println("Listen socket:" + e.getMessage());
		}
	}

}

class MailThread implements Runnable {
	Socket clientSocket;
	PrintWriter out;
	BufferedReader in;
	ArrayList<Account> accounts;

	public MailThread(Socket clientSocket, ArrayList<Account> accounts) {
		this.accounts = accounts;
		try {
			out = new PrintWriter(clientSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(
					clientSocket.getInputStream()));

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public void run() {
		String inputLine = "", outputLine = "";

		MailProtocol mp = new MailProtocol(accounts);
		try {
			outputLine = mp.processInput(null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.println(outputLine);

		try {
			while ((inputLine = in.readLine()) != null) {
				outputLine = mp.processInput(inputLine);
				out.println(outputLine);
				if (outputLine.equals("Exit"))
					break;

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
