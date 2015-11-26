package controleur;

import java.sql.Date;

import modele.Livraison;
import modele.Noeud;
import modele.Plan;

public class CommandeAjouter implements Commande {
	private Plan plan;
	private Livraison livraison;
	private Date heureDebut;
	private Date heureFin;
	
	public CommandeAjouter(Plan plan,Livraison livraison, Date heureDebut, Date heureFin){
		this.plan = plan;
		this.livraison = livraison;
		this.heureDebut = heureDebut;
		this.heureFin = heureFin;
	}
	
	public void executer(){
		plan.getDemandeLivraisons().ajouterLivraison(livraison, heureDebut, heureFin);
	}
	
	public void undo(){
		plan.getDemandeLivraisons().supprimerLivraison(livraison.getAdresse().getId());
	}
	
}
