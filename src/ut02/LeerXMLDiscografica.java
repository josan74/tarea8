package ut02;

import java.io.File;
import javax.xml.parsers.*;
import org.w3c.dom.*;

public class LeerXMLDiscografica {

	public static void leerXML1(String xml) {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new File(xml));
			document.getDocumentElement().normalize();

			System.out.println("Elemento raiz: "
					+ document.getDocumentElement().getNodeName());

			// crea una lista con todos los nodos de empleadp
			NodeList discografica = document.getElementsByTagName("Disco");

			// recorrer la lista
			for (int i = 0; i < discografica.getLength(); i++) {
				Node emple = discografica.item(i); // obtener u nnodo
				if (emple.getNodeType() == Node.ELEMENT_NODE) { // tipo de nodo
					Element elemento = (Element) emple; // obtener los elementos
														// del nodo
					System.out
							.println("titulo: " + getNodo("titulo", elemento));
					System.out.println("formato: "
							+ getNodo("formato", elemento));
					System.out.println("autor: " + getNodo("autor", elemento));

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void leerXML2(String xml) {

		try {

			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();

			Document doc = builder.parse(xml);

			NodeList labTestList = doc.getElementsByTagName("LabTest");
			for (int i = 0; i < labTestList.getLength(); ++i) {
				Element labTest = (Element) labTestList.item(i);
				String labTestType = labTest.getAttribute("type");

				NodeList valueList = labTest.getElementsByTagName("value");
				for (int j = 0; j < valueList.getLength(); ++j) {
					Element value = (Element) valueList.item(j);
					String valueType = value.getAttribute("type");

					NodeList conditionList = value
							.getElementsByTagName("condition");
					for (int k = 0; k < conditionList.getLength(); ++k) {
						Element condition = (Element) conditionList.item(k);
						String conditionText = condition.getFirstChild()
								.getNodeValue();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		leerXML1("Discografica.xml");

		leerXML2("Discografica.xml");
		

	} // fin de main

	// obtener información de un nodo
	private static String getNodo(String etiqueta, Element elem) {
		NodeList nodo = elem.getElementsByTagName(etiqueta).item(0)
				.getChildNodes();
		Node valornodo = (Node) nodo.item(0);
		return valornodo.getNodeValue(); // devuelve el valor del nodo
	}
}
