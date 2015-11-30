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
		this.updatePlan();
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
	
	public void updatePlan() 
	{
		setChanged();
		notifyObservers();
		
	}

	public Noeud getNoeud(int xPoint, int yPoint, int rayon) {
			for(Noeud noeud:intersections)
			{
				//System.out.println("Noeud " + noeud.getId() + ": " + noeud.getX() + "," + noeud.getY());
				if((xPoint>noeud.getX()-rayon) && (xPoint<noeud.getX()+rayon)  && (yPoint<noeud.getY()+rayon)  && (yPoint>noeud.getY()-rayon))
				{
					System.out.println(noeud.getId()+" idnoeud");
					return noeud;
				}
			}
			return null;
	}
	
}
