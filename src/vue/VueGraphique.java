package vue;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.QuadCurve2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;

import modele.DemandeLivraison;
import modele.FenetreLivraison;
import modele.Itineraire;
import modele.Livraison;
import modele.Noeud;
import modele.Plan;
import modele.Tournee;
import modele.Visiteur;

/**
 * Classe VueGraphique qui cntient le plan de la ville et les livraisons
 * @author H4101 Internal Corp
 * 
 */
public class VueGraphique extends JPanel implements Observer, Visiteur
{
	private static final long serialVersionUID = 1L;
	private final int RAYON_LIVRAISON=5;
	private final int RAYON_NOEUD=3;
	private final int HAUTEUR_DEFAULT=200;
	private final int LARGEUR_DEFAULT=200;
	private int echelle;
	private int hauteurVue;
	private int largeurVue;
	private Plan plan;
	private Graphics g;
	private ArrayList<TraitsDejaDessine> traitsDejaDessine;

	/**
	 * Cree la vue graphique permettant de dessiner plan avec l'echelle e dans la fenetre f
	 * @param plan
	 * @param e l'echelle
	 * @param f la fenetre
	 */
	public VueGraphique(Plan plan, int e, Fenetre f) 
	{
		super();
		// Observer ce plan
		plan.addObserver(this);
		
		this.traitsDejaDessine = new ArrayList<TraitsDejaDessine>();
		this.echelle = e;
		this.hauteurVue = HAUTEUR_DEFAULT;
		this.largeurVue = LARGEUR_DEFAULT;
		this.plan = plan;
		
		setLayout(null);
		setBackground(Color.white);
		setPreferredSize(new Dimension(HAUTEUR_DEFAULT, LARGEUR_DEFAULT));
		setSize(largeurVue, hauteurVue);
		f.getContentPane().add(this);
	}
	
	
	/**
	 * Methode appelee a chaque fois que VueGraphique doit etre redessinee
	 * @param Graphics g le graphe de contenu
	 */
	@Override
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		
		// Arrière-plan blanc
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
		RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		
		this.g = g;
		
		// Pour chaque intersections
		for(int i = 0; i < this.plan.getIntersections().size(); i++)
		{
			Noeud noeudOrigine = this.plan.getIntersections().get(i);
			g2.fillOval(noeudOrigine.getX()*echelle-3, noeudOrigine.getY()*echelle-3, 6,6);
			
			// Dessiner les traoncons
			for(int j = 0; j < noeudOrigine.getListeTronconsSortants().size(); j++) 
			{
				int idNoeudDestination = noeudOrigine.getListeTronconsSortants().get(j).getIdNoeudDestination();
				Noeud noeudDestination = null;
				
				for(int k = 0; k < this.plan.getIntersections().size(); k++)
				
					if(idNoeudDestination == this.plan.getIntersections().get(k).getId())
					{
						noeudDestination = this.plan.getIntersections().get(k);
						break;
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

		// Remplir les livraisons
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
			
			traitsDejaDessine.clear();
		}
		
		// Déssiner la tournée si déjà calculer
		if(dem != null && dem.getTournee() != null)
		{
			Tournee tour = dem.getTournee();
			List<Itineraire> itIti = tour.getItineraires();
			
			for(int i=0;i<itIti.size();i++)
			
				itIti.get(i).accepte(this);	
		}
	}
	
	/**
	 * Setteur de l'échelle de la vue
	 * @param e int l'échelle
	 */
	public void setEchelle(int e) 
	{
		largeurVue = (largeurVue/echelle)*e;
		hauteurVue = (hauteurVue/echelle)*e;
		setSize(largeurVue, hauteurVue);
		echelle = e;
	}

	/**
	 * Getteur de l'échelle de la vue
	 * @return int l'échelle
	 */
	public int getEchelle() 
	{
		return echelle;
	}

	/**
	 * Getteur de l'hauteur
	 * @return int l'hauteur
	 */
	public int getHauteur() 
	{
		return hauteurVue;
	}

	/**
	 * Getteur de la largeur
	 * @return int largeur
	 */
	public int getLargeur() 
	{
		return largeurVue;
	}
	
	/**
	 * Getteur du plan
	 * @return Plan
	 */
	public Plan getPlan() 
	{
		return plan;
	}

	/**
	 * Getteur de rayon livraison
	 * @return int rayon
	 */
	public int getRayonLivraison() 
	{
		return RAYON_LIVRAISON;
	}
	
	/**
	 * Getteur de rayon noeud
	 * @return int rayon
	 */
	public int getRayonNoeud()
	{
		return RAYON_NOEUD;
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
		setPreferredSize(new Dimension(largeurVue, hauteurVue));
		
		repaint();
	}

	/**
	 * Methode appelee par l'objet visite (une livraison) a chaque fois qu'il recoit le message accepte
	 */
	@Override
	public void visite(Livraison liv) 
	{
		int x = liv.getAdresse().getX();
		int y = liv.getAdresse().getY();
		
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.RED);
		
		g2.fillOval(x*echelle-RAYON_LIVRAISON, y*echelle-RAYON_LIVRAISON, 2*RAYON_LIVRAISON, 2*RAYON_LIVRAISON);
	}
	
