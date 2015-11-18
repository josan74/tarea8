package ut02.ejemplo.DOM.empleado;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.io.*;
import java.net.URI;
import java.nio.file.Paths;
import java.util.ArrayList;

public class CrearEmpleadoXML {

	private static final String XML_FILE = "res/xml/empleado.xml";
	private static final String RANDOM_FILE = "res/aleatorio/employee.bin";

	// Inserci�n de los datos del empleado
	static void CrearElemento(String datoEmple, String valor, Element raiz, Document document) {
		Element elem = document.createElement(datoEmple); // creamos hijo
		Text text = document.createTextNode(valor); // damos valor
		raiz.appendChild(elem); // pegamos el elemento hijo a la ra�z
		elem.appendChild(text); // pegamos el valor
	}

	public static void main(String[] args) throws IOException {
		ArrayList<Empleado> empleados = RandomEmpleado.getAll(Paths.get(RANDOM_FILE));

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();

			DOMImplementation implementation = builder.getDOMImplementation();
			Document document = implementation.createDocument(null, "Empleados", null);
			document.setXmlVersion("1.0"); // asignamos la versi�n de nuestro
											// XML

			for (Empleado e : empleados) {

				Element raiz = document.createElement("empleado"); // nodo
																	// empleado
				document.getDocumentElement().appendChild(raiz); // lo pegamos a

				CrearElemento("id", Integer.toString(e.getId()), raiz, document);// A�adir
																					// ID
				CrearElemento("nombre", e.getNombre().trim(), raiz, document);// A�adir
																				// apellido
				CrearElemento("dep", Integer.toString(e.getDep()), raiz, document); // A�adir
																					// departamento
				CrearElemento("salario", Double.toString(e.getSalario()), raiz, document); // A�adir
																							// salario
			}

			Source source = new DOMSource(document);

			Result result = new StreamResult(new java.io.File(XML_FILE));

			Transformer transformer = TransformerFactory.newInstance().newTransformer();

			transformer.setOutputProperty(OutputKeys.METHOD, "xml");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		    transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

			Result console = new StreamResult(System.out);
			transformer.transform(source, console);

		} catch (ParserConfigurationException e1) {
			System.out.println("Error al crear el parser");
		} catch (TransformerException e1) {
			System.out.println("Error al crear el transformer");

		}
	}

}
