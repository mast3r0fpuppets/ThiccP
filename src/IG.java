import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class IG extends Thread {
	private JFrame frame;
	private static Utilizador u;
	private DefaultListModel<FileDetails> files = new DefaultListModel<FileDetails>();
	private JList<FileDetails> fileList = new JList<FileDetails>(files);
	private BufferedReader in;
	private PrintWriter out;
	private FileServer fs;
	private static String pasta;
	private static String porto;

	public IG() throws NumberFormatException, IOException {
		fs = new FileServer();
		frame = new JFrame("Interface Grafica");
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		addFrameContent();
		frame.pack();
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				u.Exit();
			}
		});
		System.out.println("IMPRIMO");
//		p2pcom(porto);
	}

	public void open(int i, int j) {
		frame.setSize(i, j);
		frame.setVisible(true);
	}

//	public void p2pcom(String porto) throws NumberFormatException, IOException {
//		InetAddress address = InetAddress.getLocalHost();
//		Socket socket = new Socket(address.getHostAddress(), Integer.parseInt(porto));
//		ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
//		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//		out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
//		try {
//			FileDetails object = (FileDetails) input.readObject();
//			files.addElement(object);
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}

	private void addFrameContent() {

		JPanel partecima = new JPanel();

		partecima.setLayout(new GridLayout(1, 3));
		JLabel text = new JLabel("Texto a procurar: ");
		partecima.add(text);
		JTextField search = new JTextField();
		partecima.add(search);
		JButton procurar = new JButton("Procurar");

		procurar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				files.removeAllElements();
				if (!search.getText().isEmpty()) {
					WordSearchMessage w = new WordSearchMessage(search.getText());
					for (FileDetails f : fs.SearchFiles(pasta, w)) {
						files.addElement(f);

					}
				}

			}
		});
		partecima.add(procurar);

		frame.add(partecima, BorderLayout.NORTH);
		// LADO DIREITO
		JPanel ladodireito = new JPanel();
		ladodireito.setLayout(new GridLayout(2, 1));
		JButton descarregar = new JButton("Descarregar");
		descarregar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

			}
		});
		ladodireito.add(descarregar);
//		JButton disconnect = new JButton("Disconnect");
//		disconnect.addActionListener(new ActionListener() {
//
//			public void actionPerformed(ActionEvent e) {
//				u.Exit();
//			}
//		});
//		ladodireito.add(disconnect);
		JProgressBar prog = new JProgressBar();
		ladodireito.add(prog);
		frame.add(ladodireito, BorderLayout.EAST);
		// CENTRO
//		JList list = new JList();
//		frame.add(list, BorderLayout.CENTER);

		frame.add(new JScrollPane(fileList), BorderLayout.CENTER);
		fileList.addListSelectionListener(new ListSelectionListener() {
			private int previous = -1;

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (fileList.getSelectedIndex() != -1 && previous != fileList.getSelectedIndex()) {

				}
				previous = fileList.getSelectedIndex();
			}
		});
	}

	public static void main(String args[]) throws NumberFormatException, IOException {
		pasta = args[3];
		porto = args[2];
		IG window = new IG();
		window.open(350, 350);
		u = new Utilizador();
		u.Connect(args[0], args[1], args[2], args[3]);

	}
}