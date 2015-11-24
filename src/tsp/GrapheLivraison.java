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
	Map<Integer, Itineraire> listItinéraires;
	final int BLANC=0;
	final int GRIS=1;
	final int NOIR=2;
	final float DUREE_MAX=100000; //en heure pour l'instant


	public GrapheLivraison(Plan p){
		listItinéraires=new HashMap<Integer, Itineraire>();
		nbSommets=p.getNbIntersections();
		graphePlan = new float[nbSommets][nbSommets]; 
		grapheChemin = new float[nbSommets][nbSommets]; 
		for (int i=0; i<nbSommets; i++){
		    for (int j=0; j<nbSommets; j++){
		         grapheChemin[i][j] = -1;
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
				grapheChemin[idOrigine][idDestination]=DUREE_MAX;
				
			}
		}
	}
	public void dijkstra(Noeud noeudDebut)
	{
		Integer idDebut=noeudDebut.getId();
		System.out.println("id debut: "+ idDebut);
		grapheChemin[idDebut][idDebut]=0;
		int[] etatNoeuds=initEtatNoeuds();
		int[] predecesseurs=initPredecesseurs();
		etatNoeuds[idDebut]=GRIS;
		List <Integer> gris=new ArrayList<Integer>();
		gris.add(idDebut);
		while(!gris.isEmpty())
		{
			Integer idNoeudCourant=choisirSommetGris(gris, idDebut);
			System.out.println("si :"+idNoeudCourant);
			for(Integer i=0; i<nbSommets; i++)
			{
				if(graphePlan[idNoeudCourant][i]!=-1 && (etatNoeuds[i]!=NOIR) && i!=idNoeudCourant)
				{
					System.out.println("sj: "+i);
					if(grapheChemin[idDebut][i]>grapheChemin[idDebut][idNoeudCourant]+graphePlan[idNoeudCourant][i])
					{
						grapheChemin[idDebut][i]=grapheChemin[idDebut][idNoeudCourant]+graphePlan[idNoeudCourant][i];
						predecesseurs[i]=idNoeudCourant;
					}
					if(etatNoeuds[i]==BLANC)
					{
						etatNoeuds[i]=GRIS;
						gris.add(i);						
					}
					
				}
			}
			etatNoeuds[idNoeudCourant]=NOIR;
			gris.remove(idNoeudCourant);
			for(Integer it:gris)
			{
				System.out.print("|"+it);
			}
			System.out.println("|");
			
		}
		System.out.println("predecesseurs");

		for(int j=0; j<nbSommets; j++)
		{
			System.out.print("|"+predecesseurs[j]);
		}
		System.out.println("etat noeud");

		for(int j=0; j<nbSommets; j++)
		{
			System.out.print("|"+etatNoeuds[j]);
		}
		System.out.println("|");
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
}
	
	