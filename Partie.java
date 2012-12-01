/**
 * Cette classe permet de générer les paramètres de la partie en tant que tel.
 * C'est-à-dire le niveau de la partie et le joueur qui y jouera.
 * 
 * @author Betas A. & Bremer C.
 * @see Plateau
 * 
 */
public class Partie {

	/**
	 * Constantes : Nombre de lignes, de collonnes et le nombre de mines en
	 * fonction du niveau (1, 2 ou 3)
	 */
	
	public static int nbLignes_Facile = 10;
	public static int nbCols_Facile = 10;
	public static int nbMines_Facile = 10;
	
	public static int nbLignes_Moyen = 15;
	public static int nbCols_Moyen = 15;
	public static int nbMines_Moyen = 40;
	
	public static int nbLignesDif = 15;
	public static int nbColsDif = 30;
	public static int nbMinesDif = 95;

	private int level;

	public int getLevel() {
		return level;
	}

	/**
	 * Permet de vérifier le niveau : doit être compris entre 1 et 3
	 * 
	 * @param level
	 */
	public void setLevel(int level) {
		this.level = level;
	}

}
