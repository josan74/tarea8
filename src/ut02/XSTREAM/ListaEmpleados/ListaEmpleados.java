package ut02.XSTREAM.ListaEmpleados;

import java.util.ArrayList;
import java.util.List;

import ut02.ejemplo.DOM.empleado.Empleado;

public class ListaEmpleados {

	private List<Empleado> lista = new ArrayList<Empleado>();

	public void add(Empleado per) {
		lista.add(per);
	}

	public List<Empleado> getListaEmpleados() {
		return lista;
	}
}
