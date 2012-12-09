package GUI;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import DEMINEUR.Partie;
import DEMINEUR.Plateau;

/*
 * GUI g�n�rale de notre d�mineur.
 * (il ne faut qu'une seule classe)
 */
public class Fenetre extends JFrame{

	private JMenuBar menuBar = new JMenuBar();
	private JMenu partie = new JMenu("Partie");
	private JMenu options = new JMenu("Options");
	private JMenu infos = new JMenu("?");
	private JMenuItem nouvelle = new JMenuItem("Nouvelle partie");
	private JMenuItem reseau = new JMenuItem("Nouvelle partie en r�seau");
	private JMenuItem statistiques = new JMenuItem("Statistiques");
	private JMenuItem fermer = new JMenuItem("Fermer");
	private JMenuItem chrono = new JMenuItem("Afficher le chrono");
	private JMenuItem apropos = new JMenuItem("A propos");
	private JMenuItem aide = new JMenuItem("Aide");

	private JPanel container = new JPanel();

	/*
	 * Constructeur de l'interface graphique qui d�finit une taille, ajoute le menu et le container 
	 */
	public Fenetre(){

		this.init();
		this.setMenu();
		this.setGrille();
		this.setVisible(true);

	}

	/*
	 * Initialisation de la fen�tre par d�faut
	 */
	public void init(){

		this.setTitle("D�mineur en Java");
		this.setMinimumSize(new Dimension(800, 800));
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().add(container, BorderLayout.CENTER);
		container.setBackground(new java.awt.Color(255, 255, 0));
	}

	/*
	 * barre de menu
	 */
	public void setMenu() {

		// Menu "Partie"
		nouvelle.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}        
		});
		partie.add(nouvelle);
		partie.add(reseau);
		partie.add(statistiques);
		partie.addSeparator();

		fermer.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				choixNiveau(); 
			}
		});
		partie.add(fermer);

		// Menu "Options"
		options.add(chrono);

		// Menu "?"
		aide.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Cliquez sur les cases sans toucher les mines ...");
			}        
		});
		infos.add(aide);

		apropos.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "D�mineur\nVersion : d�cembre 2012\nBy Antoine Betas & C�dric Bremer");
			}        
		});
		infos.add(apropos);

		// Barre g�n�rale qui contient les diff�rents menu 
		partie.setMnemonic('P');
		menuBar.add(partie);
		options.setMnemonic('O');
		menuBar.add(options);
		infos.setMnemonic('?');
		menuBar.add(infos);

		this.setJMenuBar(menuBar);
	}

	public void choixNiveau(){
		
	}
	/*
	 * Mise en forme de la grille de jeu
	 */
	public void setGrille() {
		GridLayout grille = new GridLayout(20 /*Plateau.getNbLignes()*/, 20 /*Plateau.getNbCols()*/);
		container.setLayout(grille);

		for (int i = 0; i < 20 /*Plateau.getNbLignes()*/; i++) {
			for (int j = 0; j < 20 /*Plateau.getNbCols()*/; j++) {
				// ??? 
			}
		}
	}

}
