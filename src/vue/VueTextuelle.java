package vue;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controleur.Controleur;
import controleur.EtatTourneeCalculee;
import modele.DemandeLivraison;
import modele.FenetreLivraison;
import modele.Itineraire;
import modele.Livraison;
import modele.Plan;
import modele.Visiteur;

/**
 * Classe VueTextuelle qui descrit la lsite les livraisons sur le plan
 * @author H4101 Internal Corp
 * 
 */
@SuppressWarnings("serial")
public class VueTextuelle extends JPanel implements Observer, Visiteur
{
	private JLabel labelFenetreTexte;
    private JLabel lableFenetreSelection;
    
    private JTable tableLivraison;
    
    private JScrollPane jScrollPane2;
    
    private JButton btnDeplacer;
    private JButton btnLivraison;
    private JButton btnSupprimerLivraison;
    private JButton btnAnnuler;
    private JButton btnValider;
    
    private Plan plan;
    private Controleur controleur;
    
    /**
     * Constructeur d'objet
     * @param plan
     * @param controleur
     */
	public VueTextuelle(Plan plan, Controleur controleur)
	{
		this.plan = plan;
		this.controleur = controleur;
		
		jScrollPane2 = new JScrollPane();
      
        tableLivraison = new JTable();
        
        lableFenetreSelection = new JLabel();
        labelFenetreTexte = new JLabel();
        
        creerBoutons();
        creerTableView();
        
        setBorder(BorderFactory.createEtchedBorder());
        setVerifyInputWhenFocusTarget(false);
        setPreferredSize(new Dimension(400, 500));
        
        plan.addObserver(this);

        ajoutComponents();
	}
	
	/**
	 * M�thode selectionner une livraison on selectionant la ligne dans la table GridView
	 * @param Livraison
	 */
	public void selectionnerLivraison(Livraison liv)
	{
		int columid = Controleur.getEtatCourant() instanceof EtatTourneeCalculee ? 3 : 2;
		
		for (int i=0; i < tableLivraison.getRowCount(); i++)
			
			if ((int)tableLivraison.getValueAt(i, columid) == liv.getId())
			{
				tableLivraison.setRowSelectionInterval(i, i);
				break;
			}
	}          
	
	/**
	 * M�thode qui selectionne la livraison dans la vue textuelle
	 */
	public void selectionnerLivraisonTextuelle(Livraison liv)
	{
		int columid = Controleur.getEtatCourant() instanceof EtatTourneeCalculee ? 3 : 2;
		
		for (int i=0; i < tableLivraison.getRowCount(); i++)
		
			if ((int)tableLivraison.getValueAt(i, columid) == liv.getAdresse().getId())
			{
				tableLivraison.setRowSelectionInterval(i, i);
				break;
			}
		
	}
	
	/**
	 * M�thode qui selectionne la livraison dans le syst�me
	 * @param evt
	 */
	@SuppressWarnings("deprecation")
	private void selectionnerLivraisonLigne(MouseEvent evt) 
	{                                                
		int noeudid;
		
		try
		{
			noeudid = (int)tableLivraison.getValueAt(tableLivraison.getSelectedRow(), 3);
        
			Iterator<FenetreLivraison> itFen = plan.getDemandeLivraisons().getFenetreIterator();
			Livraison liv = null;
			boolean trouver = true;

			while (itFen.hasNext() && trouver)
			{
				
				FenetreLivraison fen = itFen.next();
				Iterator<Livraison> itLiv = fen.getLivraisonIterator();
				
				while (itLiv.hasNext())
				{
					liv = itLiv.next();
					
					if (liv.getAdresse().getId() == noeudid)
					{
						lableFenetreSelection.setText(liv.getHeureDebut().getTime().getHours() + ":" + 
								  liv.getHeureDebut().getTime().getMinutes() +
								  " -> " + liv.getHeureFin().getTime().getHours() + ":" + 
								  liv.getHeureFin().getTime().getMinutes());
						
						
						trouver = false;
						break;
					}
				}
			}
			
			controleur.selectionnerLivraison(liv);
		}
		catch (Exception e)
		{
		}
	} 
	
