/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea8;

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

    public void crearXML(ArrayList<Cliente> cliente) {
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
            Result result = new StreamResult(new java.io.File("clientes.xml"));
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
