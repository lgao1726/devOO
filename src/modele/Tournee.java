package modele;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import tsp.GrapheLivraison;
import tsp.TSP;
import tsp.TSP1;

public class Tournee {
	
	private static final int TEMPSLIMITE = 60000;
	
	private ArrayList<Itineraire> itineraires;	
	
	//constructeur temporaire pour facilier le dev de la vue
	public Tournee(Plan plan,DemandeLivraison demande){
		itineraires = new ArrayList<Itineraire>();
	} 
	public void calculTournee(Plan plan, ArrayList<FenetreLivraison> fenetreLivraisons){
		GrapheLivraison grapheLivraison = new GrapheLivraison(plan, fenetreLivraisons);
		TSP tsp = new TSP1();
		tsp.chercheSolution(TEMPSLIMITE, grapheLivraison);
		Queue<Integer> ordreLivraisons = new LinkedList<Integer>();
		for(int i=0; i<grapheLivraison.getNbSommets(); i++){
			ordreLivraisons.add(tsp.getSolution(i));
		}
		
	}
	
	//méthode temporaire pour faciliter le dev de la vue
	public void creerItineraires(Plan plan){
		Noeud racine = plan.getNoeud(0);
		ArrayList<Integer> idNoeuds = new ArrayList<Integer>();
		
		idNoeuds.add(racine.getId());
		
		int nbNoeuds = plan.getIntersections().size();
		for(int i = 0;i < 60; i++){
			int idNext = racine.getListeTronconsSortants().get(1).getIdNoeudDestination();
			racine = plan.getNoeud(idNext);
			idNoeuds.add(idNext);
		}
		
		Itineraire iti = new Itineraire();
		for(int i:idNoeuds){iti.ajouterNoeud(i);}
		itineraires.add(iti);
		
		
	}
	
	public Iterator<Itineraire> getItineraireIterator(){
		return itineraires.iterator();
	}
}
