package vue;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import javax.swing.AbstractListModel;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controleur.Controleur;
import controleur.Etat;
import controleur.EtatLivraisonCharge;
import controleur.EtatTourneeCalculee;
import modele.DemandeLivraison;
import modele.FenetreLivraison;
import modele.Itineraire;
import modele.Livraison;
import modele.Plan;
import modele.Visiteur;


public class TextuelleView extends JPanel implements Observer, Visiteur
{
	private JLabel labelFenetreTexte;
    private JLabel lableFenetreSelection;
    private JList<String> listFenetre;
    private JTable tableLivraison;
    private JLabel jLabel1;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    
    private JButton btnDeplacer;
    private JButton btnLivraison;
    private JButton btnSupprimerLivraison;
    
    private JButton btnAnnuler;
    private JButton btnValider;
    
    private Plan plan;
    private Controleur controleur;
    
	public TextuelleView(Plan plan, Controleur controleur)
	{
		jScrollPane1 = new JScrollPane();
        listFenetre = new JList<>();
        jLabel1 = new JLabel();
        jScrollPane2 = new JScrollPane();
        tableLivraison = new JTable();
        labelFenetreTexte = new JLabel();
        lableFenetreSelection = new JLabel();
        btnLivraison = new JButton();
        btnSupprimerLivraison = new JButton();
        btnDeplacer = new JButton();
        btnAnnuler = new JButton();
        btnValider = new JButton();
        
        btnValider.setVisible(false);
        btnAnnuler.setVisible(false);
        
        setBorder(BorderFactory.createEtchedBorder());
        setVerifyInputWhenFocusTarget(false);
        setPreferredSize(new Dimension(400, 500));
        
        plan.addObserver(this);
		this.plan = plan;
		this.controleur = controleur;
        
        listFenetre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectionnerFenetre(evt);
            }
        });
        jScrollPane1.setViewportView(listFenetre);
        listFenetre.getAccessibleContext().setAccessibleParent(this);

        jLabel1.setText("Selectionner une fenetre de livraison :");

        tableLivraison.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableLivraison.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectionnerLivraisonLigne(evt);
            }
        });
        jScrollPane2.setViewportView(tableLivraison);
        tableLivraison.getAccessibleContext().setAccessibleParent(this);

        labelFenetreTexte.setText("Demande de livraisons de la fenetre :");

        lableFenetreSelection.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lableFenetreSelection.setText("");

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
        btnDeplacer.setToolTipText("");
        btnDeplacer.setEnabled(false);

        btnDeplacer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnDeplacerActionPermoed(arg0);
			}
		});

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
	
	protected void hideButtons(boolean type)
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

	private void selectionnerLivraisonLigne(java.awt.event.MouseEvent evt) {                                            
	        
			int noeudid;
			
			
				noeudid = (int)tableLivraison.getValueAt(tableLivraison.getSelectedRow(), 3);
	        
	        
			System.out.println(noeudid);
	        
			Iterator<FenetreLivraison> itFen = plan.getDemandeLivraisons().getFenetreIterator();
			Livraison liv = null;
			System.out.println("noueud id selection textuelle"+noeudid);
			while (itFen.hasNext())
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
						break;
					}
				}
				if (liv.getAdresse().getId() == noeudid)
					
					break;
				
			}
			
			System.out.println("selection textuelle dans echange ajout: "+liv.getAdresse().getId());
			controleur.selectionnerLivraison(liv);
	    }           
	
	public void selectionnerLivraisonTextuelle(Livraison liv)
	{
		System.out.println("dans vue textuelle");
		int columid = Controleur.getEtatCourant() instanceof EtatTourneeCalculee ? 3 : 2;
		
		for (int i=0; i < tableLivraison.getRowCount(); i++)
		{	
			System.out.println((int)tableLivraison.getValueAt(i, columid));
			if ((int)tableLivraison.getValueAt(i, columid) == liv.getAdresse().getId())
			{
				tableLivraison.setRowSelectionInterval(i, i);
				break;
			}
		}
	}
    private void selectionnerFenetre(java.awt.event.MouseEvent evt) {                                     
        
        int fenetreSelectionne = listFenetre.getSelectedIndex();
        
        // Code here
        
    }   
    
    public void activerModification()
    {
    	btnLivraison.setEnabled(true);
    	btnDeplacer.setEnabled(true);
    	btnSupprimerLivraison.setEnabled(true);
    }
    
    public void changerValider(boolean state)
    {
    	btnValider.setEnabled(state);
  
    }

    

    private void btnLivraisonActionPerformed(java.awt.event.ActionEvent evt) 
    {                                             
    	controleur.ajouterLivraison();
    	
    	hideButtons(true);
    }

	@Override
	public void visite(Livraison v) 
	{
	}

	@Override
	public void visite(Itineraire v) 
	{
		//plan.getDemandeLivraisons().accepte(this);
	}

	@Override
	public void visite(DemandeLivraison v) 
	{
		if (Controleur.getEtatCourant() instanceof EtatTourneeCalculee)
		
			listeOrdonnee(v);
		
		else
			
			listeNonOrdonnee(v);
	}     
	
	private void listeOrdonnee(DemandeLivraison v) {
		
		Iterator<FenetreLivraison> itFen = v.getFenetreIterator();
		
		//remplirFenetre(itFen);
		
		String[] colums = new String[] {"Num�ro", "Heure Passage", "Client", "Adresse"};
		
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

		count -= 2;
		
		Object[][] rows = new Object[count][4];
		
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
		
		tableLivraison.setModel(new javax.swing.table.DefaultTableModel(rows,colums) {
	            Class[] types = new Class [] {
	                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
	            };
	            boolean[] canEdit = new boolean [] {
	                false, false, false, false
	            };

	            public Class getColumnClass(int columnIndex) {
	                return types [columnIndex];
	            }

	            public boolean isCellEditable(int rowIndex, int columnIndex) {
	                return canEdit [columnIndex];
	            }
	        });
		
		class TableCellRender extends DefaultTableCellRenderer
		{
			private ArrayList<Integer> TableReatrd;
			
			public TableCellRender(ArrayList<Integer> TableReatrd)
			{
				this.TableReatrd = TableReatrd;
			}
			
		    @Override
		    public Component getTableCellRendererComponent(JTable table,
		            Object value, boolean isSelected, boolean hasFocus, int row, int col) {

		        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

		        if (TableReatrd.contains(row))
		        	
		        	setBackground(Color.RED);
		        
		        else
		        	if (!isSelected)
		        		
		        		setBackground(Color.WHITE);
		        
		        return this;
		    }   
		}
		
		tableLivraison.setDefaultRenderer(Object.class, new TableCellRender(TableReatrd));
	}

	private void listeNonOrdonnee(DemandeLivraison v) {
		
		Iterator<FenetreLivraison> itFen = v.getFenetreIterator();		
		
		remplirFenetre(v.getFenetreIterator());
		
		String[] colums = new String[] {"Fenetre", "Client", "Adresse"};
		
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

		if (count == 0)
			return;
		
		count -= 2;
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
		
		tableLivraison.setModel(new javax.swing.table.DefaultTableModel(rows,colums) {
	            Class[] types = new Class [] {
	                java.lang.String.class, java.lang.String.class, java.lang.Integer.class
	            };
	            boolean[] canEdit = new boolean [] {
	                false, false, false
	            };

	            public Class getColumnClass(int columnIndex) {
	                return types [columnIndex];
	            }

	            public boolean isCellEditable(int rowIndex, int columnIndex) {
	                return canEdit [columnIndex];
	            }
	        });
		
		tableLivraison.setDefaultRenderer(Object.class, new DefaultTableCellRenderer());
		
	}

	private void remplirFenetre(Iterator<FenetreLivraison> f)
	{

	}

	@Override
	public void update(Observable o, Object obj) {
		
		DemandeLivraison dem = plan.getDemandeLivraisons();
		
		if (dem != null)
			
			dem.accepte(this);
		
		else
		{
			lableFenetreSelection.setText("");
			
			
			tableLivraison.setModel(new AbstractTableModel() {
				
				@Override
				public Object getValueAt(int arg0, int arg1) {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public int getRowCount() {
					// TODO Auto-generated method stub
					return 0;
				}
				
				@Override
				public int getColumnCount() {
					// TODO Auto-generated method stub
					return 0;
				}
			});
			
		}
	}
}