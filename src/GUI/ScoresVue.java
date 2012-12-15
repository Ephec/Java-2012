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

public class ScoresVue extends JFrame{

	private JPanel scores = new JPanel();

	private JLabel nom = new JLabel("Nom");
	private JLabel niveau = new JLabel("Niveau");
	private JLabel score = new JLabel("Score");
	
	private String[][] tabScores = new String[Scores.lignesFichier()][3];

	public ScoresVue(){

		tabScores = Scores.lireFichier();
		init();

	}

	public void init(){

		this.setTitle("Scores");
		this.setMinimumSize(new Dimension(500, 400));
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		this.add(scores);
		scores.setLayout(new GridLayout(Scores.lignesFichier() + 1, 3));

		scores.add(nom);
		scores.add(niveau);		
		scores.add(score);
		
		for(int i = 0; i < Scores.lignesFichier(); i++){
			
			scores.add(new JLabel(tabScores[i][1]));
			scores.add(new JLabel(tabScores[i][2]));
			scores.add(new JLabel(tabScores[i][3]));
			
		}

		
		this.setVisible(true);


	}

}


