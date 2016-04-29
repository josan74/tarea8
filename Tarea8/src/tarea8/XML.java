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

    public String leerXML() {

        return leerXML(XML_FILE);
    }
    
    public ArrayList<Cliente> leerClientesXML() {

        return leerClientesXML(XML_FILE);
    }

    public ArrayList<Cliente> leerClientesXML(String ruta) {
        String sDNI="", sNombre="", sApellido="", sTelefono, sEmail;
        Cliente cliente;
        ArrayList<Cliente> clientes = new ArrayList<>(); 
        ArrayList<String> telefonos = new ArrayList<>();
        ArrayList<String> emails = new ArrayList<>();

        StringBuffer sb = new StringBuffer();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(ruta));
            document.getDocumentElement().normalize();

            System.out.println("Elemento raiz: "
                    + document.getDocumentElement().getNodeName());

            //crea una lista con todos los nodos de cliente			
            NodeList nlClientes = document.getElementsByTagName("cliente");

            //recorrer la lista
            for (int i = 0; i < nlClientes.getLength(); i++) {
                if (nlClientes.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    Element eCliente = (Element) nlClientes.item(i); // obtener u nnodo
                    sDNI = eCliente.getElementsByTagName("dni").item(0).getTextContent();                                  
                    sNombre = eCliente.getElementsByTagName("nombre").item(0).getTextContent();
                    sApellido = eCliente.getElementsByTagName("apellidos").item(0).getTextContent();
                    

                    if (eCliente.getElementsByTagName("telefono") != null && eCliente.getElementsByTagName("telefono").getLength() > 0) {
                        NodeList nlTelefonos = eCliente.getElementsByTagName("telefono");

                        System.out.format("Telefonos:%d ", nlTelefonos.getLength());
                        sb.append("Telefonos: \n");
                        for (int j = 0; j < nlTelefonos.getLength(); j++) {

                            if (nlTelefonos.item(j).getNodeType() == Node.ELEMENT_NODE) {
                                Element telefono = (Element) nlTelefonos.item(j); // obtener u nnodo
                                sTelefono = telefono.getTextContent();
                              telefonos.add(sTelefono);
                               
                            }
                        }
                    }

                    if (eCliente.getElementsByTagName("email") != null && eCliente.getElementsByTagName("email").getLength() > 0) {

                        NodeList nlEmails = eCliente.getElementsByTagName("email");

                        System.out.format("Emails:%d ", nlEmails.getLength());
                        sb.append("Emails: \n");
                        for (int j = 0; j < nlEmails.getLength(); j++) {

                            if (nlEmails.item(j).getNodeType() == Node.ELEMENT_NODE) {
                                Element email = (Element) nlEmails.item(j); // obtener u nnodo
                                sEmail = email.getTextContent();
                                emails.add(sEmail);
                               
                            }
                        }
                    }
                }
                
                if(!sDNI.equals(""))
                    cliente = new Cliente(sDNI,sNombre,sApellido,telefonos,emails);
            }
            
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return clientes;
    } // fin de main
    
    /*
71437136X,"carlos","fernández", 674528528,915434543,987645676, fernandez.carlos@gmail.com
     */
    public String leerXML(String ruta) {
        String sDNI, sNombre, sApellido, sTelefono, sEmail;
        StringBuffer sb = new StringBuffer();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(ruta));
            document.getDocumentElement().normalize();

            System.out.println("Elemento raiz: "
                    + document.getDocumentElement().getNodeName());

            //crea una lista con todos los nodos de cliente			
            NodeList clientes = document.getElementsByTagName("cliente");

            //recorrer la lista
            for (int i = 0; i < clientes.getLength(); i++) {
                if (clientes.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    Element cliente = (Element) clientes.item(i); // obtener u nnodo
                    sDNI = cliente.getElementsByTagName("dni").item(0).getTextContent();
                    System.out.println("DNI: " + sDNI);
                    sb.append("DNI: ");
                    sb.append(sDNI);
                    sb.append("\n");
                    sNombre = cliente.getElementsByTagName("nombre").item(0).getTextContent();
                    System.out.println("Nombre: " + sNombre);
                    sb.append("Nombre: ");
                    sb.append(sNombre);
                    sb.append("\n");
                    sApellido = cliente.getElementsByTagName("apellidos").item(0).getTextContent();
                    System.out.println("Apellidos: " + sApellido);
                    sb.append("Apellidos: ");
                    sb.append(sApellido);
                    sb.append("\n");

                    if (cliente.getElementsByTagName("telefono") != null && cliente.getElementsByTagName("telefono").getLength() > 0) {
                        NodeList nlTelefonos = cliente.getElementsByTagName("telefono");

                        System.out.format("Telefonos:%d ", nlTelefonos.getLength());
                        sb.append("Telefonos: \n");
                        for (int j = 0; j < nlTelefonos.getLength(); j++) {

                            if (nlTelefonos.item(j).getNodeType() == Node.ELEMENT_NODE) {
                                Element telefono = (Element) nlTelefonos.item(j); // obtener u nnodo
                                sTelefono = telefono.getTextContent();
                                System.out.println(sTelefono);
                                sb.append("\t: ");
                                sb.append(sTelefono);
                                sb.append("\n");
                            }
                        }
                    }

                    if (cliente.getElementsByTagName("email") != null && cliente.getElementsByTagName("email").getLength() > 0) {

                        NodeList nlEmails = cliente.getElementsByTagName("email");

                        System.out.format("Emails:%d ", nlEmails.getLength());
                        sb.append("Emails: \n");
                        for (int j = 0; j < nlEmails.getLength(); j++) {

                            if (nlEmails.item(j).getNodeType() == Node.ELEMENT_NODE) {
                                Element email = (Element) nlEmails.item(j); // obtener u nnodo
                                sEmail = email.getTextContent();
                                System.out.println(sEmail);
                                sb.append("\t: ");
                                sb.append(sEmail);
                                sb.append("\n");
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sb.toString();
    } // fin de main

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
                if (c.getTelefono().size() > 0) {
                    Element telefonos = document.createElement("telefonos"); //nodo
                    raiz.appendChild(telefonos); //pegamos el elemento hijo a la raiz
                    for (String telefono : c.getTelefono()) {
                        CrearElemento("telefono", telefono, telefonos, document);
                    }

                }
                if (c.getEmail().size() > 0) {
                    Element emails = document.createElement("emails");
                    raiz.appendChild(emails);
                    for (String email : c.getEmail()) {
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
