package ut02.ejemplos.DOM;
import java.io.File;
import javax.xml.parsers.*;
import org.w3c.dom.*;

public class LeerEmpleadoXml {
	
	public static void main(String[] args) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		
		try{
			DocumentBuilder builder = factory.newDocumentBuilder();			
			Document document = builder.parse(new File ("res/xml/Empleados.xml"));			
			document.getDocumentElement().normalize();
			
			System.out.println("Elemento raiz: "+
					document.getDocumentElement().getNodeName());
			
			//crea una lista con todos los nodos de empleadp			
			NodeList empleados = document.getElementsByTagName("empleado");
			
			//recorrer la lista
			for (int i = 0; i < empleados.getLength(); i++){
				Node emple = empleados.item(i); // obtener u nnodo
				if (emple.getNodeType() == Node.ELEMENT_NODE) { // tipo de nodo
					Element elemento = (Element) emple; // obtener los elementos del nodo
					System.out.println("ID: "+ getNodo("id", elemento));
					System.out.println("Apellido: " + getNodo("nombre", elemento));
					System.out.println("Departamento: " + getNodo("dep", elemento));
					System.out.println("Salario: " + getNodo("salario", elemento));

				}
			}
		} catch (Exception e) {e.printStackTrace();}
	} // fin de main
		
	//obtener informaci�n de un nodo
	private static String getNodo(String etiqueta, Element elem)
	{
		NodeList nodo = elem.getElementsByTagName(etiqueta).item(0).getChildNodes();
		Node valornodo = (Node) nodo.item(0);
		return valornodo.getNodeValue(); // devuelve el valor del nodo
	}
}


