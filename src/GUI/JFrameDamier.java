package GUI;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;
/**
 * Cette classe est un exemple de code pour illustrer comment faire un damier en java
 * Vous pouvez soit afficher les numéros de lignes colonnes dans chaque jButton en 
 * laissant ce code inchangé. Ou bien, vous mettez en commentaires la ligne 65 et 
 * enlevez les commentaires de la ligne 66 pour avoir une image dans les jButton.
 * @author mnv
 *
 */
public class JFrameDamier extends javax.swing.JFrame {
  private JPanel jPanel1;
  private int nbLignes=10;
  private int nbColonnes=10;
  private int largeur=50; // la taille de l'image (50 sur 50 ici)
  private int hauteur=50;
  private JButton[][] tabJButton = new JButton[nbColonnes][nbLignes];

  public static void main(String[] args) {
	SwingUtilities.invokeLater(new Runnable() {
	  public void run() {
		  JFrameDamier inst = new JFrameDamier();
		  inst.setLocationRelativeTo(null);
		  inst.setVisible(true);
	  }
	});
  }

  public JFrameDamier() {
	super();
	initGUI();
  }

  
 
private void initGUI() {

	setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	{
	  jPanel1 = new JPanel();
	  GridBagLayout jPanel1Layout = new GridBagLayout();
	  getContentPane().add(jPanel1, BorderLayout.CENTER);
	  jPanel1.setLayout(jPanel1Layout);
	  jPanel1.setPreferredSize(
	    new java.awt.Dimension(largeur*nbColonnes, hauteur*nbLignes));
	  jPanel1.setSize(largeur*nbColonnes, hauteur*nbLignes); 
	  // va s'adapter à la taille image et au nombre de lignes ou de colonnes
	  for (int i = 0; i < nbColonnes; i++) {
	    for (int j = 0; j < nbLignes; j++) {
		  tabJButton[i][j] = new JButton();
		  BorderLayout jButton1Layout = new BorderLayout();
		  tabJButton[i][j].setLayout(jButton1Layout);
		  jPanel1.add(tabJButton[i][j], 
				  new GridBagConstraints(i, j, 1, 1, 0.0, 0.0, 
						  GridBagConstraints.CENTER, 
						  GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		  // choisir une ligne ci-dessous et mettre l'autre en commentaires
		  tabJButton[i][j].setText(i+":"+j);
		  /*tabJButton[i][j].setIcon(
		    new ImageIcon(getClass().getClassLoader().getResource
		      ("images/StrategoBackground_01.gif")));*/
		  tabJButton[i][j].setBorderPainted(false);
		  tabJButton[i][j].setOpaque(false);
		  tabJButton[i][j].setBorder(BorderFactory.createCompoundBorder(
				  null, 
				  null));
		  tabJButton[i][j].setSize(largeur, hauteur);
		  tabJButton[i][j].setMargin(new java.awt.Insets(0, 0, 0, 0));
		  tabJButton[i][j].setPreferredSize(new java.awt.Dimension(largeur, hauteur));
		  tabJButton[i][j].setMaximumSize(new java.awt.Dimension(largeur, hauteur));
		  tabJButton[i][j].setMinimumSize(new java.awt.Dimension(largeur, hauteur));
		}
	  }
	  pack();
	}
  }
}

