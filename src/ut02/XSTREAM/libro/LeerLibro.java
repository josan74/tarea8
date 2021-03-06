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

public class LeerLibro {

	public static void main(String[] args) {
		
		//Mucho cuidado con pasar un nombre de directorio existente a new File. Si no existe, obtendremos NullPointerException
		//XStream no preparado para java.nio.file.Path
		PersistenceStrategy strategy = new FilePersistenceStrategy(new File("res/xml/"));
		// creates the list:
		XmlArrayList lista = new XmlArrayList(strategy);
		
		for(Iterator it = lista.iterator(); it.hasNext(); ) {
			Libro libro = (Libro) it.next();			
				System.out.println(libro.toString());
			
		}
		 

	}

}


