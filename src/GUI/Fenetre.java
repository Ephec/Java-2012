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

import DEMINEUR.Partie;
import DEMINEUR.Plateau;

/*
 * GUI générale de notre démineur.
 * (il ne faut qu'une seule classe)
 */
public class Fenetre extends JFrame{

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

	private JPanel container = new JPanel();
	JFrame choixNiveau = new JFrame();
	
	/**
	 * Constantes : Nombre de lignes, de collonnes et le nombre de mines en
	 * fonction du niveau (1, 2 ou 3)
	 */

	public static final int NIVEAU_FACILE = 0;
	public static final int NIVEAU_MOYEN = 1;
	public static final int NIVEAU_DIF = 2;
	public static final int NIVEAU_PERSO = 3;

	public static int NB_LIGNES_FACILE = 10;
	public static int NB_COLS_FACILE = 10;
	public static int NB_MINES_FACILE = 10;

	public static int NB_LIGNES_MOYEN = 15;
	public static int NB_COLS_MOYEN = 15;
	public static int NB_MINES_MOYEN = 40;

	public static int NB_LIGNES_DIF = 15;
	public static int NB_COLS_DIF = 30;
	public static int NB_MINES_DIF = 95;

	/*
	 * Constructeur de l'interface graphique qui définit une taille, ajoute le menu et le container 
	 */
	public Fenetre(){

		this.init();
		this.setMenu();
		this.setGrille(1);
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

	public void choixNiveau(){

		JOptionPane choix = new JOptionPane();
		String[] niv = {"Facile", "Moyen", "Difficile", "Personnalisé"};
		int reponse = choix.showOptionDialog(null, "Veuillez choisir votre niveau pour cette partie.", "Sélection Niveau", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, niv, niv[2]);
		System.out.println(reponse); //debug


	}

	/*
	 * Mise en forme de la grille de jeu
	 */
	public void setGrille( int niv ){
		
		setMinimumSize(new Dimension(500, 500));
		setLayout(new GridLayout(10, 10));

		for (int i = 0; i < 9 ; i++) {
			for (int j = 0; j < 10 ; j++) {
				JButton casedem = new JButton();
				add(casedem);

			}
		}

	}
}
