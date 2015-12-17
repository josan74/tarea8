package ut03.sentenciasDeDescripcion;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DescripcionDeDatos {

	public static void main(String[] args)

		{
		try {
				
			Connection conexion = null;
				
			// Cargar el driver

			Class.forName("org.sqlite.JDBC");

			// Establecer conexi�n:

			conexion = DriverManager.getConnection(	"jdbc:sqlite:db/ejemplo.db");

			DatabaseMetaData dbmd = conexion.getMetaData();

	        // Creamos el objeto
			ResultSet resul = null;
				
			String nombre = dbmd.getDatabaseProductName();
			String driver = dbmd.getDriverName();
			String url = dbmd.getURL();
			//String usuario = dbmd.getUserName();
				
			System.out.println("INFORMACION SOBRE LA BASE DE DATOS:");
			System.out.println("===================================");
			System.out.println("Nombre: "+nombre);
			System.out.println("Driver: "+driver);
			System.out.println("URL:"	 +url);	
			//System.out.println("Usuario:"+usuario);
				
			//Obtener informaci�n de las tablas y vistas que hay
				
			resul = dbmd.getTables(null,  "ejemplo", null, null);
				
				
			while (resul.next()) {
	        // Columna 1 que devuelve Resultset
				String catalogo = resul.getString(1); 
				String esquema = resul.getString(2);
				String tabla = resul.getString(3);
				String tipo = resul.getString(4);
				System.out.println(tipo+" - Catalogo: "+ catalogo+ 
					", Esquema: "+ esquema+ ", Nombre: "+ tabla);
				}
				// Liberar recursos
			
				
				System.out.println("COLUMNAS DE LAS TABLAS de EJEMPLO:");		
				System.out.println("===================================");
				ResultSet columnas=null;
				columnas = dbmd.getColumns(null, "ejemplo","department", null);

				while (columnas.next()) {
				String nombreCol = columnas.getString("COLUMN_NAME"); // getString(4)
				String tipoCol = columnas.getString("TYPE_NAME"); // getString(6)
				String tamCol = columnas.getString("COLUMN_SIZE"); // getString(7)
				String nula = columnas.getString("IS_NULLABLE"); // getString(18)
				       
				       System.out.println("Columna: " + nombreCol+ ", Tipo:"+ tipoCol + ", tama�o: "+ tamCol + ", �Puede ser Nula:?" + nula );
				}	
				
				conexion.close(); // Cerrar conexi�n



			} catch (ClassNotFoundException cn) {
				cn.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();

			}
		}
	}


