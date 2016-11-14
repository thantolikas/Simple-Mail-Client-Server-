import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class MailClient {
	public static void main(String[] args) throws IOException {

		if (args.length != 2) {
			System.err
					.println("Usage: java <name> <host name> <port number>");
			System.exit(1);
		}

		String hostName = args[0];
		int portNumber = Integer.parseInt(args[1]);

		try (Socket emailSocket = new Socket(hostName, portNumber);
			
			PrintWriter out = new PrintWriter(emailSocket.getOutputStream(),true);
			BufferedReader in = new BufferedReader(new InputStreamReader(emailSocket.getInputStream()));) {
			BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
			
			String fromServer;
			String fromUser;
			
			 while ((fromServer = in.readLine()) != null) {
	                System.out.println(fromServer + "\n");
	                
	                if (fromServer.equals("Exit"))
	                    break;
	                 
	                fromUser = stdIn.readLine();
	                if (fromUser != null) {
	                    out.println(fromUser);
	                }
	            }
			


		} catch (UnknownHostException e) {
			System.err.println("Don't know about host " + hostName);
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to "
					+ hostName);
			System.exit(1);
		}
	}
}
