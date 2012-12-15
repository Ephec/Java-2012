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
	
	private JTextField nbLignes = new JTextField();
	private JTextField nbCols = new JTextField();
	private JTextField nbMines = new JTextField();
	
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
		boite.add(nbLignes);
		boite.add(txtCols);
		boite.add(nbCols);
		boite.add(txtMines);
		boite.add(nbMines);
		
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
				Fenetre.setNbLignes(Integer.parseInt(nbLignes.getText()));
				//System.out.println(Lignes);
				Fenetre.setNbCols(Integer.parseInt(nbCols.getText()));
				Fenetre.setNbMines(Integer.parseInt(nbMines.getText()));
				
			}        
		});
		boite.add(ok);

		this.setVisible(true);
	}
	
	public void verifMines(){
		
	}
}
