package DEMINEUR;

public class Score {

	private String nom;
	private int temps;
	private int level;

	// Ici la classe score, c'est facile, on défini ce qu'est un objet score. On
	// appellera le constructeur du socre dans le démineur est c'est le démineur
	// qui créera le "tableau" de score et le fichier de sauvegarde des scores
	// en fonctions des méthodes de la classe.

	public Score(String nom, int temps, int level) {
		
		this.nom=nom;
		this.temps=temps;
		this.level=level;

	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getTemps() {
		return temps;
	}

	public void setTemps(int temps) {
		this.temps = temps;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level=level;
	}
	
	

}
