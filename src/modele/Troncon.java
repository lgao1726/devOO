package modele;

/**
 * Classe troncon qui decrit un lien entre deux noeuds
 * @author H4101
 *
 */
public class Troncon 
{
	private float vitesse;
	private float longueur;
	private String nomRue;
	
	private int idNoeudDestination;


	public Troncon(float vitesse, float longueur, String nomRue, int idNoeudDestination) 
	{
		this.vitesse = vitesse;
		this.longueur = longueur;
		this.nomRue = nomRue;
		this.idNoeudDestination = idNoeudDestination;
	}

	/**
	 * Obtenir la vitesse moyenne du troncon
	 * @return vitesse moyenne du troncon
	 */
	public float getVitesse() {
		return vitesse;
	}

	/**
	 * Obtenir le longeur du troncon
	 * @return longeur du troncon
	 */
	public float getLongueur() {
		return longueur;
	}


	/**
	 * Obtenir le nom d'un troncon sous form de String
	 * @return Nom du troncon
	 */
	public String getNomRue() {
		return nomRue;
	}



	/**
	 * Obtenir l'adresse du noeud auquel cet troncon amene
	 * @return
	 */
	public int getIdNoeudDestination() {
		return idNoeudDestination;
	}



}
	
