package DEMINEUR;

import GUI.Fenetre;

public class TabDrapeaux {
	
	private boolean[][] drapeaux;
	
	public TabDrapeaux(){
		
		drapeaux = new boolean[Fenetre.getNbLignes()][Fenetre.getNbCols()];
		initTab(Fenetre.getNbLignes(),Fenetre.getNbCols());
		
	}
	
	public void initTab(int lignes, int cols){
		
		for(int i=0; i<lignes; i++){
			for(int j=0; j<cols; j++){
				drapeaux[i][j] = false;
			}
		}
		
	}

	public boolean getDrapeau(int x, int y) {
		return drapeaux[x][y];
	}

	public void setDrapeau(boolean drapeau, int x, int y) {
		drapeaux[x][y] = drapeau;
	}
	
	
}
