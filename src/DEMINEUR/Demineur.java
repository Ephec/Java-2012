package DEMINEUR;

import javax.swing.JFrame;

import GUI.Menu;

public class Demineur extends JFrame {

	public Demineur(){ // Par après, il faudra encore séparer cette classe pour avoir une GUI bien distincte. 
		this.setSize(500,500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);

		Menu m = new Menu();
		//Partie p = new Partie();

	}

}

