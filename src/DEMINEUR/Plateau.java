package DEMINEUR;

import java.util.Arrays;
import java.util.Random;

import javax.swing.JButton;

/**
 * Cette classe crée un plateau de jeu composé d'un nombre de lignes X et de
 * collones Y en fonction du niveau. Lors du premier clic (qui ne doit pas être
 * une mine), les mines sont placées et le c
 * 
 * @author Betas A. & Bremer C.
 * @see Case
 */

public class Plateau {

	private static int nbLignes;
	private static int nbCols;
	private int nbMines;
	private int[] tabMines;
	private int nbCases;
	private int nbDrapeau;
	private int nbCaseDecou;
	
	/**
	 * Tableau a deux dimensions qui constitue le plateau de jeu composé de
	 * cases [i][j] ou [y][x]
	 */
	public static Case[][] plateau;

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
		plateau = new Case[nbLignes][nbCols];
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
				plateau[i][j] = new Case(false); // ajout d'une case non
				// minée
				plateau[i][j].setCoordX(i); // Ajout de X
				plateau[i][j].setCoordY(j); // Ajout de Y
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
				plateau[ligne][col].setMinee(true);
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
					if (plateau[i][j].isMinee())
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
				if (!plateau[i][j].isMinee()) {
					nbMines = nbMinesCase(i, j);
					plateau[i][j].setMinesProxi(nbMines);
				}
			}
		}
	}

	/**
	 * Change le paramètre drapeau en true de la case en question
	 * 
	 * @param x
	 * @param y
	 */
	public void placerDrapeau(int x, int y) {
		plateau[x][y].setDrapeau(true);
		nbDrapeau++;
	}

	/**
	 * Enlève un drapeau d'une case passée en paramètre
	 * 
	 * @param x
	 * @param y
	 */
	public void enleverDrapeau(int x, int y) {
		plateau[x][y].setDrapeau(false);
		nbDrapeau--;
	}

	/**
	 * Découvre une case placée en paramètres, opération irréversible
	 * 
	 * @param x
	 * @param y
	 */
	public void decouvrirCase(int x, int y) {
		plateau[x][y].setDecouvert();
		nbCaseDecou++;
	}

	/**
	 * Découvre les cases autour d'une case passée en paramètre. Si pas minées,
	 * si pas découverte et si pas drapeau
	 * 
	 * @param x
	 * @param y
	 */
	public void decouvrirAutour(int x, int y) {
		if (plateau[x][y].getMinesProxi() == 0) {
			for (int i = -1; i <= 1; i++) {
				for (int j = -1; j <= 1; j++) {
					if ((x + i) >= 0 && (x + i) < nbLignes && (y + j) >= 0
							&& (y + j) < nbCols) {
						if (plateau[x + i][y + j].getMinesProxi() == 0
								&& !plateau[x + i][y + j].isDecouvert()
								&& !plateau[x + i][y + j].isMinee()
								&& !plateau[x + i][y + j].isDrapeau()) {
							decouvrirCase(x + i, y + j);
							decouvrirAutour(x + i, y + j);
						} else if (!plateau[x + i][y + j].isMinee()
								&& !plateau[x + i][y + j].isDecouvert()
								&& !plateau[x + i][y + j].isDrapeau()) {
							decouvrirCase(x + i, y + j);
						}
					}
				}
			}
		}
	}

	/**
	 * Methode qui set toute les cases à découvert, cette méthode sert lors
	 * d'une victoire ou d'une défaite.
	 */
	public void toutDecouvrir() {
		for (int i = 0; i < this.nbLignes; i++) {
			for (int j = 0; j < this.nbCols; j++) {
				plateau[i][j].setDecouvert();
			}
		}
	}

	public static int getNbLignes() { // Utilisé dans la GUI
		return nbLignes;
	}

	public void setNbLignes(int nbLignes) {
		this.nbLignes = nbLignes;
	}

	public static int getNbCols() { // Utilisé dans la GUI
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

	public int getNbDrapeau() {
		return nbDrapeau;
	}

	public void setNbDrapeau(int nbDrapeau) {
		this.nbDrapeau = nbDrapeau;
	}

	public int getNbCaseDecou() {
		return nbCaseDecou;
	}

	public void setNbCaseDecou(int nbCaseDecou) {
		this.nbCaseDecou = nbCaseDecou;
	}
	public Case[][] getPlateau() {
		return plateau;
	}

	public void setPlateau(Case[][] plateau) {
		this.plateau = plateau;
	}
	
	

	// Fin de la classe
}