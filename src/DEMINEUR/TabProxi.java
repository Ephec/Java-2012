package DEMINEUR;

import DEMINEUR.TabMines;
import GUI.Fenetre;

/**
 * Classe de gestion des mines à proximité des cases
 * 
 * @author BETAS A. & BREMER C.
 */
public class TabProxi {

	/*
	 * Tableau 2D qui contient le nbre de mines adjacentes à la case
	 */
	private static int[][] nbre = new int[Fenetre.getNbLignes()][Fenetre
			.getNbCols()];

	/**
	 * Constructeur du tableau de mines à proximité
	 */
	public TabProxi() {

		nbMinesCase(Fenetre.getNbLignes(), Fenetre.getNbCols());

	}

	/**
	 * Trouve le nombre de mines à proximité d'une case en parcourant une ligne
	 * au dessus et en dessous et une collonne à droite et a gauche.
	 * 
	 * @param x
	 * @param y
	 */
	public static void nbMinesCase(int lignes, int cols) {

		nbre = new int[lignes][cols];
		// Parcourt toutes les cases et compte le nbre de mines adjacentes en
		// dehors des bords
		for (int x = 0; x < lignes; x++) {
			for (int y = 0; y < cols; y++) {

				int nbProxi = 0;

				// si la case X+1 existe et qu'elle contient une mine, nbProxi
				// augmente
				if ((x + 1) < lignes) {
					if (TabMines.getMine(x + 1, y)) {
						nbProxi++;
					}
				}
				if ((x + 1) < lignes && (y - 1) >= 0) {
					if (TabMines.getMine(x + 1, y - 1)) {
						nbProxi++;
					}
				}
				if ((x + 1) < lignes && (y + 1) < cols) {
					if (TabMines.getMine(x + 1, y + 1)) {
						nbProxi++;
					}
				}
				if ((x - 1) >= 0) {
					if (TabMines.getMine(x - 1, y)) {
						nbProxi++;
					}
				}
				if ((x - 1) >= 0 && (y + 1) < cols) {
					if (TabMines.getMine(x - 1, y + 1)) {
						nbProxi++;
					}
				}
				if ((x - 1) >= 0 && (y - 1) >= 0) {
					if (TabMines.getMine(x - 1, y - 1)) {
						nbProxi++;
					}
				}
				if ((y + 1) < cols) {
					if (TabMines.getMine(x, y + 1)) {
						nbProxi++;
					}
				}
				if ((y - 1) >= 0) {
					if (TabMines.getMine(x, y - 1)) {
						nbProxi++;
					}
				}

				nbre[x][y] = nbProxi;

			}
		}

	}

	/**
	 * Permet de savoir si une case se trouve au bord du plateau. Renvoi un
	 * tableau avec [haut,droite,bas,gauche] et des booléens si c'es au bord ou
	 * non
	 * 
	 * @param x
	 * @param y
	 * @return boolean[]
	 */
	public static int[] estAuBord(int x, int y) {
		int[] tabBords = { 0, 0, 0, 0 };

		if (x == 0) {
			if (y == 0) {
				tabBords[0] = 1;
				tabBords[3] = 1;
			}
			if (y == Fenetre.getNbCols() - 1) {
				tabBords[0] = 1;
				tabBords[1] = 1;
			} else
				tabBords[0] = 1;
		}
		if (x == Fenetre.getNbLignes() - 1) {
			if (y == 0) {
				tabBords[2] = 1;
				tabBords[3] = 1;
			}
			if (y == Fenetre.getNbCols() - 1) {
				tabBords[2] = 1;
				tabBords[1] = 1;
			} else
				tabBords[2] = 1;
		} else {
			if (y == 0) {
				tabBords[3] = 1;
			}
			if (y == Fenetre.getNbCols() - 1) {
				tabBords[1] = 1;
			}
		}
		// for(int k=0; k<tabBords.length; k++){
		// System.out.println(tabBords[k]);
		// }
		return tabBords;
	}

	public static int getNbre(int x, int y) {
		return nbre[x][y];
	}

	public static void setNbre(int[][] nbre) {
		TabProxi.nbre = nbre;
	}

}
