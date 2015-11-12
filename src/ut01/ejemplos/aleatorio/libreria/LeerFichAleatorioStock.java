package ut01.ejemplos.aleatorio.libreria;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;


public class LeerFichAleatorioStock {

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

	public static Libro leer(String fichero, String ficheroCol, int id) {
		// declara el fichero de acceso aleatorio
		// variables para leer los datos

		Libro libro = new Libro();
		int posicion = hash(id) * TAMREG; // para situarnos al principio

		try (RandomAccessFile file = new RandomAccessFile(fichero, "r")) {

			if (posicion < file.length()) { // recorremos los arrays

				file.seek(posicion);

				libro = readStock(file);
				
				if (libro.getBookId() != id){
					System.out.println("colision");
					libro = readColision(id, ficheroCol);
				}

				posicion += TAMREG; // nos situamos en el
									// siguiente registro
									// del empleado
				// Cada empleado ocupa 104 bytes (4+80+4+4+4+4)
				// Si he recorrido todos los bytes salimos del while
			}
		} catch (EOFException e) {

		} catch (IOException e) {

		} // nos posici�n

		return libro;

	}

	private static Libro readColision(int id, String file) {
		Libro libro = new Libro();
		try (ObjectInputStream dataIS = new ObjectInputStream(Files.newInputStream(Paths.get(file)))) {
			libro = (Libro) dataIS.readObject(); // leo la primera Persona
			while (libro != null & libro.getBookId() != id) {

				libro = (Libro) dataIS.readObject(); // leo cada Persona
			}
		} catch (IOException e) {

		} catch (ClassNotFoundException e) {

		}
		return libro;
	}

	public static void leerTodos(String fichero) {
		// declara el fichero de acceso aleatorio
		RandomAccessFile file = null;
		// variables para leer los datos

		Libro libro = new Libro();
		int posicion = 0; // para situarnos al principio

		try {
			file = new RandomAccessFile(fichero, "r");

			do { // recorremos los arrays

				file.seek(posicion);

				libro = readStock(file);

				System.out.println(libro);

				posicion += TAMREG; // nos situamos en el
									// siguiente registro
									// del empleado
				// Cada empleado ocupa 104 bytes (4+80+4+4+4+4)
				// Si he recorrido todos los bytes salimos del while
			} while (file.getFilePointer() != file.length());
		} catch (EOFException e) {
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // nos posici�n

		finally {
			try {
				file.close(); // cerramos el fichero
			} catch (IOException e) {
			}
			// cerramos el fichero
		}
	}

	private static Libro readStock(RandomAccessFile file) throws IOException {

		int bookId;
		String sTitle;
		int fkAuthor;
		int year;
		int fkPublisher;
		int stock;
		Libro oStock = new Libro();

		try {
			bookId = file.readInt();

			sTitle = readString(file, TAMTITULO);

			fkAuthor = file.readInt();
			year = file.readInt();
			fkPublisher = file.readInt();
			stock = file.readInt();

			oStock = new Libro(bookId, sTitle, fkAuthor, year, fkPublisher, stock);
		} catch (Exception e) {
		}

		return oStock;
	}

	private static String readString(RandomAccessFile file, int length) throws IOException {
		char title[] = new char[length];
		char aux;

		for (int i = 0; i < length; i++) {
			aux = file.readChar();// recorremos uno a uno los caracteres
									// del titulo
			if ((int) aux != 0)
				title[i] = aux;
			else
				title[i] = ' '; // los vamos guardando en el array
			// System.out.println((int)aux);
		}

		return new String(title);
	}

	public static void nuevo(String fichero, Libro libro) {
		StringBuffer buffer = null;// buffer para almacenar el titulo

		// declara el fichero de acceso aleatorio
		RandomAccessFile file = null;
		try {
			file = new RandomAccessFile(fichero, "rw");

			long posicion = (libro.getBookId() - 1) * TAMREG; // calculamos la
																// posici�n
			file.seek(posicion + 4); // nos posicionamos

			// file.writeInt(libro.getBookId()); // identificar el libro
			buffer = new StringBuffer(libro.getTitle());
			buffer.setLength(TAMTITULO);
			file.writeChars(buffer.toString());
			file.writeInt(libro.getFkAuthor());
			file.writeInt(libro.getFkPublisher());
			file.writeInt(libro.getYear());
			file.writeInt(libro.getStock());

		} catch (IOException e) {
		} finally {
			try {
				file.close();
			} catch (IOException e) {
			} // cerramos el fichero
		}
	}

	public static void main(String[] args) {

		String fichero = "res/aleatorio/Libros-hash.dat";
		Libro libro = new Libro(3, "El hobbit", 2, 2, 2000, 2);
		String ficheroColAle = "res/aleatorio/Libros-Colision.dat";

		// nuevo(fichero,libro);
		// leerTodos(fichero);
		libro = leer(fichero, ficheroColAle, 711789); // Wakefield
		System.out.println(libro);
		libro = leer(fichero, ficheroColAle, 388742); // Wakefield
		System.out.println(libro);

		
	}
}
