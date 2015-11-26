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
	private LinkedList<Itineraire> itineraires;
	private GrapheLivraison grapheLivraison;

	// constructeur temporaire pour facilier le dev de la vue
	public Tournee(Plan plan) {
		itineraires = new LinkedList<Itineraire>();
		grapheLivraison = null;
	}

	public void calculerTournee(Plan plan, ArrayList<FenetreLivraison> fenetreLivraisons) {
		grapheLivraison = new GrapheLivraison(plan, fenetreLivraisons);
		System.out.println("xxx");
		//grapheLivraison.afficherMatrice();
		TSP tsp = new TSP1();
		tsp.chercheSolution(TEMPSLIMITE, grapheLivraison);
		Queue<Integer> ordreLivraisons = new LinkedList<Integer>();
		int nbLivraisons=getNbLivraisons(fenetreLivraisons);
		for (int i = 0; i <nbLivraisons-1 ; i++) {
			ordreLivraisons.add(tsp.getSolution(i));
		}
		//entrepot
		int entrepot = grapheLivraison.mapLivraison(ordreLivraisons.peek());
	    while(!ordreLivraisons.isEmpty())
	    {
            int origine = grapheLivraison.mapLivraison(ordreLivraisons.poll());
            if(ordreLivraisons.peek() != null){
	            int destination = grapheLivraison.mapLivraison(ordreLivraisons.peek());
	            ArrayList<Integer> noeudsItineraire = grapheLivraison.getItiniraire(origine, destination);
		    	Itineraire iti = new Itineraire(noeudsItineraire);
		    	itineraires.add(iti);
		    	
	    	}else{
	    		ArrayList<Integer> noeudsItineraire = grapheLivraison.getItiniraire(origine, entrepot);
		    	Itineraire iti = new Itineraire(noeudsItineraire);
		    	itineraires.add(iti);
	    	}
	    	//System.out.print("|"+grapheLivraison.mapLivraison(ordreLivraisons.poll()));
            //System.out.print("|"+ordreLivraisons.poll());
         }
           
    }
	

	// méthode temporaire pour faciliter le dev de la vue
	/*public void creerItineraires(Plan plan) {
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

	}*/

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
	
	public boolean supprimerLivraison(int idLivraison ){
		int nouvelOrigine;
		int nouvelleDestination;
		Itineraire aSupprimer1 = null;
		Itineraire aSupprimer2 = null;
		for(Itineraire itineraire : itineraires){
			if(itineraire.getLivraisonDestination().getId() == idLivraison){
				nouvelOrigine = itineraire.getLivraisonOrigine().getId();
				aSupprimer1 = itineraire;
			}
			if(itineraire.getLivraisonOrigine().getId() == idLivraison){ 
				nouvelleDestination = itineraire.getLivraisonDestination().getId();
				aSupprimer2 = itineraire;
			}
		}
		if(aSupprimer1 != null && aSupprimer2 != null){
			itineraires.remove(aSupprimer1);
			itineraires.remove(aSupprimer2);
		}else{
			return false;
		}
		if(grapheLivraison != null) {
			
		}
		else{
			return false;
		}
		Itineraire itineraireRemplacant;
		return true;
	}
}