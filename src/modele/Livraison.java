package modele;

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
	
	public Livraison(int id, Noeud adresse, int client) 
	{
		super();
		this.id = id;
		this.adresse = adresse;
		this.client = client;
	
	}

	public int getId() {
		return id;
	}

	public Noeud getAdresse() {
		return adresse;
	}

	public int getClient() {
		return client;
	}
	
	
	public void accepte(Visiteur v)
	{
		v.visite(this);
	}
}
