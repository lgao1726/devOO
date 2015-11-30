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
