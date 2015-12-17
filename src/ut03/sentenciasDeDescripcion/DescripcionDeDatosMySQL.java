package ut03.sentenciasDeDescripcion;

import java.sql.*;

public class DescripcionDeDatosMySQL {

	public static void main(String[] args)

	{
		try {
			
			Connection conexion = null;
			
			// Cargar el driver

			Class.forName("org.mariadb.jdbc.Driver");

			// Establecer conexi�n:

			conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost/EJEMPLO", "EJEMPLO", "EJEMPLO");

			DatabaseMetaData dbmd = conexion.getMetaData(); // Creamos el objeto
			ResultSet resul = null;
			
			String nombre = dbmd.getDatabaseProductName();
			String driver = dbmd.getDriverName();
			String url = dbmd.getURL();
			String usuario = dbmd.getUserName();
			
			System.out.println("INFORMACI�N SOBRE LA BASE DE DATOS:");
			System.out.println("===================================");
			System.out.println("Nombre: "+nombre);
			System.out.println("Driver: "+driver);
			System.out.println("URL:"	 +url);	
			System.out.println("Usuario:"+usuario);
			
			//Obtener informaci�n de las tablas y vistas que hay
			
			resul = dbmd.getTables(null,  "ejemplo", null, null);
			
			
			while (resul.next()) {
				String catalogo = resul.getString(1); // Columna 1 que devuelve Resultset
				String esquema = resul.getString(2);
				String tabla = resul.getString(3);
				String tipo = resul.getString(4);
				System.out.println(tipo+" - Catalogo: "+ catalogo+ 
						", Esquema: "+ esquema+ ", Nombre: "+ tabla);
			}
			// Liberar recursos

		
			conexion.close(); // Cerrar conexi�n

		} catch (ClassNotFoundException cn) {
			cn.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();

		}
	}

}
