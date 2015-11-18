/**
 *Alumno.java
 *@author Laura Lozano
 *@version 1.0
 */
package ut02.XSTREAM.Serializados;



/**
 *  @descrition Clase que describe un Alumno para los ejercicios
 *	@author Laura
 *  @date 26/3/2015
 *  @version 1.0
 *  @license GPLv3
 */
public class Alumno {

	String nombre;
	String apellidos;
	int anoNacimiento;
	
	public Alumno(String nombre, String apellidos, int anoNacimiento) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.anoNacimiento = anoNacimiento;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public int getAnoNacimiento() {
		return anoNacimiento;
	}
	public void setAnoNacimiento(int anoNacimiento) {
		this.anoNacimiento = anoNacimiento;
	}
	@Override
	public String toString() {
		return "Alumno [nombre=" + nombre + ", apellidos=" + apellidos
				+ ", anoNacimiento=" + anoNacimiento + "]";
	}
	




	
}
