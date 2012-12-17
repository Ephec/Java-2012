package GUI;

import javax.swing.BorderFactory;
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
import javax.swing.border.LineBorder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.InetAddress;

import DEMINEUR.Chrono;
import DEMINEUR.Scores;
import DEMINEUR.TabDecouvertes;
import DEMINEUR.TabDrapeaux;
import DEMINEUR.TabMines;
import DEMINEUR.TabProxi;
import RESEAU.Client;
import RESEAU.Serveur;

/**
 * 
 * @author BETAS A. & BREMER C.
 * 
 *         Classe g�n�rale de la fen�tre de jeu
 * 
 */
public class Fenetre extends JFrame implements MouseListener {

	private JMenuBar menuBar = new JMenuBar();
	private JMenu partie = new JMenu("Partie");
	private JMenu infos = new JMenu("?");
	private JMenuItem nouvelle = new JMenuItem("Nouvelle partie");
	private JMenuItem reseau = new JMenuItem("Nouvelle partie en r�seau");
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
	public static int NB_MINES_FACILE = 13;

	public static int NB_LIGNES_MOYEN = 15;
	public static int NB_COLS_MOYEN = 20;
	public static int NB_MINES_MOYEN = 50;

	public static int NB_LIGNES_DIF = 20;
	public static int NB_COLS_DIF = 30;
	public static int NB_MINES_DIF = 120;

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
	private boolean premierClic;
	static Chrono chrono = new Chrono();

	/**
	 * Constructeur de la fen�tre appel� dans la class Main
	 */
	public Fenetre() {

		this.init();
		this.setMenu();

		this.setGrille();

		this.add(details, BorderLayout.NORTH);
		this.pack();
		this.setVisible(true);
	}

	/**
	 * Initialisation de la fen�tre
	 */
	public void init() {

		this.setTitle("D�mineur en Java");
		this.setMinimumSize(new Dimension(400, 400));

		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		details.setEditable(false);

	}

