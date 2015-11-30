package vue;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;

import controleur.Controleur;
import modele.DemandeLivraison;
import modele.Livraison;
import modele.Noeud;
import modele.Plan;


public class EcouterDeMvtSouris extends MouseAdapter {

	private Controleur controleur;
	private VueGraphique vueGraphique;
	private Fenetre fenetre;

	public EcouterDeMvtSouris(Controleur controleur, VueGraphique vueGraphique, Fenetre fenetre){
		this.controleur = controleur;
		this.vueGraphique = vueGraphique;
		this.fenetre = fenetre;
	}

	public void mouseMoved(MouseEvent evt) {
		// Methode appelee a chaque fois que la souris est bougee
		// Envoie au controleur les coordonnees de la souris.
		Noeud courant = getPoint(evt);
		if (courant!=null){
			controleur.sourisSurNoeud(); 
		}else{
			controleur.sourisPasSurNoeud();
		}
	}
	
	private Noeud getPoint(MouseEvent evt){
		Plan plan = vueGraphique.getPlan();
		Point p  = evt.getPoint();
		
		return plan.getNoeud(p.x, p.y,vueGraphique.getRayonLivraison());
		
	}


}
