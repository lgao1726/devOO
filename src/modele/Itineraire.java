package modele;

import java.util.ArrayList;

public class Itineraire {

	//private Livraison livraisonOrigine;
	//private Livraison livraisonDestination;
	private ArrayList<Integer> noeudsItineraire;
	
	public Itineraire() {
		super();
		//this.livraisonOrigine = livraisonOrigine;
		//this.livraisonDestination = livraisonDestination;
		noeudsItineraire = new ArrayList<Integer>();
	}
	
	/**
	public Livraison getLivraisonOrigine() {
		return livraisonOrigine;
	}
	public void setLivraisonOrigine(Livraison livraisonOrigine) {
		this.livraisonOrigine = livraisonOrigine;
	}
	public Livraison getLivraisonDestination() {
		return livraisonDestination;
	}
	public void setLivraisonDestination(Livraison livraisonDestination) {
		this.livraisonDestination = livraisonDestination;
	}**/
	
	public void addNoeud(int id){
		noeudsItineraire.add(id);
	}
	
	public void accepte(Visiteur v){
		v.visite(this);
	}
	
	public ArrayList<Integer> getNoeuds(){
		return noeudsItineraire;
	}
	
	
}
