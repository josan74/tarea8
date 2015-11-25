package ut02.ejemplos.SAX;
import java.io.*;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;	
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

public class PruebaSax1 {

	public static void main(String[] args) 
					throws FileNotFoundException, IOException  {
		try{
			XMLReader procesadorXML = XMLReaderFactory.createXMLReader();
			
			GestionContenido gestor = new GestionContenido();
			procesadorXML.setContentHandler(gestor);
			
			InputSource fileXML = new InputSource("res/xml/Discografica.xml");
			
			procesadorXML.parse(fileXML);
			
		} catch (SAXException saxe) {}
	}
}// fin PruebaSax1

class GestionContenido extends DefaultHandler {
	public GestionContenido(){
		super();
	}
	public void startDocument(){
		System.out.println("Comienzo del documento XML");
	}
	public void endDocument(){
		System.out.println("Final del documento XML");
	}
	public void startElement(String uri, String nombre,
			String nomobreC, Attributes atts){
		System.out.println("\tPrincipio Elemento:" + nombre);
	}
	public void endElement(String uri, String nombre,
			String nomobreC, Attributes atts) {
		System.out.println("\tFin Elemento:" + nombre);
	}
	public void characters(char[] ch, int inicio, int longitud)	
			throws SAXException{
		String car = new String(ch, inicio, longitud);
		car = car.replaceAll("[\t\n]",""); // quitar saltos de l�nea
		System.out.println("\tCaracteres : "+car);
	}
} // fin GestionContenido

