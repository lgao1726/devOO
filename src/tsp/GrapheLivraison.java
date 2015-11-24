package tsp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import modele.Noeud;
import modele.Plan;
import modele.Troncon;
import modele.Itineraire;
public class GrapheLivraison implements Graphe {
	
	int nbSommets;
	float[][] graphePlan;
	float[][] grapheChemin;
	Map<Integer, Itineraire> listItineraires;
	final int BLANC=0;
	final int GRIS=1;
	final int NOIR=2;
	final float DUREE_MAX=100000; //en heure pour l'instant


	public GrapheLivraison(Plan p){
		listItineraires=new HashMap<Integer, Itineraire>();
		nbSommets=p.getNbIntersections();
		graphePlan = new float[nbSommets][nbSommets]; 
		grapheChemin = new float[nbSommets][nbSommets]; 
		for (int i=0; i<nbSommets; i++){
		    for (int j=0; j<nbSommets; j++){
		         grapheChemin[i][j] = DUREE_MAX;
		         graphePlan[i][j] = -1;
		    }
		}
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
	
	
	public void dijkstra(Noeud noeudDebut)
	{
		//s0
		Integer idDebut=noeudDebut.getId();
		int[] couleurNoeud=initEtatNoeuds(); //pour chaque sommet,colorier si en blanc,
		int[] predecesseurs=initPredecesseurs(); //pour chaque sommet,		 faire pi[si]=-1; 

		List <Integer> gris=new ArrayList<Integer>();
		
		grapheChemin[idDebut][idDebut]=0; //d[si]=0
		couleurNoeud[idDebut]=GRIS;
		gris.add(idDebut);
		
		while(!gris.isEmpty())
		{
			Integer iNoeud=choisirSommetGris(gris, idDebut); //choisir si tel que d[si]minimal
			for(Integer jNoeud=0; jNoeud<nbSommets; jNoeud++)
			{
				if(graphePlan[iNoeud][jNoeud]!=-1 && (couleurNoeud[jNoeud]!=NOIR) && jNoeud!=iNoeud)
				{
					relacher(idDebut, iNoeud, jNoeud, predecesseurs);
					
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
	
	private void relacher(Integer idDebut, Integer iNoeud, Integer jNoeud, int[] predecesseurs)
	{
		if(grapheChemin[idDebut][jNoeud]>grapheChemin[idDebut][iNoeud]+graphePlan[iNoeud][jNoeud])
		{
			grapheChemin[idDebut][jNoeud]=grapheChemin[idDebut][iNoeud]+graphePlan[iNoeud][jNoeud];
			predecesseurs[jNoeud]=iNoeud;
		}
	}
	// va certainement nettoyer graphe chemin en passant
	//private Itineraire ConstruireItineraireDePredecesseur(int[] predecesseurs, Livraison){
		
	//}
}
	
	