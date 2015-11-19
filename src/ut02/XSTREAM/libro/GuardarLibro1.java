package ut02.XSTREAM.libro;

import java.io.File;
import java.util.Iterator;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.persistence.FilePersistenceStrategy;
import com.thoughtworks.xstream.persistence.PersistenceStrategy;
import com.thoughtworks.xstream.persistence.XmlArrayList;

/**
 *  @descrition Primera clase de prueba de XStream;
 *	@author Laura
 *  @date 29/4/2015
 *  @version 1.0
 *  @license GPLv3
 */

public class GuardarLibro1 {

	public static void main(String[] args) {
		
		//Mucho cuidado con pasar un nombre de directorio existente a new File. Si no existe, obtendremos NullPointerException
		//XStream no preparado para java.nio.file.Path
		PersistenceStrategy strategy = new 
				FilePersistenceStrategy(new File("res/xml/"));
		// creates the list:
		XmlArrayList lista = new XmlArrayList(strategy);
		//A�adimos un Libro
		Libro libro1=new Libro();
		libro1.setAutor("Stallman");
		libro1.setCopias(3);
		libro1.setEditorial("ies");
		libro1.setIsbn(2345);
		libro1.setPaginas(43);
		libro1.setTitulo("milibro");
		
		Libro libro2=new Libro();
		libro2.setAutor("Tessier");
		libro2.setCopias(3);
		libro2.setEditorial("ies");
		libro2.setIsbn(2345);
		libro2.setPaginas(43);
		libro2.setTitulo("miotrolibro");
		
		lista.add(libro1);		
		lista.add(libro2);		

		 

	}

}


