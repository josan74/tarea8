
package ut02;
import org.w3c.dom.*;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

import java.io.*;
import java.util.ArrayList;

public class CrearXMLDiscografica  {
	
	// Insercion de los datos del disco
	static void CrearElemento(String datoEmple, String valor,Element raiz, Document document){
		Element elem = document.createElement(datoEmple); // creamos hijo
		Text text = document.createTextNode(valor); //damos valor
		raiz.appendChild(elem); //pegamos el elemento hijo a la raiz
		elem.appendChild(text); // pegamos el valor
	}
	
	static void crearXML(ArrayList<Disco> discografica){

		// Construimos el parser
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try{
			DocumentBuilder builder = factory.newDocumentBuilder();
			DOMImplementation implementation = builder.getDOMImplementation();
			
			// Creamos un documento vac’o de nombre  con el nodo ra’z de nombre Discografiac 
			Document document = implementation.createDocument(null, "Discografica",null);
			// Asignamos la versi—n del XML
			document.setXmlVersion("1.0"); //asignamos la version de nuestro XML
			
			
			
				for(Disco disco: discografica)	{
					Element raiz = document.createElement("Disco"); // nodo empleado
					document.getDocumentElement().appendChild(raiz); // lo pegamos a la raíz del documento
					
					CrearElemento("titulo",disco.getTitulo(), raiz, document);// Añadir ID
					CrearElemento("formato",disco.getFormato().toString(),raiz, document);// Añadir apellido
					CrearElemento("autor",disco.getAutor(),raiz,document); //Añadir departamento
			
				}
				
			
			Source source = new DOMSource(document);
			
			Result result = new StreamResult(new java.io.File("Discografica.xml"));
			
			Transformer t = TransformerFactory.newInstance().newTransformer();
			
			t.setOutputProperty(OutputKeys.INDENT, "yes");
			t.setOutputProperty(OutputKeys.METHOD, "xml");
			t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
			t.transform(source,  result);		
			
			Result console = new StreamResult(System.out);
			t.transform(source, console);
			
		} catch(Exception e) {System.err.println("Error: "+e);}
	}

	public static void main(String[] args) throws IOException{
		
		ArrayList <Disco> discografica = new ArrayList <Disco> ();
		
		discografica.add(new Disco("Nightfall in Middle-Earth",Disco.Formato.CD,"Blind Guardian"));
		discografica.add(new Disco("The number of the beast",Disco.Formato.CD,"Iron Maiden"));
		discografica.add(new Disco("Crash! Boom! Bang!",Disco.Formato.MP3,"Roxette"));
		discografica.add(new Disco("Metallica",Disco.Formato.CD,"Metallica"));
		discografica.add(new Disco("Ardi Larruz",Disco.Formato.OGG,"Latzen"));
		
		crearXML(discografica);
		

		
		
	}
	

}

