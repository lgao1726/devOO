package modele;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Itineraire {

	private Livraison livraisonOrigine;
	private Livraison livraisonDestination;
	private ArrayList<Integer> listeNoeud;
	float cout;

	public Itineraire(ArrayList<Integer> listeNoeud) {
		this.listeNoeud = listeNoeud;
		//this.livraisonOrigine = livraisonOrigine;
		//this.livraisonDestination = livraisonDestination;
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

	public ArrayList<Integer> getNoeuds() {
		return listeNoeud;
	}


	public void setListeNoeud(ArrayList<Integer> listeNoeud) {
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
	
	public void affichertIneraire()
	{
		System.out.print("Itinéraire entier de la tournée");
		for(Integer i:listeNoeud)
		{
			System.out.print(i+"|");
			
		}
		System.out.println("");

	}
	
	public void setCout(float cout){
		this.cout = cout + 10*60;
	}
	
	public float getCout(){
		return cout;
	}
	

}
