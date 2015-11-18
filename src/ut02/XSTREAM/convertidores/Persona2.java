package ut02.XSTREAM.convertidores;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *  @descrition Clase que representa una persona
 *	@author Laura
 *  @date 29/4/2015
 *  @version 1.0
 *  @license GPLv3
 */

public class Persona2 {
	private String nombre;
	private String apellido;
	private Telefono telefono;
	private boolean mayorEdad;
	private float peso;
	private Date fecha;
	
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public float getPeso() {
		return peso;
	}
	public void setPeso(float peso) {
		this.peso = peso;
	}
	public boolean isMayorEdad() {
		return mayorEdad;
	}
	public void setMayorEdad(boolean mayorEdad) {
		this.mayorEdad = mayorEdad;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public Telefono getTelefono() {
		return telefono;
	}
	public void setTelefono(Telefono telefono) {
		this.telefono = telefono;
	}
	@Override
	public String toString() {
		DateFormat formatter = DateFormat.getDateInstance(DateFormat.FULL,
                new Locale("es"));
		return "Persona2 [nombre=" + nombre + ", apellido=" + apellido
				+ ", telefono=" + telefono.toString() + ", mayorEdad=" + mayorEdad
				+ ", peso=" + peso + ", fecha=" + formatter.format(fecha) + "]";
	}
	
	
	
	

}


