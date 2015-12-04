package vue;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import controleur.Controleur;
import modele.DemandeLivraison;
import modele.Livraison;
import modele.Noeud;

/**
 * Classe EcouteurDeSouris qui attend un évenement se génére sur les bouttons de la souris
 * @author H4101 International Corp
 *
 */
public class EcouteurDeSouris extends MouseAdapter 
{
	private Controleur controleur;
	private VueGraphique vueGraphique;

	/**
	 * Constructeur d'objet
	 * @param controleur
	 * @param vueGraphique
	 */
	public EcouteurDeSouris(Controleur controleur, VueGraphique vueGraphique)
	{
		this.controleur = controleur;
		this.vueGraphique = vueGraphique;
	}

	@Override
	public void mouseClicked(MouseEvent evt) 
	{	
		// Methode appelee par l'ecouteur de souris a chaque fois que la souris est cliquee

		switch (evt.getButton())
		{
			// Bouton gauche cliqué
			case MouseEvent.BUTTON1: 
			{
				Livraison livraison = getLivraison(evt);
				Noeud noeud = vueGraphique.getPlan().getNoeud(evt.getPoint().x * vueGraphique.getEchelle(), 
															  evt.getPoint().y * vueGraphique.getEchelle(),
															  vueGraphique.getRayonNoeud());
				
				if (livraison != null)
					controleur.selectionnerLivraison(livraison);
				else if(noeud != null)
					controleur.selectionnerNoeud(noeud);
				else
					controleur.deselectionner();
			}
			break;
			default:
		}
	}
	
	/**
	 * Getteur de Livraison en fonction des coordonnées de la souris
	 * @param evt
	 * @return Livraison une livraison
	 */
	private Livraison getLivraison(MouseEvent evt)
	{
		DemandeLivraison demandeLivraison=vueGraphique.getPlan().getDemandeLivraisons();
		
		if (demandeLivraison != null)
		{
			demandeLivraison.getLivraison(evt.getPoint().x * vueGraphique.getEchelle(), 
									  evt.getPoint().y * vueGraphique.getEchelle(), 
									  vueGraphique.getRayonLivraison());		
		
			return demandeLivraison.getLivraison(evt.getPoint().x * vueGraphique.getEchelle(), 
											 evt.getPoint().y * vueGraphique.getEchelle(), 
											 vueGraphique.getRayonLivraison());		
		}
		else
			
			return null;
	}
}
