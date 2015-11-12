package ut01.ejemplos.aleatorio.farmacia;


public class Tests {
	
	public static void main (String[] args){
		/*
	GestorAlmacen g = new GestorAlmacen("Farmacia.dat");
	
	
	Medicamento med = 
			new Medicamento(5, "aspirina",3.0, 0,1);
	
	g.añadirMed(med);
	
	g.añadirMed(new Medicamento(2, "parazetamol",4.0, 0,2));
	g.añadirMed(new Medicamento(1, "nolotil",2.0, 0,1));
	
	
	g.borrarMed(1);
	if(!g.añadirMed(new Medicamento(1, "prueba",2.0, 0,1))){
		System.err.println("no se ha podido añadir el medicamento");
	}
	else System.out.println("medicamento añadido con éxito");
	
	if (g.leer(1)!=null)
		System.out.println("cod 1 "+g.leer(1).toString());
	
	if (g.leer(3)!=null)
		System.out.println("cod 3 "+g.leer(3).toString());
	if (g.leer(-10)!=null)
		System.out.println("cod 10 "+g.leer(-10).toString());
	System.err.println("registro -10 no válido");
	if (g.leer(10)!=null)
		System.out.println("cod 10 "+g.leer(10).toString());
	System.err.println("registro 10 no válido");
	
	
	g.modificarMed(new Medicamento(2, "paracetamol",4.0, 1,0));
	g.modificarMed(new Medicamento(5, "aspirina",3.0, 1,0));
	
	for(Medicamento m : g.leerTodos()){
		System.out.println(m.toString());
	}
	
	*/
	
		System.out.println("17 es primo "+esPrimo(15));
	
	}
	
	public static boolean esPrimo(int numero){
		int divisor =2;
		boolean primo = true;

		while ((divisor <numero) && primo) {
			System.out.println(divisor);
			if(numero % divisor ==0)
			primo = false;
			divisor++;
		}
		return primo;

	}

}
