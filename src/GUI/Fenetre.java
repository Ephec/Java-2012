package GUI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import DEMINEUR.Case;
import DEMINEUR.Partie;
import DEMINEUR.Plateau;

/*
 * GUI générale de notre démineur.
 * (il ne faut qu'une seule classe)
 */
public class Fenetre extends JFrame {
	
	private JMenuBar menuBar = new JMenuBar();
	private JMenu partie = new JMenu("Partie");
	private JMenu options = new JMenu("Options");
	private JMenu infos = new JMenu("?");
	private JMenuItem nouvelle = new JMenuItem("Nouvelle partie");
	private JMenuItem reseau = new JMenuItem("Nouvelle partie en réseau");
	private JMenuItem statistiques = new JMenuItem("Statistiques");
	private JMenuItem fermer = new JMenuItem("Fermer");
	private JMenuItem chrono = new JMenuItem("Afficher le chrono");
	private JMenuItem apropos = new JMenuItem("A propos");
	private JMenuItem aide = new JMenuItem("Aide");
	
	JButton[][] btnCase = new JButton[nbLignes][nbCols];

	JPanel container = new JPanel();
	JTextArea details = new JTextArea("\n  Lignes : "+nbLignes +" \n  Colonnes : "+nbCols+" \n  Mines : "+nbMines+" \n  Plus jamais de Java pour moi ... :D \n");
	
	JFrame choixNiveau = new JFrame();

	public static int NB_LIGNES_FACILE = 10;
	public static int NB_COLS_FACILE = 10;
	public static int NB_MINES_FACILE = 10;

	public static int NB_LIGNES_MOYEN = 15;
	public static int NB_COLS_MOYEN = 15;
	public static int NB_MINES_MOYEN = 40;

	public static int NB_LIGNES_DIF = 20;
	public static int NB_COLS_DIF = 20;
	public static int NB_MINES_DIF = 95;
	
	private static int nbLignes = NB_LIGNES_FACILE;
	private static int nbCols = NB_COLS_FACILE;
	private static int nbMines = NB_MINES_FACILE;
	
	
	/*
	 * Constructeur de l'interface graphique qui définit une taille, ajoute le menu et le container 
	 */
	public Fenetre(){

		this.init();
		this.setMenu();
		
		this.setGrille(nbLignes, nbCols);
		
		this.add(details, BorderLayout.NORTH);
		this.pack();
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
		details.setEditable(false);
		
	}

	/*
	 * barre de menu
	 */
	public void setMenu() {

		// Menu "Partie"
		nouvelle.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				choixNiveau();
			}        
		});
		partie.add(nouvelle);
		partie.add(reseau);
		partie.add(statistiques);
		partie.addSeparator();

		fermer.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
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
				JOptionPane.showMessageDialog(null, "Démineur\nVersion : décembre 2012\nBy Antoine Betas & Cédric Bremer");
			}        
		});
		infos.add(apropos);

		// Barre générale qui contient les différents menu 
		partie.setMnemonic('P');
		menuBar.add(partie);
		options.setMnemonic('O');
		menuBar.add(options);
		infos.setMnemonic('?');
		menuBar.add(infos);

		this.setJMenuBar(menuBar);
	}
	
	/**
	 * Bon je commenterai tout ca plus tard :p
	 */
	public void choixNiveau(){

		JOptionPane choix = new JOptionPane();
		String[] niv = {"Facile", "Moyen", "Difficile", "Personnalisé"};
		int reponse = choix.showOptionDialog(null, "Veuillez choisir votre niveau pour cette partie.", "Sélection Niveau", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, niv, niv[2]);
	
		setNiveau(reponse);

	}
	
	/**
	 * Un switch qui en fonction du nombre entier determine le niveau. Ce niveau
	 * sera passé en paramètre à la méthode qui créer le plateau. De plus, cette
	 * méthode retournera le niveau de la partie à la classe mère, Démineur ce
	 * qui permettra de générer le niveau pour les scores
	 */

	public void setNiveau(int niveau){
		
		switch (niveau) {
		case 0:
			this.nbLignes = NB_LIGNES_FACILE;
			this.nbCols = NB_COLS_FACILE;
			this.nbMines = NB_MINES_FACILE;
			break;
		case 1:
			this.nbLignes = NB_LIGNES_MOYEN;
			this.nbCols = NB_COLS_MOYEN;
			this.nbMines = NB_MINES_MOYEN;
			break;
		case 2:
			this.nbLignes = NB_LIGNES_DIF;
			this.nbCols = NB_COLS_DIF;
			this.nbMines = NB_MINES_DIF;
			break;
		case 3:
			// Créer méthode avec niveau perso
		
		}
		setGrille(nbLignes, nbCols);

	}

	/*
	 * Mise en forme de la grille de jeu
	 */
	public void setGrille(int lignes, int cols){
		
		this.remove(container);
		
		Plateau.initMines(nbMines, nbLignes, nbCols);
		container = new JPanel();
		btnCase = new JButton[nbLignes][nbCols];
		container.setLayout(new GridLayout(lignes, cols));
		
		for (int i = 0; i < nbLignes ; i++) {
			for(int j = 0; j < nbCols ; j++) {
			btnCase[i][j] = new JButton();
			btnCase[i][j].setText(" "+Plateau.mine[i][j]);
			btnCase[i][j].setEnabled(true);
			container.add(btnCase[i][j]);
			
			}
		}
		
		
		details.setText("\n  Lignes : "+nbLignes +" \n  Colonnes : "+nbCols+" \n  Mines : "+nbMines+" \n  Plus jamais de Java pour moi ... :D \n");
		this.add(container);
		this.pack();
		
	}

	// Getters and setters
	
	public static int getNbLignes() {
		return nbLignes;
	}
	
	public static int getNbCols() {
		return nbCols;
	}
	
	public static int getNbMines() {
		return nbMines;
	}
}

