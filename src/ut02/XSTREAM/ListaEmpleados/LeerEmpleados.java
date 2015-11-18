package ut02.XSTREAM.ListaEmpleados;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;

public class LeerEmpleados {

	public static List<Empleado> leerXML() throws IOException {

		XStream xstream = new XStream();

		xstream.alias("ListaEmpleados", ListaEmpleados.class);
		xstream.alias("Empleado", Empleado.class);
		xstream.addImplicitCollection(ListaEmpleados.class, "lista");

		ListaEmpleados listadoTodas = (ListaEmpleados) xstream
				.fromXML(new FileInputStream("empleados.xml"));
		System.out.println("N�mero de Empleados: "
				+ listadoTodas.getListaEmpleados().size());

		List<Empleado> listaEmpleados = new ArrayList<Empleado>();
		listaEmpleados = listadoTodas.getListaEmpleados();

		return listaEmpleados;

	}// fin main

	public static void main(String[] args) {

		List<Empleado> lista = null;
		try {
			lista = leerXML();
		} catch (IOException e) {
		}

		if (lista!=null)
		for (Empleado e : lista) {

			System.out.println(e.toString());
		}
		System.out.println("Fin de listado ....");
	}

} // fin LeerPersonas

