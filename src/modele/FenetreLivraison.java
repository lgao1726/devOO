package modele;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class FenetreLivraison 
{
	private Date heureDebut;
	private Date heureFin;
	private LinkedList<Livraison> listeLivraisons;
	
	public FenetreLivraison(Date heureDebut, Date heureFin) {
		super();
		this.heureDebut = heureDebut;
		this.heureFin = heureFin;
		listeLivraisons = new LinkedList<Livraison>();
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
	
	public void ajouterLivraison(Livraison livraison)
	{
		listeLivraisons.add(livraison);
	}
	
	public void supprimerLivraison(Livraison livraison)
	{
		listeLivraisons.remove(livraison);
	}
	
	public Iterator<Livraison> getLivraisonIterator()
	{
		return listeLivraisons.iterator();
	}
	
	public List getLivraisons(){
		return listeLivraisons;
	}
	
	public boolean appartient(Livraison livraison){
		for(Livraison liv:listeLivraisons){
			if(livraison.getAdresse().getId()==liv.getAdresse().getId()
					&& livraison.getId()==liv.getId()) return true;
		}
		return false;		
	}
	
	public int getNbLivraisons(){
		return listeLivraisons.size();
	}
	
}

	

