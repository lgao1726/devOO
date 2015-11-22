package tsp;

import modele.Plan;

public class GrapheLivraison implements Graphe {
	
	private int[][] graphe;
	private int[][] grapheComplet;
	int nbSommets;
	
	public GrapheLivraison(){
		
	}

	@Override
	public int getNbSommets() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getCout(int i, int j) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean estArc(int i, int j) {
		// TODO Auto-generated method stub
		return false;
	}

}
