package ut01.ejemplos.serializable;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class LeerFichObject {

	public static void main(String[] args) {
		
		Path path = Paths.get("res/FichPersona.dat");
		Persona persona = null; //defino variable persona		
		
		// Conecto el flujo de bytes al flujo de datos con un try with resources
		try(ObjectInputStream dataIS = new ObjectInputStream(Files.newInputStream(path))){
				
			persona = (Persona) dataIS.readObject(); //leo la primera Persona
			while (persona != null) {		           		  
			System.out.println("Nombre: "+ persona.getNombre()+" ,edad "+persona.getEdad());
			persona = (Persona) dataIS.readObject(); //leo cada Persona
			}
		} catch (IOException | ClassNotFoundException e) {
			System.err.println("Error al lerr el fichero "+path.getFileName());	}
		
	}
}