	@Override
	public void visite(Livraison v) 
	{
	}

	@Override
	public void visite(Itineraire v) 
	{
	}

	/**
	 * M�thode qui visite la demande apr�s un appel accepte dans la demande de livraison
	 */
	@Override
	public void visite(DemandeLivraison v) 
	{
		if (Controleur.getEtatCourant() instanceof EtatTourneeCalculee)
		
			listeOrdonnee(v);
		
		else
			
			listeNonOrdonnee(v);
	}     
	
	/**
	 * M�thode qui mis � jour la liste des livraisons une fois l'itin�raire est chang� en provoquant un appel accepte
	 */
	@Override
	public void update(Observable o, Object obj) 
	{
		
		DemandeLivraison dem = plan.getDemandeLivraisons();
		
		if (dem != null)
			
			dem.accepte(this);
		
		else
		{
			lableFenetreSelection.setText("");
			
			
			tableLivraison.setModel(new AbstractTableModel() {
				
				@Override
				public Object getValueAt(int arg0, int arg1) {
					return null;
				}
				
				@Override
				public int getRowCount() {
					return 0;
				}
				
				@Override
				public int getColumnCount() {
					return 0;
				}
			});
			
		}
	}
	
	/**
	 * M�thode qui active les boutons
	 */
    public void activerModification()
    {
    	btnLivraison.setEnabled(true);
    	btnDeplacer.setEnabled(true);
    	btnSupprimerLivraison.setEnabled(true);
    }
    
    /**
     * Changer l'etat de bouton valider
     * @param state
     */
    public void changerValider(boolean state)
    {
    	btnValider.setEnabled(state);
    }
	
	/**
	 * M�thode qui remplit la table grid par une demande de livraison Ordonn�e
	 * @param DemandeLivraison
	 */
	@SuppressWarnings("deprecation")
	private void listeOrdonnee(DemandeLivraison v) 
	{
		
		Iterator<FenetreLivraison> itFen = v.getFenetreIterator();
		
		String[] colums = new String[] {"Num�ro", "Heure Passage", "Client", "Adresse"};
		
		Object[][] rows = new Object[nombreFenetres(itFen)][4];
		
		int i=0;
		
		ArrayList<Integer> TableReatrd = new ArrayList<Integer>();
		
		itFen = v.getFenetreIterator();	
		
		Iterator<Itineraire> it = v.getTournee().getItineraireIterator();
		
		while (it.hasNext())
		{
			Itineraire itn = it.next();
			
			Livraison liv = itn.getLivraisonDestination();
			
			if (liv.getAdresse() != plan.getAdresseEntrepot())
			{
			   String di = "";
			   
			   if (liv.getHeurePassage().compareTo(liv.getHeureFin()) > 0)
			   {
				   TableReatrd.add(i);
				   
				   
				   Date tF = liv.getHeureFin().getTime();
				   Date tP = liv.getHeurePassage().getTime();
				   
				   di = " +(" + (tP.getTime() - tF.getTime()) / 1000 / 60 + "min)";
			   }
				
			   rows[i][1] = liv.getHeurePassage().getTime().getHours() + ":" + liv.getHeurePassage().getTime().getMinutes() + di;
			   rows[i][0] = i+1;
			   rows[i][2] = liv.getClient();
			   rows[i][3] = liv.getAdresse().getId();
			   i++;
			}
		}	
		
		tableLivraison.setModel(new TableGridViewModele(rows,colums));
		tableLivraison.setDefaultRenderer(Object.class, new TableGridViewCell(TableReatrd));
	}

