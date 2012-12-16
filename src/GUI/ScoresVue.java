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
 * Classe de gestion de la fen�tre "Statistiques" du d�mineur
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
	 * Constructeur de la fen�tre
	 */
	public ScoresVue(){

		tabScores = importation.lireFichier();
		init();
		afficherScores();

	}

	/**
	 * Initialise la fen�tre permettant de voir les scores
	 */
	public void init(){

		this.setTitle("Scores");
		this.setMinimumSize(new Dimension(400, (importation.lignesFichier() + 2 ) * 30));
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		this.add(scores);
		scores.setLayout(new GridLayout(importation.lignesFichier() + 2, 3));

		scores.add(nom);
		scores.add(niveau);		
		scores.add(score);

		scores.add(new JLabel());
		scores.add(new JLabel());
		scores.add(new JLabel());

		this.setVisible(true);

	}

	/**
	 * Permet d'afficher les scores dans la fen�tre
	 */
	private void afficherScores() {

		if(importation.lignesFichier()<=10){

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

}


