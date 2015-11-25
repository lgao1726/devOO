import tsp.Graphe;
import tsp.GrapheLivraison;
import modele.Noeud;
import modele.Plan;
import modele.Tournee;
import controleur.Controleur;

public class Main 
{
	private final static int dimX = 400;
	private final static int dimY = 400;
	
	/**
	 * Lanceur d'application
	 */
	public static void main(String[] args) 
	{
		// Creation du plan
		Plan plan = new Plan(dimX, dimY);
		
		Controleur controleur=new Controleur(plan);
		

		//graphe.afficherMatrice();
		try {
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
<<<<<<< HEAD

=======
		controleur.calculerTournee();
>>>>>>> branch 'development' of https://github.com/lgao1726/devOO.git
		
	}

}
