package model;

/**
 * Classe noeud qui d�finir une intersection des tron�ons
 * @author interCorp
 *
 */
public class Noeud 
{
	private int id;
	private int x;
	private int y;
	
	/**
	 * 
	 * @param id identifiant de neoud sur le plan
	 * @param x coordonn�e X
	 * @param y coordonn�e y
	 */
	public Noeud(int id, int x, int y) 
	{
		this.id = id;
		this.x = x;
		this.y = y;
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
	
}
