package tsp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;


import modele.Noeud;
import modele.Plan;
import modele.Troncon;
import modele.FenetreLivraison;
import modele.Itineraire;
import modele.Livraison;
public class GrapheLivraison implements Graphe {
	
	int nbSommets;
	float[][] graphePlan;
	float[][] grapheChemin;//intialisé dans initGraphe
	Map<Integer, ArrayList<int[]>> listItineraires;
	final int BLANC=0;
	final int GRIS=1;
	final int NOIR=2;
	final float DUREE_MAX=100000; //en heure pour l'instant
	static int grapheCheminLigne = 0; //utilisée pour l'écriture dans grapheChemin
	int nbLivraisons=0;

	public GrapheLivraison(Plan p,List<FenetreLivraison> fenetres){
		for(FenetreLivraison fenetre:fenetres){
			nbLivraisons += fenetre.getNbLivraisons();
		}
		listItineraires=new HashMap<Integer, ArrayList<int[]>>();
		nbSommets=p.getNbIntersections();
		graphePlan = new float[nbSommets][nbSommets]; 
		grapheChemin = new float[nbSommets][nbSommets]; 
		for (int i=0; i<nbSommets; i++){
		    for (int j=0; j<nbSommets; j++){
		         graphePlan[i][j] = -1;
		         grapheChemin[i][j] = -1;
		    }
		}
		initGraphe(p,fenetres);
		creerGrapheChemin(fenetres);
	}

	@Override
	public int getNbSommets() {
		return nbSommets;
	}
	
	public int getNbLivraisons() {
		return nbLivraisons;
	}

	@Override
	public float getCout(int i, int j) {
		if (i<0 || i>=nbSommets || j<0 || j>=nbSommets)
			return -1;
		return grapheChemin[i][j];
	}

	@Override
	public boolean estArc(int i, int j) {
		if (i<0 || i>=nbSommets || j<0 || j>=nbSommets)
			return false;
		else if(grapheChemin[i][j]!=-1)
		{
			return true;
		}
		else
		{
		return false;
		}
	}
	
	public boolean estLivraison(int k){
		for(int i=0; i<nbSommets; i++)
		{
			if(grapheChemin[i][k]!=-1 ||grapheChemin[k][i]!=-1)
			{
				return true;
			}
		}
		return false;
	}

