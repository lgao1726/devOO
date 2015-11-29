package vue;

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
    
    private Plan plan;
    
	public TextuelleView(Plan plan)
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
        
        setBorder(BorderFactory.createEtchedBorder());
        setVerifyInputWhenFocusTarget(false);
        
        plan.addObserver(this);
		this.plan = plan;

        
        listFenetre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectionnerFenetre(evt);
            }
        });
        jScrollPane1.setViewportView(listFenetre);
        listFenetre.getAccessibleContext().setAccessibleParent(this);

        jLabel1.setText("Séléctionner une fenêtre de livraison :");

        tableLivraison.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableLivraison.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectionnerLivraisonLigne(evt);
            }
        });
        jScrollPane2.setViewportView(tableLivraison);
        tableLivraison.getAccessibleContext().setAccessibleParent(this);

        labelFenetreTexte.setText("Demande de les Livraisons de la fenêtre :");

        lableFenetreSelection.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lableFenetreSelection.setText(" 08:30 -> 12:30");

        btnLivraison.setText("Ajouter Livraison");
        btnLivraison.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLivraisonActionPerformed(evt);
            }
        });

        btnSupprimerLivraison.setText("Supprimer Livraison");

        btnDeplacer.setText("Déplacer Livraison");
        btnDeplacer.setToolTipText("");

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
                            .addComponent(jLabel1)
                            .addComponent(jScrollPane1)
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
                                .addGap(18, 18, 18)
                                .addComponent(btnSupprimerLivraison)
                                .addGap(18, 18, 18)
                                .addComponent(btnDeplacer)))
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        vueTextuelleLayout.setVerticalGroup(
            vueTextuelleLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(vueTextuelleLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel1)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(btnLivraison))
                .addGap(35, 35, 35))
        );
	}
	
	private void selectionnerLivraisonLigne(java.awt.event.MouseEvent evt) {                                            
        
        int livraisonSelectionne = tableLivraison.getSelectedRow();
        
        // Code de selection here
        
    }                                           

    private void selectionnerFenetre(java.awt.event.MouseEvent evt) {                                     
        
        int fenetreSelectionne = listFenetre.getSelectedIndex();
        
        // Code here
        
    }                                    

    private void btnLivraisonActionPerformed(java.awt.event.ActionEvent evt) 
    {                                             
        // TODO add your handling code here:
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
		
		String[] colums = new String[] {"Numéro", "Heure Passage", "Client", "Adresse"};
		
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
		
		itFen = v.getFenetreIterator();	
		
		Iterator<Itineraire> it = v.getTournee().getItineraireIterator();
		
		while (it.hasNext())
		{
			Itineraire itn = it.next();
			
			//Livraison livOr = itn.getLivraisonOrigine();
			Livraison liv = itn.getLivraisonDestination();
			
			if (liv.getAdresse() != plan.getAdresseEntrepot())
			{
			   rows[i][1] = liv.getHeurePassage().getTime().getHours() + ":" + liv.getHeurePassage().getTime().getMinutes();
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
		
	}

	private void listeNonOrdonnee(DemandeLivraison v) {
		
		Iterator<FenetreLivraison> itFen = v.getFenetreIterator();		
		
		remplirFenetre(v.getFenetreIterator());
		
		String[] colums = new String[] {"Fenêtre", "Client", "Adresse"};
		
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
		
	}

	private void remplirFenetre(Iterator<FenetreLivraison> f)
	{

		class ListFenetre extends AbstractListModel<String>
		{
			ArrayList<String> strings;
			
			public ListFenetre(Iterator<FenetreLivraison> itFen)
			{
				strings = new ArrayList<String>();
					
				strings.add("Toute les fenêtres");
				
				while (itFen.hasNext())
				{
					FenetreLivraison fen = itFen.next();

					if (fen != null)
					{
						if (fen.getHeureDebut() != null && fen.getHeureFin() != null)
						{
							strings.add(fen.getHeureDebut().getTime().getHours() + 
							    ":" + fen.getHeureDebut().getTime().getMinutes() + " -> " + 
							    fen.getHeureFin().getTime().getHours() + ":" + 
							    fen.getHeureFin().getTime().getMinutes());
						}
					}
				}
			}
			
			@Override
			public String getElementAt(int arg0) {
				// TODO Auto-generated method stub
				return strings.get(arg0);
			}

			@Override
			public int getSize() {
				// TODO Auto-generated method stub
				return strings.size();
			}
		}
		
		
		listFenetre.setModel(new ListFenetre(f));
	}

	@Override
	public void update(Observable o, Object obj) {
		
		DemandeLivraison dem = plan.getDemandeLivraisons();
		
		if (dem != null)
			
			dem.accepte(this);
	}
}
