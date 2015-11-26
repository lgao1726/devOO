package controleur;

import modele.Noeud;
import modele.Plan;

public class CommandeAjouter implements Commande {
	private Plan plan;
	private Noeud noeud;
	private int client;
	private int adresseLivraisonAvant;
	private int id;
	
	public CommandeAjouter(Plan plan,Noeud noeud,int client,int adresseLivraisonAvant,int id){
		this.plan = plan;
		this.noeud = noeud;
		this.client = client;
		this.adresseLivraisonAvant = adresseLivraisonAvant;
		this.id = id;
	}
	
	public void executer(){
		plan.getDemandeLivraisons().getTournee().ajouterLivraison(id, noeud, client, adresseLivraisonAvant);
	}
	
	public void undo(){
		plan.getDemandeLivraisons().getTournee().supprimerLivraison(noeud.getId());
	}
	
}
