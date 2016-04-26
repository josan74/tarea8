/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea8;

import java.io.File;
import java.util.ArrayList;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

/**
 *
 * @author Jose Angel
 */
public class XML {

    private static final String XML_FILE = "clientes.xml";
    
    public String leerXML(){
        
        return leerXML(XML_FILE);
    }
    
    public String leerXML(String ruta){
        StringBuffer sb = new StringBuffer();
        	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		
		try{
			DocumentBuilder builder = factory.newDocumentBuilder();			
			Document document = builder.parse(new File (ruta));			
			document.getDocumentElement().normalize();
			
			System.out.println("Elemento raiz: "+
					document.getDocumentElement().getNodeName());
			
			//crea una lista con todos los nodos de cliente			
			NodeList clientes = document.getElementsByTagName("cliente");
			
			//recorrer la lista
			for (int i = 0; i < clientes.getLength(); i++){
				Node cliente = clientes.item(i); // obtener u nnodo
				if (cliente.getNodeType() == Node.ELEMENT_NODE) { // tipo de nodo
					Element elemento = (Element) cliente; // obtener los elementos del nodo
					System.out.println("NCliente: "+ getNodo("dni", elemento));
                                        sb.append("NCliente: ");
                                        sb.append(getNodo("dni",elemento));                                      
                                        sb.append("\n");
					System.out.println("Apellido: " + getNodo("nombre", elemento));
                                         sb.append("Apellido: ");
                                         sb.append(getNodo("nombre", elemento));
                                         sb.append("\n");
					System.out.println("Departamento: " + getNodo("apellidos", elemento));
                                         sb.append("Departamento: ");
                                         sb.append(getNodo("apellidos",elemento));
                                          sb.append("\n");
                                        //crea una lista con todos los nodos de cliente	
                                        //NodeList telefonos = ;
                                        
                                        //Todo añadir telefonos emails
                     /*
                                        NodeList telefonos = elemento.getElementsByTagName("telefonos");
                                        
                                        if (telefonos.getLength()>0){
                                            System.out.println("Telefonos");
                                            
                                                Node nodoTelefono = telefonos.item(0); // obtener u nnodo
                                                Element elementoTelefono = (Element) nodoTelefono;
                                                NodeList listaNodoTelefonos = elementoTelefono.getElementsByTagName("telefono").item(0).getChildNodes();
                                                for (int j = 0; j < telefonos.getLength(); j++){
                                                    Node valorTelefono = (Node) listaNodoTelefonos.item(0);
                                                    System.out.println("telefono: "+ valorTelefono.getNodeValue());
                                             }
                                            
                                        } */
                                         
                                         
					

				}
			}
		} catch (Exception e) {e.printStackTrace();}
                
                return sb.toString();
	} // fin de main
		
	//obtener informaci�n de un nodo
	private static String getNodo(String etiqueta, Element elem)
	{
            
		NodeList nodo = elem.getElementsByTagName(etiqueta).item(0).getChildNodes();
		Node valornodo = (Node) nodo.item(0);
                if (valornodo == null){
                    System.out.println("Error al obtener el nodo ");
                    return "";
                }
		return valornodo.getNodeValue(); // devuelve el valor del nodo
          
	}
    

    public void crearXML(ArrayList<Cliente> clientes) {

        crearXML(clientes, XML_FILE);
    }

    public void crearXML(ArrayList<Cliente> cliente, String rutaArchivoXML) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation implementation = builder.getDOMImplementation();
            //Ahora creamos el objeto Document, lo que vendría a ser nuestra raíz del arbol XML y asignamos la version de XML.
            Document document = implementation.createDocument(null, "Clientes", null);
            document.setXmlVersion("1.0");

            for (Cliente c : cliente) {
                Element raiz = document.createElement("cliente"); //nodo
                document.getDocumentElement().appendChild(raiz);
                CrearElemento("dni", c.getnCliente(), raiz, document);
                CrearElemento("nombre", c.getNombre(), raiz, document);
                CrearElemento("apellidos", c.getApellidos(), raiz, document);
                if(c.getTelefono().size()>0){
                     Element telefonos = document.createElement("telefonos"); //nodo
                     raiz.appendChild(telefonos); //pegamos el elemento hijo a la raiz
                     for(String telefono:c.getTelefono()){
                      CrearElemento("telefono",telefono,telefonos,document);
                     }
      
                }
                if(c.getEmail().size()>0){
                    Element emails=document.createElement("emails");
                    raiz.appendChild(emails);
                    for(String email:c.getEmail()){
                        CrearElemento("email", email, emails, document);
                    }
                }
            }

            //guardamos el XML
            Source source = new DOMSource(document);
            Result result = new StreamResult(new java.io.File(rutaArchivoXML));
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

            
            Result console = new StreamResult(System.out);
            transformer.transform(source, console);

        } catch (ParserConfigurationException ex) {
            System.out.println("Error al crear el parser");
        } catch (TransformerException ex) {
            System.out.println("Error al crear el transformer");
        }

    }

    private static void CrearElemento(String datoCliente, String valor, Element raiz, Document document) {
        Element elem = document.createElement(datoCliente); //creamos el hijo
        Text text = document.createTextNode(valor); //damos valor
        raiz.appendChild(elem); //pegamos el elemento hijo a la raiz
        elem.appendChild(text); //pegamos el valor
    }

}
