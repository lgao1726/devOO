package vue;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;

import controleur.Controleur;
import modele.DemandeLivraison;
import modele.Livraison;
import modele.Noeud;


public class EcouteurDeSouris extends MouseAdapter {

	private Controleur controleur;
	private VueGraphique vueGraphique;
	private Fenetre fenetre;

	public EcouteurDeSouris(Controleur controleur, VueGraphique vueGraphique, Fenetre fenetre){
		this.controleur = controleur;
		this.vueGraphique = vueGraphique;
		this.fenetre = fenetre;
	}

	@Override
	public void mouseClicked(MouseEvent evt) {
		
		// Methode appelee par l'ecouteur de souris a chaque fois que la souris est cliquee
		// S'il s'agit d'un clic gauche dans la vue graphique, l'ecouteur envoie au controleur les coordonnees du point clique.
		// S'il s'agit d'un clic droit, l'ecouteur envoie le message d'echappement au controleur
		switch (evt.getButton()){
		case MouseEvent.BUTTON1: 
			Livraison livraison = getLivraison(evt);
			Noeud noeud= getNoeud(evt);
			
			if (livraison != null)
			{
				controleur.selectionnerLivraison(livraison); 
				vueGraphique.selectionnerLivraison(livraison, Color.CYAN);
			}
			else if(noeud != null)
			{
				controleur.selectionnerNoeud(noeud);
				//vueGraphique.selectionnerNoeud(noeud, Color.CYAN);
			}
			else
			{
				Noeud noeudDeselectionne=controleur.deselectionner();
				//vueGraphique.deselectionnerLivraison(noeudDeselectionne);
			}
			break;
		case MouseEvent.BUTTON3: 
			//controleur.clicDroit(); 
			break;
		default:
		}
	}

	/**public void mouseMoved(MouseEvent evt) {
		// Methode appelee a chaque fois que la souris est bougee
		// Envoie au controleur les coordonnees de la souris.
		Point p = coordonnees(evt);
		if (p != null)
			controleur.sourisBougee(p); 
	}**/
	
	private Livraison getLivraison(MouseEvent evt){
		DemandeLivraison demandeLivraison=vueGraphique.getPlan().getDemandeLivraisons();
		
		java.awt.Point Pt = evt.getPoint();	 
		
		 demandeLivraison.getLivraison(Pt.x, Pt.y, vueGraphique.getRayonLivraison());		
		
		return demandeLivraison.getLivraison(Pt.x, Pt.y, vueGraphique.getRayonLivraison());		
	}

	private Noeud getNoeud(MouseEvent evt)
	{
		java.awt.Point Pt = evt.getPoint();	 
		return vueGraphique.getPlan().getNoeud( Pt.x, Pt.y, vueGraphique.getRayonNoeud());
	}

}
