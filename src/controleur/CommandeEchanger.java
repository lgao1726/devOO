package controleur;

import modele.Plan;

public class CommandeEchanger implements Commande{
	private Plan plan;
	private int livraison1;
	private int livraison2;
	
	public CommandeEchanger(Plan plan,int livraison1,int livraison2){
		this.plan = plan;
		this.livraison1 = livraison1;
		this.livraison2 = livraison2;
	}
	
	@Override
	public void executer() {
		plan.getDemandeLivraisons().getTournee().echangerLivraison(livraison1, livraison2);
		
	}

	@Override
	public void undo() {
		plan.getDemandeLivraisons().getTournee().echangerLivraison(livraison2, livraison1);
		
	}

}
