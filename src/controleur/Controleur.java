package controleur;

import modele.Plan;
import modele.Tournee;
import vue.Fenetre;

public class Controleur 
{
	private Plan plan;	
	private static Tournee tournee;
	private Fenetre fenetre;
	private static Etat etatCourant;
	
	protected static final EtatInit etatInit = new EtatInit();
	protected static final EtatPlanCharge etatPlanCharge = new EtatPlanCharge();
	protected static final EtatLivraisonCharge etatLivraisonCharge = new EtatLivraisonCharge();

	public Controleur(Plan p) 
	{
		this.plan = p;
		//listeDeCdes = new ListeDeCdes();
		etatCourant = etatInit;
		this.fenetre = new Fenetre(p, this);
		tournee= new Tournee(plan);
	}
	
	protected static void setEtatCourant(Etat etat){
		etatCourant = etat;
	}
	
	public static void setTournee(Tournee t)
	{
		tournee=t;
	}
	
	public void chargerPlan() 
	{
		etatCourant.chargerPlan(plan, fenetre);
	}
	
	public void chargerLivraison()
	{
		etatCourant.chargerDemandes(plan, fenetre);
	}

	
	public void calculerTournee()
	{
		etatCourant.calculerTournee(plan, fenetre);
	}


}
