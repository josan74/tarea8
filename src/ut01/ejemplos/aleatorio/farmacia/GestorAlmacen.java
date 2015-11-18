package ut01.ejemplos.aleatorio.farmacia;


import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class GestorAlmacen {
	private File fichero;

	GestorAlmacen(String ruta) {
		fichero = new File(ruta);
	}
	
	public boolean modificarMed(Medicamento m) {
		RandomAccessFile raf = null;
		int posicion;
		boolean result = true; // termina bien
		posicion = (m.getCod() - 1) * Medicamento.TAM_REGISTRO;
		// si el fichero existe, lo aï¿½ado
				
		try {
			raf = new RandomAccessFile(fichero, "rw");

			// escribo en el registro que le corresponde a m
			raf.seek(posicion);			
			raf.writeInt(m.getCod());
			raf.writeChars(m.getNombre());
			raf.writeDouble(m.getPrecio());
			raf.writeInt(m.getCodProveedor());
			raf.writeInt(m.getStock());
			raf.writeInt(m.getStockMaximo());
			raf.writeInt(m.getStockMinimo());
			
			
		} catch (IOException io) {
			System.err.println("Error IOException");
			io.printStackTrace();
			result = false; // termina mal
		} finally {
			try {
				raf.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// devolvemos como termina
		
		
		
		return result;
	}

	/**
	 * Recibe un objeto Medicamento y lo guardar en un fichero de Acceso
	 * aleatorio en una posiciï¿½n libre
	 * 
	 * @param m
	 * @return Si lo hace correctamente devuelve true
	 */
	public boolean añadirMed(Medicamento m) {
		RandomAccessFile raf = null;
		int posicion;
		boolean result = true; // termina bien
		posicion = (m.getCod() - 1) * Medicamento.TAM_REGISTRO;
		// si el fichero existe, lo aï¿½ado
		if (fichero.exists()){
		
		try {
			raf = new RandomAccessFile(fichero, "rw");

			// escribo en el registro que le corresponde a m
			raf.seek(posicion);
			// comprobamos que estï¿½ vacio
			if (raf.readInt()==0){
			raf.seek(posicion);
			raf.writeInt(m.getCod());
			raf.writeChars(m.getNombre());
			raf.writeDouble(m.getPrecio());
			raf.writeInt(m.getCodProveedor());
			raf.writeInt(m.getStock());
			raf.writeInt(m.getStockMaximo());
			raf.writeInt(m.getStockMinimo());
			}
			else result = false;
		} catch (IOException io) {
			System.err.println("Error IOException");
			io.printStackTrace();
			result = false; // termina mal
		} finally {
			try {
				raf.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// devolvemos como termina
		
		}
		else result = false;
		
		return result;
	}

	public ArrayList<Medicamento> leerTodos() {
		RandomAccessFile raf = null;
		ArrayList<Medicamento> lista = new ArrayList<Medicamento>();
		Medicamento m;
		char[] nombre = new char[Medicamento.TAM_NOMBRE]; // 60 bytes
		String sNombre;
		char aux;
		double precio; // 8 bytes
		int cod; // 4 bytes
		int stock; // 4 bytes
		int stockMaximo; // 4 bytes
		int stockMinimo; // 4 bytes
		int codProveedor; // 4 bytes
		try {
			raf = new RandomAccessFile(fichero, "r");
			// leo hasta que termine el fichero
			while (raf.getFilePointer() < raf.length()) {
				// leo el codigo
				cod = raf.readInt();
				// leo los caracteres del nombre
				for (int i = 0; i < nombre.length; i++) {
					aux = raf.readChar();
					// si leo un caracter
					if ((int) aux != 0)
						nombre[i] = aux;
					// si leo 'relleno' pongo blancos
					else
						nombre[i] = ' ';
				}
				sNombre = new String(nombre);

				precio = raf.readDouble();
				codProveedor = raf.readInt();
				stock = raf.readInt();
				stockMaximo = raf.readInt();
				stockMinimo = raf.readInt();

				m = new Medicamento(cod, sNombre, precio, codProveedor, stock,
						stockMaximo, stockMinimo);

				lista.add(m);

			}
		} catch (IOException io) {
			System.err.println("Error IOException");
		} finally {
			try {
				raf.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return lista;
	}
	/**
	 * Devuelve un medicamente con el codigo
	 * Si no es vï¿½lido devuelve null
	 * @param codigo
	 * @return
	 */
	public Medicamento leer(int codigo) {
		RandomAccessFile raf = null;
		
		Medicamento m = null;
		char[] nombre = new char[Medicamento.TAM_NOMBRE]; // 60 bytes
		String sNombre;
		char aux;
		double precio; // 8 bytes
		int cod, stock, stockMaximo, stockMinimo, 
		codProveedor, posicion; // 4 bytes
		
		posicion = (codigo-1)*Medicamento.TAM_REGISTRO;
		try {
			raf = new RandomAccessFile(fichero, "r");
			// si es un cï¿½digo de registro de valido...
			if (posicion< raf.length() && posicion>=0) {
				// nos posicionamos
				raf.seek(posicion);
				// leo el codigo
				cod = raf.readInt();
				// leo los caracteres del nombre
				for (int i = 0; i < nombre.length; i++) {
					aux = raf.readChar();
					// si leo un caracter
					if ((int) aux != 0)
						nombre[i] = aux;
					// si leo 'relleno' pongo blancos
					else
						nombre[i] = ' ';
				}
				sNombre = new String(nombre);

				precio = raf.readDouble();
				codProveedor = raf.readInt();
				stock = raf.readInt();
				stockMaximo = raf.readInt();
				stockMinimo = raf.readInt();

				m = new Medicamento(cod, sNombre, precio, codProveedor, stock,
						stockMaximo, stockMinimo);

			}
		} catch (IOException io) {
			System.err.println("Error IOException");
		} finally {
			try {
				raf.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return m;
	}
	
	/**
	 * Recibe un objeto Medicamento y lo guardar en un fichero de Acceso
	 * aleatorio en una posiciï¿½n libre
	 * 
	 * @param m
	 * @return Si lo hace correctamente devuelve true
	 */
	public boolean borrarMed(int codigo ) {
		RandomAccessFile raf = null;
		int posicion;
		boolean result = true; // termina bien
		posicion = (codigo - 1) * Medicamento.TAM_REGISTRO;
		// si el fichero existe, lo aï¿½ado
		
		
		try {
			raf = new RandomAccessFile(fichero, "rw");
			raf.seek(posicion);
			raf.writeInt(0);
			StringBuffer buffer = new StringBuffer(" ");
			buffer.setLength(Medicamento.TAM_NOMBRE);
	
			raf.writeChars(buffer.toString());
			raf.writeDouble(0.0);
			raf.writeInt(0);
			raf.writeInt(0);
			raf.writeInt(0);
			raf.writeInt(0);
			
		} catch (IOException io) {
			System.err.println("Error IOException");
			io.printStackTrace();
			result = false; // termina mal
		} finally {
			try {
				raf.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// devolvemos como termina
		
		
		
		return result;
	}
	
	public ArrayList<Medicamento> buscarProv(int prov) {
		RandomAccessFile raf = null;
		ArrayList<Medicamento> lista = new ArrayList<Medicamento>();
		
		Medicamento m = null;
		char[] nombre = new char[Medicamento.TAM_NOMBRE]; // 60 bytes
		String sNombre;
		char aux;
		double precio; // 8 bytes
		int cod, stock, stockMaximo, stockMinimo, 
		codProveedor, posicion; // 4 bytes
		
		posicion = 0;
		try {
			raf = new RandomAccessFile(fichero, "r");
			// recorro todo el fichero
			while(posicion< raf.length()){			
				// nos posicionamos en el proveedor
				raf.seek(posicion+72);
				// leo el codigo
				codProveedor = raf.readInt();
				if(codProveedor == prov){
					raf.seek(posicion);
				cod = raf.readInt();				
				// leo los caracteres del nombre
				for (int i = 0; i < nombre.length; i++) {
					aux = raf.readChar();
					// si leo un caracter
					if ((int) aux != 0)
						nombre[i] = aux;
					// si leo 'relleno' pongo blancos
					else
						nombre[i] = ' ';
				}
				sNombre = new String(nombre);

				precio = raf.readDouble();
				codProveedor = raf.readInt();
				stock = raf.readInt();
				stockMaximo = raf.readInt();
				stockMinimo = raf.readInt();

				m = new Medicamento(cod, sNombre, precio, codProveedor, stock,
						stockMaximo, stockMinimo);
				lista.add(m);
				}
				posicion+=Medicamento.TAM_REGISTRO;

			}
		} catch (IOException io) {
			System.err.println("Error IOException");
		} finally {
			try {
				raf.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return lista;
	}

}
