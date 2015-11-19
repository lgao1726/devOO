
import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import model.Plan;
import xml.DeserialiseurXML;
import xml.ExceptionXML;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		DeserialiseurXML deserialiseurXML = new DeserialiseurXML();
		Plan plan = new Plan();
		try {
			deserialiseurXML.traiterPlan(plan);
		} catch (ExceptionXML e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
