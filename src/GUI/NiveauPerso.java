package GUI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe utilisée lors de la création d'une partie personnalisée
 * 
 * @author BETAS A. & BREMER C.
 */
public class NiveauPerso extends JFrame{

	private JPanel boite = new JPanel();

	private JLabel txtLignes = new JLabel("Nombre de lignes  : ");
	private JLabel txtCols = new JLabel("Nombre de colonnes : ");
	private JLabel txtMines = new JLabel("Nombre de mines   : ");

	private JTextField nbLignesC = new JTextField();
	private JTextField nbColsC = new JTextField();
	private JTextField nbMinesC = new JTextField();

	private JButton ok = new JButton("ok");
	private JButton annuler = new JButton("Annuler");

		private int nbLignes;
		private int nbCols;
		private int nbMines;

	/**
	 * Constructeur de la fenêtre pour le niveau personnalisé
	 */
	public NiveauPerso() {

		initNivPerso();

	}

	/**
	 * Initialisation de la fenêtre pour le niveau personnalisé
	 */
	public void initNivPerso(){

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
				if(verifEntrees()){
					creationNiv();
					dispose();
				}else{
					JOptionPane.showMessageDialog(null, "Saisie non correcte ! \n\n- Max 50 lignes ou colonnes \n- Min 5 lignes ou colonnes \n- Pas plus de mines que de cases \n- Que des chiffres ou nombres", "Erreur", JOptionPane.ERROR_MESSAGE);
				}

			}        
		});
		boite.add(ok);

		this.setVisible(true);
	}

	/**
	 * Vérification des nombres entrés afin de ne pas faire planter le programme
	 * @return boolean
	 */
	public boolean verifEntrees(){

		try{
			nbLignes = Integer.parseInt(nbLignesC.getText());
			nbCols = Integer.parseInt(nbColsC.getText());
			nbMines = Integer.parseInt(nbMinesC.getText());
		} catch (NumberFormatException n) {
			return false;
		}
		
		if(nbMines < nbLignes * nbCols && nbLignes <= 50 && nbLignes >= 5 && nbCols <= 50 && nbCols >= 5){
			return true;
		} else {		
			return false;
		}
	}
	
	/**
	 * Créer le niveau personnalisé au niveau de la fenêtre principale via ses getters
	 */
	public void creationNiv() {

		Fenetre.setNbLignes(nbLignes);
		Fenetre.setNbCols(nbCols);
		Fenetre.setNbMines(nbMines);
		Fenetre f = new Fenetre();

	}

}
