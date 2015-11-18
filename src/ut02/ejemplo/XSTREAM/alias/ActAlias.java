package ut02.ejemplo.XSTREAM.alias;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;


/**
 *  @descrition Solución Actividad Transparencia 6
 *	@author Laura
 *  @date 29/4/2015
 *  @version 1.0
 *  @license GPLv3
 */

public class ActAlias {

	public static void main(String[] args) {
		
		//Instanciamos un objeto XStream: requiere un parser XML para funcionar
		
		XStream xstream1 = new XStream(new DomDriver());
		
		Libro libro1=new Libro();
		libro1.setAutor("Stallman");
		libro1.setCopias(3);
		libro1.setEditorial("ies");
		libro1.setIsbn(2345);
		libro1.setPaginas(43);
		libro1.setTitulo("milibro");
		
		//Convertimos el objeto persona a xml invocando el método toXML
		//Definimos alias de clases
		xstream1.alias("ejemplar", Libro.class);
								
		//Definimos alias de atributo
		xstream1.aliasField("escritor", Libro.class, "autor");
		String xml=xstream1.toXML(libro1);
		
		System.out.println(xml);		
		
		//Reconstruimos un objeto persona a partir del XML generado invocando el método fromXML
		Libro libro2=(Libro)xstream1.fromXML(xml);
		
		System.out.println(libro2.toString());
		

	}

}


