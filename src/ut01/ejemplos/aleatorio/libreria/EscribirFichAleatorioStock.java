package ut01.ejemplos.aleatorio.libreria;

import java.io.*;
import java.util.StringTokenizer;

public class EscribirFichAleatorioStock {

	private static final int TAMREG = 120;
	private static final int TAMTITULO = 50;
	private static final int MAX_LIBROS = 1013;

	private static int hash(int id) {
		/*String clave = String.valueOf(id);
		String medio = "0";
		for (int i = 1; i < clave.length() - 1; i++)
			medio += String.valueOf(clave.charAt(i));
*/
		
		return id%MAX_LIBROS;
	}

	public static void crear(File ficheroAleatorio, int tam) {
		// declara el fichero de acceso aleatorio
		RandomAccessFile fileA = null;

		StringBuffer buffer = null;

		if (ficheroAleatorio.exists())
			ficheroAleatorio.delete();

		try {
			fileA = new RandomAccessFile(ficheroAleatorio, "rw");

			for (int i = 0; i < tam; i++) {
				fileA.writeInt(0);
				buffer = new StringBuffer("");
				buffer.setLength(TAMTITULO);
				fileA.writeChars(buffer.toString());
				fileA.writeInt(0);
				fileA.writeInt(0);
				fileA.writeInt(0);
				fileA.writeInt(0);
			}

		} catch (FileNotFoundException e) {

		} catch (IOException e) {

		} finally {
			try {
				fileA.close();
			} catch (IOException e) {
			} // cerramos el fichero
		}
	}

	public static void escribir(File ficheroAleatorio, String fichero) {
		// declara el fichero de acceso aleatorio
		RandomAccessFile fileA = null;
		BufferedReader fileT = null;
		String linea = "";
		StringTokenizer tokens;

		int bookId;
		String title;
		int fkAuthor;
		int year;
		int fkPublisher;
		int stock;
		StringBuffer buffer = null;
		long posicion = 0;
		int libre;
		int max = 0;

		try {
			fileA = new RandomAccessFile(ficheroAleatorio, "rw");
			fileT = new BufferedReader(new FileReader(fichero)); // Creamos el
																	// flujo de
																	// entrada
			// arrays con los datos

			fileT.readLine();
			while ((linea = fileT.readLine()) != null) { // se va leyendo un
															// carï¿½cter
				tokens = new StringTokenizer(linea, ";");

				bookId = Integer.parseInt(tokens.nextToken());

				posicion = (hash(bookId) - 1) * TAMREG; // calculamos la
														// posición

				if (posicion > fileA.length()) {
					
					title = tokens.nextToken();
					fkAuthor = Integer.parseInt(tokens.nextToken());
					fkPublisher = Integer.parseInt(tokens.nextToken());
					year = Integer.parseInt(tokens.nextToken());
					stock = Integer.parseInt(tokens.nextToken());

					fileA.seek(posicion);
					libre = fileA.readInt();

					if (libre == 0) {
						fileA.seek(posicion);

						System.out.println(bookId + ":" + title + " "
								+ fkAuthor + " " + year + "  " + fkPublisher
								+ "  " + stock);

						fileA.writeInt(bookId);
						buffer = new StringBuffer(title);
						buffer.setLength(TAMTITULO);
						fileA.writeChars(buffer.toString());
						fileA.writeInt(fkAuthor);
						fileA.writeInt(fkPublisher);
						fileA.writeInt(year);
						fileA.writeInt(stock);
					} else
						System.out.println("colision!");

				} else
					System.out.println("Error en el puntero " + hash(bookId) );
				fileA.seek(posicion);
				// System.out.println(bookId+"-"+ hash(bookId));

			}

		} catch (FileNotFoundException e) {
			System.out.println("FIchero no encontrado");

		} catch (IOException e) {
			System.out.println("Error E/S");
		} finally {
			try {
				fileA.close();
				fileT.close();
			} catch (IOException e) {
			} // cerramos el fichero
		}
	}

	public static void main(String[] args) throws IOException {
		String fichero = "res/aleatorio/Libros-hash.csv";
		File ficheroAle = new File("res//aleatorio/Libros-hash.dat");

		if (ficheroAle.exists())
			ficheroAle.delete();

		crear(ficheroAle, MAX_LIBROS);

		escribir(ficheroAle, fichero);

	}
}
