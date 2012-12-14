package OLD;

// anciennes methodes qui peuvent servir pour la suite 

public class Plateau {

	/**
	 * Ajoute dans les paramètres des case le nombre de mines à proximité si la
	 * case en question n'est pas une mine.
	 */
	public void setNbMinesProxi() {

		int nbMines = 0;
		for (int i = 0; i < nbLignes; i++) {
			for (int j = 0; j < nbCols; j++) {
				// Si ce n'est pas une mine, trouve les mines à proximité
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
	 * Change le paramètre drapeau en true de la case en question
	 * 
	 * @param x
	 * @param y
	 */
	public void placerDrapeau(int x, int y) {
		plateau[x][y].setDrapeau(true);
		nbDrapeau++;
	}

	/**
	 * Enlève un drapeau d'une case passée en paramètre
	 * 
	 * @param x
	 * @param y
	 */
	public void enleverDrapeau(int x, int y) {
		plateau[x][y].setDrapeau(false);
		nbDrapeau--;
	}

	/**
	 * Découvre une case placée en paramètres, opération irréversible
	 * 
	 * @param x
	 * @param y
	 */
	public void decouvrirCase(int x, int y) {
		plateau[x][y].setDecouvert();
		nbCaseDecou++;

	}

	/**
	 * Découvre les cases autour d'une case passée en paramètre. Si pas minées,
	 * si pas découverte et si pas drapeau
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
	 * Methode qui set toute les cases à découvert, cette méthode sert lors
	 * d'une victoire ou d'une défaite.
	 */
	public void toutDecouvrir() {
		for (int i = 0; i < this.nbLignes; i++) {
			for (int j = 0; j < this.nbCols; j++) {
				plateau[i][j].setDecouvert();
			}
		}
	}

	