    public void afficherMatrice()
    {
        System.out.println("Matrice de chemin");
        for(int i=0; i<nbSommets; i++)
        {
                System.out.print("|"+grapheChemin[14][i]);
        }
            System.out.println("|");
     
        System.out.println("Itineraires");
        Iterator it = listItineraires.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            System.out.println(pair.getKey() + " : " );
            ArrayList<int[]> chemins = (ArrayList<int[]>)pair.getValue();
            for(int[] chemin:chemins){
	            for(int i=0;i<chemin.length;i++){System.out.print(chemin[i]+" ");}
	            System.out.println();
            }
            
        }
    }
        
	
	private void initGraphe(Plan p,List<FenetreLivraison> fenetres)
	{
		ArrayList<Noeud> intersections=p.getIntersections();
		for (Noeud curNoeud:intersections)
		{
			int idOrigine=curNoeud.getId();
			List<Troncon> troncons=  curNoeud.getListeTronconsSortants();
			for (Troncon curTroncon:troncons)
			{
				
				int idDestination=curTroncon.getIdNoeudDestination();
				float longueur=curTroncon.getLongueur();
				float vitesse=curTroncon.getVitesse();
				float duree= longueur/vitesse;
				graphePlan[idOrigine][idDestination]=duree;
			}
		}
		
	}
	
	private void creerGrapheChemin(List<FenetreLivraison> fenetres){
		for(FenetreLivraison fenetre:fenetres){
			Iterator<Livraison> it = fenetre.getLivraisonIterator();
			while(it.hasNext()){
				Livraison livraison = it.next();
				int[] dijkstraResultat= dijkstra(livraison.getAdresse().getId());
				
				//il faut séparer les chemins et les couts
				int[] predecesseurs = new int[nbSommets];
				int[] couts = new int[nbSommets];
				for(int i=0;i<nbSommets;i++){
					predecesseurs[i] = dijkstraResultat[i];
					couts[i] = dijkstraResultat[nbSommets+i];
				}remplirGrapheChemin(predecesseurs,couts,livraison,fenetres);
				
			}
		}
	}
	
	private void remplirGrapheChemin(int[] predecesseurs,int[] couts,Livraison livraison,
			List<FenetreLivraison> fenetres){
		int adresse = livraison.getAdresse().getId();
		System.out.println(fenetres.size());
		//trouver la fenetre à laquelle cette livraison appartient
		//et trouver la fenetre suivante
		FenetreLivraison fenetreCourante = null;
		FenetreLivraison fenetreSuivante = null;
		boolean done = false;
		for(FenetreLivraison fenetre:fenetres){
			if(done && fenetreSuivante==null) 
				fenetreSuivante = fenetre;
			if(fenetre.appartient(livraison)){
				fenetreCourante = fenetre;
				done = true;
			}
		}
		//cas d'entrept pas encore géré, du coup les dernières fenetres peuvent etre nuls
		if(fenetreSuivante!=null &&fenetreCourante!=null){
			itineraireFenetreCourante(fenetreCourante, predecesseurs,couts, adresse);		
			itineraireFenetreSuivante(fenetreSuivante, predecesseurs,couts, adresse);
		}
		
	}
	
	//trouver chemin entre deux livraisons
	private int[] trouverChemin(int[] predecesseurs,int origine,int destination){
		int cpt = 0;
		int[] tempResultat = new int[nbSommets]; //ce resultat est dans l'ordre invers de ce qu'on veut
		int courant = destination;
		tempResultat[cpt] = courant;
		cpt++;
		while(predecesseurs[courant] != origine){
			tempResultat[cpt] = predecesseurs[courant];
			cpt++;
			courant = predecesseurs[courant];			
		}
		//ajouter l'origine
		tempResultat[cpt] = predecesseurs[courant];
		cpt++;
		
		int[] resultat = new int[cpt];
		//retourner un tableau avec les bonnes dimensions
		//dans le bon sens
		for(int i=0;i<cpt;i++){
			resultat[i] = tempResultat[cpt-i-1];
		}
		return resultat;
	}
	
	private void itineraireFenetreCourante(FenetreLivraison fenetre,int[] predecesseurs,
			int[] couts,int adresseOrigine){
		Iterator<Livraison> it = fenetre.getLivraisonIterator();
		while(it.hasNext()){
			int adresseDestination = it.next().getAdresse().getId();
			if(adresseOrigine!=adresseDestination){
				grapheChemin[adresseOrigine][adresseDestination]=couts[adresseDestination];
				int[] chemin = trouverChemin(predecesseurs,adresseOrigine,adresseDestination);
				if(!listItineraires.containsKey(adresseOrigine)){
					listItineraires.put(adresseOrigine, new ArrayList<int[]>());
				}
				listItineraires.get(adresseOrigine).add(chemin);
			}
		}
	}
	
	private void itineraireFenetreSuivante(FenetreLivraison fenetre,int[] predecesseurs,
			int[] couts,int adresseOrigine){
		Iterator<Livraison> it = fenetre.getLivraisonIterator();
		while(it.hasNext()){
			int adresseDestination = it.next().getAdresse().getId();
			if(adresseOrigine!=adresseDestination){
				grapheChemin[adresseOrigine][adresseDestination]=couts[adresseDestination];
				int[] chemin = trouverChemin(predecesseurs,adresseOrigine,adresseDestination);
				if(!listItineraires.containsKey(adresseOrigine)){
					listItineraires.put(adresseOrigine, new ArrayList<int[]>());
				}
				listItineraires.get(adresseOrigine).add(chemin);
			}
		}
	}
	
	public int[] dijkstra(int noeudDebut)
	{
		//on va mettre les plus courts chemins et distances dans le même tableau
		//resultat
		float[] distance = new float[nbSommets];
		int[] predecesseur = new int[nbSommets];
		boolean[] visite = new boolean[nbSommets];
		int[] resultat = new int[nbSommets*2];
		
		for(int i=0;i<nbSommets;i++){
			distance[i] = DUREE_MAX;
			predecesseur[i] = -1;
			visite[i] = false;
		}
		
		distance[noeudDebut] = 0;
		predecesseur[noeudDebut] = noeudDebut;
		
		//pour chaque sommet
		for(int i=0;i<nbSommets;i++){
			float minDist = DUREE_MAX;
			int noeudCourant = noeudDebut;
			//trouver le noeud où la distance est plus courte
			for(int j=0;j<nbSommets;j++){
				if(!visite[j] && distance[j]<minDist){
					noeudCourant = j;
					minDist = distance[j];
					}
			}
			visite[noeudCourant] = true;			
			
			//regarder tous ses voisins
			for(int j=0;j<nbSommets;j++){
				//relacher arc
				if(graphePlan[noeudCourant][j]+distance[noeudCourant]<distance[j] 
						&& graphePlan[noeudCourant][j]!=-1){
					distance[j] = graphePlan[noeudCourant][j]+distance[noeudCourant];
					predecesseur[j] = noeudCourant;
				}
				
			}
		}
		for(int i=0;i<nbSommets;i++){
			resultat[i] = predecesseur[i];
		}
		
		for(int i=0;i<nbSommets;i++){
			resultat[predecesseur.length+i] = (int) distance[i];
		}
		return resultat;		
	}
	
	public ArrayList<Integer> getItiniraire(int idNoeudOrigine, int idNoeudDestination)
	{
		ArrayList<int[]> liste=listItineraires.get(idNoeudOrigine);
		for(int[] i:liste)
		{
			if(i[i.length-1]==idNoeudDestination)
			{
				ArrayList itineraire=new ArrayList<Integer>();
				for(int j=0; j<i.length; j++)
				{
					itineraire.add(i[j]);
				}
				return itineraire;
			}
		}
		return null;
	}
	
}
	
	