package ut02;

import java.io.Serializable;
import java.util.ArrayList;

public class Empleado implements Serializable{
    private static final long serialVersionUID = 4L;
	
	private String nombre;
	private String apellidos; 
	private String dni; 
	private String fechaNac;
	private int antiguedad;
	private ArrayList<String> direcciones;
	//private String direcciones;

	private int teleefonoFijo;
	private ArrayList<Integer> moviles;
	//private int moviles;

	public Empleado(String nombre, String apellidos, String dni,
			String fechaNac, int antiguedad, ArrayList<String> direcciones,
			int teleefonoFijo, ArrayList<Integer> moviles) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.dni = dni;
		this.fechaNac = fechaNac;
		this.antiguedad = antiguedad;
		this.direcciones = direcciones;
		this.teleefonoFijo = teleefonoFijo;
		this.moviles = moviles;
	}
	public String getNombre() {
		return nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public String getDni() {
		return dni;
	}
	public String getFechaNac() {
		return fechaNac;
	}
	public int getAntiguedad() {
		return antiguedad;
	}
	public ArrayList<String> getDirecciones() {
		return direcciones;
	}
	public int getTeleefonoFijo() {
		return teleefonoFijo;
	}
	public ArrayList<Integer> getMoviles() {
		return moviles;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public void setFechaNac(String fechaNac) {
		this.fechaNac = fechaNac;
	}
	public void setAntiguedad(int antiguedad) {
		this.antiguedad = antiguedad;
	}
	public void setDirecciones(ArrayList<String> direcciones) {
		this.direcciones = direcciones;
	}
	public void setTeleefonoFijo(int teleefonoFijo) {
		this.teleefonoFijo = teleefonoFijo;
	}
	public void setMoviles(ArrayList<Integer> moviles) {
		this.moviles = moviles;
	}
	
	@Override
	public String toString(){
		 return nombre;
	}
	

}
