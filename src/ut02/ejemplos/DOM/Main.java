package ut02.ejemplos.DOM;

import java.nio.file.Paths;
import java.util.ArrayList;


public class Main {

	public static void main(String[] args) {
		RandomEmpleado.importCSV(Paths.get("res/aleatorio/empleado.csv"), 
				Paths.get("res/aleatorio/employee.bin"));
		
		ArrayList<Empleado> empleados = 
				RandomEmpleado.getAll(Paths.get("res/aleatorio/employee.bin"));
	
		for(Empleado e: empleados){
			System.out.println(e);
		}
	}

}
