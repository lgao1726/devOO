FenetreLivraison fen = itFen.next();
			Iterator<Livraison> itLiv = fen.getLivraisonIterator();
			
			while (itLiv.hasNext())
			{
				liv = itLiv.next();
				
				if (liv.getAdresse().getId() == noeudid)
				{
					lableFenetreSelection.setText(fen.getHeureDebut().getTime().getHours() + ":" + 
							  fen.getHeureDebut().getTime().getMinutes() +
							  " -> " + fen.getHeureFin().getTime().getHours() + ":" + 
							  fen.getHeureFin().getTime().getMinutes());
					break;
				}
			}
			if (liv.getAdresse().getId() == noeudid)
				
				break;
