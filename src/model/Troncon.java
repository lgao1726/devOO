package model;

/**
 * Classe troncon qui décrit un lien entre deux noeud
 * @author interCorp
 *
 */
public class Troncon 
{
	private float vitesse;
	private float longueur;
	private String nomRue;
	
	private Noeud noeudOrigine;
	private Noeud noeudDestination;
	
	public Troncon(float vitesse, float longueur, String nomRue, Noeud noeudDestination) 
	{
		this.vitesse = vitesse;
		this.longueur = longueur;
		this.nomRue = nomRue;
		setNoeudDestination(noeudDestination);
	}

	public float getVitesse() {
		return vitesse;
	}

	public void setVitesse(float vitesse) {
		this.vitesse = vitesse;
	}

	public float getLongueur() {
		return longueur;
	}

	public void setLongueur(float longueur) {
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
	
