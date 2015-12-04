package modele;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * La classe FenetreLivraison contient un List de livraisons
 * qui partagent une plage horaire avec une heure de debut
 * et une heure de fin
 * @author H4101
 * 
 */
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

	/**
	 * Obtenir l'heure de debut de cette fenetre
	 * @return objet Calendar sur l'heure de debut
	 */
	public Calendar getHeureDebut() {
		return heureDebut;
	}

	/**
	 * Obtenir l'heure de fin de cette fenetre
	 * @return objet Calendar sur l'heure de fin
	 */
	public Calendar getHeureFin() {
		return heureFin;
	}

	/**
	 * Ajouter un nouvel livraison dans cette fenetre
	 * @param livraison
	 */
	public void ajouterLivraison(Livraison livraison)
	{
		listeLivraisons.add(livraison);
	}
	
	/**
	 * Supprimer un livraison dans cette fenetre
	 * @param livraison
	 */
	public void supprimerLivraison(Livraison livraison)
	{
		listeLivraisons.remove(livraison);
	}
	
	/**
	 * Obtenir un iterator pour parcourir les livraisons de cette fenetre
	 * @return
	 */
	public Iterator<Livraison> getLivraisonIterator()
	{
		return listeLivraisons.iterator();
	}
	
	/**
	 * Obtenir un List de livraisons de cette fenetre
	 * @return
	 */
	public List<Livraison> getLivraisons(){
		return listeLivraisons;
	}
	
	/**
	 * Verifie si le livraison donnee appartient a cette fenetre
	 * @param livraison
	 * @return
	 */
	public boolean appartient(Livraison livraison){
		for(Livraison liv:listeLivraisons){
			if(livraison.getAdresse().getId()==liv.getAdresse().getId()
					&& livraison.getId()==liv.getId()) return true;
		}
		return false;		
	}
	
	/**
	 * Obtenir le nombre de livraisons qui appartiennet a cette fenetre 
	 * sous forme d'int
	 * @return
	 */
	public int getNbLivraisons(){
		return listeLivraisons.size();
	}
	
}