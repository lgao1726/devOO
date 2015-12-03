/**
 * 
 */
package modele;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;

/**
 * Class plan qui décrit le plan de la ville. Il est compose de Noeuds et Troncons
 * @author interCorp
 *
 */
public class Plan extends Observable
{
	// La dimension X
	private int dimX;
	// La dimension Y
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
		
		UsineNoeud.initPointFactory(dimY*dimX);
		
		this.intersections = new ArrayList<Noeud>();
	}

	/**
	 * Obtenir la dimension X du plan
	 * @return dimension X du plan
	 */
	public int getDimX() {
		return dimX;
	}

	/**
	 * Attribuer une nouvelle dimension X au plan
	 * @param dimX la nouvelle dimension X
	 */
	public void setDimX(int dimX) {
		this.dimX = dimX;
	}

	/**
	 * Obtenir la dimension Y du plan
	 * @return Dimension Y du plan
	 */
	public int getDimY() {
		return dimY;
	}

	/**
	 * Attribuer une nouvelle dimension Y au plan
	 * @param dimY la nouvelle dimension du plan
	 */
	public void setDimY(int dimY) {
		this.dimY = dimY;
	}
	
	/**
	 * Obtenir un List de tous les noeuds sur le plan ordonnes par leurs adresses
	 * @return List de noeuds ordonnees par leurs adresses
	 */
	public ArrayList<Noeud> getIntersections() {
		return intersections;
	}
	
	/**
	 * Obtenir le nombre d'intersections/de noeuds sur le plan
	 * @return nombre d'intersections/de noeuds
	 */
	public int getNbIntersections(){
		return intersections.size();
	}
	/**
	 * Ajouter un noeud au plan
	 * @param noeud
	 */
	public void ajouterNoeud(Noeud noeud)
	{
		intersections.add(noeud);
		setChanged();
	}
	
	/**
	 * Obtenir un Noeud avec un son Id
	 * @param id
	 * @return Id du noeud
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
	 * Obtenir adresse/Id du noeud ou se trouve l'entrepot
	 * @return L'adresse d'Entrepot
	 */
	public Noeud getAdresseEntrepot() {
		return adresseEntrepot;
	}
	
	/**
	 * Affecter un noeud au plan ou se trouve l'entrepot
	 * @param adresseEntrepot affecter l'adresseEntrepot
	 */
	public void setAdresseEntrepot(Noeud adresseEntrepot) {
		this.adresseEntrepot = adresseEntrepot;
	}
	
	/**
	 * Attribue une demande de livraison au plan
	 * @param DemandeDeLivraison
	 */
	public void setDemandeLivraisons(DemandeLivraison demandes) 
	{
		this.demandeLivraisons = demandes;
	}

	
	/**
	 * Obtenir la demande de livraison du plan
	 * @param DemandeDeLivraison
	 */
	public DemandeLivraison getDemandeLivraisons() 
	{
		return this.demandeLivraisons;
	}
	
	/**
	 * Re-initialise le plan : supprime les formes du plan courant et met a jour la taille
	 */
	public void reset() 
	{
		Iterator<Noeud> it = intersections.iterator();
		
		demandeLivraisons = null;
		adresseEntrepot = null;
		
		while (it.hasNext())
		{
			it.next();
			it.remove();
		}
		
		intersections.clear();
		
		setChanged();
		notifyObservers();	
		
		// vidé la liste des noeuds
		UsineNoeud.initPointFactory(0);
	}
	
	/**
	 * Declenche les modifications du plan dans la vue
	 */
	public void updatePlan() 
	{
		setChanged();
		notifyObservers();
		
	}

	/**
	 * Obtenir un Noeud avec les coordonnes ou l'utilisateur a clique dans la vue
	 * @param xPoint
	 * @param yPoint
	 * @param rayon marge d'erreur du clic
	 * @return
	 */
	public Noeud getNoeud(int xPoint, int yPoint, int rayon) {
			for(Noeud noeud:intersections)
			{
				if((xPoint>noeud.getX()-rayon) && (xPoint<noeud.getX()+rayon)  && (yPoint<noeud.getY()+rayon)  && (yPoint>noeud.getY()-rayon))
				{
					System.out.println(noeud.getId()+" idnoeud");
					return noeud;
				}
			}
			return null;
	}
	
}
