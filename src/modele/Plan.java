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
	// La dimenssion X
	private int dimX;
	// La dimession Y
	private int dimY;
	// Liste des noeud
	private ArrayList<Noeud> intersections;
	// Adresse d'Entrepot
	private Noeud adresseEntrepot;
	// Demandes de livraison
	private DemandeLivraison demandeLivraisons;
	
	public Plan()
	{
		this.intersections = new ArrayList<Noeud>();
	}
	
	/**
	 * Constructor d'objet
	 * @param Dimession d'axe X
	 * @param Dimession d'axe Y
	 */
	public Plan(int dimX, int dimY) 
	{
		this.dimX = dimX;
		this.dimY = dimY;
		
		this.intersections = new ArrayList<Noeud>();
	}

	public int getDimX() {
		return dimX;
	}

	public void setDimX(int dimX) {
		this.dimX = dimX;
	}

	public int getDimY() {
		return dimY;
	}

	public void setDimY(int dimY) {
		this.dimY = dimY;
	}
	
	public ArrayList<Noeud> getIntersections() {
		return intersections;
	}
	
	public int getNbIntersections(){
		return intersections.size();
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
	 * 
	 * @param id
	 * @return
	 */
	public Noeud getNoeud(int id) {
		for(Noeud n:intersections){
			if(n.getId() == id){
				return n;
			}
			
		}
		return intersections.get(id);
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
	 * Re-initialise le plan : supprime les formes du plan courant et met a jour la taille
	 * @param largeur
	 * @param hauteur
	 */
	public void reset(int taille) 
	{
		Iterator<Noeud> it = intersections.iterator();
		
		demandeLivraisons = null;
		adresseEntrepot = null;
		
		while (it.hasNext())
		{
			it.next();
			it.remove();
		}
		
		setChanged();
		notifyObservers();	
		
		// vidé la liste des noeuds
		UsineNoeud.initPointFactory(taille);
	}
	
	public void setTournee() 
	{
		setChanged();
		notifyObservers();
		
	}
	
}
