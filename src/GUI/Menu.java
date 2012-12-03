package GUI;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.ButtonGroup;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame{
	
	private JMenuBar menuBar = new JMenuBar();
	private JMenu partie = new JMenu("Partie");
	private JMenu niveaux = new JMenu("Niveaux");
	private JMenu options = new JMenu("Options");
	private JMenu infos = new JMenu("?");

	private JMenuItem nouvelle = new JMenuItem("Nouvelle partie");
	private JMenuItem scores = new JMenuItem("Scores");
	private JMenuItem fermer = new JMenuItem("Fermer");
	
	private JMenuItem facile = new JMenuItem("Facile");
	private JMenuItem intermediaire = new JMenuItem("Intermédiaire");
	private JMenuItem difficile = new JMenuItem("Difficile");
	
	private JMenuItem chrono = new JMenuItem("Afficher le chrono");

	public Menu() {
		this.setSize(500,500); // pour le test
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		// Menu "Partie"
		this.partie.add(nouvelle);
		this.partie.add(scores);
		this.partie.addSeparator();

		fermer.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}        
		});
		this.partie.add(fermer);
		
		// Menu "Niveaux"
		ButtonGroup bg = new ButtonGroup();
		bg.add(facile);
		bg.add(intermediaire);
		bg.add(difficile);
		facile.setSelected(true);
		
		this.niveaux.add(facile);
		this.niveaux.add(intermediaire);
		this.niveaux.add(difficile);
		
		// Menu "Options"
		this.options.add(chrono);
		
		// Menu "?"
		this.infos.add("Aide");
		this.infos.add("A propos");
		
		// Barre générale qui contient les différents menu 
		partie.setMnemonic('P');
		this.menuBar.add(partie);
		niveaux.setMnemonic('N');
		this.menuBar.add(niveaux);
		options.setMnemonic('O');
		this.menuBar.add(options);
		infos.setMnemonic('?');
		this.menuBar.add(infos);
		
		this.setJMenuBar(menuBar);
		this.setVisible(true);

	}

	// pour tester aussi
	public static void main (String[] args){
		Menu m = new Menu();
	}

}


