vDemandeLivraison dem = plan.getDemandeLivraisons();
		
		if (dem != null)
			
			dem.accepte(this);
		
		else
		{
			lableFenetreSelection.setText("");
			
			listFenetre.setModel(new AbstractListModel<String>() {
	
				@Override
				public String getElementAt(int arg0) {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public int getSize() {
					// TODO Auto-generated method stub
					return 0;
				}
			});
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
