/**
 * Cette classe défini les paramètres d'une case du démineur. Elle sera
 * spécialisée en fonction des attrivuts booleens "minées"
 * 
 * @author Betas A. & Bremer C.
 * 
 */
public class Case {

	private int coordX; // Collonne
	private int coordY; // Ligne

	/**
	 * Permet de savoir si une case est minée
	 */
	private boolean minee;

	/**
	 * Permet de savoir si une case a déjà été découverte. Par défaut, une case
	 * n'est pas découverte
	 */
	private boolean decouvert = false;

	/**
	 * Permet de savoir si une case contient un drapeau. Par défaut, une case ne
	 * contient pas de drapeau
	 */
	private boolean drapeau = false;

	/**
	 * Permet de connaitre le nombre de mines à proximité
	 */
	private int minesProxi;

	/**
	 * Constructeur d'une case avec confirmation des paramètres par défaut et
	 * ajout ou non d'une mine comme paramètre
	 * @param mine
	 */
	public Case(boolean mine) {
		this.decouvert = false;
		this.drapeau = false;
		this.minee = mine;
	}

	public int getCoordX() {
		return coordX;
	}

	public void setCoordX(int coordX) {
		this.coordX = coordX;
	}

	public int getCoordY() {
		return coordY;
	}

	public void setCoordY(int coordY) {
		this.coordY = coordY;
	}

	public boolean isMinee() {
		return minee;
	}

	public void setMinee(boolean minee) {
		this.minee = minee;
	}

	/**
	 * Retourne un booleen si la case est découverte ou non
	 * @return decouvert
	 */
	public boolean isDecouvert() {
		return decouvert;
	}

	/**
	 * Permet de découvrir une case si elle n'est pas encore découverte et si il
	 * n'y a pas de drapeau dessus. Pas de paramètres entrant car une case peut
	 * être découverte ou non mais ne peut jamais être recouverte
	 */
	public void setDecouvert() {
		if (this.isDecouvert() == false) {
			if (this.isDrapeau() == false) {
				this.decouvert = true;
			}
		}
	}

	public boolean isDrapeau() {
		return drapeau;
	}

	/**
	 * Permet de placer un drapeau sur une case non découverte. Si le drapeau
	 * n'est pas placé, aucun effet sur la case !
	 * @param placerDrapeau
	 */
	public void setDrapeau(boolean placerDrapeau) {
		if (!this.isDecouvert()) {
			this.drapeau = placerDrapeau;
		}
	}

	public int getMinesProxi() {
		return minesProxi;
	}

	public void setMinesProxi(int minesProxi) {
		this.minesProxi = minesProxi;
	}

	/**
	 * Affichage d'un "!" pour un drapeau, d'un "X" pour une mine, du nombre de
	 * mines à proximité si il y en a ou d'un "?" si découvert.
	 */
	public String toString() {
		if (drapeau)
			return "!";
		else if (decouvert != true)
			return "?";
		else if (isMinee() == true)
			return "X";
		else if (minesProxi > 0)
			return "" + minesProxi;
		else
			return "-";
	}

}
