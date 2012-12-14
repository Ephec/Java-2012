package OLD;

import java.util.Arrays;
import java.util.Random;

import javax.swing.JButton;

import GUI.Fenetre;

/**
 * Cette classe cr�e un plateau de jeu compos� d'un nombre de lignes X et de
 * collones Y en fonction du niveau. Lors du premier clic (qui ne doit pas �tre
 * une mine), les mines sont plac�es et le c
 * 
 * @author Betas A. & Bremer C.
 * @see Case
 */

public class Plateau {

	private static int nbLignes;
	private static int nbCols;

	/*
	 * Tableau 2D qui contient l'�tat des cases (booleen)
	 * public pr l'instant, on verra apr�s pr un private
	 */
	public static boolean[][] mine = new boolean[Fenetre.getNbLignes()][Fenetre.getNbCols()];

	/*
	 * Tableau 2D qui contient le nbre de mines adjacentes � la case
	 */
	public static int[][] nbre = new int[Fenetre.getNbLignes()][Fenetre.getNbCols()];

	private int[] tabMines;
	private int nbCases;
	private int nbDrapeau;
	private int nbCaseDecou;

	/**
	 * Tableau a deux dimensions qui constitue le plateau de jeu compos� de
	 * cases [i][j] ou [y][x]
	 */
	public static Case[][] plateau;

	// Coordon�es des cases pour la cr�ation, pas besoin de protection il me
	// semble
	public int i;
	public int j;

	/**
	 * Cr�ation (d�finition des param�tres) pour le plateau de jeu : nombre de
	 * lignes, de collones et de mines. Ces param�tres sont transmis par la
	 * classe "Partie" qui d�finira le niveau de jeu. La gestion des erreurs se
	 * fera dans la classe "Partie"
	 * 
	 * @param nbLignes
	 * @param nbCols
	 * @param nbMines
	 */
	public Plateau(int nbLignes, int nbCols, int nbMines) {

	}

	/**
	 * Initiaisation des cases en fonction du nombre de lignes et de collones.
	 * Elle ne sont pas min�es. Le placement des mines se fera apr�s la premi�re
	 * s�l�ction d'une case �tant donn� qu'on ne peut pas commencer par une mine
	 */
	public void initCases() {

		nbCases = nbCols * nbLignes;

		for (int i = 0; i < nbLignes; i++) {
			for (int j = 0; j < nbCols; j++) {
				plateau[i][j] = new Case(false,i,j); // ajout d'une case non
				// min�e
			}
		}
	}



	/**
	 * Cr�ation d'un tableau d'emplacements de mines. Tri ce ce tableau et
	 * placement des mines dans le plateau
	 * 
	 * @param x
	 * @param y
	 */
	public static void initMines(int nb, int lignes, int cols) {
		int mines = nb;
		mine = new boolean[lignes][cols];

		for (int i = 0; i < lignes ; i++) {
			for(int j = 0; j < cols ; j++) {
				mine[i][j] = false;
			}
		}

		while(mines > 0){
			int coordX = (int) Math.floor(Math.random() * lignes);
			int coordY = (int) Math.floor(Math.random() * cols);
			//System.out.println("x = "+ coordX + "y = "+ coordY);
			if(!mine[coordX][coordY]){
				mine[coordX][coordY] = true;
				mines--;
			}
		}

	}

