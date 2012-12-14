package DEMINEUR;

import GUI.Fenetre;

public class TabDrapeaux {
	
	private static boolean[][] drapeau = new boolean[Fenetre.getNbLignes()][Fenetre.getNbCols()];
	
	public TabDrapeaux(){
		
		initTab(Fenetre.getNbLignes(),Fenetre.getNbCols());
		
	}
	
	public void initTab(int lignes, int cols){
		
		for(int i=0; i<lignes;i++){
			for(int j=0;j<cols;j++){
				drapeau[i][j]=false;
			}
		}
		
	}

	public static boolean getDrapeau(int x, int y) {
		return drapeau[x][y];
	}

	public static void setDrapeau(boolean drapeau, int x, int y) {
		TabDrapeaux.drapeau[x][y] = drapeau;
	}
	
	
}
