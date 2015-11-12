package ut02;

import java.io.*;
import java.util.ArrayList;

import com.thoughtworks.xstream.XStream;

public class EscribirEmpleadosXML {

	public static void crearXML(ListaEmpleados listaper) throws IOException,
			ClassNotFoundException {

		System.out
				.println("Comienza el proceso de creaci—n del fichero a XML...");

		try {

			XStream xstream = new XStream();

			// cambiar de nombre a las etiquetas XML
			xstream.alias("ListaEmpleados", ListaEmpleados.class);
			xstream.alias("Empleado", Empleado.class);
			// quitar etiqueta lista (Atributo de la clase ListaEmpleados)
			xstream.addImplicitCollection(ListaEmpleados.class, "lista");
			// Insertar los objetos en XML
			xstream.toXML(listaper, new FileOutputStream("empleado.xml"));
			System.out.println("Creado fichero XML....");

		} catch (Exception e) {
		}

	}

	public static void main(String[] args) {
	               
	        	ListaEmpleados listaper = new ListaEmpleados();
	        	ArrayList<String> direcciones; 
	        	ArrayList<Integer> telefonos; 

	        	direcciones = new ArrayList<String>();
	        	direcciones.add("calle melancolia");
	        	direcciones.add("calle de la alegria");
	        	telefonos = new ArrayList<Integer>();
	        	telefonos.add(654838222);
	        	telefonos.add(600004320);
	        	 listaper.add(new Empleado("Pepe","Garcia","70987456V",
	        			"1/1/1980",5,direcciones,915564837,telefonos));
	        	
	        	direcciones =new ArrayList<String>();
	        	direcciones.add("calle sombrereria");
	        	telefonos = new ArrayList<Integer>();
	        	telefonos.add(655438490);
	        	listaper.add(new Empleado("Maria","Fern‡ndez","60300000X",
	        			"1/1/1985",2,direcciones, 910575464,telefonos));
	        	
	        	try {
					crearXML(listaper);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
	        
	        }// fin del main
}// fin EscribirPersonas}

