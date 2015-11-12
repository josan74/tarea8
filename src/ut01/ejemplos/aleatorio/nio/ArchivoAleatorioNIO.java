package ut01.ejemplos.aleatorio.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardOpenOption.*;

/**
 * @descrition Clase que realiza acceso aleatorio a archivo con ByteBuffer y
 *             FileChannel de java.nio
 * @author Laura
 * @version 1.0
 * @license GPLv3
 */
public class ArchivoAleatorioNIO {

	public static void main(String[] args) {
		String s = "I was here!\n";
		byte data[] = s.getBytes();
		// Creo un buffer con la cadena a escribir
		ByteBuffer out = ByteBuffer.wrap(data);

		// Creo un buffer con la capacidad a leer
		ByteBuffer copy = ByteBuffer.allocate(12);
		Path file = Paths.get("entrada2.txt");

		// Abrimos el archivo para leer y escribir
		try (FileChannel fc = (FileChannel.open(file, READ, WRITE))) {
			// Leemos los primeros 12 bytes del archivo
			int nread;
			// Cuando read returns puede que todav�a no tenga los 12 bytes
			// porque java.nio es no bloqeuante
			// M�s capacidades pero m�s complejo el procesamiento
			// Cuando nread es -1 y no hay m�s bytes vacios en el buffer hemos
			// le�do todo
			do {
				nread = fc.read(copy);
			} while (nread != -1 && copy.hasRemaining());

			// Escribo "I was here!" al principio del archivo.
			fc.position(0);

			/*
			 * En este caso, yo he rellenado el buffer y s� que est�n todos los
			 * bytes, no har�a falta while Ver como v1 funciona correctamente.
			 * Pero en una comunicaci�n por red, los bytes pueden llegar a
			 * intervalos y tengo que comprobar si quedan por rececpcionar
			 */
			while (out.hasRemaining())
				fc.write(out);
			// La posici�n se establece a 0 sin cambiar el valor del l�mite.
			out.rewind();

			// Me muevo al final del archivo. Copio los 12 primeros bytes al
			// final
			// Despu�s escribo "I was here!" otra vez.
			long length = fc.size();
			fc.position(length - 1);
			/*Los buffer tienen:
			 *  - Capacidad: la cantidad de elementos m�xima del buffer. Siempre tiene un valor positivo, y no cambia durante la vida del objeto. 
			 *  Debe ser conocido al momento de inicializar el buffer.
    		 *	- L�mite: seg�n la documentaci�n de java.nio.Buffer, el l�mite es �el �ndice del primer elemento que no deber�a ser le�do ni escrito�. 
    		 *  Es un valor entre la posici�n y la capacidad del buffer. La idea es utilizarlo como marcador para que, 
    		 *  luego de terminada la escritura hacia el buffer, sepamos hasta donde lo podemos leer para obtener el array generado.
    		 *  - Posici�n: la posici�n actual dentro del bufffer. Es el �ndice del pr�ximo elemento a leer o escribir.
			 */
			
			/*prepara el buffer para una secuencia de lecturas, estableciendo el l�mite igual a la posici�n actual, 
			 * y la posici�n a cero. M�s all� de la capacidad m�xima del buffer, permite hacer operaciones sobre 
			 * la porci�n que utilizamos anteriormente
			 */
			copy.flip();
			// Cuando write returns puede que todav�a no hay escrito todo porque
			// java.nio es no bloqeuante
			// La explicaci�n del while es la misma que antes
			while (copy.hasRemaining())
				fc.write(copy);
			while (out.hasRemaining())
				fc.write(out);
			// TODO: verificar, lo he a�adido despu�s de probar
			fc.close();
		} catch (IOException x) {
			System.out.println("I/O Exception: " + x);
		}

	}

}
