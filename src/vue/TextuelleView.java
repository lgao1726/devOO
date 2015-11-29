package vue;

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

import modele.Itineraire;
import modele.Livraison;
import modele.Visiteur;


public class TextuelleView extends JPanel implements Visiteur
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
    
	public TextuelleView()
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

        listFenetre.setModel(new AbstractListModel<String>() {
            String[] strings = { "Toute les fenêtres", "08:30 -> 12:30", "13:00 -> 15:00", "15:15 -> 18:00" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        listFenetre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectionnerFenetre(evt);
            }
        });
        jScrollPane1.setViewportView(listFenetre);
        listFenetre.getAccessibleContext().setAccessibleParent(this);

        jLabel1.setText("Séléctionner une fenêtre de livraison :");

        tableLivraison.setModel(new DefaultTableModel(
            new Object [][] {
                {null, "", null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Numéro", "HeurePassage", "Client", "Adresse"
            }
        ) {
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
	public void visite(Itineraire v) {
		// TODO Auto-generated method stub
		
	}                   
}