	/**
	 * M�thode qui calcule la taille des fen�tre de livraison dans la demande
	 * @param itFen
	 * @return
	 */
	private int nombreFenetres(Iterator<FenetreLivraison> itFen) 
	{
	    int count = 0;
		
		while (itFen.hasNext())
		{
			FenetreLivraison fen = itFen.next();				
			Iterator<Livraison> itLiv = fen.getLivraisonIterator();				
			
			while (itLiv.hasNext())				
			{
				itLiv.next();
				count++;
			}
		}
		
		return count - 2;
	}

	/**
	 * M�thode qui remplit la table grid view par la demande de livraison non ordonn�e
	 * @param DemandeLivraison
	 */
	@SuppressWarnings("deprecation")
	private void listeNonOrdonnee(DemandeLivraison v) 
	{
		Iterator<FenetreLivraison> itFen = v.getFenetreIterator();		
		
		String[] colums = new String[] {"Fenetre", "Client", "Adresse"};
		
		int count = nombreFenetres(itFen);
		
		if (count < 0)
			return;
			
		Object[][] rows = new Object[count][3];
		
		int i=0;
		itFen = v.getFenetreIterator();	
		
		while (itFen.hasNext())
		{
			FenetreLivraison fen = itFen.next();				
			Iterator<Livraison> itLiv = fen.getLivraisonIterator();				
			
			while (itLiv.hasNext())				
			{
			   Livraison liv = itLiv.next();
			   
			   if (liv.getAdresse() != plan.getAdresseEntrepot())
			   {
				   rows[i][0] = liv.getHeureDebut().getTime().getHours() + ":" + liv.getHeureDebut().getTime().getMinutes() +
							   " -> " + liv.getHeureFin().getTime().getHours()  + ":" + liv.getHeureDebut().getTime().getMinutes();
				   rows[i][1] = liv.getClient();
				   rows[i][2] = liv.getAdresse().getId();
				   i++;
			   }
			}
		}	
		
		tableLivraison.setModel(new TableGridViewModele(rows, colums));
		tableLivraison.setDefaultRenderer(Object.class, new DefaultTableCellRenderer());	
	}
	
	private void btnLivraisonActionPerformed(java.awt.event.ActionEvent evt) 
    {                                             
    	controleur.ajouterLivraison();
    	
    	hideButtons(true);
    }
	
