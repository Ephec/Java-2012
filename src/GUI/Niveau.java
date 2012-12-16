package GUI;

import javax.swing.JOptionPane;

/**
 * Fen�tre de s�lection du niveau facile, moyen, difficile
 * 
 * @author BETAS A. & BREMER C.
 *
 */
public class Niveau {
	
	private int reponse;
	
	/**
	 * Constructeur de Niveau
	 */
	public Niveau(){
		
		initFenetre();

	}
	
	/**
	 * Initialise la fen�tre de s�lection du niveau
	 */
	public void initFenetre(){
		
		JOptionPane choix = new JOptionPane();
		String[] niv = {"Facile", "Moyen", "Difficile", "Personnalis�"};
		reponse = choix.showOptionDialog(null, "Veuillez choisir votre niveau pour cette partie.", "S�lection Niveau", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, niv, niv[2]);

		
	}

	public int getReponse() {
		return reponse;
	}

	public void setReponse(int reponse) {
		this.reponse = reponse;
	}
	
	
}
