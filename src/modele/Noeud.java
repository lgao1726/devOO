package modele;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Classe noeud qui d�finir une intersection des tron�ons
 * 
 * @author interCorp
 *
 */
public class Noeud {
	private int id;
	private int x;
	private int y;

	private List<Troncon> listeTronconsSortants;

	/**
	 * 
	 * @param id
	 *            identifiant de noeud sur le plan
	 * @param x
	 *            coordonn�e X
	 * @param y
	 *            coordonn�e y
	 */
	public Noeud(int id, int x, int y) {
		this.id = id;
		this.x = x;
		this.y = y;
		listeTronconsSortants = new ArrayList<Troncon>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public List<Troncon> getListeTronconsSortants() {
		return listeTronconsSortants;
	}

	public void ajouterTroncon(Troncon troncon) {
		listeTronconsSortants.add(troncon);
	}
	
	public Troncon getToncon(int adresse){
		for(Troncon troncon:listeTronconsSortants){
			if(troncon.getIdNoeudDestination() == adresse) return troncon;
		}
		
		return null;
	}

}
