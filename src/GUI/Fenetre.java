package GUI;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
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

import DEMINEUR.Plateau;

public class Fenetre extends JFrame{

	private JMenuBar menuBar = new JMenuBar();
	private JMenu partie = new JMenu("Partie");
	private JMenu niveaux = new JMenu("Niveaux");
	private JMenu options = new JMenu("Options");
	private JMenu infos = new JMenu("?");
	private JMenuItem nouvelle = new JMenuItem("Nouvelle partie");
	private JMenuItem reseau = new JMenuItem("Nouvelle partie en réseau");
	private JMenuItem statistiques = new JMenuItem("Statistiques");
	private JMenuItem fermer = new JMenuItem("Fermer");
	private JMenuItem facile = new JMenuItem("Facile");
	private JMenuItem intermediaire = new JMenuItem("Intermédiaire");
	private JMenuItem difficile = new JMenuItem("Difficile");
	private JMenuItem personnalise = new JMenuItem("Personnalisé");
	private JMenuItem chrono = new JMenuItem("Afficher le chrono");
	private JMenuItem apropos = new JMenuItem("A propos");
	private JMenuItem aide = new JMenuItem("Aide");
	
	private JPanel container = new JPanel();

	/*
	 * Constructeur de l'interface graphique qui définit une taille, ajoute le menu et le container 
	 */
	public Fenetre(){
		
		this.init();
		this.setMenu();
		this.setGrille();
		this.setVisible(true);

	}
	
	/*
	 * Initialisation de la fenêtre par défaut
	 */
	public void init(){
		
		this.setTitle("Démineur en Java");
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
		partie.add(nouvelle);
		partie.add(reseau);
		partie.add(statistiques);
		partie.addSeparator();

		fermer.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}        
		});
		partie.add(fermer);

		// Menu "Niveaux"
		ButtonGroup bg = new ButtonGroup();
		bg.add(facile);
		bg.add(intermediaire);
		bg.add(difficile);
		facile.setSelected(true);

		niveaux.add(facile);
		niveaux.add(intermediaire);
		niveaux.add(difficile);
		niveaux.addSeparator();
		niveaux.add(personnalise);

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
				JOptionPane.showMessageDialog(null, "Démineur\nVersion : décembre 2012\nBy Antoine Betas & Cédric Bremer");
			}        
		});
		infos.add(apropos);

		// Barre générale qui contient les différents menu 
		partie.setMnemonic('P');
		menuBar.add(partie);
		niveaux.setMnemonic('N');
		menuBar.add(niveaux);
		options.setMnemonic('O');
		menuBar.add(options);
		infos.setMnemonic('?');
		menuBar.add(infos);

		this.setJMenuBar(menuBar);
	}

	public void setGrille() {
		GridLayout grille = new GridLayout(20, 20);
		container.setLayout(grille);
		
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				JButton button = new JButton();
				container.add(button);
			}
		}
	}
}
