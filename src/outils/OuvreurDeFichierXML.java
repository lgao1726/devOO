package outils;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

public class OuvreurDeFichierXML extends FileFilter{

	private static OuvreurDeFichierXML instance = null;
	
	protected static OuvreurDeFichierXML getInstance()
	{
		if (instance == null) instance = new OuvreurDeFichierXML();
		return instance;
	}
	
	//TODO: A quoi sert le boolean lecture ?
	// Comment utiliser ExceptionXML ?
	
	protected File ouvre(boolean lecture) throws ExceptionXML
	{
		JFileChooser jFileChooserXML = new JFileChooser();
		jFileChooserXML.setFileFilter(this);
		int retour = jFileChooserXML.showOpenDialog(null);
		File fichierSelectionne = null;
		if(retour == JFileChooser.APPROVE_OPTION)
		{
			fichierSelectionne = jFileChooserXML.getSelectedFile();
		}			
		return fichierSelectionne;
	}
	
	@Override
	public boolean accept(File fichierSelectionne) {
		if(!this.getExtension(fichierSelectionne).equals("xml")){
			return false;
		}
		return true;
	}

	@Override
	public String getDescription() {
		return "Fichiers XML";
	}
	
	/**
	 * @param fichier
	 * @return
	 */
	private String getExtension(File fichier) {
		String nomFichier = fichier.getName();
		String extension = "";

		int i = nomFichier.lastIndexOf('.');
		if (i > 0) {
		    extension = nomFichier.substring(i+1);
		}
		
		return extension;
	}
	
	
}
