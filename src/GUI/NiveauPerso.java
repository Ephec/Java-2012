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
	
	private int nbLignes;
	private int nbCols;
	private int nbMines;
	
	private JButton ok = new JButton("ok");
	private JButton annuler = new JButton("Annuler");
	
	
	public NiveauPerso() {

		this.setTitle("Niveau personnalisé");
		this.setMinimumSize(new Dimension(300, 200));
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		this.add(boite);
		boite.setLayout(new GridLayout(6,2));
	
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
				dispose();
			}        
		});
		boite.add(annuler);
		
		ok.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				nbLignes = Integer.parseInt(nbLignesC.getText());
				//System.out.println(Lignes);
				nbCols = Integer.parseInt(nbColsC.getText());
				nbMines = Integer.parseInt(nbMinesC.getText());
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
