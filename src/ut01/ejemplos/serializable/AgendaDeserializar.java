package ut01.ejemplos.serializable;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *  @descrition Clase que deserializa un objeto Agenda a partir de un archivo
 *	@author Laura
 *  @version 1.0
 *  @license GPLv3
 */
public class AgendaDeserializar {	
	public static void main(String[] args) {
		Agenda a1 = null;
		Agenda a2 = null;
		Path entrada = Paths.get("agenda.txt");
		try {
			InputStream istream = Files.newInputStream(entrada);
			//FileOutputStream fs = new FileOutputStream("agenda.txt");
			ObjectInputStream os = new ObjectInputStream(istream);
			
			// El método readObject() recupera el objeto
			a1 = (Agenda) os.readObject();
			a2 = (Agenda) os.readObject();
			System.out.println(a1.getNombre() + " " + a2.getNombre());
			
			os.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}