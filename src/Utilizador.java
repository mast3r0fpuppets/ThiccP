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

	public static void main(String[] args) throws UnknownHostException, IOException {
		InetAddress address = InetAddress.getLocalHost();
		Socket socket = new Socket(address, Integer.parseInt(args[0]));
		System.out.println("Utilizador");
		// args[0] porto do diretorio
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out.println("INSC " + address.getHostAddress() + " " + args[1]);
		// args[1] porto do utilizador
		Scanner scanner = new Scanner(System.in);
		try {
			while (true) {
				String line = scanner.nextLine();
				
				if (!line.isEmpty()) {
					if (line.equals("CLT")) {
						System.out.println(line);
						out.println("CLT");
						String msg = in.readLine();
						if (msg != null) {
							System.out.println(msg);
						}
					}
					if (line.equals("Exit")) {
						socket.close();
					}
				}
				
			}
		} catch (IllegalStateException e) {
			e.printStackTrace();
		}

	}

}
