package modele;

import java.util.Calendar;
import java.util.Date;
import java.util.Observable;

/**
 * La classe Livraison contient les informations concernat une livraison
 * comme son adresse, heure de passage et plage horaire
 * @author InterCorp
 *
 */
public class Livraison 
{
	private int id;
	private Noeud adresse;
	private int client;
	private Calendar heurePassage;
	private Calendar heureDebut;
	private Calendar heureFin;
	
	public Livraison(int id, Noeud adresse, int client, Calendar heureDebut, Calendar heureFin) 
	{
		super();
		this.id = id;
		this.adresse = adresse;
		this.client = client;
		this.heureDebut=heureDebut;
		this.heureFin=heureFin;
	
	}
	
	/**
	 * Obtenir Id
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * Obtenir Noeud ou se trouve la livraison
	 * @return Noeud ou se trouve la livraison
	 */
	public Noeud getAdresse() {
		return adresse;
	}
	
	/**
	 * Obtenir heure de Debut de la plage horaire de la livraison 
	 * @return Calendar sur l'heure de debut
	 */
	public Calendar getHeureDebut() {
		return heureDebut;
	}
	
	/**
	 * Obtenir heure de fin de la plage horaire de la livraison
	 * @return Calendar sur l'heure de fin
	 */
	public Calendar getHeureFin() {
		return heureFin;
	}

	/**
	 * Obtenir le client auquel concerne la livraison
	 * @return int Id du client
	 */
	public int getClient() {
		return client;
	}
		
	/**
	 * 
	 * @param v
	 */
	public void accepte(Visiteur v)
	{
		v.visite(this);
	}
	
	/**
	 * Obtenir l'heure de passage de la livraison
	 * @return
	 */
	public Calendar getHeurePassage(){
		return heurePassage;
	}
	
	/**
	 * Attribuer une heure de passage a la livraison
	 * @param heure heure de passage de la livraison
	 */
	public void setHeurePassage(Calendar heure){
		heurePassage = heure;
	}
	
	/**
	 * Attribuer une heure de debut de la plage ou se trouve 
	 * la livraison
	 * @param heure heure de debut de la plage
	 */
	public void setHeureDebut(Calendar heure){
		heureDebut = heure;
	}
	
	/**
	 * Attribuer une heure de find de la plage ou se troube
	 * la livraison
	 * @param heure heure de fin de la plage
	 */
	public void setHeureFin(Calendar heure){
		heureFin = heure;
	}

	/**
	 * utilise lors de la creation d'une livraison,
	 * attribue un Id a la livraison
	 * @param idLivraison
	 */
	public void setId(int idLivraison) {
		id=idLivraison;
	}
	
	/**
	 * utilise lors de la creation d'une livraison
	 * attribue un Id client a la livraison
	 * @param idClient
	 */
	public void setIdClient(int idClient) {
		this.client=idClient;
	}
	
	
}