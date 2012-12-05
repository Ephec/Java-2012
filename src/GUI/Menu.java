package GUI;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame{
	
	JMenuBar menuBar = new JMenuBar();
	JMenu partie = new JMenu("Partie");
	JMenu niveaux = new JMenu("Niveaux");
	JMenu options = new JMenu("Options");
	JMenu infos = new JMenu("?");

	JMenuItem nouvelle = new JMenuItem("Nouvelle partie");
	JMenuItem reseau = new JMenuItem("Nouvelle partie en réseau");
	JMenuItem statistiques = new JMenuItem("Statistiques");
	JMenuItem fermer = new JMenuItem("Fermer");
	
	JMenuItem facile = new JMenuItem("Facile");
	JMenuItem intermediaire = new JMenuItem("Intermédiaire");
	JMenuItem difficile = new JMenuItem("Difficile");
	JMenuItem personnalise = new JMenuItem("Personnalisé");
	
	JMenuItem chrono = new JMenuItem("Afficher le chrono");
	
	JMenuItem apropos = new JMenuItem("A propos");
	JMenuItem aide = new JMenuItem("Aide");

	public Menu() {
		
		this.setSize(500,500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		// Menu "Partie"
		partie.add(nouvelle);
		partie.add(reseau);
		partie.add(statistiques);
		partie.addSeparator();

		fermer.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}        
		});
		partie.add(fermer);
		
		// Menu "Niveaux"
		ButtonGroup bg = new ButtonGroup();
		bg.add(facile);
		bg.add(intermediaire);
		bg.add(difficile);
		facile.setSelected(true);
		
		niveaux.add(facile);
		niveaux.add(intermediaire);
		niveaux.add(difficile);
		niveaux.addSeparator();
		niveaux.add(personnalise);
		
		// Menu "Options"
		options.add(chrono);
		
		// Menu "?"
		aide.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Cliquez sur les cases sans toucher les mines ...");
			}        
		});
		infos.add(aide);
		
		apropos.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Démineur\nVersion : décembre 2012\nBy Antoine Betas & Cédric Bremer");
			}        
		});
		infos.add(apropos);
		
		// Barre générale qui contient les différents menu 
		partie.setMnemonic('P');
		menuBar.add(partie);
		niveaux.setMnemonic('N');
		menuBar.add(niveaux);
		options.setMnemonic('O');
		menuBar.add(options);
		infos.setMnemonic('?');
		menuBar.add(infos);
		
		this.setJMenuBar(menuBar);
		this.setVisible(true);
	}
	
}