	/**
	 * G�n�re la barre des menus et ses actions
	 */
	public void setMenu() {

		// Menu "Partie"
		nouvelle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Niveau niveau = new Niveau();
				nivActuel = niveau.getReponse();
				setNiveau(nivActuel);
			}
		});
		reseau.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Serveur serveur;
				Thread t;

				try {
					t = new Thread(serveur = new Serveur(2012));
					t.start();
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				// Client client = new Client(TabMines.mine);

			}
		});
		partie.add(nouvelle);
		partie.add(reseau);
		statistiques.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ScoresVue scoresVue = new ScoresVue();
			}
		});
		partie.add(statistiques);
		partie.addSeparator();

		fermer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		partie.add(fermer);

		// Menu "?"
		aide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,"Cliquez sur les cases sans toucher les mines ...");
			}
		});
		infos.add(aide);

		apropos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,"D�mineur\nVersion : d�cembre 2012\nBy Antoine Betas & C�dric Bremer");
			}
		});
		infos.add(apropos);

		// Barre g�n�rale qui contient les diff�rents menu
		partie.setMnemonic('P');
		menuBar.add(partie);
		infos.setMnemonic('?');
		menuBar.add(infos);

		this.setJMenuBar(menuBar);
	}

	/**
	 * @param niveau
	 * 
	 * Un switch qui en fonction du nombre entier determine le
	 * niveau. Ce niveau sera pass� en param�tre � la m�thode qui
	 * cr�er le plateau. De plus, cette m�thode retournera le niveau
	 * de la partie � la classe m�re, D�mineur ce qui permettra de
	 * g�n�rer le niveau pour les scores
	 */
	public void setNiveau(int niveau) {

		// On set le niveau, la prochaine �tape est donc le premier clic
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
			// System.out.println(""+nbLignes);
			niveauPerso = new NiveauPerso();
			dispose();
			// System.out.println(""+nbLignes);
		}
		setGrille();

	}

	/**
	 * Mise en forme de la grille de jeu
	 * 
	 * @param lignes
	 * @param cols
	 */
	public void setGrille() {

		this.remove(container);
		premierClic = true;

		container = new JPanel();
		btnCase = new JButton[nbLignes][nbCols];

		mines = new TabMines();
		nbre = new TabProxi();
		decouvertes = new TabDecouvertes();
		drapeaux = new TabDrapeaux();

		container.setLayout(new GridLayout(nbLignes, nbCols));

		for (int i = 0; i < nbLignes; i++) {
			for (int j = 0; j < nbCols; j++) {

				btnCase[i][j] = new JButton();
				btnCase[i][j].setEnabled(true);
				btnCase[i][j].setPreferredSize(new Dimension(30, 30));
				btnCase[i][j].addMouseListener(this);
				container.add(btnCase[i][j]);

			}
		}

		details.setText("\n  Lignes : " + nbLignes + " \n  Colonnes : "
				+ nbCols + " \n  Mines : " + nbMinesRest + " \n");
		this.add(container);
		this.setLocationRelativeTo(null);
		this.pack();

	}

	/**
	 * V�rifie si une partie est gagn�e si le nombre de cases non d�couvertes
	 * correspond au nombre mines restantes
	 */
	public void verifGagne() {

		int compteur = 0;
		int nbNonDecouvertes = 0;

		for (int i = 0; i < nbLignes; i++) {
			for (int j = 0; j < nbCols; j++) {
				if (!decouvertes.getDecouverte(i, j)) {
					nbNonDecouvertes++;
				}
			}
		}

		if (nbNonDecouvertes == nbMines) {
			for (int i = 0; i < nbLignes; i++) {
				for (int j = 0; j < nbCols; j++) {
					if (mines.getMine(i, j) && !decouvertes.getDecouverte(i, j)) {
						compteur++;
					}
				}
			}
		}

		if (compteur == nbMines) {

			int reponse = -1;
			chrono.stop();
			String[] action = { "Recommencer", "Quitter" };

			String nom = (String) JOptionPane.showInputDialog(null,
					"F�licitations, vous avez gagn� en " + chrono.resultat()
					+ " secondes... \n\nQuel est votre nom ?",
					"Partie gagn�e", JOptionPane.QUESTION_MESSAGE, null, null,
					getComputerFullName());

			if (nom != null) {
				// System.out.println(""+nivActuel);
				scores = new Scores(nom, nivActuel, chrono.resultat());
				scores.ecrireFichier();
			}

			reponse = JOptionPane.showOptionDialog(null,
					"Que voulez-vous faire ?", "Partie gagn�e",
					JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, action, action[1]);

			if (reponse == 1) {
				System.exit(0);
			}
			if (reponse == 0) {
				Niveau niveau = new Niveau();
				setNiveau(niveau.getReponse());
			}
		}

	}

	/**
	 * Fonction permettant de retourner le nom de la machine
	 * 
	 * @return String
	 */
	public static String getComputerFullName() {
		String hostName = null;
		try {
			final InetAddress addr = InetAddress.getLocalHost();
			hostName = new String(addr.getHostName());
		} catch (final Exception e) {
		}
		return hostName;
	}

	/**
	 * Partie perdu si clic sur une mine, gen�re une fen�tre pour recommencer
	 * une partie ou pour quitter le jeu
	 */
	public void partiePerdue() {

		int reponse = -1;
		chrono.stop();

		String[] action = { "Recommencer", "Quitter" };
		while (reponse == -1) {
			decouvrirMines();
			reponse = JOptionPane.showOptionDialog(null,
					"Vous avez malheureusement explos� sur un mine ...",
					"Partie perdue", JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, action, action[1]);
		}

		if (reponse == 1) {
			System.exit(0);
		}
		if (reponse == 0) {
			Niveau niveau = new Niveau();
			setNiveau(niveau.getReponse());
		}
	}

	/**
	 * Permet de d�couvrir une case en jouant sur le Enable du JButton et sur
	 * ses couleurs. Affiche �galement le nombre de mines � proximit�
	 * 
	 * @param x
	 * @param y
	 */
	public void decouvrirCase(int x, int y) {

		decouvertes.setDecouverte(true, x, y);
		btnCase[x][y].setBackground(new java.awt.Color(190, 190, 190));
		btnCase[x][y].setForeground(new java.awt.Color(0, 0, 0));
		btnCase[x][y].setEnabled(false);
		btnCase[x][y].setFont(new java.awt.Font("Arial", 1, 11));
		btnCase[x][y].setBorder(BorderFactory.createCompoundBorder(
				new LineBorder(new java.awt.Color(150, 150, 150), 1, false),
				null));
		if (nbre.getNbre(x, y) != 0)
			btnCase[x][y].setText("" + nbre.getNbre(x, y));

	}

	/**
	 * Decouvre les cases autour d'une case cliqu�e si cette derni�re ne
	 * contient pas de mine � proximit�
	 * 
	 * @param x
	 * @param y
	 */
	public void decouvrirAutour(int x, int y) {

		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if ((x + i) >= 0 && (x + i) < nbLignes && (y + j) >= 0
						&& (y + j) < nbCols) {
					if (nbre.getNbre(x + i, y + j) == 0
							&& !decouvertes.getDecouverte(x + i, y + j)) {
						if (!drapeaux.getDrapeau(x + i, y + j)) {
							decouvrirCase(x + i, y + j);
							decouvrirAutour(x + i, y + j);
						}
						
					}

					else if (!decouvertes.getDecouverte(x + i, y + j)) {
						
						if (!drapeaux.getDrapeau(x + i, y + j))
							decouvrirCase(x + i, y + j);
					}
				}
			}
		}

	}

	/**
	 * D�couvre toutes les mines d'un plateau en cas de d�faite
	 */
	public void decouvrirMines() {
		for (int i = 0; i < nbLignes; i++) {
			for (int j = 0; j < nbCols; j++) {
				if (mines.getMine(i, j)) {
					btnCase[i][j].setEnabled(false);
					if (drapeaux.getDrapeau(i, j))
						btnCase[i][j].setBackground(new java.awt.Color(0, 255,
								0));
					else
						btnCase[i][j].setBackground(new java.awt.Color(255, 0,
								0));
					btnCase[i][j].setText("M");
					btnCase[i][j].setFont(new java.awt.Font("Arial", 1, 11));
					btnCase[i][j].setBorder(BorderFactory.createCompoundBorder(
							new LineBorder(new java.awt.Color(150, 150, 150),
									1, false), null));
				}
			}
		}
	}

	/**
	 * MouseListener qui g�re les clics et gen�re l'action en fonction du clic
	 */
	public void mousePressed(MouseEvent e) {
		// recup�re le clic (gauche ou droite)
		int clic = e.getButton();

		if (premierClic) {
			chrono.reset();
			chrono.start();
			premierClic = false;
		}

		for (int i = 0; i < nbLignes; i++) {
			for (int j = 0; j < nbCols; j++) {
				if (e.getSource() == btnCase[i][j]) { // lie le clic � une case
					// avec ses coordonn�es
					if (clic == 1) { // clic gauche
						if (!drapeaux.getDrapeau(i, j)) {
							if (mines.getMine(i, j)) { // regarde si c'est une
								// mine
								partiePerdue();
								break;
							} else { // indique les mines au alentours

								int nbProxi = nbre.getNbre(i, j);

								if (nbProxi == 0) {
									btnCase[i][j].setText("");
									// decouvrirCase(i,j);
									decouvrirAutour(i, j);

								} else {
									decouvrirCase(i, j);
	
								}

								verifGagne();

							}
							
						}
					}
					if (clic == 3 && !decouvertes.getDecouverte(i, j)) {
						if (!drapeaux.getDrapeau(i, j)) { // ajout drapeau
							btnCase[i][j].setText("D");
							btnCase[i][j].setFont(new java.awt.Font("Arial", 1,
									11));
							btnCase[i][j].setForeground(new java.awt.Color(255,
									0, 0));
							btnCase[i][j].setBorder(BorderFactory
									.createCompoundBorder(new LineBorder(
											new java.awt.Color(150, 150, 150),
											1, false), null));
							drapeaux.setDrapeau(true, i, j);
							nbMinesRest--;
						} else {
							btnCase[i][j].setText("");
							drapeaux.setDrapeau(false, i, j);
							nbMinesRest++;
						}
						details.setText("\n  Lignes : " + nbLignes
								+ " \n  Colonnes : " + nbCols + " \n  Mines : "
								+ nbMinesRest + "\n");
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
		Fenetre.nbMinesRest = nbMines;
	}

}
