package DEMINEUR;

/**
 * Cette classe permet de g�n�rer les param�tres de la partie en tant que tel.
 * C'est-�-dire le niveau de la partie et le joueur qui y jouera.
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

		// Ensuite on cr�e le plateau et on initalise les cases sans mines pour
		// permette le premier clic sur une case non min�e
		plateau = new Plateau(nbLignes, nbCols, nbMines);
		plateau.initCases();

		// Apr�s l'initialisation des cases aura lieu le premier clic dans
		// l'interface graphique je suppose. Il faut qu'on place les mines apr�s

	}

	/**
	 * Un switch qui en fonction du nombre entier determine le niveau. Ce niveau
	 * sera pass� en param�tre � la m�thode qui cr�er le plateau. De plus, cette
	 * m�thode retournera le niveau de la partie � la classe m�re, D�mineur ce
	 * qui permettra de g�n�rer le niveau pour les scores
	 */
	// Dans l'interface graphique (GUI ^^ je sais), le niveau sera recu depuis
	// une liste et sera � mon avis pass� en param�tre � la fonction set
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
			// Cr�er m�thode avec niveau perso
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
	 * D�fini ce qu'une est partie gagn�e, c'est une partie dans laquelle les
	 * cases restantes correspondent au nombre de mines. Les drapeaux ne
	 * comptent pas, ce sont des indications pour le joueur. A appeller apr�s
	 * avoir d�couvert les cases autour de la case s�l�ctionn�e.
	 * 
	 * @param nbMines
	 * @param nbDrapeau
	 * @return
	 */
	public boolean gagner(int nbMines, int nbDrapeau) {

		// Si le nombre de mines correspond au nombre de case - le nombre de
		// case d�couverte
		if (plateau.getNbCaseDecou() == (plateau.getNbCases() - plateau
				.getNbCaseDecou())) {
			plateau.toutDecouvrir();
			return true;
		} else
			return false;

	}

	/**
	 * Retourne vrai si la partie est perdue, autrement dit si la case est min�e
	 * et a �t� d�couverte. Cette m�thode sera appell�e juste apr�s la
	 * d�couverture de la case et avant de d�couvrir autour
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
