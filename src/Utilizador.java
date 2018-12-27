import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Utilizador {

	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	
	public void ServeFile(String porto, String pasta) throws IOException {
		FileServer fs = new FileServer();
	

		
		
	}

	public void Connect(String ServerIP, String PortoServer, String PortoUsr, String pasta)
			throws NumberFormatException, IOException {

		InetAddress address = InetAddress.getLocalHost();
		Socket socket = new Socket(ServerIP, Integer.parseInt(PortoServer));
		System.out.println("Utilizador");
		ServeFile(PortoUsr, pasta);
		out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out.println("INSC " + address.getHostAddress() + " " + PortoUsr);
//		SearchFiles(pasta);
		try {
			while (true) {
				Scanner scanner = new Scanner(System.in);
				String line = scanner.nextLine();

				if (!line.isEmpty()) {
					if (line.equals("CLT")) {
						CLT();
						String msg;
						while ((msg = in.readLine()) != null) {
							System.out.println(msg);
							
							if (msg.equals("END"))
								break;
						}
					}
					if (line.equals("Exit")) {
//						out.println("Exit");
//						break;
						Exit();
					}
				}
			}

//			socket.close(); 
		} catch (IllegalStateException e) {
			e.printStackTrace();
		}

	}
//	public void SearchFiles(String pasta) {
//		File[] files = new File(pasta).listFiles();
//		
//		for(File file : files) {
//			System.out.println((file.getName()));
//		}
//		
//		
//	}


	public void Exit() {
		out.println("Exit");
		//			socket.close();
		System.exit(0);

	}

	public void CLT() {
		out.println("CLT");
	}

	public static void main(String[] args) throws UnknownHostException, IOException {
		Utilizador u = new Utilizador();
		u.Connect(args[0], args[1], args[2], args[3]);

	}
}
