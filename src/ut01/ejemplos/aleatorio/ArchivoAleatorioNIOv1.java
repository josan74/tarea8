package ut01.ejemplos.aleatorio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardOpenOption.*;

public class ArchivoAleatorioNIOv1 {

	public static void main(String[] args) {
		String s = "I was here!\n";
		byte data[] = s.getBytes();
		ByteBuffer out = ByteBuffer.wrap(data);

		ByteBuffer copy = ByteBuffer.allocate(12);
		Path file = Paths.get("res/entrada2.txt");

		try (FileChannel fc = (FileChannel.open(file, READ, WRITE))) {
			// Leemos los primeros 12 bytes del archivo
			fc.read(copy);
			//Laura: en una comunicación por red no se podrían omitir los bucles de la versión NIO
			// Escribo "I was here!" al principio del archivo.
			fc.position(0);

			fc.write(out);
			out.rewind();

			// Me muevo al final del archivo. Copio los 12 primeros bytes al
			// final
			// Después escribo "I was here!" otra vez.
			long length = fc.size();
			fc.position(length - 1);
			copy.flip();

			fc.write(copy);

			fc.write(out);
		} catch (IOException x) {
			System.out.println("I/O Exception: " + x);
		}

	}

}
