package OLD;

import GUI.Fenetre;

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
	 * Constantes : Nombre de lignes, de colonnes et le nombre de mines en
	 * fonction du niveau (1, 2 ou 3)
	 */

	private int level;

	private Plateau plateau;

	public Partie() {

		// Ensuite on crée le plateau et on initalise les cases sans mines pour
		// permette le premier clic sur une case non minée
		plateau = new Plateau(Fenetre.getNbLignes(), Fenetre.getNbCols(), Fenetre.getNbMines());
		plateau.initCases();

		// Après l'initialisation des cases aura lieu le premier clic dans
		// l'interface graphique je suppose. Il faut qu'on place les mines après

	}

	/**
	 * Défini ce qu'une est partie gagnée, c'est une partie dans laquelle les
	 * cases restantes correspondent au nombre de mines. Les drapeaux ne
	 * comptent pas, ce sont des indications pour le joueur. A appeller après
	 * avoir découvert les cases autour de la case séléctionnée.
	 * 
	 * @param nbMines
	 * @param nbDrapeau
	 * @return
	 */
	public boolean gagner(int nbMines, int nbDrapeau) {

		// Si le nombre de mines correspond au nombre de case - le nombre de
		// case découverte
		if (plateau.getNbCaseDecou() == (plateau.getNbCases() - plateau
				.getNbCaseDecou())) {
			plateau.toutDecouvrir();
			return true;
		} else
			return false;

	}

	/**
	 * Retourne vrai si la partie est perdue, autrement dit si la case est minée
	 * et a été découverte. Cette méthode sera appellée juste après la
	 * découverture de la case et avant de découvrir autour
	 * 
	 * @param isMinee
	 * @return perdu
	 */
	public boolean perdu(boolean isMinee) {

		if (isMinee)
			return true;
		else
			return false;
	}	

}
