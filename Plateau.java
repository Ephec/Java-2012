import java.util.Arrays;
import java.util.Random;

/**
 * Cette classe crée un plateau de jeu composé d'un nombre de lignes X et de
 * collones Y en fonction du niveau. Lors du premier clic (qui ne doit pas être
 * une mine), les mines sont placées et le c
 * 
 * @author Betas A. & Bremer C.
 * @see Case
 */

public class Plateau {

	private int nbLignes;
	private int nbCols;
	private int nbMines;
	private int[] tabMines;
	private int nbCases;
	/**
	 * Tableau a deux dimensions qui constitue le plateau de jeu composé de
	 * cases [i][j] ou [y][x]
	 */
	private Case[][] Plateau;

	// Coordonées des cases pour la création, pas besoin de protection il me
	// semble
	public int i;
	public int j;

	/**
	 * Création (définition des paramètres) pour le plateau de jeu : nombre de
	 * lignes, de collones et de mines. Ces paramètres sont transmis par la
	 * classe "Partie" qui définira le niveau de jeu. La gestion des erreurs se
	 * fera dans la classe "Partie"
	 * 
	 * @param nbLignes
	 * @param nbCols
	 * @param nbMines
	 */
	public Plateau(int nbLignes, int nbCols, int nbMines) {
		this.nbLignes = nbLignes;
		this.nbCols = nbCols;
		this.nbMines = nbMines;
		Plateau = new Case[nbLignes][nbCols];
	}

	/**
	 * Initiaisation des cases en fonction du nombre de lignes et de collones.
	 * Elle ne sont pas minées. Le placement des mines se fera après la première
	 * séléction d'une case étant donné qu'on ne peut pas commencer par une mine
	 */
	public void initCases() {

		nbCases = nbCols * nbLignes;

		for (int i = 0; i < nbLignes; i++) {
			for (int j = 0; j < nbCols; j++) {
				Plateau[i][j] = new Case(false); // ajout d'une case non
				// minée
				Plateau[i][j].setCoordX(j); // Ajout de X
				Plateau[i][j].setCoordY(i); // Ajout de Y
			}
		}
	}

	/**
	 * Création d'un tableau d'emplacements de mines. Tri ce ce tableau et
	 * placement des mines dans le plateau
	 * 
	 * @param x
	 * @param y
	 */
	public void initMines(int x, int y) {

		int val, ligne, col;

		// Pour créer le tableau contenant les emplacements des mines
		for (int i = 0; i < nbMines; i++) {
			Random r = new Random();
			val = r.nextInt(getNbCases());
			setTabMines(i, val);
		}

		// Tri du tableau d'emplacement des mines
		Arrays.sort(tabMines);

		// Maintenant on les places dans le plateau...
		for (int i = 0; i < nbMines; i++) {

			ligne = getTabMines()[i] / this.getNbCols();
			col = getTabMines()[i] % this.getNbLignes();
			if (col != x && ligne != y) {
				// Si case n'est pas la première case selectionnée
				Plateau[ligne][col].setMinee(true);
			}

			else {
				i--;
			}
			// Sinon, on revient sur la mine en cours pour la placer ailleurs
		}

	}

	/**
	 * Trouve le nombre de mines à proximité d'une case en parcourant une ligne
	 * au dessus et en dessous et une collonne à droite et a gauche.
	 * 
	 * @param x
	 * @param y
	 * @return nbProxi
	 */
	public int nbMinesCase(int x, int y) {

		int nbProxi = 0;

		for (int i = y - 1; i < y + 1; i++) {
			for (int j = x - 1; j < x + 1; j++) {
				if (i >= 0 && i < this.nbLignes && j >= 0 && j < this.nbCols) {
					if (Plateau[i][j].isMinee())
						nbProxi++;
				}
			}
		}

		return nbProxi;

	}

	/**
	 * Ajoute dans les paramètres des case le nombre de mines à proximité si la
	 * case en question n'est pas une mine.
	 */
	public void setNbMinesProxi() {

		int nbMines = 0;
		for (int i = 0; i < nbLignes; i++) {
			for (int j = 0; j < nbCols; j++) {
				// Si ce n'est pas une mine, trouve les mines à proximité
				if (!Plateau[i][j].isMinee()) {
					nbMines = nbMinesCase(i, j);
					Plateau[i][j].setMinesProxi(nbMines);
				}
			}
		}
	}

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

	public int[] getTabMines() {
		return tabMines;
	}

	public void setTabMines(int i, int nb) {
		this.tabMines[i] = nb;
	}

	public int getNbCases() {
		return nbCases;
	}

	public void setNbCases(int nbCases) {
		this.nbCases = nbCases;
	}

	// Fin de la classe
}