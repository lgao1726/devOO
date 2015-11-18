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
	private ArrayList<Troncon> troncons;
	
	/**
	 * Constructor d'objet
	 * @param Dimession d'axe X
	 * @param Dimession d'axe Y
	 */
	public Plan(int dimX, int dimY) 
	{
		this.dimX = dimX;
		this.dimY = dimY;
		
		this.troncons = new ArrayList<Troncon>();
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
	
	public ArrayList<Troncon> getTroncons() {
		return troncons;
	}

	public void setTroncons(ArrayList<Troncon> troncons) {
		this.troncons = troncons;
	}

	/**
	 * Méthode qui ajout la liste des intersection un noeud
	 * @param noeud
	 */
	public void addNoeud(Troncon troncon)
	{
		troncons.add(troncon);
	}
}
