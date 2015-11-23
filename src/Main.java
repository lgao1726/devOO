
import tsp.Graphe;
import tsp.GrapheLivraison;
import modele.Noeud;
import modele.Plan;
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
		
		new Controleur(plan);
		
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		GrapheLivraison graphe=new GrapheLivraison(plan);
		int index=0;
		Noeud noeud=plan.getIntersections().get(index);
		graphe.dijkstra(noeud);
		//graphe.afficherMatrice();
	}

}
