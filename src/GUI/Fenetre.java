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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import DEMINEUR.Case;
import DEMINEUR.TabDrapeaux;
import DEMINEUR.TabMines;
import DEMINEUR.TabProxi;

/**
 * 
 * @author BETAS A. & BREMER C.
 * 
 * Classe générale de la fenêtre de jeu
 *
 */
public class Fenetre extends JFrame implements MouseListener {

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
	JTextArea details = new JTextArea();

	JFrame choixNiveau = new JFrame();

	public static int NB_LIGNES_FACILE = 10;
	public static int NB_COLS_FACILE = 15;
	public static int NB_MINES_FACILE = 10;

	public static int NB_LIGNES_MOYEN = 15;
	public static int NB_COLS_MOYEN = 20;
	public static int NB_MINES_MOYEN = 40;

	public static int NB_LIGNES_DIF = 20;
	public static int NB_COLS_DIF = 30;
	public static int NB_MINES_DIF = 150;

	private static int nbLignes = NB_LIGNES_FACILE;
	private static int nbCols = NB_COLS_FACILE;
	private static int nbMines = NB_MINES_FACILE;
	private static int nbMinesRest = nbMines;

	private TabMines mines;
	private TabProxi nbre;
	private TabDrapeaux drapeaux;

	/**
	 * 
	 */
	public Fenetre(){

		this.init();
		this.setMenu();

		this.setGrille(nbLignes, nbCols);

		this.add(details, BorderLayout.NORTH);
		this.pack();
		this.setVisible(true);

	}

	/**
	 * Initialisation de la fenêtre
	 */
	public void init(){

		this.setTitle("Démineur en Java");
		this.setMinimumSize(new Dimension(400, 400));

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
				Niveau niveau = new Niveau();
				setNiveau(niveau.getReponse());
			}        
		});
		partie.add(nouvelle);
		partie.add(reseau);
		statistiques.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Score score = new Score();
			}
		});
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
	 * @param niveau
	 * 
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
			this.setNivPerso();

		}
		setGrille(nbLignes, nbCols);

	}
	
	public void setNivPerso(){
		/*this.setTitle("Niveau personnalisé");
		this.setMinimumSize(new Dimension(300, 200));
		this.setLocationRelativeTo(null);
		
		JPanel choixNiv = new JPanel();
		choixNiv.setLayout(new GridLayout(6,4));
		this.add(choixNiv);
		
		//int lignes = JTextField();

		this.setVisible(true);*/
	}

	/**
	 * Mise en forme de la grille de jeu
	 * 
	 * @param lignes
	 * @param cols
	 */
	public void setGrille(int lignes, int cols){

		this.remove(container);

		//Plateau.initMines(nbMines, nbLignes, nbCols);
		//Plateau.nbMinesCase(lignes, cols);

		container = new JPanel();
		btnCase = new JButton[nbLignes][nbCols];
		
		TabMines mines = new TabMines();
		TabProxi nbre = new TabProxi();
		
		mines.initMines(nbMines, nbLignes, nbCols);
		nbre.nbMinesCase(lignes, cols);

		container.setLayout(new GridLayout(lignes, cols));

		for (int i = 0; i < nbLignes ; i++) {
			for(int j = 0; j < nbCols ; j++) {
				btnCase[i][j] = new JButton();

				/*if(Plateau.mine[i][j]){
					btnCase[i][j].setText("M");
				}else{
					btnCase[i][j].setText(""+Plateau.nbre[i][j]);
				}*/
				btnCase[i][j].setEnabled(true);
				btnCase[i][j].setPreferredSize(new Dimension(45,45));
				btnCase[i][j].addMouseListener(this);
				container.add(btnCase[i][j]);

			}
		}


		details.setText("\n  Lignes : "+nbLignes +" \n  Colonnes : "+nbCols+" \n  Mines : "+nbMinesRest+" \n");
		this.add(container);
		this.pack();

	}
	
	/*
	 * Classes générées automatiquement via MouseListener
	 */
	public void mouseClicked(MouseEvent arg0) {
	}


	public void mouseEntered(MouseEvent arg0) {
	}

	public void mousePressed(MouseEvent e) {
		// recupère le clic (gauche ou droite)
		int clic = e.getButton();

		for(int i = 0; i < nbLignes; i++){
			for(int j = 0; j < nbCols; j++){
				if(e.getSource() == btnCase[i][j]){  // lie le clic à une case avec ses coordonnées
					if(clic == 1){ // si clic gauche
						if(mines.getMine(i,j)){  // regarde si c'est une mine
							btnCase[i][j].setText("M");
						}else{ // si non, indique les mines au alentours
							btnCase[i][j].setText(""+nbre.getNbre(i,j));
						}
						btnCase[i][j].setEnabled(false);  // désactive les cases cliquée mais ca ne désactive pas complètement :p
					}
					if(clic == 3) {  // si clic droit, ajout d'un drapeau
						if(!drapeaux.getDrapeau(i,j)){
							btnCase[i][j].setText("D"); //faut une solution car on peut cliquer plusieurs fois sur une meme case et meme si elle est disable :p
							drapeaux.setDrapeau(true, i, j);
							nbMinesRest --;
						}
						else {
							btnCase[i][j].setText("");
							drapeaux.setDrapeau(false, i, j);
							nbMinesRest ++;
						}
						details.setText("\n  Lignes : "+nbLignes +" \n  Colonnes : "+nbCols+" \n  Mines : "+nbMinesRest+" \n");
					}
				}
			}
		}
	}


	public void mouseExited(MouseEvent arg0) {
	}


	public void mouseReleased(MouseEvent arg0) {
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

