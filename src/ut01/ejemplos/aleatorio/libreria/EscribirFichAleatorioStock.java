package ut01.ejemplos.aleatorio.libreria;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.StringTokenizer;

public class EscribirFichAleatorioStock {

	private static final int TAMREG = 120;
	private static final int TAMTITULO = 50;
	private static final int MAX_LIBROS = 1999;

	private static int hash(int id) {
		/*
		 * String clave = String.valueOf(id); String medio = "0"; for (int i =
		 * 1; i < clave.length() - 1; i++) medio +=
		 * String.valueOf(clave.charAt(i));
		 */

		return id % MAX_LIBROS;
	}

	public static void crear(String ficheroAleatorio, int tam) {
		// declara el fichero de acceso aleatorio
		RandomAccessFile fileA = null;

		StringBuffer buffer = null;

		if (new File(ficheroAleatorio).exists())
			new File(ficheroAleatorio).delete();

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

	public static void escribir(String ficheroAleatorio,String ficheroColisiones, String ficheroCSV) {
		// declara el fichero de acceso aleatorio
		RandomAccessFile fileA = null;
		BufferedReader fileT = null;
		ObjectOutputStream fileC = null;
		String linea = "";

		long posicion = 0;
		int libre;

		try {
			fileA = new RandomAccessFile(ficheroAleatorio, "rw");
			fileT = new BufferedReader(new FileReader(ficheroCSV)); // Creamos el
			fileC = new ObjectOutputStream(Files.newOutputStream(Paths.get(ficheroColisiones)));													// flujo de
																	// entrada
			// arrays con los datos

			fileT.readLine();
			while ((linea = fileT.readLine()) != null) { // se va leyendo un

				Libro oStock = readStringStock(linea, ";");

				posicion = (hash(oStock.getBookId())) * TAMREG; // calculamos la

				if (posicion < fileA.length()) {

					fileA.seek(posicion);
					libre = fileA.readInt();

					if (libre == 0) {
						fileA.seek(posicion);

						writeStock(fileA, oStock);

					} else{
						System.out.println("colision!");
					    System.out.println(oStock);
					    writeStockColision(fileC,oStock);
					}

				} else {
					System.out.format("Error en el puntero %d ",hash(oStock.getBookId()));

					//fileA.seek(posicion);
					// System.out.println(bookId+"-"+ hash(bookId));
				}
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

	private static void writeStockColision(ObjectOutputStream fileC, Libro oStock) throws IOException {
		fileC.writeObject(oStock);
	}

	private static void writeStock(RandomAccessFile fileA, Libro oStock) throws IOException {
		StringBuffer buffer = null;

		fileA.writeInt(oStock.getBookId());
		buffer = new StringBuffer(oStock.getTitle());
		buffer.setLength(TAMTITULO);
		fileA.writeChars(buffer.toString());
		fileA.writeInt(oStock.getFkAuthor());
		fileA.writeInt(oStock.getFkPublisher());
		fileA.writeInt(oStock.getYear());
		fileA.writeInt(oStock.getStock());
	}

	private static Libro readStringStock(String linea, String string) {
		StringTokenizer tokens = new StringTokenizer(linea, ";");

		int bookId;
		String title;
		int fkAuthor;
		int year;
		int fkPublisher;
		int stock;
		Libro oStock = new Libro();

		try {
			bookId = Integer.parseInt(tokens.nextToken());
			title = tokens.nextToken();
			fkAuthor = Integer.parseInt(tokens.nextToken());
			fkPublisher = Integer.parseInt(tokens.nextToken());
			year = Integer.parseInt(tokens.nextToken());
			stock = Integer.parseInt(tokens.nextToken());
			oStock = new Libro(bookId, title, fkAuthor, year, fkPublisher, stock);

		} catch (Exception e) {

		}

		return oStock;
	}

	public static void main(String[] args) throws IOException {
		String fichero = "res/aleatorio/Libros-hash.csv";
		String ficheroAle = "res/aleatorio/Libros-hash.dat";
		String ficheroColAle = "res/aleatorio/Libros-Colision.dat";

		if (new File(ficheroAle).exists())
			new File(ficheroAle).delete();
		if (new File(ficheroColAle).exists())
			new File(ficheroColAle).delete();
		crear(ficheroAle, MAX_LIBROS);

		escribir(ficheroAle,ficheroColAle, fichero);

	}
}
