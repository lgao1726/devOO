package tsp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import javax.swing.plaf.synth.SynthSeparatorUI;

import modele.Noeud;
import modele.Plan;
import modele.Troncon;
import modele.FenetreLivraison;
import modele.Itineraire;
import modele.Livraison;
public class GrapheLivraison implements Graphe {
	
	int nbSommets;
	float[][] graphePlan;
	float[][] grapheChemin;//intialis� dans initGraphe
	float[][] grapheTSP;
	Map<Integer,Integer> indicesLivraisons;
	Map<Integer, ArrayList<int[]>> listItineraires;
	final int BLANC=0;
	int premierSommet;
	final int GRIS=1;
	final int NOIR=2;
	final float DUREE_MAX=100000; //en heure pour l'instant
	static int grapheCheminLigne = 0; //utilis�e pour l'�criture dans grapheChemin


	public GrapheLivraison(Plan p,List<FenetreLivraison> fenetres){
		listItineraires=new HashMap<Integer, ArrayList<int[]>>();
		indicesLivraisons = new HashMap<Integer,Integer>();
		nbSommets=p.getNbIntersections();
		premierSommet = fenetres.get(0).getLivraisons().get(0).getAdresse().getId();
		indicesLivraisons.put(premierSommet, 0);
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
		creerGrapheTSP();
		afficherMatrice();
	}

	@Override
	public int getNbSommets() {
		return indicesLivraisons.size();
	}

	@Override
	public float getCout(int i, int j) {
		if (i<0 || i>=indicesLivraisons.size() || j<0 || j>=indicesLivraisons.size())
			return -1;
		return grapheTSP[i][j];
	}

	@Override
	public boolean estArc(int i, int j) {
		if (i<0 || i>=indicesLivraisons.size() || j<0 || j>=indicesLivraisons.size())
			return false;
		return i != j;
	}
	

    public void afficherMatrice()
    {
        System.out.println("Matrice du chemin");
        for(int i=0; i<indicesLivraisons.size(); i++)
        {
            for(int j=0; j<indicesLivraisons.size(); j++)
            {
            	
                System.out.print("|"+grapheTSP[i][j]);
            }
            System.out.println("|");
        }
        
        for(int i:indicesLivraisons.keySet()){
        	System.out.println(indicesLivraisons.get(i)+" | "+ i);
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
				
				//il faut s�parer les chemins et les couts
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
		//trouver la fenetre � laquelle cette livraison appartient
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
		//cas d'entrept pas encore g�r�, du coup les derni�res fenetres peuvent etre nuls
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
				grapheChemin[adresseOrigine][adresseDestination] = couts[adresseDestination];
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
				grapheChemin[adresseOrigine][adresseDestination] = couts[adresseDestination];
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
		//on va mettre les plus courts chemins et distances dans le m�me tableau
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
			//trouver le noeud o� la distance est plus courte
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
		ArrayList<Integer> resultat = new ArrayList<Integer>();
		ArrayList<int[]> liste=listItineraires.get(idNoeudOrigine);		
		for(int[] i:liste)
		{	//for(int k=0;k<i.length;k++){System.out.print(i[k]+" | ");}System.out.println("");
			if(i[i.length-1]==idNoeudDestination)
			{
				for(int j=0;j<i.length;j++){
					resultat.add(i[j]);
				}return resultat;
			}
		}
		return null;
	}
	
	private void creerGrapheTSP(){
		int cpt = 1;
		//remplir le map indicesLivraisons
		for(int i=0;i<nbSommets;i++){
			for(int j=0;j<nbSommets;j++){
				if(grapheChemin[i][j] != -1 && i!=premierSommet){
					indicesLivraisons.put(i,cpt);
					cpt++;
					break;
				}
			}			
		}
		grapheTSP = new float[indicesLivraisons.size()][indicesLivraisons.size()]; 
		for(int i=0;i<indicesLivraisons.size();i++){
			for(int j=0;j<indicesLivraisons.size();j++){
				grapheTSP[i][j] = 10000;
			}
		}
		for(int i=0;i<nbSommets;i++){
			for(int j=0;j<nbSommets;j++){
				if(grapheChemin[i][j] != -1){
					grapheTSP[indicesLivraisons.get(i)][indicesLivraisons.get(j)] = grapheChemin[i][j];
				}
			}
		}
		
	}
	
	public int mapLivraison(int indice){
		for(int i:indicesLivraisons.keySet()){
			if(indicesLivraisons.get(i) == indice) return i;
		}return -1;
	}
	
	public ArrayList<Integer> obtenirPlusCourtChemin(int origine, int destination){
		ArrayList<Integer> chemin = new ArrayList<Integer>();
		int[] dijkstraResultat = dijkstra(origine);
		//il faut s�parer les chemins et les couts
		int[] predecesseurs = new int[nbSommets];
		int[] couts = new int[nbSommets];
		for(int i=0;i<nbSommets;i++){
			predecesseurs[i] = dijkstraResultat[i];
			couts[i] = dijkstraResultat[nbSommets+i];
		}
		int [] tabChemin = trouverChemin(predecesseurs,origine,destination);
		for(int i=0;i<tabChemin.length;i++){
			chemin.add(tabChemin[i]);
		}
		return chemin;
	}
	
	public float getCoutItineraire(List<Integer> itineraire){
		float cout = 0;
		int size = itineraire.size();
		int courant = itineraire.get(0);
		for(int i=1;i<size;i++){
			cout += graphePlan[courant][itineraire.get(i)];
			courant = itineraire.get(i);
		}
		//pour le retour � l'entrepot
		cout += graphePlan[itineraire.get(size-1)][itineraire.get(0)];
		
		return cout;
	}
	
}