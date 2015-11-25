package tsp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import modele.FenetreLivraison;
import modele.Livraison;
import modele.Noeud;
import modele.Plan;
import modele.Troncon;
import modele.Itineraire;
public class GrapheLivraison implements Graphe {
	
	int nbSommets;
	int nbLivraisons;
	float[][] graphePlan;
	float[][] grapheChemin;
	Integer entrepot;
	List<FenetreLivraison> listeFenetres;
	List<Itineraire> listItineraires;
	final int BLANC=0;
	final int GRIS=1;
	final int NOIR=2;
	final float DUREE_MAX=100000; //en heure pour l'instant


	public GrapheLivraison(Plan p, List<FenetreLivraison> listeFenetres){
		entrepot=p.getAdresseEntrepot().getId();
		listItineraires=new ArrayList<Itineraire>();
		nbLivraisons=calculerNbLivraisons(listeFenetres);
		nbSommets=p.getNbIntersections();
		graphePlan = new float[nbSommets][nbSommets]; 
		grapheChemin = new float[nbLivraisons][nbLivraisons]; 
		initGraphe(p);
	}

	@Override
	public int getNbSommets() {
		return nbSommets;
	}

	/**@Override
	public float getCout(int i, int j) {
		if (i<0 || i>=nbSommets || j<0 || j>=nbSommets)
			return -1;
		return grapheChemin[i][j];
	}**/

	@Override
	public boolean estArc(int i, int j) {
		if (i<0 || i>=nbSommets || j<0 || j>=nbSommets)
			return false;
		return i != j;
	}
	
    public void afficherMatrice(Integer idDebut)
    {
        /**System.out.println("Matrice du plan");
        for(int i=0; i<nbSommets; i++)
        {
            for(int j=0; j<nbSommets; j++)
            {
            	
                System.out.print("|"+graphePlan[i][j]);
            }
            System.out.println("|");
        }**/
        System.out.println("Matrice de chemin");
        for(int j=0; j<nbSommets; j++)
        {
        	
            System.out.print("|"+grapheChemin[idDebut][j]);
        }
            System.out.println("|");
        }
        
	
	private void initGraphe(Plan p)
	{
		for (int i=0; i<nbSommets; i++){
		    for (int j=0; j<nbSommets; j++){
		         
		         graphePlan[i][j] = -1;
		    }
		}
		for (int i=0; i<nbLivraisons; i++){
		    for (int j=0; j<nbLivraisons; j++){
		         
		         grapheChemin[i][j] = -1;
		    }
		}
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
	
	
	public int[] dijkstra(Noeud noeudDebut)
	{
		//s0
		Integer idDebut=noeudDebut.getId();
		int[] couleurNoeud=initEtatNoeuds(); //pour chaque sommet,colorier si en blanc,
		int[] predecesseurs=initPredecesseurs(); //pour chaque sommet,		 faire pi[si]=-1; 
		float [] d=new float[nbLivraisons];
		
		for (int i=0; i<nbSommets; i++){         
		         d[i] = DUREE_MAX;
		}
		List <Integer> gris=new ArrayList<Integer>();
		
		d[idDebut]=0; //d[si]=0
		couleurNoeud[idDebut]=GRIS;
		gris.add(idDebut);
		
		while(!gris.isEmpty())
		{
			Integer iNoeud=choisirSommetGris(gris, idDebut); //choisir si tel que d[si]minimal
			for(Integer jNoeud=0; jNoeud<nbSommets; jNoeud++)
			{
				if(graphePlan[iNoeud][jNoeud]!=-1 && (couleurNoeud[jNoeud]!=NOIR) && jNoeud!=iNoeud)
				{
					relacher(d, iNoeud, jNoeud, predecesseurs);
					
					if(couleurNoeud[jNoeud]==BLANC)
					{
						couleurNoeud[jNoeud]=GRIS;
						gris.add(jNoeud);						
					}
				}
			}
			couleurNoeud[iNoeud]=NOIR;
			gris.remove(iNoeud);
		}
		
		System.out.println("predecesseurs");
		for(int j=0; j<nbSommets; j++)
		{
			System.out.print("|"+predecesseurs[j]);
		}
		return predecesseurs;
}
	
	private Integer choisirSommetGris(List<Integer> gris, int idDebut)
	{
		float dureeMin=DUREE_MAX;
		int idNoeudChoisi=gris.get(0);
		for(Integer id:gris)
		{
			if (grapheChemin[idDebut][id]<dureeMin)
			{
				idNoeudChoisi=id;
				dureeMin=grapheChemin[idDebut][id];
			}
		}
		return idNoeudChoisi;
	}
	
	private int[] initEtatNoeuds()
	{
		int[] etatNoeuds=new int [nbSommets] ;
		for(int i=0; i<nbSommets; i++)
		{
			etatNoeuds[i]=BLANC;
		}
		return etatNoeuds;
	}
	
	private int[] initPredecesseurs()
	{
		int[] predecesseurs=new int [nbSommets] ;
		for(int i=0; i<nbSommets; i++)
		{
			predecesseurs[i]=-1;
		}
		return predecesseurs;
	}
	
	private void relacher(float[]d, Integer iNoeud, Integer jNoeud, int[] predecesseurs)
	{
		if(d[jNoeud]>d[iNoeud]+graphePlan[iNoeud][jNoeud])
		{
			d[jNoeud]=d[iNoeud]+graphePlan[iNoeud][jNoeud];
			predecesseurs[jNoeud]=iNoeud;
		}
	}
	// va certainement nettoyer graphe chemin en passant
	private Itineraire ConstruireItinerairesDePredecesseur(Integer debut, int[] predecesseurs, float[]d, FenetreLivraison fenetre, FenetreLivraison fenetreSuivante){
		Iterator<Livraison> it=fenetre.getLivraisonIterator();
		while(it.hasNext())
		{
			Livraison livraison=(Livraison)it.next();
			Itineraire itineraire=new Itineraire()
			int idNoeud=livraison.getAdresse().getId();
			{
				grapheChemin[debut][idNoeud]=d[idNoeud];
			}
		}
		
		return null;
	}
	
	private int calculerNbLivraisons(List<FenetreLivraison> listeFenetres){
		int nbLivraisons=0;
		for(FenetreLivraison fenetre:listeFenetres)
		{
			nbLivraisons+=fenetre.getNbLivraison();
		}
		return nbLivraisons;
	}
}
	
	