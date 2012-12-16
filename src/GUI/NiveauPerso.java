package GUI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NiveauPerso extends JFrame{
	
	private JPanel boite = new JPanel();
	
	private JLabel txtLignes = new JLabel("Nombre de lignes  : ");
	private JLabel txtCols = new JLabel("Nombre de colonnes : ");
	private JLabel txtMines = new JLabel("Nombre de mines   : ");
	
	private JTextField nbLignesC = new JTextField();
	private JTextField nbColsC = new JTextField();
	private JTextField nbMinesC = new JTextField();
	
	private int nbLignes = 1;
	private int nbCols = 1;
	private int nbMines = 1;
	
	private JButton ok = new JButton("ok");
	private JButton annuler = new JButton("Annuler");
	
	
	public NiveauPerso() {

		this.setTitle("Niveau personnalisé");
		this.setMinimumSize(new Dimension(300, 200));
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.add(boite);
		boite.setLayout(new GridLayout(5,2));
	
		boite.add(txtLignes);
		boite.add(nbLignesC);
		boite.add(txtCols);
		boite.add(nbColsC);
		boite.add(txtMines);
		boite.add(nbMinesC);
		
		boite.add(new JLabel());
		boite.add(new JLabel());
		
		annuler.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				Fenetre f = new Fenetre();
				dispose();
			}        
		});
		boite.add(annuler);
		
		ok.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				
				Fenetre.setNbLignes(Integer.parseInt(nbLignesC.getText()));
				Fenetre.setNbCols(Integer.parseInt(nbColsC.getText()));
				Fenetre.setNbMines(Integer.parseInt(nbMinesC.getText()));
				Fenetre f = new Fenetre();
				dispose();
				
			}        
		});
		boite.add(ok);

		this.setVisible(true);
	}
	
	public void verifMines(){
		
	}

	public int getNbLignes() {
		return nbLignes;
	}

	public void setNbLignes(int nbLignes) {
		this.nbLignes = nbLignes;
	}

	public int getNbCols() {
		return nbCols;
	}

	public void setNbCols(int nbCols) {
		this.nbCols = nbCols;
	}

	public int getNbMines() {
		return nbMines;
	}

	public void setNbMines(int nbMines) {
		this.nbMines = nbMines;
	}
	
	
}
