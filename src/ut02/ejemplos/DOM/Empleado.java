package ut02.ejemplos.DOM;

import java.io.Serializable;

public class Empleado implements Serializable{
    private static final long serialVersionUID = 4L;
	
	private int id;
    private String nombre;
	private int dep; 
	private double salario;
	
	
	
	public Empleado(){
		
	}
	
	
	public Empleado(int id, String nombre, int dep, double salario) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.dep = dep;
		this.salario = salario;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public int getDep() {
		return dep;
	}



	public void setDep(int dep) {
		this.dep = dep;
	}



	public double getSalario() {
		return salario;
	}



	public void setSalario(double salario) {
		this.salario = salario;
	}



	@Override
	public String toString(){
		 StringBuilder sb = new StringBuilder();
		 sb.append("Id ");
		 sb.append(id);
		 sb.append("\n");
		 sb.append("Nombre ");
		 sb.append(nombre);
		 sb.append("\n");
		 sb.append("Departamento ");
		 sb.append(dep);
		 sb.append("\n");
		 sb.append("Salario ");
		 sb.append(salario);
		 sb.append("\n");
		 return sb.toString();
	}
	

}
