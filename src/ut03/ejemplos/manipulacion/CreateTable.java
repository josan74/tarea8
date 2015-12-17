package ut03.ejemplos.manipulacion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class CreateTable {
	
	public static void main(String[] args)
	{
		try {
			Statement sentencia = null;
			Connection conexion = null;
			// Cargar el driver
			Class.forName("org.sqlite.JDBC");

			// Establecer conexi�n:

			// Establecer conexi�n:
			try {
				conexion = DriverManager.getConnection(	"jdbc:sqlite:db/ejemplo.db");
				// Ejecutar sentencias SQL:
				System.out.println("Java Crear Tablas");
				
				sentencia = conexion.createStatement();
				
				String sql = "CREATE TABLE IF NOT EXISTS departamentos ( "
						+ "dept_no 	TINYINT(2) NOT NULL PRIMARY KEY,"
						+ "dnombre 	VARCHAR(15),"
						+ "loc 		VARCHAR(15));";
				
				String sql2 = "CREATE TABLE IF NOT EXISTS empleados ("
						+ "emp_no 	SMALLINT PRIMARY KEY, "
						+ "apellido 	VARCHAR(10),"
						+ "oficio 	VARCHAR(10),"
						+ "dir		SMALLINT,"
						+ "fecha_alt	DATE,"
						+ "salario	FLOAT(6,2),"
						+ "comision	FLOAT(6,2),"
						+ "dept_no	TINYINT(2) NOT NULL,"
						+ "FOREIGN KEY(dept_no) REFERENCES departamentos (dept_no))";
				
				System.out.println(sql);
				int filas = sentencia.executeUpdate(sql);
				System.out.println("Filas afectadas: "+filas);
				
				System.out.println(sql2);
				sentencia.executeUpdate(sql2);
				System.out.println("Filas afectadas: "+filas);
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				// Liberar recursos
				try {
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