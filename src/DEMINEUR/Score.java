package DEMINEUR;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import GUI.Fenetre;

public class Score {

	private String nom;
	private int temps;
	private int level;
	private String sLevel;

	// Ici la classe score, c'est facile, on défini ce qu'est un objet score. On
	// appellera le constructeur du socre dans le démineur est c'est le démineur
	// qui créera le "tableau" de score et le fichier de sauvegarde des scores
	// en fonctions des méthodes de la classe.

	public Score(String nom, int temps, int level) {
		
		this.nom=nom;
		this.temps=temps;
		this.level=level;
		sLevel = strLevel();

	}
	
    public String strLevel(){
        String str = "";
        switch(level){
            case 0: str = "Facile"; break;
            case 1: str = "Moyen"; break;
            case 2: str = "Difficile"; break;
            case 3: str = "PersonnalisÃ©"; break;
        }

        return str;
    }
    
    public void sauverFichier(){
		String chaine="";
		String fichier ="fichiertexte.txt";
		
		//lecture du fichier texte	
		try{
			InputStream ips=new FileInputStream(fichier); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String ligne;
			while ((ligne=br.readLine())!=null){
				System.out.println(ligne);
				chaine+=ligne+"\n";
			}
			br.close(); 
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
	
		//création ou ajout dans le fichier texte
		try {
			FileWriter fw = new FileWriter (fichier);
			BufferedWriter bw = new BufferedWriter (fw);
			PrintWriter fichierSortie = new PrintWriter (bw); 
				fichierSortie.println (chaine+"\n 2e test !!"); 
			fichierSortie.close();
			System.out.println("Le fichier " + fichier + " a été créé!"); 
		}
		catch (Exception e){
			System.out.println(e.toString());
		}		
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

	public String getsLevel() {
		return sLevel;
	}

	public void setsLevel(String sLevel) {
		this.sLevel = sLevel;
	}
	
	

}
