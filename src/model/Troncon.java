package model;

/**
 * Classe troncon qui décrit un lien entre deux noeud
 * @author interCorp
 *
 */
public class Troncon 
{
	private int vitesse;
	private int longueur;
	private String nomRue;
	
	private Noeud noeudOrigine;
	private Noeud noeudDestination;
	
	public Troncon(int vitesse, int longueur, String nomRue) 
	{
		this.vitesse = vitesse;
		this.longueur = longueur;
		this.nomRue = nomRue;
	}

	public int getVitesse() {
		return vitesse;
	}

	public void setVitesse(int vitesse) {
		this.vitesse = vitesse;
	}

	public int getLongueur() {
		return longueur;
	}

	public void setLongueur(int longueur) {
		this.longueur = longueur;
	}

	public String getNomRue() {
		return nomRue;
	}

	public void setNomRue(String nomRue) {
		this.nomRue = nomRue;
	}

	public Noeud getNoeudOrigine() {
		return noeudOrigine;
	}

	public void setNoeudOrigine(Noeud noeudOrigine) {
		this.noeudOrigine = noeudOrigine;
	}

	public Noeud getNoeudDestination() {
		return noeudDestination;
	}

	public void setNoeudDestination(Noeud noeudDestination) {
		this.noeudDestination = noeudDestination;
	}
}
	
