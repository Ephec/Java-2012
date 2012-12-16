package DEMINEUR;

import GUI.Fenetre;

/**
 * Classe de gestion de l'emplacement des drapeaux
 * 
 * @author BETAS A. & BREMER C.
 */
public class TabDrapeaux {
	
	private boolean[][] drapeaux;
	
	/**
	 * Constructeur du tableau de drapeaux
	 */
	public TabDrapeaux(){
		
		drapeaux = new boolean[Fenetre.getNbLignes()][Fenetre.getNbCols()];
		initTab(Fenetre.getNbLignes(),Fenetre.getNbCols());
		
	}
	
	/**
	 * Initialisation du tableau de drapeaux
	 * @param lignes
	 * @param cols
	 */
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
