package ut01.ejemplos.aleatorio.libreria;

import java.io.*;

public class LeerFichAleatorioStock {

	private static final int TAMREG = 120;
	private static final int TAMTITULO = 50;
	
	private static int hash(int id){
		String clave = String.valueOf(id);
		String medio ="0";
		for(int i=1; i< clave.length()-1; i++)
			medio += String.valueOf(clave.charAt(i));
			
		
		return Integer.valueOf(medio);
	}

	public static void leerTodos(String fichero) {
		// declara el fichero de acceso aleatorio
		RandomAccessFile file = null;
		// variables para leer los datos

		int bookId = 0;
		char title[] = new char[TAMTITULO], aux;
		int fkAuthor = 0;
		int year = 0;
		int fkPublisher = 0;
		int stock = 0;
		int posicion = 0; // para situarnos al principio

		try {
			file = new RandomAccessFile(fichero, "r");

			do { // recorremos los arrays

				file.seek(posicion);

				bookId = file.readInt(); // leemos el id

				for (int i = 0; i < title.length; i++) {
					aux = file.readChar();// recorremos uno a uno los caracteres
											// del titulo
					if ((int) aux != 0)
						title[i] = aux;
					else
						title[i] = ' '; // los vamos guardando en el array
					// System.out.println((int)aux);
				}
				String titleS = new String(title); // convertimos a String el
													// array
				fkAuthor = file.readInt();// obtenemos
				fkPublisher = file.readInt();

				year = file.readInt();
				stock = file.readInt();

				System.out.println("id " + bookId + ":" + titleS + " "
						+ fkAuthor + " " + fkPublisher + "  " + year + "  "
						+ +stock);
				
				

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
		} // nos posición

		finally {
			try {
				file.close(); // cerramos el fichero
			} catch (IOException e) {
			}
			// cerramos el fichero
		}
	}

	public static void nuevo(String fichero, Stock libro) {
		StringBuffer buffer = null;// buffer para almacenar el titulo

		// declara el fichero de acceso aleatorio
		RandomAccessFile file = null;
		try {
			file = new RandomAccessFile(fichero, "rw");

			long posicion = (libro.getBookId() - 1) * TAMREG; // calculamos la
																// posición
			file.seek(posicion+4); // nos posicionamos

			//file.writeInt(libro.getBookId()); // identificar el libro
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
		Stock libro = new Stock(3,"El hobbit",2,2,2000,2);

		//nuevo(fichero,libro);
		leerTodos(fichero);
		
	}
}
