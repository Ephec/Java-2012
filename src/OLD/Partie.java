package OLD;

//anciennes methodes qui peuvent servir pour la suite 

public class Partie {

	/**
	 * D�fini ce qu'une est partie gagn�e, c'est une partie dans laquelle les
	 * cases restantes correspondent au nombre de mines. Les drapeaux ne
	 * comptent pas, ce sont des indications pour le joueur. A appeller apr�s
	 * avoir d�couvert les cases autour de la case s�l�ctionn�e.
	 * 
	 * @param nbMines
	 * @param nbDrapeau
	 * @return
	 */
	public boolean gagner(int nbMines, int nbDrapeau) {

		// Si le nombre de mines correspond au nombre de case - le nombre de
		// case d�couverte
		if (plateau.getNbCaseDecou() == (plateau.getNbCases() - plateau
				.getNbCaseDecou())) {
			plateau.toutDecouvrir();
			return true;
		} else
			return false;

	}

	/**
	 * Retourne vrai si la partie est perdue, autrement dit si la case est min�e
	 * et a �t� d�couverte. Cette m�thode sera appell�e juste apr�s la
	 * d�couverture de la case et avant de d�couvrir autour
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
