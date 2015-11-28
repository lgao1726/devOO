package controleur;

import javax.swing.plaf.synth.SynthSeparatorUI;

import modele.Livraison;
import modele.Plan;
import modele.Tournee;
import vue.Fenetre;

public class Controleur 
{
	private Plan plan;	
	private static Tournee tournee;
	private Fenetre fenetre;
	private static Etat etatCourant;
	private ListeCommandes listeDeCdes;

	
	protected static final EtatInit etatInit = new EtatInit();
	protected static final EtatPlanCharge etatPlanCharge = new EtatPlanCharge();
	protected static final EtatLivraisonCharge etatLivraisonCharge = new EtatLivraisonCharge();
	protected static final EtatTourneeCalculee etatTourneeCalculee = new EtatTourneeCalculee();
	protected static final EtatLivraisonSelectionnee etatLivraisonSelectionnee = new EtatLivraisonSelectionnee();


	public Controleur(Plan p) 
	{
		this.plan = p;
		listeDeCdes = new ListeCommandes();
		etatCourant = etatInit;
		this.fenetre = new Fenetre(p, this);
		tournee= new Tournee();
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
	
	public void selectionnerLivraison(Livraison livraison)
	{	
		etatCourant.selectionnerLivraison(livraison);
	}
	public void supprimerLivraison(){
		etatCourant.supprimerLivraison(plan, listeDeCdes);
	}
	
	public void echangerLivraison(){
		etatCourant.echangerLivraison(plan, listeDeCdes);
		
	}
	
	public void sourisSurNoeud(){
		fenetre.sourisSurNoeud();
	}
	
	public void sourisPasSurNoeud(){
		fenetre.sourisPasSurNoeud();
	}
	
	
	//pour voir si etat courant est selectionner
	private boolean isSelectionnerLivraison(){
		boolean res;
		if(etatCourant.equals(etatLivraisonSelectionnee)) return true;
		return false;
		
	}

}
