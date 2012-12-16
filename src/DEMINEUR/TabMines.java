package DEMINEUR;

import GUI.Fenetre;

/**
 * Gère l'emplacement des mines dans un tableau
 * 
 * @author BETAS A. & BREMER C.
 */
public class TabMines {
	
	/*
	 * Tableau 2D qui contient l'état des cases (booleen)
	 */
	private static boolean[][] mine = new boolean[Fenetre.getNbLignes()][Fenetre.getNbCols()];
	
	/**
	 * Constructeur du tableau de mines
	 */
	public TabMines(){
		
		initMines(Fenetre.getNbMines(),Fenetre.getNbLignes(),Fenetre.getNbCols());
		
	}
	
	/**
	 * Création d'un tableau d'emplacements de mines. Tri ce ce tableau et
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

	public static boolean getMine(int x, int y) {
		return mine[x][y];
	}

	public static void setMine(boolean[][] mine) {
		TabMines.mine = mine;
	}
	
	

}