	/**
	 * Methode appelee par l'objet visite (un iténiraire) a chaque fois qu'il recoit le message accepte
	 */
	@Override
	public void visite(Itineraire iti) 
	{
		int i = 0;
		List<Integer> idNoeuds = iti.getNoeuds();
		
		Color[] colors = new Color[]
				{
				    Color.RED, Color.BLUE, Color.YELLOW, Color.GREEN,
				    Color.BLACK, Color.PINK, Color.ORANGE,Color.CYAN,
				    Color.GRAY,Color.magenta,Color.ORANGE
				};
		
		Color c = colors[idNoeuds.get(0)%colors.length];
		
		while(i < idNoeuds.size() - 1)
		{
			Graphics2D g2 = (Graphics2D) g;
			
			Noeud origine = plan.getNoeud(idNoeuds.get(i));
			Noeud destination = plan.getNoeud(idNoeuds.get(i+1));
			g2.setColor(Color.blue);
			g2.drawString(""+origine.getId(), origine.getX()+5, origine.getY()-10);			
			g2.setColor(c);
			g2.setStroke(new BasicStroke(1));
			
			drawArrow(g2,origine.getX(),origine.getY(),
					destination.getX(),destination.getY());		
			i++;
		}
	}
	
	/**
	 * Methode appelee par l'objet visite (une livraison) a chaque fois qu'il recoit le message accepte
	 */
	@Override
	public void visite(DemandeLivraison v) 
	{	
	}
	
	/**
	 * Methode qui déssine les flêches
	 * @param g1 Graphic
	 * @param x1 int x orgine
	 * @param y1 int y origine
	 * @param x2 int x destination
	 * @param y2 int y destination
	 */
	private void drawArrow(Graphics g1, int x1, int y1, int x2, int y2) 
	{
	        Graphics2D g = (Graphics2D) g1.create();
	
	        double control = 15;
	        double dx = x2 - x1, dy = y2 - y1;
	        double angle = Math.atan2(dy, dx);
	        int len = (int) Math.sqrt(dx*dx + dy*dy);
	        
	        AffineTransform at = AffineTransform.getTranslateInstance(x1, y1);
	        at.concatenate(AffineTransform.getRotateInstance(angle));
	        g.transform(at);
	        
	        // Draw horizontal arrow starting in (0, 0)
	        QuadCurve2D.Double s = new QuadCurve2D.Double(0, 0, len/2, control, len, 0);
	        g.draw(s);
	        
	        AffineTransform atArrow = AffineTransform.getTranslateInstance(len/2, control);
	        atArrow.concatenate(AffineTransform.getRotateInstance(Math.atan2(-control, len/2)));
	        g.transform(atArrow);
	        g.fillPolygon(new int[] {len/2, (len/2)-12, (len/2)-12, len/2},
	                      new int[] {0, -8, 8, 0}, 4);
    	}
}


