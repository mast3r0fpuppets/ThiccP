import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Diretorio {

	static class ConnectionThread extends Thread {
		private Socket socket;
		BufferedReader in;
		PrintWriter out;
		List<PrintWriter> outs = new ArrayList<>();
		List<User> users = new ArrayList<>();
		User john = new User("222.111.123.1", "8");

		public ConnectionThread(Socket socket) {
			this.socket = socket;
			try {
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
				outs.add(out);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		public void run() {
			String msg;
			users.add(john);
			try {
				msg = in.readLine();
				if (msg.contains("INSC")) {
					String[] partes = msg.split(" ");
					User user = new User(partes[1], partes[2]);
					users.add(user);
					System.out.println(users);
				} else if (msg.equals("CLT")) {
					for (User usr : users) {
						out.println("CLT " + usr.toString());
					}
					out.println("END");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	public static void main(String[] args) throws IOException, InterruptedException {
		System.out.println("Diretorio");
		ServerSocket server = new ServerSocket(Integer.parseInt(args[0]));
		while (true) {
			Socket socket = server.accept();
			ConnectionThread t = new ConnectionThread(socket);
			t.start();
		}
	}

}