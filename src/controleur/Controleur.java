package controleur;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import modele.Livraison;
import modele.Noeud;
import modele.Plan;
import vue.Fenetre;

public class Controleur 
{
	private Plan plan;	
	private Fenetre fenetre;
	private static Etat etatCourant;
	private ListeCommandes listeDeCdes;

	
	protected static final EtatInit etatInit = new EtatInit();
	protected static final EtatPlanCharge etatPlanCharge = new EtatPlanCharge();
	protected static final EtatLivraisonCharge etatLivraisonCharge = new EtatLivraisonCharge();
	protected static final EtatTourneeCalculee etatTourneeCalculee = new EtatTourneeCalculee();
	protected static final EtatModeAjout etatModeAjout = new EtatModeAjout();
	protected static final EtatModeEchange etatModeEchange = new EtatModeEchange();
	protected static EtatModeSuppression EtatModeSuppresion = new EtatModeSuppression();
	protected static EtatNoeudSelectionne etatNoeudSelectionne=new EtatNoeudSelectionne();
	protected static EtatLivraisonSelectionneeEchange etatLivraisonSelectionneeEchange=new EtatLivraisonSelectionneeEchange();

	public Controleur(Plan p) 
	{
		this.plan = p;
		listeDeCdes = new ListeCommandes();
		etatCourant = etatInit;
		this.fenetre = new Fenetre(p, this);

	}
	
	protected static void setEtatCourant(Etat etat){
		etatCourant = etat;
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
		etatCourant.selectionnerLivraison(plan, livraison, listeDeCdes, fenetre);
	}
	
	public void supprimerLivraison(){
		etatCourant.supprimerLivraison(plan, fenetre);
	}
	
	public void echangerLivraison(){
		etatCourant.echangerLivraison(plan, listeDeCdes, fenetre);
		
	}
	
	public void sourisSurNoeud(){
		fenetre.sourisSurNoeud();
	}
	
	public void sourisPasSurNoeud(){
		fenetre.sourisPasSurNoeud();
	}
	
	public void undo(){
		etatCourant.undo(listeDeCdes);
	}

	public void redo() {
		etatCourant.redo(listeDeCdes);
	}

	public void selectionnerNoeud(Noeud noeud) {
		etatCourant.selectionnerNoeud(plan,noeud, fenetre);		
	}

	public void ajouterLivraison() {
		etatCourant.ajouterLivraison(plan, fenetre);
		
	}
	
	public static Etat getEtatCourant()
	{
		return etatCourant;
	}
	
	public void annuler() {
		etatCourant.annuler(fenetre);
		
	}

	public void valider() {
		etatCourant.valider(fenetre);
	}

	public void deselectionner() {
		etatCourant.deselectionner(fenetre);
	}
	
	public void genererFeuilleDeRoute()
	{
		etatCourant.genererFeuilleDeRoute(plan);
	}

	public Noeud getNoeud(int x, int y, int rayon) 
	{
		return plan.getNoeud(x, y, rayon);
	}

	public void quitter() 
	{
		if (fenetre.afficherMessageConfirmation("Voulez-vous vraiment quittez l'application ?"))
			
			System.exit(JFrame.EXIT_ON_CLOSE);
	}

	public void apropos() 
	{
		String about =  "Optimod'Lyon Application (SOLNON)\n\n" +
						"Groupe: H4101 International Corp\n\n" +
					    "Membres: \n" +
					    "AHAZAT Iliass\n" + 
					    "BARATTOLO Hugo\n" +
					    "BENEA Orlando\n" +
					    "GAO Lingfan\n" +
					    "GUEGAN Thomas\n" +
					    "LAHJOUJI Zineb\n" +
					    "LUNGENSTRASS Valentin\n\n" +
					    "Copyright © 2015 Tous les droits sont réservés";
				
		JOptionPane.showMessageDialog(null, about, "A propos", JOptionPane.INFORMATION_MESSAGE);
	}

}
