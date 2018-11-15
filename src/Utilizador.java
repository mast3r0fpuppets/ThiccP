import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Utilizador {
	
	public void Connect(String PortoServer, String PortoUsr) throws NumberFormatException, IOException {
		
		InetAddress address = InetAddress.getLocalHost();
		Socket socket = new Socket(address, Integer.parseInt(PortoServer));
		System.out.println("Utilizador");
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out.println("INSC " + address.getHostAddress() + " " + PortoUsr);
		try {
			
			while (true) {
				Scanner scanner = new Scanner(System.in);
				String line = scanner.nextLine();

				if (!line.isEmpty()) {
					if (line.equals("CLT")) {
						out.println("CLT");
						String msg;
						while( (msg =in.readLine())!=null ) {
							System.out.println(msg);
							if(msg.equals("END"))
								break;
						}
					}
					if (line.equals("Exit")) {
						out.println("Exit");
						break;
					}
				}
			}
			
			socket.close(); 
		} 
		catch (IllegalStateException e) {
			e.printStackTrace();
		}

	}
		
	
	

	public static void main(String[] args) throws UnknownHostException, IOException {
		Utilizador u = new Utilizador();
		u.Connect(args[0], args[1]);
		
	}
}
