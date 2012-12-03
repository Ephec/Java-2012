package GUI;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JFrame;
import javax.swing.JMenuItem;

public class Menu extends JFrame{

	private JMenuBar menuBar = new JMenuBar();
	private JMenu partie = new JMenu("Partie");
	
	private JMenuItem nouvelle = new JMenuItem("Nouvelle partie");
	private static Menu m;
	

	public Menu() {
		this.setSize(500,500); // pour le test
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		this.partie.add(nouvelle);
		
		this.menuBar.add(partie);
		this.setJMenuBar(menuBar);
		
	}

	// pour tester aussi
	public static void main (String[] args){
		m = new Menu();
	}

}


