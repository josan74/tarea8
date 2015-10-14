import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;


public class TestIbex {
	
	final static String ruta = "./fichero.csv";

	@Test
	public void testFechaNoExiste() {
		Float resultado = 0.0f;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String sFecha = "00000000";
		Date dFecha = null;
		try {
			dFecha = sdf.parse(sFecha);
		} catch (ParseException e) {
			
		}
		assertEquals(resultado, Ibex.getCloseValue(dFecha, ruta ),0.0001);
	}
	
	@Test
	public void testFechaNull(){
		float resultado= -1;
		assertEquals(resultado, Ibex.getCloseValue(null, ruta ),0.0001);

	}
	
	

}
