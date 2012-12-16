package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import DEMINEUR.Scores;

/**
 * Classe de gestion de la fenêtre "Statistiques" du démineur
 * 
 * @author BETAS A. & BREMER C.
 */
public class ScoresVue extends JFrame{

	private JPanel scores = new JPanel();

	private JLabel nom = new JLabel("Nom");
	private JLabel niveau = new JLabel("Niveau");
	private JLabel score = new JLabel("Temps (s)");

	private String[][] tabScores = new String[Scores.lignesFichier()][3];
	public Scores importation = new Scores("",0,0);

	/**
	 * Constructeur de la fenêtre
	 */
	public ScoresVue(){

		tabScores = importation.lireFichier();
		init();
		afficherScores();

	}

	/**
	 * Initialise la fenêtre permettant de voir les scores
	 */
	public void init(){

		this.setTitle("Scores");
		if(!estLong(importation.lignesFichier()))
			this.setMinimumSize(new Dimension(400, (importation.lignesFichier() + 2 ) * 30));
		else
			this.setMinimumSize(new Dimension(400,12*30));
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		this.add(scores);
		if(!estLong(importation.lignesFichier()))
			scores.setLayout(new GridLayout(importation.lignesFichier() + 2, 3));
		else
			scores.setLayout(new GridLayout(12, 3));

		scores.add(nom);
		scores.add(niveau);		
		scores.add(score);

		scores.add(new JLabel());
		scores.add(new JLabel());
		scores.add(new JLabel());

		this.setVisible(true);

	}

	/**
	 * Permet d'afficher les scores dans la fenêtre
	 */
	private void afficherScores() {

		if(!estLong(importation.lignesFichier())){

			for(int i = 0; i < importation.lignesFichier(); i++){
				scores.add(new JLabel(tabScores[i][0]));
				scores.add(new JLabel(tabScores[i][1]));
				scores.add(new JLabel(tabScores[i][2]));
			}
		}
		else{
			for(int i=importation.lignesFichier()-10;i<importation.lignesFichier();i++){
				scores.add(new JLabel(tabScores[i][0]));
				scores.add(new JLabel(tabScores[i][1]));
				scores.add(new JLabel(tabScores[i][2]));
			}
		}
	}
	
	/**
	 * Permet de savoir si notre fichier contient plus de 10 lignes
	 * @param x
	 * @return boolean
	 */
	public boolean estLong(int x){
		
		boolean estlong;
		if (x>10) estlong=true;
		else estlong=false;
		return estlong;
	}

}


