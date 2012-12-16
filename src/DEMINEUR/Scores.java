package DEMINEUR;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

import GUI.Fenetre;

/**
 * Classe de gestion des scores
 * 
 * @author BETAS A. & BREMER C.
 */
public class Scores {

	private String nom;
	private int temps;
	private int level;
	private String sLevel;
	private String sTemps;
	// Tableau de scores de x lignes et " colonnes (nom, niveau et temps sous
	// forme de string)
	private static String[][] tabScores;

	private static String fichier = "score.txt";

	public Scores(String nom, int level, int temps) {

		//System.out.println(level);
		this.nom = nom;
		this.temps = temps;
		this.level = level;
		//sTemps = String.valueOf(level);
		sLevel = strLevel();

	}

	public String strLevel() {
		String str = "";
		switch (level) {
		case 0:
			str = "Facile";
			break;
		case 1:
			str = "Moyen";
			break;
		case 2:
			str = "Difficile";
			break;
		case 3:
			str = "Personnalisé";
			break;
		}

		return str;
	}

	/**
	 * Permet de connaitre le nombre de lignes de score dans le fichier texte
	 * afin de créer un tableau de scores lors de la lecture du fichier
	 * 
	 * @return int
	 */
	public static int lignesFichier() {

		int lignes = 0;

		try {
			InputStream ips = new FileInputStream(fichier);
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String ligne;
			while ((ligne = br.readLine()) != null) {
				lignes++;
			}
			br.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return lignes;
	}

	/**
	 * Lit le fichier texte score.txt et renvoi un tableau de String à deux dimensions
	 * Colonne 0 : Nom
	 * Colonne 1 : Temps
	 * Colonne 2 : Niveau
	 * 
	 * @return String[][]
	 */
	public String[][] lireFichier() {
		String chaine = "";
		int i=0;
		tabScores = new String[lignesFichier()][3];
		String[] temp = new String[3];

		// lecture du fichier texte
		try {
			InputStream ips = new FileInputStream(fichier);
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String ligne;
			while ((ligne = br.readLine()) != null) {
				int j=0;
				temp =ligne.split("/");
				chaine += ligne + "\n";
				tabScores[i]=temp;
				i++;
			}
			br.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return tabScores;

	}

	public void ecrireFichier() {

		String chaine = "";

		// lecture du fichier texte pour savoir ou on se trouve au niveau du
		// fichier
		try {
			InputStream ips = new FileInputStream(fichier);
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String ligne;
			while ((ligne = br.readLine()) != null) {
				chaine += ligne + "\n";
			}
			br.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		// création ou ajout dans le fichier texte
		try {
			FileWriter fw = new FileWriter(fichier);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter fichierSortie = new PrintWriter(bw);
			fichierSortie.println(chaine + "" +nom+ "/" +sLevel+ "/" +temps);
			fichierSortie.close();
			//System.out.println("Le fichier " + fichier + " a été créé!");
		} catch (Exception e) {
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
		this.level = level;
	}

	public String getsLevel() {
		return sLevel;
	}

	public void setsLevel(String sLevel) {
		this.sLevel = sLevel;
	}

}