package RESEAU;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import GUI.Fenetre;

public class Serveur extends ServerSocket implements Runnable{
	
	public Serveur(int port) throws IOException {
		
		super(port);
		
		try {
			
			Socket socket = this.accept();
			//InputStream is = socket.getInputStream();
			//ObjectInputStream ois = new ObjectInputStream(is);
			
			//boolean[][] tab = new boolean[Fenetre.getNbLignes()][Fenetre.getNbCols()];
			
			//boolean[][] tab = (boolean[][])ois.readObject();
			//System.out.println("Etu recu : "+tab[2][2]);
			
			
			
			/*System.out.println("IP du client : "+socket.getInetAddress());
			System.out.println("Le port local : "+socket.getLocalPort());
			System.out.println("L'autre port : "+socket.getPort());
			System.out.println("Ip du serveur:"+socket.getLocalSocketAddress());
			*/
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
	}

	public void run() {
		
	}

}
