package DEMINEUR;

import GUI.Fenetre;

/**
 * Classe de gestion des cases d�couvertes
 * 
 * @author BETAS A. & BREMER C.
 */
public class TabDecouvertes {
	
	private boolean[][] decouvertes;
	
	/**
	 * Constructeur du tableau de cases d�couvertes
	 */
	public  TabDecouvertes() {
		
		decouvertes = new boolean[Fenetre.getNbLignes()][Fenetre.getNbCols()];
		initDecouvertes(Fenetre.getNbLignes(),Fenetre.getNbCols());
		
	}

	/**
	 * Initialisation du tableau de cases d�couvertes
	 * @param lignes
	 * @param cols
	 */
	public void initDecouvertes(int lignes, int cols) {		
		
		for(int i=0; i<lignes; i++){
			for(int j=0; j<cols; j++){
				decouvertes[i][j] = false;
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
