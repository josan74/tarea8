package ut01.quijote;

import static org.junit.Assert.*;

import org.junit.Test;


public class TestQuijote {

	final static String fichero = "res/pruebas.txt";

	
	@Test
	public void test() {
		long actual = new FicherosTexto().countChars(fichero);
		long expected = 10;
		assertTrue(actual==expected);
	}

}
