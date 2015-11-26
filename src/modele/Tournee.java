package modele;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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
		    	setLivraisonsPourItineraire(fenetreLivraisons, iti, origine, destination);
		    	setCout(iti);
		    	itineraires.add(iti);
		    	
	    	}else{
	    		ArrayList<Integer> noeudsItineraire = grapheLivraison.getItiniraire(origine, entrepot);
		    	Itineraire iti = new Itineraire(noeudsItineraire);
		    	setLivraisonsPourItineraire(fenetreLivraisons, iti, origine, entrepot);
		    	setCout(iti);
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

	private void setLivraisonsPourItineraire(ArrayList<FenetreLivraison> fenetreLivraisons, Itineraire iti, int origine,
			int destination) {
		
		for(FenetreLivraison fenetreLivraison: fenetreLivraisons)
		{
			Iterator<Livraison> it = fenetreLivraison.getLivraisonIterator();
			while(it.hasNext()){
				Livraison liv = it.next();
				if(liv.getAdresse().getId()==origine){
					iti.setLivraisonOrigine(liv);
				}
				else if(liv.getAdresse().getId() == destination){
					iti.setLivraisonDestination(liv);
				}
			}
		}
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
	
	public boolean supprimerLivraison(int idLivraison){
		Livraison nouvelOrigine = null;
		Livraison nouvelleDestination = null;
		Itineraire aSupprimer1 = null;
		Itineraire aSupprimer2 = null;
		int positionInsertion = -1;
		for(int i=0; i<itineraires.size(); i++){
			Itineraire itineraire = itineraires.get(i);
			if(itineraire.getLivraisonDestination().getAdresse().getId() == idLivraison){
				nouvelOrigine = itineraire.getLivraisonOrigine();
				aSupprimer1 = itineraire;
				positionInsertion = i;
			}
			if(itineraire.getLivraisonOrigine().getAdresse().getId() == idLivraison){ 
				nouvelleDestination = itineraire.getLivraisonDestination();
				aSupprimer2 = itineraire;
			}
		}
		if(aSupprimer1 != null && aSupprimer2 != null){
			itineraires.remove(aSupprimer1);
			itineraires.remove(aSupprimer2);
		}else{
			return false;
		}
		if(grapheLivraison != null && nouvelleDestination != null && nouvelOrigine != null) {
			ArrayList<Integer> plusCourtChemin =  grapheLivraison.obtenirPlusCourtChemin(nouvelOrigine.getAdresse().getId(), nouvelleDestination.getAdresse().getId());
			Itineraire itineraireRemplacant = new Itineraire(plusCourtChemin);
			itineraireRemplacant.setLivraisonOrigine(nouvelOrigine);
			itineraireRemplacant.setLivraisonDestination(nouvelleDestination);
			if(positionInsertion != -1) itineraires.add(positionInsertion, itineraireRemplacant);
		}
		else{
			return false;
		}
		return true;
	}
	
	//recalculer les 2 itinéraires autour des 2 itinéraires on veut échanger
	//inverser l'itinéraire entre les 2 itinéraires qu'on veut échanger
	//livraison 1 est le livraison precedent
	public boolean echangerLivraison(int livraison1,int livraison2){
		Itineraire itiAInverser = null;
		Itineraire itiAvant = null;//itineraire qui se trouve avant le changement
		Itineraire itiApres = null;//itineraire qui se trouve apès le changement
		
		int posItiAvant = -1;
		int posItiApres = -1;
		for(int i=0;i<itineraires.size();i++){
			Itineraire iti = itineraires.get(i);
			int itiSize = iti.getNoeuds().size();
			//trouver l'itineraire qui commence avec livraison 1
			//et termine avec livraison2
			if(iti.getNoeuds().get(0)==livraison1 && 
				iti.getNoeuds().get(itiSize - 1)==livraison2){
				itiAInverser = iti;
			}if(iti.getNoeuds().get(0)==livraison2){
				//itineraire - livraison après livraison2
				itiApres = iti;
				posItiApres = i; 
			}if(iti.getNoeuds().get(itiSize - 1)==livraison1){
				//itineraire - livraison avant livraison2
				itiAvant = iti;
				posItiAvant = i;
			}
		}
		if(itiAvant==null || itiApres==null || posItiAvant==-1 || posItiApres==-1 || itiAInverser==null){
			return false;
		}
		
		Collections.reverse(itiAInverser.getNoeuds());//inverser itineraire
		int avant = itiAvant.getLivraisonOrigine().getAdresse().getId();
		int apres = itiApres.getLivraisonDestination().getAdresse().getId();
		ArrayList<Integer> cheminAvant = grapheLivraison.obtenirPlusCourtChemin(avant, livraison2);
		ArrayList<Integer> cheminApres = grapheLivraison.obtenirPlusCourtChemin(livraison1, apres);
		
		itiAvant.setListeNoeud(cheminAvant);
		itiApres.setListeNoeud(cheminApres);
		return true;
	}
	
	public boolean ajouterLivraison(int id, Noeud noeud, int client, int adresseLivraisonAvant){
		
		Livraison livraison = new Livraison(id, noeud, client);
		Livraison avant = null;
		Livraison apres = null;
		int posItineraireADiviser = -1;
		
		for(int i =0; i<itineraires.size(); i++){
			Itineraire itineraire = itineraires.get(i);
			if(itineraire.getLivraisonOrigine().getAdresse().getId() == adresseLivraisonAvant){
				avant = itineraire.getLivraisonOrigine();
				apres = itineraire.getLivraisonDestination();
				posItineraireADiviser = i;
			}
		}
		
		if(posItineraireADiviser != -1){
			itineraires.remove(posItineraireADiviser);
		}else{
			return false; 
		}
		
		if(avant != null && apres != null){
			ArrayList<Integer> plusCourtChemin1 =  grapheLivraison.obtenirPlusCourtChemin(avant.getAdresse().getId(), livraison.getAdresse().getId());
			Itineraire itineraireRemplacant1 = new Itineraire(plusCourtChemin1);
			ArrayList<Integer> plusCourtChemin2 =  grapheLivraison.obtenirPlusCourtChemin(livraison.getAdresse().getId(), apres.getAdresse().getId());
			Itineraire itineraireRemplacant2 = new Itineraire(plusCourtChemin2);
			
			itineraires.add(posItineraireADiviser, itineraireRemplacant1);
			itineraires.add(posItineraireADiviser+1, itineraireRemplacant2);	
		}else{
			return false;
		}		
	
		return true;
	}
	
	public void setCout(Itineraire itineraire){
		float cout = grapheLivraison.getCoutItineraire(itineraire.getNoeuds());
		itineraire.setCout(cout);
	}
	
	//pour les tests
	public void afficherListeItineraires(){
		for( Itineraire itineraire : itineraires){
			for(int noeud : itineraire.getNoeuds()){
				System.out.print(noeud + ", ");
			}
			System.out.println(";;");
		}
		System.out.println("]");
	}
}