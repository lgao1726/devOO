package tsp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import modele.FenetreLivraison;
import modele.Livraison;
import modele.Noeud;
import modele.Plan;
import modele.Troncon;

public class GrapheLivraison implements Graphe {
	
	int nbSommets;
	float[][] graphePlan;
	float[][] grapheChemin;


	public GrapheLivraison(Plan p, ArrayList<FenetreLivraison> listeFenetres){
		int nbSommets=p.getNbIntersections();
		graphePlan = new float[nbSommets][nbSommets]; 
		grapheChemin = new float[nbSommets][nbSommets]; 
		for (int i=0; i<nbSommets; i++){
		    for (int j=0; j<nbSommets; j++){
		         grapheChemin[i][j] = -1;
		         graphePlan[i][j] = -1;
		    }
		}
		initGraphePlan(p);
	}

	@Override
	public int getNbSommets() {
		return nbSommets;
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
		return i != j;
	}
	
	private void initGraphePlan(Plan p)
	{
		ArrayList<Noeud> intersections=p.getIntersections();
		for (Iterator<Noeud> itNoeud=intersections.iterator(); itNoeud.hasNext();)
		{
			Noeud curNoeud=(Noeud)itNoeud.next();
			int idOrigine=curNoeud.getId();
			List troncons= curNoeud.getListeTronconsSortants();
			for (Iterator itTroncon=intersections.iterator(); itTroncon.hasNext();)
			{
				Troncon curTroncon=(Troncon)itTroncon.next();
				int idDestination=curTroncon.getIdNoeudDestination();
				float longueur=curTroncon.getLongueur();
				float vitesse=curTroncon.getVitesse();
				float duree= longueur/vitesse;
				graphePlan[idOrigine][idDestination]=duree;
				
				
			}
		}
	}
	