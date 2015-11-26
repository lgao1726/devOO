import tsp.Graphe;
import tsp.GrapheLivraison;
import modele.Noeud;
import modele.Plan;
import modele.Tournee;
import controleur.Controleur;

public class Main 
{
	/**
	 * Lanceur d'application
	 */
	public static void main(String[] args) 
	{
		// Creation du plan
		Plan plan = new Plan();
		
		Controleur controleur=new Controleur(plan);
		

		//graphe.afficherMatrice();
		try {
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}
