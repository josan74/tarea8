package ut02.ejemplo.xsl;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import java.io.*;

public class Convertidor {
	
		public static void convertidor(String docXML, String docXLS, File docHMTL) throws IOException{
			 FileOutputStream os = new FileOutputStream(docHMTL); // crear fichero HTML
             Source estilos = new StreamSource(docXLS); //fuente XLS
             Source datos = new StreamSource(docXML); //fuente XML
             Result result = new StreamResult(os); // resultado de la transformaci—n
             try{
                     
                     Transformer transformer =
                                     TransformerFactory.newInstance().newTransformer(estilos);
                     
                     transformer.transform(datos,  result); // obiente el HTML
             }
             catch (Exception e){System.err.println("Error: "+e);}
             finally { os.close();}
		}


	        public static void main(String[] args)  {
	                String hojaEstilo = "./xml/alumnosplantilla.xsl";
	                String datosAlumnos = "./xml/alumnos.xml";
	                File pagHTML = new File ("./html/mipagina.html");
	                try{
	                	convertidor(datosAlumnos,hojaEstilo,pagHTML);
	                }catch (IOException e) {}
	               
	        }
	}


