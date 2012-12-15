package GUI;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import DEMINEUR.Scores;
import DEMINEUR.TabDecouvertes;
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
	private JMenu infos = new JMenu("?");
	private JMenuItem nouvelle = new JMenuItem("Nouvelle partie");
	private JMenuItem reseau = new JMenuItem("Nouvelle partie en réseau");
	private JMenuItem statistiques = new JMenuItem("Statistiques");
	private JMenuItem fermer = new JMenuItem("Fermer");
	private JMenuItem apropos = new JMenuItem("A propos");
	private JMenuItem aide = new JMenuItem("Aide");

	JButton[][] btnCase = new JButton[nbLignes][nbCols];

	JPanel container = new JPanel();
	JTextArea details = new JTextArea();

	JFrame choixNiveau = new JFrame();

	public static int NB_LIGNES_FACILE = 10;
	public static int NB_COLS_FACILE = 15;
	public static int NB_MINES_FACILE = 3;

	public static int NB_LIGNES_MOYEN = 15;
	public static int NB_COLS_MOYEN = 20;
	public static int NB_MINES_MOYEN = 20;

	public static int NB_LIGNES_DIF = 20;
	public static int NB_COLS_DIF = 30;
	public static int NB_MINES_DIF = 50;

	private static int nbLignes = NB_LIGNES_FACILE;
	private static int nbCols = NB_COLS_FACILE;
	private static int nbMines = NB_MINES_FACILE;
	private static int nbMinesRest = nbMines;
	private static int nivActuel = 0;

	private TabMines mines;
	private TabProxi nbre;
	private TabDrapeaux drapeaux;
	private TabDecouvertes decouvertes;

	private Scores scores;
	private NiveauPerso niveauPerso;

	/**
	 * 
	 */
	public Fenetre(){

		this.init();
		this.setMenu();

		this.setGrille();

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
				nivActuel = niveau.getReponse();
				System.out.println(""+nivActuel);
				if (nivActuel>=0 && nivActuel<=2)
					setNiveau(nivActuel);
				if(nivActuel==3) {
					niveauPerso = new NiveauPerso();
					setNiveau(nivActuel);
				}
			}        
		});
		partie.add(nouvelle);
		partie.add(reseau);
		statistiques.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				ScoresVue scoresVue = new ScoresVue();
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
			this.nbMinesRest = nbMines;
			break;
		case 1:
			this.nbLignes = NB_LIGNES_MOYEN;
			this.nbCols = NB_COLS_MOYEN;
			this.nbMines = NB_MINES_MOYEN;
			this.nbMinesRest = nbMines;
			break;
		case 2:
			this.nbLignes = NB_LIGNES_DIF;
			this.nbCols = NB_COLS_DIF;
			this.nbMines = NB_MINES_DIF;
			this.nbMinesRest = nbMines;
			break;
		case 3:
			this.nbLignes = niveauPerso.getNbLignes();
			this.nbCols = niveauPerso.getNbCols();
			this.nbMines = niveauPerso.getNbMines();
			this.nbMinesRest = nbMines;
		
		}
		setGrille();

	}

	/**
	 * Mise en forme de la grille de jeu
	 * 
	 * @param lignes
	 * @param cols
	 */
	public void setGrille(){

		this.remove(container);

		container = new JPanel();
		btnCase = new JButton[nbLignes][nbCols];

		mines = new TabMines();
		nbre = new TabProxi();
		decouvertes = new TabDecouvertes();
		drapeaux = new TabDrapeaux();

		//mines.initMines(nbMines, nbLignes, nbCols);
		//nbre.nbMinesCase(lignes, cols);
		//decouvertes.initDecouvertes(nbLignes, nbCols);

		container.setLayout(new GridLayout(nbLignes, nbCols));

		for (int i = 0; i < nbLignes ; i++) {
			for(int j = 0; j < nbCols ; j++) {
				btnCase[i][j] = new JButton();
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

	public void verifGagne() {

		int compteur = 0;
		int nbNonDecouvertes = 0;

		for (int i = 0; i < nbLignes ; i++) {
			for(int j = 0; j < nbCols ; j++) {
				if(!decouvertes.getDecouverte(i, j)){
					nbNonDecouvertes++;
				}
			}
		}
		if(nbNonDecouvertes == nbMines){
			for (int i = 0; i < nbLignes ; i++) {
				for(int j = 0; j < nbCols ; j++) {
					if(mines.getMine(i, j) && !decouvertes.getDecouverte(i, j)){
						compteur++;
					}
				}
			}
		}

		if(compteur == nbMines){

			int reponse = - 1;
			String[] action = {"Recommencer", "Quitter"};

			String nom = JOptionPane.showInputDialog(null, "Félicitations, vous avez gagné ... \n\nQuel est votre nom ?", "Partie gagnée", JOptionPane.QUESTION_MESSAGE);
			scores = new Scores(nom, 0, nivActuel);
			scores.ecrireFichier();
			reponse = JOptionPane.showOptionDialog(null, "Que voulez-vous faire ?", "Partie gagnée", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, action, action[1]);

			if(reponse == 1){
				System.exit(0);
			}
			if(reponse == 0){
				Niveau niveau = new Niveau();
				setNiveau(niveau.getReponse());
			}
		}

	}

	public void partiePerdue() {

		int reponse = -1;

		String[] action = {"Recommencer", "Quitter"};
		while(reponse == -1){
			reponse = JOptionPane.showOptionDialog(null, "Vous avez malheureusement explosé sur un mine ...", "Partie perdue", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, action, action[1]);
		}

		if(reponse == 1){
			System.exit(0);
		}
		if(reponse == 0){
			Niveau niveau = new Niveau();
			setNiveau(niveau.getReponse());
		}
	}

	public void decouvrirCase(int x, int y){
		decouvertes.setDecouverte(true,x,y);
		btnCase[x][y].setBackground(new java.awt.Color(190,190,190));
		btnCase[x][y].setForeground(new java.awt.Color(0,0,0));
		btnCase[x][y].setEnabled(false);
		if(nbre.getNbre(x, y)!=0) btnCase[x][y].setText(""+nbre.getNbre(x,y));

	}

	public void decouvrirAutour(int x, int y){

		for(int i=-1;i<=1;i++){
			for(int j=-1;j<=1;j++){
				if((x+i)>= 0 && (x+i)<nbLignes && (y+j)>=0 && (y+j)<nbCols){
					if(
							nbre.getNbre(x+i, y+j) == 0 && !decouvertes.getDecouverte(x+i, y+j)
							)
					{
						if(!drapeaux.getDrapeau(x+i, y+j)) {
							decouvrirCase(x+i,y+j);
							decouvrirAutour(x+i,y+j);
						}
						//else decouvrirAutour(x+i,y+j);
					}

					else if(!decouvertes.getDecouverte(x+i, y+j)){
						//btnCase[x+i][y+j].setText(String.valueOf(nbre.getNbre(x+i, y+j)));
						if (!drapeaux.getDrapeau(x+i, y+j)) decouvrirCase(x+i,y+j);
					}
				}
			}
		}

	}

	public void mousePressed(MouseEvent e) {
		// recupère le clic (gauche ou droite)
		int clic = e.getButton();

		for(int i = 0; i < nbLignes; i++){
			for(int j = 0; j < nbCols; j++){
				if(e.getSource() == btnCase[i][j]){  // lie le clic à une case avec ses coordonnées
					if(clic == 1){ // si clic gauche
						if(!drapeaux.getDrapeau(i, j)){
							if(mines.getMine(i,j)){  // regarde si c'est une mine
								partiePerdue();
								break;
							}else{ // si non, indique les mines au alentours

								int nbProxi = nbre.getNbre(i,j);

								if(nbProxi == 0){
									btnCase[i][j].setText("");
									//decouvrirCase(i,j);
									decouvrirAutour(i,j);


								} else {
									decouvrirCase(i,j);
									//btnCase[i][j].setText(""+nbre.getNbre(i,j));
								}

								verifGagne();

							}
							//btnCase[i][j].setEnabled(false);  // désactive les cases cliquée mais ca ne désactive pas complètement :p
						}
					}
					if(clic == 3 && !decouvertes.getDecouverte(i,j)) {  // si clic droit, ajout d'un drapeau
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

	public void mouseClicked(MouseEvent e) {

	}


	public void mouseReleased(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

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

	public static void setNbLignes(int nbLignes) {
		Fenetre.nbLignes = nbLignes;
	}

	public static void setNbCols(int nbCols) {
		Fenetre.nbCols = nbCols;
	}

	public static void setNbMines(int nbMines) {
		Fenetre.nbMines = nbMines;
	}
	
	

}

