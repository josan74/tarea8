package ut01.quijote;

import static org.junit.Assert.*;

import org.junit.Test;


public class TestQuijote {

	final static String fichero = "res/pruebas.txt";
	final static String ficheroP = "res/palabras.txt";

	
	@Test
	public void testCountChars() {
		long actual = new FicherosTexto().countChars(fichero);
		long expected = 12;
		assertTrue(actual==expected);
	}
	
	@Test
	public void countAlphabeticChars() {
		long actual = new FicherosTexto().countAlphabeticChars(fichero);
		long expected = 5;
		assertTrue(actual==expected);
	}
	
	@Test
	public void countWords() {
		long actual = new FicherosTexto().countWords(ficheroP);
		long expected = 3;
		assertTrue(actual==expected);
	}

}
