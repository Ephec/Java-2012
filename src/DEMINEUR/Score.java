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

public class Score {

	private String nom;
	private int temps;
	private int level;
	private String sLevel;
	private String sTemps;
	// Tableau de scores de x lignes et " colonnes (nom, niveau et temps sous
	// forme de string)
	private String[][] tabScores;

	private static String fichier = "score.txt";

	public Score(String nom, int temps, int level) {

		this.nom = nom;
		this.temps = temps;
		this.level = level;
		sTemps = String.valueOf(level);
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
			str = "PersonnalisÃ©";
			break;
		}

		return str;
	}

	/**
	 * Permet de connaitre le nombre de lignes de score dans le fichier texte
	 * afin de créer un tableau de scores lors de la lecture du fichier
	 * 
	 * @return
	 */
	public int ligneFichier() {

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
	 * Lit le fichier texte et enregistre les score dans un tableau de score
	 */
	public void lireFichier() {
		String chaine = "";
		int i=0;
		// Séparateur de chaine
		StringTokenizer stringTokenizer = new StringTokenizer(chaine,"\t"); 

		// lecture du fichier texte
		try {
			InputStream ips = new FileInputStream(fichier);
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String ligne;
			// Décompose une chaine sur le \t et va le mettre sur la ligne i et la colonne j
			// La colonne 0 est le nom, 1 est le temps et 2 est le niveau
			while ((ligne = br.readLine()) != null) {
				int j=0;
				System.out.println(ligne);
				while ( stringTokenizer.hasMoreTokens() )
				{ 
				   tabScores[i][j] = stringTokenizer.nextToken(); 
				   i++;
				}
				chaine += ligne + "\n";
			}
			br.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}

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
			fichierSortie.println(chaine + "\n test de lecture et écriture !!");
			fichierSortie.close();
			System.out.println("Le fichier " + fichier + " a été créé!");
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