package vue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import modele.DemandeLivraison;
import modele.FenetreLivraison;
import modele.Itineraire;
import modele.Livraison;
import modele.Noeud;
import modele.Plan;
import modele.Tournee;
import modele.Visiteur;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;


public class VueGraphique extends JPanel implements Observer, Visiteur {

	private int echelle;
	private int hauteurVue;
	private int largeurVue;
	private Plan plan;
	private Graphics g;

	/**
	 * Cree la vue graphique permettant de dessiner plan avec l'echelle e dans la fenetre f
	 * @param plan
	 * @param e l'echelle
	 * @param f la fenetre
	 */
	public VueGraphique(Plan plan, int e, Fenetre f) {
		super();
		plan.addObserver(this); // this observe plan
		
		this.echelle = e;
		
		hauteurVue = plan.getDimY()*e;
		largeurVue = plan.getDimX()*e;
		
		setLayout(null);
		setBackground(Color.white);
		setSize(largeurVue, hauteurVue);
		f.getContentPane().add(this);
		this.plan = plan;
	}
	
	/**
	 * Methode appelee a chaque fois que VueGraphique doit etre redessinee
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
		RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		
		g2.setColor(Color.lightGray);
		for (int y=0; y<largeurVue/echelle; y+=20)
			g.drawLine(y*echelle, 0, y*echelle, hauteurVue);
		for (int x=0; x<hauteurVue/echelle; x+=20)
			g.drawLine(0, x*echelle, largeurVue, x*echelle);
		g2.setColor(Color.gray);
		g2.drawRect(0, 0, largeurVue, hauteurVue);
		this.g = g;
		
		
		for(int i = 0; i < this.plan.getIntersections().size(); i++)
		{
			Noeud noeudOrigine = this.plan.getIntersections().get(i);
			g2.fillOval(noeudOrigine.getX()*echelle-3, noeudOrigine.getY()*echelle-3, 6,6);
			
			for(int j = 0; j < noeudOrigine.getListeTronconsSortants().size(); j++) 
			{
				int idNoeudDestination = noeudOrigine.getListeTronconsSortants().get(j).getIdNoeudDestination();
				Noeud noeudDestination = null;
				
				for(int k = 0; k < this.plan.getIntersections().size(); k++)
				{
					if(idNoeudDestination == this.plan.getIntersections().get(k).getId())
					{
						noeudDestination = this.plan.getIntersections().get(k);
						break;
					}
				}
				
				g2.drawLine(noeudOrigine.getX()*echelle, noeudOrigine.getY()*echelle, 
						noeudDestination.getX()*echelle, noeudDestination.getY()*echelle);
			}
		}
		
		// Déssiner l'entrepot
		Noeud entropt = plan.getAdresseEntrepot();
		
		if (entropt != null)
		{
			g2.setColor(Color.BLUE);
			g2.fillRect(entropt.getX()*echelle-5, entropt.getY()*echelle-5, 10, 10);
		}
		
		DemandeLivraison dem = plan.getDemandeLivraisons();
		
		if (dem != null)
		{
			Iterator<FenetreLivraison> itFen = dem.getFenetreIterator();			
			while (itFen.hasNext())
			{
				FenetreLivraison fen = itFen.next();				
				Iterator<Livraison> itLiv = fen.getLivraisonIterator();				
				while (itLiv.hasNext())				
					itLiv.next().accepte(this);
			}
		}
		
		if(dem != null && dem.getTournee() != null){
			Tournee tour = dem.getTournee();
			Iterator<Itineraire> itIti = tour.getItineraireIterator();
			while(itIti.hasNext()){
				itIti.next().accepte(this);
				
			}
		}
	}

	public void setEchelle(int e) {
		largeurVue = (largeurVue/echelle)*e;
		hauteurVue = (hauteurVue/echelle)*e;
		setSize(largeurVue, hauteurVue);
		echelle = e;
	}

	public int getEchelle() {
		return echelle;
	}

	public int getHauteur() {
		return hauteurVue;
	}

	public int getLargeur() {
		return largeurVue;
	}

	/**
	 * Methode appelee par les objets observes par this a chaque fois qu'ils ont ete modifies
	 */
	@Override
	public void update(Observable o, Object arg) 
	{
		hauteurVue = plan.getDimY()*this.echelle;
		largeurVue = plan.getDimX()*this.echelle;
		
		setSize(largeurVue, hauteurVue);
		
		repaint();
	}

	/**
	 * Methode appelee par l'objet visite (un livraison) a chaque fois qu'il recoit le message accepte
	 */
	@Override
	public void visite(Livraison liv) 
	{
		int x = liv.getAdresse().getX();
		int y = liv.getAdresse().getY();
		
		
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.RED);
		
		g2.fillOval(x*echelle-5, y*echelle-5, 10, 10);
	}
	
	@Override
	public void visite(Itineraire iti) {
		int i = 0;
		List<Integer> idNoeuds = iti.getNoeuds();
		while(i < idNoeuds.size() - 1){
			Graphics2D g2 = (Graphics2D) g;
			Noeud origine = plan.getNoeud(idNoeuds.get(i));
			Noeud destination = plan.getNoeud(idNoeuds.get(i+1));		
			g2.setColor(Color.green);
			g2.setStroke(new BasicStroke(3));
			g2.drawLine(origine.getX(),origine.getY(),
						destination.getX(),destination.getY());			
			i++;
			
		}
		
	}

}
