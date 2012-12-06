package DEMINEUR;

/**
 * Cette classe permet de générer les paramètres de la partie en tant que tel.
 * C'est-à-dire le niveau de la partie et le joueur qui y jouera.
 * 
 * @author Betas A. & Bremer C.
 * @see Plateau
 * 
 */
public class Partie {

	/**
	 * Constantes : Nombre de lignes, de collonnes et le nombre de mines en
	 * fonction du niveau (1, 2 ou 3)
	 */

	public static final int NIVEAU_FACILE = 1;
	public static final int NIVEAU_MOYEN = 2;
	public static final int NIVEAU_DIF = 3;
	public static final int NIVEAU_PERSO = 4;

	public static int NB_LIGNES_FACILE = 10;
	public static int NB_COLS_FACILE = 10;
	public static int NB_MINES_FACILE = 10;

	public static int NB_LIGNES_MOYEN = 15;
	public static int NB_COLS_MOYEN = 15;
	public static int NB_MINES_MOYEN = 40;

	public static int NB_LIGNES_DIF = 15;
	public static int NB_COLS_DIF = 30;
	public static int NB_MINES_DIF = 95;

	private static int nbLignes;
	private int nbCols;
	private int nbMines;

	private int level;

	private Plateau plateau;

	public Partie() {

		// D'abord on choisi le niveau, ici 1 est en exemple
		setNiveau(1);

		// Ensuite on crée le plateau et on initalise les cases sans mines pour
		// permette le premier clic sur une case non minée
		plateau = new Plateau(nbLignes, nbCols, nbMines);
		plateau.initCases();

		// Après l'initialisation des cases aura lieu le premier clic dans
		// l'interface graphique je suppose. Il faut qu'on place les mines après

	}

	/**
	 * Un switch qui en fonction du nombre entier determine le niveau. Ce niveau
	 * sera passé en paramètre à la méthode qui créer le plateau. De plus, cette
	 * méthode retournera le niveau de la partie à la classe mère, Démineur ce
	 * qui permettra de générer le niveau pour les scores
	 */
	// Dans l'interface graphique (GUI ^^ je sais), le niveau sera recu depuis
	// une liste et sera à mon avis passé en paramètre à la fonction set
	public int setNiveau(int niveau) {

		switch (niveau) {
		case NIVEAU_MOYEN:
			setNbLignes(NB_LIGNES_MOYEN);
			setNbLignes(NB_COLS_MOYEN);
			setNbLignes(NB_MINES_MOYEN);
			break;
		case NIVEAU_DIF:
			setNbLignes(NB_LIGNES_DIF);
			setNbLignes(NB_COLS_DIF);
			setNbLignes(NB_MINES_DIF);
			break;
		case NIVEAU_PERSO:
			// Créer méthode avec niveau perso
		default:
			setNbLignes(NB_LIGNES_FACILE);
			setNbLignes(NB_COLS_FACILE);
			setNbLignes(NB_MINES_FACILE);
			break;
		}

		this.level = niveau;
		return niveau;

	}

	/**
	 * Défini ce qu'une est partie gagnée, c'est une partie dans laquelle les
	 * cases restantes correspondent au nombre de mines. Les drapeaux ne
	 * comptent pas, ce sont des indications pour le joueur. A appeller après
	 * avoir découvert les cases autour de la case séléctionnée.
	 * 
	 * @param nbMines
	 * @param nbDrapeau
	 * @return
	 */
	public boolean gagner(int nbMines, int nbDrapeau) {

		// Si le nombre de mines correspond au nombre de case - le nombre de
		// case découverte
		if (plateau.getNbCaseDecou() == (plateau.getNbCases() - plateau
				.getNbCaseDecou())) {
			plateau.toutDecouvrir();
			return true;
		} else
			return false;

	}

	/**
	 * Retourne vrai si la partie est perdue, autrement dit si la case est minée
	 * et a été découverte. Cette méthode sera appellée juste après la
	 * découverture de la case et avant de découvrir autour
	 * 
	 * @param isMinee
	 * @return perdu
	 */
	public boolean perdu(boolean isMinee) {

		if (isMinee)
			return true;
		else
			return false;
	}

	// Getters et Setters pour le nb de lignes, de collonnes et de mines

	public int getNbLignes() {
		return nbLignes;
	}

	public void setNbLignes(int nbLignes) {
		this.nbLignes = nbLignes;
	}

	public int getNbCols() {
		return nbCols;
	}

	public void setNbCols(int nbCols) {
		this.nbCols = nbCols;
	}

	public int getNbMines() {
		return nbMines;
	}

	public void setNbMines(int nbMines) {
		this.nbMines = nbMines;
	}

}
