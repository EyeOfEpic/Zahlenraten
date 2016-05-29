import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class Main {

	private JFrame frame;
	private JTextArea text = new JTextArea();
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {e.printStackTrace();}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();

		int zufall;
		int spanne=100;
		int zahl = 0;
		int zaehler = 1;
		int versucheUebrig = (int) (Math.log(spanne)/Math.log(2));
		int speicher = versucheUebrig;
		int unterschied;
		boolean istZahl;

		zufall = (int) (Math.random()*spanne)+1;
		//text.append(Integer.toString(zufall)+"/n");

		do{
			text.append("Verbleibende Versuche im Optimalfall: "+versucheUebrig+"\n");
			try {
				JOptionPane pane = new JOptionPane();
				String temp = pane.showInputDialog("Geben Sie eine Zahl zwischen 0 und "+ spanne+" ein!\n");
				if (temp == null) System.exit(0);
				zahl = Integer.parseInt(temp);
				unterschied = Math.abs(spanne - zahl);
				versucheUebrig = (int) (Math.log(unterschied)/Math.log(2));
				text.append("Verbleibende Versuche im Optimalfall: "+ (speicher-zaehler) +" Vom jetzigen Punkt aus: "+versucheUebrig+"\n");
				text.append("Eingegebene Zahl: "+temp+"\n");
				istZahl = true;
			} catch (Exception e) {
				text.append("Die Eingabe muss eine Zahl sein!\n");
				istZahl = false;
			}
		}while (!istZahl);

		if (zahl > spanne) text.append("Die Zahl kann gar nicht vorkommen!\n");
		else if (zahl < 0) text.append("Seriously?\n");

		while (zahl != zufall)
		{
			int zahlvorher = zahl;
			zaehler ++;
			if (zahl > zufall)
			{
				text.append("Die gesuchte Zahl ist kleiner\n");
			}
			else if (zahl < zufall)
			{
				text.append("Die gesuchte Zahl ist größer\n");
			}
			do{
				try {
					JOptionPane pane = new JOptionPane();
					String temp = pane.showInputDialog("Geben Sie eine Zahl zwischen 0 und "+ spanne+" ein!\n");
					if (temp == null) System.exit(0);
					zahl = Integer.parseInt(temp);
					unterschied = Math.abs(zahlvorher - zahl);
					versucheUebrig = (int) (Math.log(unterschied)/Math.log(2));
					text.append("Verbleibende Versuche im Optimalfall: "+ (speicher-zaehler+1) +" Vom jetzigen Punkt aus: "+versucheUebrig+"\n");
					text.append("Eingegebene Zahl: "+temp+"\n");
					istZahl = true;
				} catch (Exception e) {
					text.append("Die Eingabe muss eine Zahl sein!\n");
					istZahl = false;
				}
			}while (!istZahl);
		}
		scrollPane.setViewportView(text);
		text.append("Die gesuchte Zahl ("+zahl+") wurde gefunden! Versuche: "+ (zaehler-1)+"\n");
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);

		JLabel lblEingabefeld = new JLabel("Eingabefeld:");
		lblEingabefeld.setBounds(10, 11, 75, 14);
		frame.getContentPane().add(lblEingabefeld);

		JLabel lblAktuelleZahl = new JLabel("Aktuelle Zahl:");
		lblAktuelleZahl.setBounds(10, 36, 84, 14);
		frame.getContentPane().add(lblAktuelleZahl);

		scrollPane = new JScrollPane(text);
		scrollPane.setBounds(10, 61, 414, 189);
		frame.getContentPane().add(scrollPane);
	}
}