	/**
	 * M�thode qui cr�� la table grid des livraisons
	 */
	private void creerTableView() 
	{
		tableLivraison.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableLivraison.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectionnerLivraisonLigne(evt);
            }
        });
        
        jScrollPane2.setViewportView(tableLivraison);
        tableLivraison.getAccessibleContext().setAccessibleParent(this);

        labelFenetreTexte.setText("Demande de livraisons de la fenetre :");

        lableFenetreSelection.setFont(new java.awt.Font("Tahoma", 1, 11));
        lableFenetreSelection.setText("");	
	}

	/**
	 * M�thode qui cr�� les boutons de la vue
	 */
	private void creerBoutons() 
	{
		btnLivraison = new JButton();
        btnSupprimerLivraison = new JButton();
        btnDeplacer = new JButton();
        btnAnnuler = new JButton();
        btnValider = new JButton();
        
        btnValider.setVisible(false);
        btnAnnuler.setVisible(false);
        
        btnLivraison.setText("Ajouter Livraison");
        btnLivraison.setEnabled(false);
        btnLivraison.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLivraisonActionPerformed(evt);
            }
        });
        
        btnAnnuler.setText("Annuler");
        btnAnnuler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnnulerActionPerformed(evt);
            }
        });
        
        btnValider.setText("Valider");
        btnValider.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnValiderActionPerformed(evt);
            }
        });

        btnSupprimerLivraison.setText("Supprimer Livraison");
        btnSupprimerLivraison.setEnabled(false);
        btnSupprimerLivraison.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnSupprimerActionePermoed(arg0);	
			}
		});

        btnDeplacer.setText("Echanger Livraison");
        btnDeplacer.setEnabled(false);

        btnDeplacer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnDeplacerActionPermoed(arg0);
			}
		});	
	}

	/**
	 * Ajouter les composants a la vue
	 */
	private void ajoutComponents() 
	{
		GroupLayout vueTextuelleLayout = new GroupLayout(this);
        
        setLayout(vueTextuelleLayout);
        vueTextuelleLayout.setHorizontalGroup(
            vueTextuelleLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(vueTextuelleLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(vueTextuelleLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(GroupLayout.Alignment.TRAILING, vueTextuelleLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(vueTextuelleLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2, GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE))
                        .addGap(257, 257, 257))
                    .addGroup(vueTextuelleLayout.createSequentialGroup()
                        .addGroup(vueTextuelleLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(vueTextuelleLayout.createSequentialGroup()
                                .addComponent(labelFenetreTexte, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lableFenetreSelection, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE))
                            .addGroup(vueTextuelleLayout.createSequentialGroup()
                                .addComponent(btnLivraison)
                                .addComponent(btnValider)
                                .addGap(18, 18, 18)
                                .addComponent(btnSupprimerLivraison)
                                .addComponent(btnAnnuler)
                                .addGap(18, 18, 18)
                                .addComponent(btnDeplacer)
                            		))
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        vueTextuelleLayout.setVerticalGroup(
            vueTextuelleLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(vueTextuelleLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(vueTextuelleLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(labelFenetreTexte)
                    .addComponent(lableFenetreSelection))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 105, Short.MAX_VALUE)
                .addGroup(vueTextuelleLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDeplacer)
                    .addComponent(btnSupprimerLivraison)
                    .addComponent(btnLivraison)
                    .addComponent(btnValider)
                    .addComponent(btnAnnuler)
                	)
                .addGap(35, 35, 35))
        );
	}

	/**
	 * M�thode qui active et d�active les boutons de modification des livraisons
	 * @param boolean type indique qu'on a dans l'etat de modification alors activ� les boutons Valider et Annuler
	 */
	private void hideButtons(boolean type)
	{
		btnDeplacer.setVisible(!type);
		btnSupprimerLivraison.setVisible(!type);
		btnLivraison.setVisible(!type);
		
		btnValider.setVisible(type);
		btnAnnuler.setVisible(type);
	}
	
	
	
	protected void btnValiderActionPerformed(ActionEvent evt) {
		controleur.valider();
		
		hideButtons(false);
	}

	protected void btnAnnulerActionPerformed(ActionEvent evt) {
		controleur.annuler();
		
		hideButtons(false);
	}

	protected void btnSupprimerActionePermoed(ActionEvent arg0) {
		controleur.supprimerLivraison();
		hideButtons(true);
	}

	protected void btnDeplacerActionPermoed(ActionEvent arg0) {
		controleur.echangerLivraison();
		
		hideButtons(true);
	}
}

/**
 * Classe mod�le de la table grid view
 * @author H4101 International Corp
 *
 */
class TableGridViewModele extends DefaultTableModel 
{
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("rawtypes")
	Class[] types = new Class [] 
    {
            java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
    };
    
    boolean[] canEdit = new boolean [] {false, false, false, false};

    public TableGridViewModele(Object[][] rows, Object[] colums)
    {
    	super(rows,colums);
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public Class getColumnClass(int columnIndex) 
    {
            return types [columnIndex];
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) 
    {
            return canEdit [columnIndex];
    }
}

/**
 * Classe mod�le de la cellule de la table grid view
 * @author H4101 International Corp
 *
 */
@SuppressWarnings("serial")
class TableGridViewCell extends DefaultTableCellRenderer
{
	private ArrayList<Integer> TableReatrd;
	
	/**
	 * Constructeur d'objet
	 */
	public TableGridViewCell(ArrayList<Integer> TableReatrd)
	{
		this.TableReatrd = TableReatrd;
	}
	
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) 
    {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

        if (TableReatrd.contains(row))
        	
        	setBackground(Color.RED);
        
        else
        	if (!isSelected)
        		
        		setBackground(Color.WHITE);
        
        return this;
    }   
}