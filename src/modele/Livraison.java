package modele;

import java.util.Calendar;
import java.util.Date;
import java.util.Observable;

/**
 * Classe livraison
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

	public int getId() {
		return id;
	}

	public Noeud getAdresse() {
		return adresse;
	}
	
	public Calendar getHeureDebut() {
		return heureDebut;
	}
	
	public Calendar getHeureFin() {
		return heureFin;
	}

	public int getClient() {
		return client;
	}
	
	
	public void accepte(Visiteur v)
	{
		v.visite(this);
	}
	
	public Calendar getHeurePassage(){
		return heurePassage;
	}
	
	public void setHeurePassage(Calendar heure){
		heurePassage = heure;
	}
	
	public void setHeureDebut(Calendar heure){
		heureDebut = heure;
	}
	
	public void setHeureFin(Calendar heure){
		heureFin = heure;
	}

	public void setId(int idLivraison) {
		id=idLivraison;
	}
	
	public void setIdClient(int idClient) {
		this.client=idClient;
	}
	
	
}