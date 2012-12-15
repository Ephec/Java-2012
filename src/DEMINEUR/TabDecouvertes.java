package DEMINEUR;

import GUI.Fenetre;

public class TabDecouvertes {
	
	private boolean[][] decouvertes;
	
	public  TabDecouvertes() {
		
		decouvertes = new boolean[Fenetre.getNbLignes()][Fenetre.getNbCols()];
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
	
	public boolean getDecouverte(int x, int y) {
		return decouvertes[x][y];
	}

	public void setDecouverte(boolean decouverte, int x, int y) {
		decouvertes[x][y] = decouverte;
	}
	
	
}