	/**
	 * Trouve le nombre de mines � proximit� d'une case en parcourant une ligne
	 * au dessus et en dessous et une collonne � droite et a gauche.
	 * 
	 * @param x
	 * @param y
	 */
	public static void nbMinesCase(int lignes, int cols) {

		nbre = new int[lignes][cols];
		// Parcourt toutes les cases et compte le nbre de mines adjacentes en dehors des bords
		for (int x = 0; x < lignes ; x++) {
			for (int y = 0; y < cols; y++) {

				int nbProxi = 0;

				// si la case X+1 existe et qu'elle contient une mine, nbProxi augmente
				if((x+1) < lignes){
					if(mine[x+1][y]){
						nbProxi++;
					}
				}
				if((x+1) < lignes && (y-1) >=0 ){
					if(mine[x+1][y-1]){
						nbProxi++;
					}
				}
				if((x+1) < lignes && (y+1) < cols){
					if(mine[x+1][y+1]){
						nbProxi++;
					}
				}
				if((x-1) >= 0){
					if(mine[x-1][y]){
						nbProxi++;
					}
				}
				if((x-1) >= 0 && (y+1) < cols){
					if(mine[x-1][y+1]){
						nbProxi++;
					}
				}
				if((x-1) >=0 && (y-1) >= 0){
					if(mine[x-1][y-1]){
						nbProxi++;
					}
				}
				if((y+1) < cols){
					if(mine[x][y+1]){
						nbProxi++;
					}
				}
				if((y-1) >= 0){
					if(mine[x][y-1]){
						nbProxi++;
					}
				}

				nbre[x][y] = nbProxi;

			}
		}

	}

	/**
	 * Ajoute dans les param�tres des case le nombre de mines � proximit� si la
	 * case en question n'est pas une mine.
	 */
	public void setNbMinesProxi() {

		int nbMines = 0;
		for (int i = 0; i < nbLignes; i++) {
			for (int j = 0; j < nbCols; j++) {
				// Si ce n'est pas une mine, trouve les mines � proximit�
				if (!plateau[i][j].isMinee()) {
					//nbMines = nbMinesCase(i, j);
					plateau[i][j].setMinesProxi(nbMines);
				}
			}
		}
	}
	
	// HAUT, DROITE, BAS, GAUCHE
	public int[] isAuBord(int x, int y){
		int[] tabBords = {0,0,0,0};
		
		if(x==0){
			if (y==0) {tabBords[0]=1; tabBords[3]=1;}
			if (y==nbCols) {tabBords[0]=1; tabBords[1]=1;}
			else tabBords[0]=1;
		}
		if(x==nbLignes){
			if (y==0) {tabBords[2]=1; tabBords[3]=1;}
			if (y==nbCols) {tabBords[2]=1; tabBords[1]=1;}
			else tabBords[2]=1;
		}
		else{
			if (y==0) {tabBords[3]=1;}
			if (y==nbCols) {tabBords[1]=1;}
		}
		System.out.println(tabBords);
		return tabBords;
	}

	/**
	 * Change le param�tre drapeau en true de la case en question
	 * 
	 * @param x
	 * @param y
	 */
	public void placerDrapeau(int x, int y) {
		plateau[x][y].setDrapeau(true);
		nbDrapeau++;
	}

	/**
	 * Enl�ve un drapeau d'une case pass�e en param�tre
	 * 
	 * @param x
	 * @param y
	 */
	public void enleverDrapeau(int x, int y) {
		plateau[x][y].setDrapeau(false);
		nbDrapeau--;
	}

	/**
	 * D�couvre une case plac�e en param�tres, op�ration irr�versible
	 * 
	 * @param x
	 * @param y
	 */
	public void decouvrirCase(int x, int y) {
		plateau[x][y].setDecouvert();
		nbCaseDecou++;

	}

	/**
	 * D�couvre les cases autour d'une case pass�e en param�tre. Si pas min�es,
	 * si pas d�couverte et si pas drapeau
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
	 * Methode qui set toute les cases � d�couvert, cette m�thode sert lors
	 * d'une victoire ou d'une d�faite.
	 */
	public void toutDecouvrir() {
		for (int i = 0; i < this.nbLignes; i++) {
			for (int j = 0; j < this.nbCols; j++) {
				plateau[i][j].setDecouvert();
			}
		}
	}

	public static int getNbLignes() { // Utilis� dans la GUI
		return nbLignes;
	}

	public void setNbLignes(int nbLignes) {
		this.nbLignes = nbLignes;
	}

	public static int getNbCols() { // Utilis� dans la GUI
		return nbCols;
	}

	public void setNbCols(int nbCols) {
		this.nbCols = nbCols;
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