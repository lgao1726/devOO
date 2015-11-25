package modele;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import tsp.GrapheLivraison;
import tsp.TSP;
import tsp.TSP1;

public class Tournee {

	private static final int TEMPSLIMITE = 60000;
	private ArrayList<Itineraire> itineraires;

	// constructeur temporaire pour facilier le dev de la vue
	public Tournee(Plan plan) {
		itineraires = new ArrayList<Itineraire>();
	}

	public void calculTournee(Plan plan, ArrayList<FenetreLivraison> fenetreLivraisons) {
		GrapheLivraison grapheLivraison = new GrapheLivraison(plan, fenetreLivraisons);
		//grapheLivraison.afficherMatrice();
		TSP tsp = new TSP1();
		tsp.chercheSolution(TEMPSLIMITE, grapheLivraison);
		Queue<Integer> ordreLivraisons = new LinkedList<Integer>();
		int nbLivraisons=getNbLivraisons(fenetreLivraisons);
		for (int i = 0; i <nbLivraisons ; i++) {
			ordreLivraisons.add(tsp.getSolution(i));
		}
		System.out.println("Circuit");
	    while(!ordreLivraisons.isEmpty())
	    {
              System.out.print("|"+ordreLivraisons.poll());
         }
            System.out.println("|");
    }
	

	// méthode temporaire pour faciliter le dev de la vue
	public void creerItineraires(Plan plan) {
		Noeud racine = plan.getNoeud(0);
		ArrayList<Integer> idNoeuds = new ArrayList<Integer>();

		idNoeuds.add(racine.getId());

		int nbNoeuds = plan.getIntersections().size();
		for (int i = 0; i < 60; i++) {
			int idNext = racine.getListeTronconsSortants().get(1).getIdNoeudDestination();
			racine = plan.getNoeud(idNext);
			idNoeuds.add(idNext);
		}

		Itineraire iti = new Itineraire();
		for (int i : idNoeuds) {
			iti.ajouterNoeud(i);
		}
		itineraires.add(iti);

	}

	public Iterator<Itineraire> getItineraireIterator() {
		return itineraires.iterator();
	}
	
	private int getNbLivraisons(ArrayList<FenetreLivraison> fenetreLivraisons)
	{
		int nb=0;
		for(FenetreLivraison fenetre:fenetreLivraisons){
			nb += fenetre.getNbLivraisons();
		}
		return nb;
	}
}