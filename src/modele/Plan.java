/**
 * 
 */
package modele;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import vue.Fenetre;
import vue.VueGraphique;

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

	public void setIntersections(ArrayList<Noeud> noeuds) {
		this.intersections = noeuds;
	}

	/**
	 * Méthode qui ajout la liste des intersection un noeud
	 * @param noeud
	 */
	public void ajouterNoeud(Noeud noeud)
	{
		intersections.add(noeud);
	}

}
