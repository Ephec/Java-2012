package DEMINEUR;

import DEMINEUR.TabMines;
import GUI.Fenetre;


public class TabProxi {
	
	private static int[][] nbre = new int[Fenetre.getNbLignes()][Fenetre.getNbCols()];
	
	public TabProxi(){
		
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
		// Parcourt toutes les cases et compte le nbre de mines adjacentes en dehors des bords
		for (int x = 0; x < lignes ; x++) {
			for (int y = 0; y < cols; y++) {

				int nbProxi = 0;

				// si la case X+1 existe et qu'elle contient une mine, nbProxi augmente
				if((x+1) < lignes){
					if(TabMines.getMine(x+1,y)){
						nbProxi++;
					}
				}
				if((x+1) < lignes && (y-1) >=0 ){
					if(TabMines.getMine(x+1,y-1)){
						nbProxi++;
					}
				}
				if((x+1) < lignes && (y+1) < cols){
					if(TabMines.getMine(x+1,y+1)){
						nbProxi++;
					}
				}
				if((x-1) >= 0){
					if(TabMines.getMine(x-1,y)){
						nbProxi++;
					}
				}
				if((x-1) >= 0 && (y+1) < cols){
					if(TabMines.getMine(x-1,y+1)){
						nbProxi++;
					}
				}
				if((x-1) >=0 && (y-1) >= 0){
					if(TabMines.getMine(x-1,y-1)){
						nbProxi++;
					}
				}
				if((y+1) < cols){
					if(TabMines.getMine(x,y+1)){
						nbProxi++;
					}
				}
				if((y-1) >= 0){
					if(TabMines.getMine(x,y-1)){
						nbProxi++;
					}
				}

				nbre[x][y] = nbProxi;

			}
		}

	}

	public static int getNbre(int x, int y) {
		return nbre[x][y];
	}

	public static void setNbre(int[][] nbre) {
		TabProxi.nbre = nbre;
	}
	
	
	
}
