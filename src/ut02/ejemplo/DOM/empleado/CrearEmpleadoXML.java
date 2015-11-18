package ut02.ejemplo.DOM.empleado;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.io.*;

public class CrearEmpleadoXML {
	
	// Inserción de los datos del empleado
	static void CrearElemento(String datoEmple, String valor,Element raiz, Document document){
		Element elem = document.createElement(datoEmple); // creamos hijo
		Text text = document.createTextNode(valor); //damos valor
		raiz.appendChild(elem); //pegamos el elemento hijo a la raíz
		elem.appendChild(text); // pegamos el valor
	}

	public static void main(String[] args) throws IOException{
		File fichero = new File ("D:/ficheros/Employee.dat");
		// declara el fichero de acceso aleatorio
		RandomAccessFile file = new RandomAccessFile(fichero, "r");
		//variables para leer los datos
		int id, dep, posicion=0;
		Double salario;
		char nombre[] = new char[10], aux;
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try{
			DocumentBuilder builder = factory.newDocumentBuilder();
			
			DOMImplementation implementation = builder.getDOMImplementation();
			Document document = implementation.createDocument(null, "Empleados",null);
			document.setXmlVersion("1.0"); //asignamos la versión de nuestro XML
			
			do{ // recorremos los arrays
				file.seek(posicion); //nos posición 
				id=file.readInt(); //leemos el id de empleado
				for (int i = 0; i < nombre.length; i++){
					aux = file.readChar();//recorremos uno a uno los caracteres del apellido
					if ((int)aux!=0) nombre[i] = aux;
					else nombre[i]=' '; //los vamos guardando en el array
					//System.out.println((int)aux);
				}	
				String nombreS = new String(nombre); // convertimos a String el array
				dep=file.readInt();//obtenemos el dep
				salario=file.readDouble(); //obtenemos el salario
				
				if(id>0){ //validamos a partir de 1
					
					Element raiz = document.createElement("empleado"); // nodo empleado
					document.getDocumentElement().appendChild(raiz); // lo pegamos a la raíz del documento
					
					CrearElemento("id",Integer.toString(id), raiz, document);// Añadir ID
					CrearElemento("nombre",nombreS.trim(),raiz, document);// Añadir apellido
					CrearElemento("dep",Integer.toString(dep),raiz,document); //Añadir departamento
					CrearElemento("salario",Double.toString(salario), raiz, document); // Añadir salario
				}
				
				posicion = posicion + 36; // vamos al siguiente registro
				// Si he recorrido todos los bytes salimos del while
				} while (file.getFilePointer()!=file.length());
			
			Source source = new DOMSource(document);
			
			Result result = new StreamResult(new java.io.File(".\\src\\uem\\ficheros\\Empleados.xml"));
			
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			
			transformer.transform(source,  result);		
			
			Result console = new StreamResult(System.out);
			transformer.transform(source, console);
			
		} catch(Exception e) {System.err.println("Error: "+e);}
		file.close(); //cerramos el fichero
	}

}

