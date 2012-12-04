/**
 * Cette classe d�fini les param�tres d'une case du d�mineur. Elle sera
 * sp�cialis�e en fonction des attrivuts booleens "min�es"
 * 
 * @author Betas A. & Bremer C.
 * 
 */
public class Case {

	private int coordX; // Collonne
	private int coordY; // Ligne

	/**
	 * Permet de savoir si une case est min�e
	 */
	private boolean minee;

	/**
	 * Permet de savoir si une case a d�j� �t� d�couverte. Par d�faut, une case
	 * n'est pas d�couverte
	 */
	private boolean decouvert = false;

	/**
	 * Permet de savoir si une case contient un drapeau. Par d�faut, une case ne
	 * contient pas de drapeau
	 */
	private boolean drapeau = false;

	/**
	 * Permet de connaitre le nombre de mines � proximit�
	 */
	private int minesProxi;

	/**
	 * Constructeur d'une case avec confirmation des param�tres par d�faut et
	 * ajout ou non d'une mine comme param�tre
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
	 * Retourne un booleen si la case est d�couverte ou non
	 * @return decouvert
	 */
	public boolean isDecouvert() {
		return decouvert;
	}

	/**
	 * Permet de d�couvrir une case si elle n'est pas encore d�couverte et si il
	 * n'y a pas de drapeau dessus. Pas de param�tres entrant car une case peut
	 * �tre d�couverte ou non mais ne peut jamais �tre recouverte
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
	 * Permet de placer un drapeau sur une case non d�couverte. Si le drapeau
	 * n'est pas plac�, aucun effet sur la case !
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
	 * mines � proximit� si il y en a ou d'un "?" si d�couvert.
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
