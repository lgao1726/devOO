/**
 * 
 */
package modele;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;

/**
 * Class plan qui décrit le plan de la ville
 * @author interCorp
 *
 */
public class Plan extends Observable
{
	// Liste des noeud
	private ArrayList<Noeud> intersections;
	// Adresse d'Entrepot
	private Noeud adresseEntrepot;
	// Demandes de livraison
	private DemandeLivraison demandeLivraisons;
	
	/**
	 * Constructor d'objet
	 */
	public Plan() 
	{
		this.intersections = new ArrayList<Noeud>();
	}
	
	public ArrayList<Noeud> getIntersections() {
		return intersections;
	}

	/**
	 * Méthode qui ajout la liste des intersection un noeud
	 * @param noeud
	 */
	public void ajouterNoeud(Noeud noeud)
	{
		intersections.add(noeud);
		
		setChanged();
		//notifyObservers(noeud);
	}
	
	/**
	 * @return L'adresse d'Entrepot
	 */
	public Noeud getAdresseEntrepot() {
		return adresseEntrepot;
	}
	
	/**
	 * @param adresseEntrepot affecter l'adresseEntrepot
	 */
	public void setAdresseEntrepot(Noeud adresseEntrepot) {
		this.adresseEntrepot = adresseEntrepot;
	}
	
	/**
	 * @param DemandeDeLivraison
	 */
	public void setDemandeLivraisons(DemandeLivraison demandes) 
	{
		this.demandeLivraisons = demandes;
		setChanged();
	}
	
	/**
	 * @param DemandeDeLivraison
	 */
	public DemandeLivraison getDemandeLivraisons() 
	{
		return this.demandeLivraisons;
	}
	
	/**
	 * Re-initialise le plan : supprime les noeud du plan courant et met a jour la taille
	 */
	public void reset() 
	{
		Iterator<Noeud> it = intersections.iterator();
		
		adresseEntrepot = null;
		demandeLivraisons = null;
		
		while (it.hasNext())
		{
			it.next();
			it.remove();
		}
		
		setChanged();
		notifyObservers();	
		
		// vidé la liste des noeuds
		UsineNoeud.initPointFactory(0);
	}
}
