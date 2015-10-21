import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;
/**
 * 
 * @author Carlos Tessier
 *
 */

public class Ibex {
	
	public static final int CAMPO_FECHA = 2;
	final static String RUTA ="res/bolsa.csv";

	/**
	 *  Recibe la ruta de un fichero de texto CSV 
	 *  con la información bursatil de la bolsa de Madrid 
	 *  http://www.bolsaes.com/historico-cotizaciones/%5EIBEX/ 
	 *  y 
	 *   en la fecha dada si existe 
	 *   
float getCloseValue(Date fecha, String path)
	 *
	 * @param fecha en forma (<DTYYYYMMDD>) 
	 * @param path por defecto sea res/bolsa.csv
	 * @return devuelve el valor de cierre de la bolsa (<close>)
	 * si la fecha no existe devolverá 0.0, si ocurriera algún error devolverá -1
	 * 
	 */
	static float getCloseValue(Date fecha, String path){
		String record;
		Path ruta = Paths.get(path);
		float close = 0.0f;
		if(fecha==null)
			return -1;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String sFecha = sdf.format(fecha);
		
		// Utilizo el paquete nio de Java y un try with resources
		// para cerrarlo automáticamente
		try (BufferedReader br = Files.newBufferedReader(ruta)){
			//BufferedReader br = new BufferedReader (new FileReader(path));
			
			while ( (record = br.readLine())!=null){
			
			ArrayList<String> lFields = leerCampos(record);
			
			try{
				if(sFecha.equals(lFields.get(CAMPO_FECHA))) {
					int CAMPO_CLOSE = 7;
					close = Float.parseFloat(lFields.get(CAMPO_CLOSE));
				}

			}
			catch(Exception e)
			{
				System.err.println("Erro al leer la linea");
			}
				
			}
		
		} catch (IOException e) {
			System.err.println("Error E/S");
			close = -1;
		}
		
		
		return close;
	}
	
	/**
	 * Método que me obtiene los campos de un fichero csv
	 * @param record
	 * @return
	 */
	private static ArrayList<String> leerCampos(String record) {
		ArrayList<String> lista = new ArrayList<>();
		StringTokenizer st = new StringTokenizer(record,",");
		while(st.hasMoreTokens()){
			lista.add(st.nextToken());
		}

		return lista;
	}

	static float getCloseValue(Date fecha){
		return getCloseValue(fecha,RUTA);
	}
	
	
}
