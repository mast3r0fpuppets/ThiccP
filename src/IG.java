import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class IG {
	private JFrame frame;
	

	public IG() {
		frame = new JFrame("Interface Grafica");
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		addFrameContent();
		frame.pack();
	}

	public void open(int i, int j) {
		frame.setSize(i, j);
		frame.setVisible(true);
	}

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
		JProgressBar prog = new JProgressBar();
		ladodireito.add(prog);
		frame.add(ladodireito, BorderLayout.EAST);
		// CENTRO
		JList list = new JList();
		frame.add(list, BorderLayout.CENTER);
	}

	public static void main(String args[]) throws NumberFormatException, IOException {
		IG window = new IG();
		Utilizador u = new Utilizador();
		window.open(350,350);
		u.Connect("8080", "22");
//		u.Insc("21");
		
	}
}