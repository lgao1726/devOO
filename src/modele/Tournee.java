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

	private static final int TEMPSLIMITE = 600000;
	private LinkedList<Itineraire> itineraires;

	// constructeur temporaire pour facilier le dev de la vue
	public Tournee(Plan plan) {
		itineraires = new LinkedList<Itineraire>();
	}

	public void calculTournee(Plan plan, ArrayList<FenetreLivraison> fenetreLivraisons) {
		GrapheLivraison grapheLivraison = new GrapheLivraison(plan, fenetreLivraisons);
		grapheLivraison.afficherMatrice();
		TSP tsp = new TSP1();
		tsp.chercheSolution(TEMPSLIMITE, grapheLivraison);
		LinkedList<Integer> ordreLivraisons = new LinkedList<Integer>();
		int nbLivraisons=getNbLivraisons(fenetreLivraisons);
		System.out.println("Circuit");
		for (int i = 0; i <grapheLivraison.getNbLivraisons()-1; i++) { //On enleve un psq l'entrepot compte pour deux livraisons
			ordreLivraisons.add(tsp.getSolution(i));
			System.out.print("|"+tsp.getSolution(i));
			
		}
		for(Integer j:ordreLivraisons){
			creerItineraire(grapheLivraison, j, getLivraisonSuivante(j, ordreLivraisons));
		}
		
	   
    }
	
	// méthode temporaire pour faciliter le dev de la vue
	private void creerItineraire(GrapheLivraison grapheLivraison, Integer idLivraisonOrigine, Integer idLivraisonDestination) {
		itineraires.add(new Itineraire(grapheLivraison.getItiniraire(idLivraisonOrigine, idLivraisonDestination)));
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
	
	private Integer getLivraisonSuivante(Integer i, LinkedList<Integer> ordreLivraisons)
	{
		if(i==ordreLivraisons.getLast())
		{
			return  ordreLivraisons.getFirst();
		}
		else
		{//A changer psq c moche
			return ordreLivraisons.get(ordreLivraisons.indexOf(i)+1);
		}
	}
	
}