package ut01.eje01;


import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *  @descrition Solución Ejercicio 1
 *	@author Carlos
 *  @date 21/10/2015
 *  @version 1.0
 *  @license GPLv3
 */
public class Parte02 {
	
	final static String RUTA1 ="C:/Users/./joe/foo"; 
	final static String RUTA2 ="C:/Users/joe/../something/foo"; 

	public static void main(String[] args) {
		
		Path path1 = Paths.get(RUTA1);
		Path path2 = Paths.get(RUTA2);
		//El método normalize es el que elimina las redundancias
		System.out.println(path1.normalize());
		System.out.println(path2.normalize());

	}

}
