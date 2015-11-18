/**
 * 
 */
package model;

import java.util.ArrayList;

/**
 * Class plan qui décrit le plan de la ville
 * @author interCorp
 *
 */
public class Plan 
{
	// La dimenssion X
	private int dimX;
	// La dimession Y
	private int dimY;
	// Liste des noeud
	private ArrayList<Noeud> intersection;
	
	/**
	 * Constructor d'objet
	 * @param Dimession d'axe X
	 * @param Dimession d'axe Y
	 */
	public Plan(int dimX, int dimY) 
	{
		this.dimX = dimX;
		this.dimY = dimY;
		
		this.intersection = new ArrayList<Noeud>();
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
	
	public ArrayList<Noeud> getTroncons() {
		return intersection;
	}

	public void setTroncons(ArrayList<Noeud> noeuds) {
		this.intersection = noeuds;
	}

	/**
	 * Méthode qui ajout la liste des intersection un noeud
	 * @param noeud
	 */
	public void ajoutNoeud(Noeud noeud)
	{
		intersection.add(noeud);
	}
}
