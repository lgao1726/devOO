package controleur;

import java.util.Calendar;
import java.util.Date;

import modele.Livraison;
import modele.Noeud;
import modele.Plan;

public class CommandeAjouter implements Commande {
	private Plan plan;
	private Livraison livraison;
	private Calendar heureDebut;
	private Calendar heureFin;
	
	public CommandeAjouter(Plan plan,Livraison livraison, Calendar heureDebut, Calendar heureFin){
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
