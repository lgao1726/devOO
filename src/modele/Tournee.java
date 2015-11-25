package modele;


import java.util.ArrayList;
import java.util.Iterator;

public class Tournee {
	private ArrayList<Itineraire> itineraires;
	
	//constructeur temporaire pour facilier le dev de la vue
	public Tournee(Plan plan,DemandeLivraison demande){
		itineraires = new ArrayList<Itineraire>();
	}
	public void calculTournee(Plan plan, ArrayList<Livraison>livraison){
		
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