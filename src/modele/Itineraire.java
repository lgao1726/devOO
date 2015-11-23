package modele;

import java.util.ArrayList;
import java.util.List;

public class Itineraire {

	private Livraison livraisonOrigine;
	private Livraison livraisonDestination;
	private List <Integer> listeNoeud;
	
	
	public Itineraire(Livraison livraisonOrigine, Livraison livraisonDestination) {
		super();
		listeNoeud= new ArrayList<Integer>();
		this.livraisonOrigine = livraisonOrigine;
		this.livraisonDestination = livraisonDestination;
	}
	
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
	}
	public void ajouterNoeud(int id ) {
		listeNoeud.add(id);
	}
	public void ajouterNoeud(int index, int id) {
		listeNoeud.add(index, id);
	}
	
	
}
