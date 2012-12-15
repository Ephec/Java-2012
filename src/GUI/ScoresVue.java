package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class ScoresVue extends JFrame{

	// Panel
	JPanel sauverScore;
	private JPanel validation;
	// Texte
	private JTextPane nom;
	private JTextPane niveau;
	private JTextPane score;
	// Champs
	private JTextField niveauAffiche;
	private JTextField scoreAffiche;
	private JTextField insertNom;

	public ScoresVue(){

		init();

	}

	public void init(){

		this.setTitle("Score");
		this.setMinimumSize(new Dimension(500, 200));
		this.setLocationRelativeTo(null);
		sauverScore = new JPanel();
		this.add(sauverScore);
		sauverScore.setLayout(new GridLayout(4,2));

		// Colonne 1
		sauverScore.add(getNom());
		sauverScore.add(getScore());		
		sauverScore.add(getNiveau());

		this.setVisible(true);


	}

	public JTextPane getNom(){

		nom = new JTextPane();
		nom.setText("Nom");
		nom.setEditable(false);

		return nom;

	}

	public JTextPane getScore(){

		score = new JTextPane();
		score.setText("Score");
		score.setEditable(false);

		return score;
	}

	public JTextPane getNiveau(){

		niveau = new JTextPane();
		niveau.setText("Niveau");
		niveau.setEditable(false);

		return niveau;
	}

}


