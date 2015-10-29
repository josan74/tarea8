package ut01.ejemplos.caracteres;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 *  @descrition Clase que lee  y escribe un archivo entero de caracteres con una única llamada a método
 *	@author Laura
 *  @version 1.0
 *  @license GPLv3
 */
public class ArchivosPequenosCaracteres {

	public static void main(String[] args) {
		Path entrada = Paths.get("res/entrada.txt");
		Path salida = Paths.get("res/salida.txt");
		//Lista de cadenas para leer las lineas
		List<String> fileLista;
		try {
			//Leemos de una vez un archivo entero de caracteres utilizando java.nio
			fileLista = Files.readAllLines(entrada,Charset.forName("UTF-8"));
			//Escribimos una vez un archivo entero de caracteres utilizando java.nio
			Files.write(salida, fileLista,Charset.forName("UTF-8"));
		} catch (IOException io) {
			System.err.println(io);
		}
	}

}
