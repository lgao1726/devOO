package vue;

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
			if (livraison != null){
				controleur.selectionnerLivraison(livraison); 
				//controleur.selectionnerNoeud(livraison.getAdresse());
			}
			else
			{
				Noeud noeud= getNoeud(evt);
				if(noeud != null)
				controleur.selectionnerNoeud(noeud);
			}
			break;
		case MouseEvent.BUTTON3: 
			controleur.annuler(); 
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
		MouseEvent e = SwingUtilities.convertMouseEvent(fenetre, evt, vueGraphique);
		int x = Math.round((float)e.getX()/(float)vueGraphique.getEchelle());
		int y = Math.round((float)e.getY()/(float)vueGraphique.getEchelle());
		return demandeLivraison.getLivraison(x, y, vueGraphique.getRayonLivraison());
	}

	private Noeud getNoeud(MouseEvent evt)
	{
		MouseEvent e = SwingUtilities.convertMouseEvent(fenetre, evt, vueGraphique);
		int x = Math.round((float)e.getX()/(float)vueGraphique.getEchelle());
		int y = Math.round((float)e.getY()/(float)vueGraphique.getEchelle());
		return vueGraphique.getPlan().getNoeud(x, y, vueGraphique.getRayonNoeud());
	}

}
