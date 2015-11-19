package modele;
/**package model;

import java.util.Date;

public class FenetreLivraison {
	private Date heureDebut;
	private Date heureFin;
	
	public FenetreLivraison(Date heureDebut, Date heureFin) {
		super();
		this.heureDebut = heureDebut;
		this.heureFin = heureFin;
	}

	public Date getHeureDebut() {
		return heureDebut;
	}

	public void setHeureDebut(Date heureDebut) {
		this.heureDebut = heureDebut;
	}

	public Date getHeureFin() {
		return heureFin;
	}

	public void setHeureFin(Date heureFin) {
		this.heureFin = heureFin;
	}
	
	public Livraison getLivraison(int idLivraison){
		
	}
	
	public void echangerLivraison(int idLivraison1, int idLivraison2){
		
	}
	
	public void supprimerLivraison(int idLivraison){
		
	}
	
	public void ajouterLivraison(Livraison livraison){
		
	}

	
}

**/
/**
package model;

import java.util.ArrayList;
import java.util.Date;

public class FenetreLivraison {
	private Date heureDebut;
	private Date heureFin;
	
	private ArrayList<Livraison> listeLivraisons;
	
	public FenetreLivraison(Date heureDebut, Date heureFin) {
		super();
		this.heureDebut = heureDebut;
		this.heureFin = heureFin;
	}

	public Date getHeureDebut() {
		return heureDebut;
	}

	public void setHeureDebut(Date heureDebut) {
		this.heureDebut = heureDebut;
	}

	public Date getHeureFin() {
		return heureFin;
	}

	public void setHeureFin(Date heureFin) {
		this.heureFin = heureFin;
	}
	
	public ArrayList<Livraison> getListeLivraisons() {
		return listeLivraisons;
	}

	public void setListeLivraisons(ArrayList<Livraison> listeLivraisons) {
		this.listeLivraisons = listeLivraisons;
	}

	
	public Livraison getLivraison(int idLivraison){
		Livraison livraison = null;
		for(Livraison l: listeLivraisons){
			if(l.getId() == idLivraison){
				livraison = l;
			}
		}
		return livraison;
	}
	
	public void echangerLivraison(int idLivraison1, int idLivraison2){
		
	}
	
	public void supprimerLivraison(int idLivraison){
		listeLivraisons.remove(idLivraison);
	}
	
	public void ajouterLivraison(Livraison livraison){
		listeLivraisons.add(livraison);
	}

	
}**/

