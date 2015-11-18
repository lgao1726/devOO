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
	private int idNoeudDestination;


	public Troncon(float vitesse, float longueur, String nomRue, int idNoeudDestination) 
	{
		this.vitesse = vitesse;
		this.longueur = longueur;
		this.nomRue = nomRue;
		setIdNoeudDestination(idNoeudDestination);
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
	
	public int getIdNoeudDestination() {
		return idNoeudDestination;
	}

	public void setIdNoeudDestination(int idNoeudDestination) {
		this.idNoeudDestination = idNoeudDestination;
	}


}
	
