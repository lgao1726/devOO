package controleur;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import modele.Livraison;
import modele.Noeud;
import modele.Plan;
import vue.Fenetre;

/**
 * Classe controleur d'application
 * @author H4101 International Corp
 *
 */
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
	protected static EtatLivraisonSelectionnee etatLivraisonSelectionneeEchange=new EtatLivraisonSelectionnee();

	/**
	 * Contructeur d'objet
	 * @param Plan
	 */
	public Controleur(Plan p) 
	{
		this.plan = p;
		listeDeCdes = new ListeCommandes();
		etatCourant = etatInit;
		this.fenetre = new Fenetre(p, this);

	}
	
	/**
	 * Méthode qui change l'etat d'application
	 * @param etat
	 */
	protected static void setEtatCourant(Etat etat)
	{
		etatCourant = etat;
	}
	
	/**
	 * Méthode qui appel le service charger plan
	 */
	public void chargerPlan() 
	{
		etatCourant.chargerPlan(plan, fenetre);
	}
	
	/**
	 * Méthode qui appel le service charger livraison
	 */
	public void chargerLivraison()
	{
		etatCourant.chargerDemandes(plan, fenetre);
	}

	/**
	 * Méthode qui appel le service calculer trournée
	 */
	public void calculerTournee()
	{
		etatCourant.calculerTournee(plan, fenetre);
	}
	
	/**
	 * Méthode qui appel un service selectionner une livraison
	 * @param livraison
	 */
	public void selectionnerLivraison(Livraison livraison)
	{	
		etatCourant.selectionnerLivraison(plan, livraison, listeDeCdes, fenetre);
	}
	
	/**
	 * Méthode qui appel le service supprimer une livraison
	 */
	public void supprimerLivraison()
	{
		etatCourant.supprimerLivraison(plan, fenetre);
	}
	
	/**
	 * Méthode qui appel le service echanger une livraison
	 */
	public void echangerLivraison()
	{
		etatCourant.echangerLivraison(plan, listeDeCdes, fenetre);
		
	}
	
	/**
	 * Méthode qui appel le service de changement du curseur lorsque le pointeur est sur un noeud
	 */
	public void sourisSurNoeud()
	{
		fenetre.sourisSurNoeud();
	}
	
	/**
	 * Méthode qui appel un service pour changer le curseur 
	 */
	public void sourisPasSurNoeud()
	{
		fenetre.sourisPasSurNoeud();
	}
	
	/**
	 * Méthode qui appel le service undo
	 */
	public void undo(){
		etatCourant.undo(listeDeCdes);
	}

	/**
	 * Méthode qui appel le service redo
	 */
	public void redo() 
	{
		etatCourant.redo(listeDeCdes);
	}

	/**
	 * Méthode qui appel le service selectionner un noeud
	 * @param noeud
	 */
	public void selectionnerNoeud(Noeud noeud) 
	{
		etatCourant.selectionnerNoeud(plan,noeud, fenetre);		
	}

	/**
	 * Méthode qui appel le service ajouter une livraison
	 */
	public void ajouterLivraison() 
	{
		etatCourant.ajouterLivraison(plan, fenetre);
		
	}
	
	/**
	 * Méthode qui retourne l'état courante de l'application
	 * @return Etat
	 */
	public static Etat getEtatCourant()
	{
		return etatCourant;
	}
	
	/**
	 * Méthode qui appel le service annuler une action
	 */
	public void annuler() 
	{
		etatCourant.annuler(fenetre);
		
	}

	/**
	 * Méthode qui appel le service valider une action
	 */
	public void valider() 
	{
		etatCourant.valider(fenetre);
	}

	/**
	 * Méthode qui déselectionne une livraison
	 */
	public void deselectionner() 
	{
		etatCourant.deselectionner(fenetre);
	}
	
	/**
	 * Méthode qui génére une feuille de reoute
	 */
	public void genererFeuilleDeRoute()
	{
		etatCourant.genererFeuilleDeRoute(plan);
	}

	/**
	 * Getteur de Noeud
	 * @param x coordoner de clic
	 * @param y coordoner de clic
	 * @param rayon de détéctage de noeud sur le plan
	 * @return Noeud
	 */
	public Noeud getNoeud(int x, int y, int rayon) 
	{
		return plan.getNoeud(x, y, rayon);
	}

	/**
	 * Méthode qui quiite l'application
	 */
	public void quitter() 
	{
		if (fenetre.afficherMessageConfirmation("Voulez-vous vraiment quittez l'application ?"))
			
			System.exit(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * Méthode qui affiche about
	 */
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
					    "Copyright Â© 2015 Tous les droits sont rÃ©servÃ©s";
				
		JOptionPane.showMessageDialog(null, about, "A propos", JOptionPane.INFORMATION_MESSAGE);
	}
}
