package DEMINEUR;


/**
 * Chonomètre avec methode start, stop, résultat
 * Source de la classe : http://files.codes-sources.com/fichier.aspx?id=40336
 *  -> A la date du 21 novembre
 * Les commentaires étaient présents au départ
 * 
 * @author BETAS A. & BREMER C.
 *
 */
public class Chrono implements Runnable
{
	private Thread chronometre;
	private int    dixiemeseconde = 0;
	boolean arret;//utilisation de cette variable pour l'arret du thread

	/**
	 * Permet de démarrer le chrono
	 */
	public void start ()
	{
		//demarrage de notre thread
		chronometre = new Thread (this);
		chronometre.start ();
	}

	/**
	 * Méthode qui défini la mannière dont le thread fonctionne, en comptant en dixièmes de seconde
	 */
	public void run()
	{
		try
		{
			arret = false;
			chronometre = Thread.currentThread();
			//continuer tand que le thread n'a pas ete arrete 
			while (!arret)
			{
				dixiemeseconde++;
				Thread.sleep (100);

			}
		}
		catch (InterruptedException e) { 
			System.out.println(e.getMessage());
		}
		catch(NullPointerException e1)
		{
			System.out.println(e1.getMessage());
		}
	}

	/**
	 * Permet de stopper le chrono
	 */
	public void stop ()
	{
		arret = true;
		if(chronometre != null)
		{
			chronometre.isInterrupted();
		}
	}
	
	/**
	 * Retourne le résultat en secondes de notre chrono
	 * @return dixiemesseconde/10
	 */
	public int resultat(){
		return (dixiemeseconde/10);
	}
	
	/**
	 * Reset le chrono a zéro
	 */
	public void reset(){
		dixiemeseconde=0;
	}

}