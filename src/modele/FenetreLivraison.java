package modele;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class FenetreLivraison 
{
	private Calendar heureDebut;
	private Calendar heureFin;
	private LinkedList<Livraison> listeLivraisons;
	
	public FenetreLivraison(Calendar heureDebut, Calendar heureFin) {
		super();
		this.heureDebut = heureDebut;
		this.heureFin = heureFin;
		listeLivraisons = new LinkedList<Livraison>();
	}

	public Calendar getHeureDebut() {
		return heureDebut;
	}

	/**public void setHeureDebut(Calendar heureDebut) {
		this.heureDebut = heureDebut;
	}**/

	public Calendar getHeureFin() {
		return heureFin;
	}

	/**public void setHeureFin(Calendar heureFin) {
		this.heureFin = heureFin;
	}**/
	
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
	
	public List<Livraison> getLivraisons(){
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