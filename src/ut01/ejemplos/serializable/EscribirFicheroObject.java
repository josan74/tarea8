package ut01.ejemplos.serializable;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class EscribirFicheroObject {
	
	public static void main(String[] args) {
		
		Path path = Paths.get("res/FichPersona.dat");
		Persona persona = null; //defino variable persona		
	
		String nombre[] = {"Daniel", "Javier", "Jorge", "Francisco", "Ismael", "Carlos"};
		int edades[] = {23,21,25,27,25,31};
		
		//Crea el flujo de salida
		try (OutputStream fileout =  Files.newOutputStream(path)){
			
			// Conecto el flujo de bytes al flujo de datos
			ObjectOutputStream dataOS = new ObjectOutputStream(fileout);			
			for (int i=0;i<edades.length; i++) { // recorremos los arrays)
				persona = new Persona(nombre[i],edades[i]); // creo el restaurante
				dataOS.writeObject(persona); //escribo restaurante en el fichero		
			}
		}			
		catch (FileNotFoundException fnfe) {
			System.err.println("El fichero "+path.getFileName()+" no se encuentra");	}
		catch (IOException ioe) {	
			System.err.println("Error de E/S");	}
		
	}
}
