package model;

/**
 * Classe Livraison 
 * @author interCorp
 *
 */
public class Livraison 
{
	private int id;
	private Noeud adresse;
	private int client;
	private FentereLivraison fenetre;
	
	public Livraison(int id, Noeud adresse, int client, FentereLivraison fenetre) 
	{
		super();
		this.id = id;
		this.adresse = adresse;
		this.client = client;
		this.fenetre = fenetre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Noeud getAdresse() {
		return adresse;
	}

	public void setAdresse(Noeud adresse) {
		this.adresse = adresse;
	}

	public int getClient() {
		return client;
	}

	public void setClient(int client) {
		this.client = client;
	}

	public FentereLivraison getFenetre() {
		return fenetre;
	}

	public void setFenetre(FentereLivraison fenetre) {
		this.fenetre = fenetre;
	}
	
	
	
}
