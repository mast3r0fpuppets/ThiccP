import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class FileServer {
	private static ArrayList<FileDetails> foundfiles;

	static class ConnectionThread extends Thread {
		private BufferedReader in;
		private ObjectInputStream input;
		private PrintWriter out;
		private ArrayList<String> pastas;
		String pasta;
		Socket socket;

		public ConnectionThread(Socket socket, String pasta) {
			try {
				this.pasta = pasta;
				this.socket = socket;
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				input = new ObjectInputStream(socket.getInputStream());
				out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		public void run() {
			WordSearchMessage w;
			File[] files = new File(pasta).listFiles();
			while (true) {
				try {
					w = (WordSearchMessage) input.readObject();
					for (File file : files) {
						if (file.getName().contains(w.toString())) {
							out.println(file);
						}
					}

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

//			for(FileDetails f : foundfiles) {
//				out.println(f);
//				
//			}
//			foundfiles.clear();
//			
//		}

		}

	}

	public void StartServing(int porto, String pasta) throws IOException {
		ServerSocket p2pserv = new ServerSocket(porto);
		while (true) {
			Socket socket = p2pserv.accept();
			ConnectionThread t = new ConnectionThread(socket, pasta);
		}
	}

	public ArrayList<FileDetails> SearchFiles(String pasta, WordSearchMessage w) {
		foundfiles = new ArrayList<>();
		File[] files = new File(pasta).listFiles();
		for (File file : files) {
			if (file.getName().contains(w.toString())) {
				FileDetails ficheiro = new FileDetails(file.getName(), file.length());
				foundfiles.add(ficheiro);
			}
		}
		return foundfiles;
	}

}
