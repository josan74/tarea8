package ut01.ejemplos.aleatorio;

import static java.nio.file.StandardOpenOption.READ;
import static java.nio.file.StandardOpenOption.WRITE;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;

public class EscribirFichAleatorio {

	final static int NAME_LENGTH = 10;
	// arrays con los datos

	static String nombre[] = { "Alejandro", "Patricia", "Nicolas", "Roberto",
			"Vladimir", "David", "Owen", "Carlos", "Miguel", " Javier",
			" David", " Issam", " Andrés", " Matías", " Daniel", " Guillermo" };
	// nombres

	static int dep[] = { 10, 20, 10, 10, 30, 30, 10, 20, 10, 10, 30, 30, 10, 20, 10,
			10 }; // departamentos
	static Double salario[] = { 1000.45, 2400.60, 3000.0, 1500.56, 2200.0, 1435.87,
			1000.45, 2400.60, 3000.0, 1500.56, 2200.0, 2200.0, 1435.87,
			1000.45, 2400.60, 3000.0, }; // salarios
	
	private  static void escribirBuffer( FileChannel fc  ,String cad) throws IOException{
		byte data[] = cad.getBytes();
		ByteBuffer out = ByteBuffer.wrap(data);
		fc.write(out);
	}	

	public static void writeEmployee(String path) {

		Path fichero = Paths.get(path);
		
		try (FileChannel fc = (FileChannel.open(fichero, READ, WRITE))) {
			
			for (int i = 0; i < dep.length; i++) {
				escribirBuffer(fc,Integer.toString((i + 1))); 
				escribirBuffer(fc,nombre[i]);
				escribirBuffer(fc,Integer.toString(dep[i])); 
				escribirBuffer(fc,Double.toString(salario[i]));// 
			}
			
		} catch (IOException e) {
			System.out.println("I/O Exception: " + e);

		}		
		
	}
}
