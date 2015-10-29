package ut01.quijote;

public class Main {

	final static String fichero = "res/quijote.txt";

	public static void main(String[] args) {

		long startTime = System.currentTimeMillis();
		long total = 0;

		System.out.format("%s tiene %d caracteres",fichero,new FicherosTexto().countUTFChars(fichero));

		long stopTime = System.currentTimeMillis();
		long elapsedTime = stopTime - startTime;
		
		System.out.format("Ejecutado en %d milisegundos", elapsedTime);
	}

}
