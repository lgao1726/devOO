package modele;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Classe noeud qui defini une intersection de troncons
 * Compose d'un Id qui est l'adresse de l'intersection et 
 * les coordonnees cartesinnes ou se trouve ce noeud sur le plan
 * @author H4101
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
	 *            coordonnee X
	 * @param y
	 *            coordonnee y
	 */
	public Noeud(int id, int x, int y) {
		this.id = id;
		this.x = x;
		this.y = y;
		listeTronconsSortants = new ArrayList<Troncon>();
	}

	/**
	 * Obtenir Id du noeud
	 * @return Id du noeud
	 */
	public int getId() {
		return id;
	}

	/**
	 * Obtenir la coordonee X du noeud
	 * @return coordonnee X du noeud
	 */
	public int getX() {
		return x;
	}

	/**
	 * Obtenir la coordonnee Y du noeud
	 * @return coordonnee Y du noeud
	 */
	public int getY() {
		return y;
	}

	/**
	 * Obtenir un List de Troncons du noeud
	 * @return Troncons qui sortent de l'intersection
	 */
	public List<Troncon> getListeTronconsSortants() {
		return listeTronconsSortants;
	}

	/**
	 * Ajouter un Troncon au noeud
	 * @param troncon
	 */
	public void ajouterTroncon(Troncon troncon) {
		listeTronconsSortants.add(troncon);
	}
	
	/**
	 * Obtenir le Troncon qui amene a un autre noeud dont son adresse est donnee
	 * @param adresse du noeud
	 * @return Tronc qui amene a ce noeud
	 */
	public Troncon getToncon(int adresse){
		for(Troncon troncon:listeTronconsSortants){
			if(troncon.getIdNoeudDestination() == adresse) return troncon;
		}
		
		return null;
	}

}
