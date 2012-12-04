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

		// D'abord on choisi le niveau
		setNiveau();

		// Ensuite on cr�e le plateau et on initalise les cases sans mines pour
		// permette le premier clic sur une case non min�e
		plateau = new Plateau(nbLignes, nbCols, nbMines);
		plateau.initCases();

		// Ici il faudra mettre le premier clic et ensuite jouer
		//
		// M�thodes a ajouter
		//
		// - Gagner
		// - Perdre
		// - ...

	}

	
	/**
	 * Un switch qui en fonction du nombre entier determine le niveau. Ce niveau
	 * sera pass� en param�tre � la m�thode qui cr�er le plateau
	 */
	public void setNiveau(int niveau) {

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

	}
	
	public boolean gagner(int nbMines, int nbDrapeau){
		
		// Si le nombre de mines correspond au nombre de case - le nombre de case d�couverte
		if(plateau.getNbCaseDecou()==(plateau.getNbCases()-plateau.getNbCaseDecou())){
			plateau.toutDecouvrir();
			return true;
		}
		else return false;
		
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
