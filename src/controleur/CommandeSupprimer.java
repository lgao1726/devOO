package controleur;

import java.util.Calendar;
import java.util.Date;

import modele.Livraison;
import modele.Plan;

public class CommandeSupprimer implements Commande {

	private Plan plan;
	private Livraison livraison;
	private Calendar heureDebut;
	private Calendar heureFin;
	
	public CommandeSupprimer(Plan plan,Livraison livraison,Calendar heureDebut, Calendar heureFin){
		this.plan = plan;
		this.livraison = livraison;
		this.heureDebut = heureDebut;
		this.heureFin = heureFin;
	}
	
	@Override
	public void executer() {
		plan.getDemandeLivraisons().supprimerLivraison(livraison.getAdresse().getId());
		
	}

	@Override
	public void undo() {
		plan.getDemandeLivraisons().ajouterLivraison(livraison, heureDebut, heureFin);
		
	}

}
