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
	 * Permet d'afficher les scores dans la fenêtre
	 */
	private void afficherScores() {

		for(int i = 0; i < importation.lignesFichier(); i++){

			//System.out.println(""+tabScores[i][0]+"   "+tabScores[i][1]+"   "+tabScores[i][2]+"");
			scores.add(new JLabel(tabScores[i][0]));
			scores.add(new JLabel(tabScores[i][1]));
			scores.add(new JLabel(tabScores[i][2]));

		}

	}

}


