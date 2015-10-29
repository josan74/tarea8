package ut01.ejemplos.aleatorio;

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
			// Cuando read returns puede que todavía no tenga los 12 bytes
			// porque java.nio es no bloqeuante
			// Más capacidades pero más complejo el procesamiento
			// Cuando nread es -1 y no hay más bytes vacios en el buffer hemos
			// leído todo
			do {
				nread = fc.read(copy);
			} while (nread != -1 && copy.hasRemaining());

			// Escribo "I was here!" al principio del archivo.
			fc.position(0);

			/*
			 * En este caso, yo he rellenado el buffer y sé que están todos los
			 * bytes, no haría falta while Ver como v1 funciona correctamente.
			 * Pero en una comunicación por red, los bytes pueden llegar a
			 * intervalos y tengo que comprobar si quedan por rececpcionar
			 */
			while (out.hasRemaining())
				fc.write(out);
			// La posición se establece a 0 sin cambiar el valor del límite.
			out.rewind();

			// Me muevo al final del archivo. Copio los 12 primeros bytes al
			// final
			// Después escribo "I was here!" otra vez.
			long length = fc.size();
			fc.position(length - 1);
			/*Los buffer tienen:
			 *  - Capacidad: la cantidad de elementos máxima del buffer. Siempre tiene un valor positivo, y no cambia durante la vida del objeto. 
			 *  Debe ser conocido al momento de inicializar el buffer.
    		 *	- Límite: según la documentación de java.nio.Buffer, el límite es “el índice del primer elemento que no debería ser leído ni escrito”. 
    		 *  Es un valor entre la posición y la capacidad del buffer. La idea es utilizarlo como marcador para que, 
    		 *  luego de terminada la escritura hacia el buffer, sepamos hasta donde lo podemos leer para obtener el array generado.
    		 *  - Posición: la posición actual dentro del bufffer. Es el índice del próximo elemento a leer o escribir.
			 */
			
			/*prepara el buffer para una secuencia de lecturas, estableciendo el límite igual a la posición actual, 
			 * y la posición a cero. Más allá de la capacidad máxima del buffer, permite hacer operaciones sobre 
			 * la porción que utilizamos anteriormente
			 */
			copy.flip();
			// Cuando write returns puede que todavía no hay escrito todo porque
			// java.nio es no bloqeuante
			// La explicación del while es la misma que antes
			while (copy.hasRemaining())
				fc.write(copy);
			while (out.hasRemaining())
				fc.write(out);
			// TODO: verificar, lo he añadido después de probar
			fc.close();
		} catch (IOException x) {
			System.out.println("I/O Exception: " + x);
		}

	}

}
