package modele;

import java.util.ArrayList;
import java.util.List;

public class Itineraire {

	private Livraison livraisonOrigine;
	private Livraison livraisonDestination;
	private ArrayList<Integer> noeudsItineraire;
	private List<Integer> listeNoeud;

	public Itineraire() {
		super();
		listeNoeud = new ArrayList<Integer>();
		// this.livraisonOrigine = livraisonOrigine;
		// this.livraisonDestination = livraisonDestination;
		noeudsItineraire = new ArrayList<Integer>();
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

	public List<Integer> getNoeuds() {
		return listeNoeud;
	}

	public ArrayList<Integer> getNoeudsItineraire() {
		return noeudsItineraire;
	}

	public void setNoeudsItineraire(ArrayList<Integer> noeudsItineraire) {
		this.noeudsItineraire = noeudsItineraire;
	}

	public List<Integer> getListeNoeud() {
		return listeNoeud;
	}

	public void setListeNoeud(List<Integer> listeNoeud) {
		this.listeNoeud = listeNoeud;
	}

	public void ajouterNoeud(int id) {
		listeNoeud.add(id);
	}

	public void ajouterNoeud(int index, int id) {
		listeNoeud.add(index, id);
	}

	public void accepte(Visiteur v) {
		v.visite(this);
	}

}
