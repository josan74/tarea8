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
public class Parte01 {

	// la ruta no tiene que existir
	final static String RUTA ="C:/Users/joe/foo"; //Windows
	// final static String RUTA ="/home/joe/foo"  //Mac y Linux



	public static void main(String[] args) {
		
	
		Path path = Paths.get(RUTA);		
	
		System.out.println();
		System.out.println("toString: "+ path.toString());
		System.out.println("getFileName: "+ path.getFileName());
		System.out.println("getName(0): "+ path.getName(0));
		System.out.println("getNameCount:"+ path.getNameCount());
		System.out.println("subpath(0,2): "+ path.subpath(0,2));
		System.out.println("getParent: "+ path.getParent());
		System.out.println("getRoot: "+path.getRoot());
	}

}
