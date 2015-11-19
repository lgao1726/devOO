package controleur;

import modele.Plan;
import vue.Fenetre;

public class Controleur {
	
	Plan plan;	
	private Fenetre fenetre;
	private static Etat etatCourant;
	
	protected static final EtatInit etatInit = new EtatInit();
	protected static final EtatPlanCharge etatPlanCharge = new EtatPlanCharge();
	
	public Controleur(Plan p, int e) {
		this.plan = p;
		//listeDeCdes = new ListeDeCdes();
		etatCourant = etatInit;
		fenetre = new Fenetre(p, e, this);
	}
	
	protected static void setEtatCourant(Etat etat){
		etatCourant = etat;
	}
	
	public void chargerPlan()
	{
		etatCourant.chargerPlan(plan, fenetre);
	}

}
