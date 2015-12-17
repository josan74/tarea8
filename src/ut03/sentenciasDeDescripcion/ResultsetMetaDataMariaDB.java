package ut03.sentenciasDeDescripcion;

import java.sql.*;

public class ResultsetMetaDataMariaDB {

	public static void main(String[] args)

	{
		try {
			ResultSet resul = null;
			Statement sentencia = null;
			Connection conexion = null;
			int nColumnas;
			String nula;
			// Cargar el driver

			// Class.forName("com.mysql.jdbc.Driver");
			Class.forName("org.mariadb.jdbc.Driver");

			// Establecer conexion:
			try {
				conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost/EJEMPLO", "EJEMPLO", "EJEMPLO");

			// Ejecutar sentencias SQL:
			
				sentencia = conexion.createStatement();
				resul = sentencia.executeQuery("SELECT * FROM empleados");
				ResultSetMetaData rsmd = resul.getMetaData();
				nColumnas = rsmd.getColumnCount();
				System.out.println("N�mero de columnas recuperadas "+ nColumnas);
				for (int i = 1; i <= nColumnas; i++) {
					System.out.println("Columna "+ i + ":");
					System.out.println("	Nombre : "+ rsmd.getColumnName(i));
					System.out.println("	Tipo : "+ rsmd.getColumnTypeName(i));
					if (rsmd.isNullable(i)==0) nula="NO"; else nula="SI";
					System.out.println("	Puede ser nula? :"+ nula);
					System.out.println("	M�ximo ancho de la columna: "+ 
							rsmd.getColumnDisplaySize(i));
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				// Liberar recursos
				try {
					resul.close(); // Cerrar ResultSet
					sentencia.close(); // Cerrar Statement
					conexion.close(); // Cerrar conexi�n
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		} catch (ClassNotFoundException cn) {
			cn.printStackTrace();
		}

	}
}