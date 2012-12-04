// Source du dossier : http://files.codes-sources.com/fichier.aspx?id=40336

public class Chrono implements Runnable
{
	private Thread chronometre;
	private int    dixiemeseconde = 0;
	boolean arret;//utilisation de cette variable pour l'arret du thread


	public void start ()
	{
		//demarrage de notre thread
		chronometre = new Thread (this);
		chronometre.start ();
	}


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

	//methode pour "l'arret" de notre thread
	public void stop ()
	{
		arret = true;
		if(chronometre != null)
		{
			chronometre.isInterrupted();
		}
	}
	
	//methode retournant notre temps sous forme de chaine de caracteres
	public String resultat()
	{
		return dixiemeseconde / 36000
        + ":" + (dixiemeseconde / 6000) % 6 + (dixiemeseconde / 600) % 10
        + ":" + (dixiemeseconde / 100) % 6  + (dixiemeseconde / 10) % 10
        + ":" + dixiemeseconde % 10;

	}
	

}