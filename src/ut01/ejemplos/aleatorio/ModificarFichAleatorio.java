package ut01.ejemplos.aleatorio;
import java.io.*;

public class ModificarFichAleatorio {

	
	final static int RECORD_SIZE = 36;	
	final static int NAME_POSITION= 4;
	final static int DEPARTMENT_POSITION = 4+20;
	final static int SALARY_POSITION = 4+20+8;
	
	public void modifySalaryEmployee(String path,  int id,	double salario) {
		// declara el fichero de acceso aleatorio
		RandomAccessFile sourceOut = null;

		try {
			sourceOut = new RandomAccessFile(new File(path), "rw");
			// arrays con los datos

			StringBuffer buffer = null;// buffer para almacenar apellido

			long posicion = (id - 1) * RECORD_SIZE; // calculamos la posición
			sourceOut.seek(posicion); // nos posicionamos
			posicion+=SALARY_POSITION; //Sumo el ID + el apellido + departamento
			sourceOut.writeDouble(4000.87);//modificamos el salario
		} catch (FileNotFoundException e) {

		} catch (IOException e) {

		} finally {
			try {
				sourceOut.close();
			} catch (IOException e) {
			} // cerramos el fichero
		}
	}
}

