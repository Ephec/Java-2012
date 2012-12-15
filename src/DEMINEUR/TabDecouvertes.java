package DEMINEUR;

import GUI.Fenetre;

public class TabDecouvertes {
	
	private static boolean[][] decouverte = new boolean[Fenetre.getNbLignes()][Fenetre.getNbCols()];
	
	public  TabDecouvertes() {
		
		initDecouvertes(Fenetre.getNbLignes(),Fenetre.getNbCols());
		
	}

	public void initDecouvertes(int lignes, int cols) {
		
		
		
		for(int i=0; i<lignes; i++){
			for(int j=0; j<cols; j++){
				//decouverte[i][j] = new boolean;
				//decouverte[i][j] = false;
			}
		}
		
	}
	
	public static boolean getDecouverte(int x, int y) {
		return decouverte[x][y];
	}

	public static void setDecouverte(boolean decouverte, int x, int y) {
		TabDecouvertes.decouverte[x][y] = decouverte;
	}
	
	
}
