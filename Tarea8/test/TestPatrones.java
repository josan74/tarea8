/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tarea8.Cliente;
import tarea8.Patrones;

/**
 *
 * @author Jose Angel
 */
public class TestPatrones {
    
   
    
    public TestPatrones() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
       
        
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
   
    
    @Test
    public void testCadenaTelefonosValidos(){
        String [] telefonos = {"674528528","915434543","987645676"};
       
        Patrones patrones = new Patrones();
        for(String telefono:telefonos){
            assertTrue(patrones.esTelefono(telefono));
        }
        
    }
    
     @Test
    public void testCadenaTelefonosInvalidos(){
        String [] telefonos = {"1","1234567890"};
       
        Patrones patrones = new Patrones();
        for(String telefono:telefonos){
            assertFalse(patrones.esTelefono(telefono));
        }
        
    }
    
    @Test
    public void testCadenaValida(){
        Patrones patrones = new Patrones();
        String cadena = "71437136X,\"carlos\",\"fernández\", 674528528, fernandez.carlos@gmail.com";
        Cliente prueba = new Cliente ("71437136X","carlos","fernández", "674528528", "fernandez.carlos@gmail.com");

        Cliente cliente = patrones.procesarDatos(cadena);
        assertEquals(prueba, cliente);
    }  
    
    @Test
    public void testCadenaValidaConVariosTelefonos(){
        Patrones patrones = new Patrones();
         String cadena = "71437136X,\"carlos\",\"fernández\", 674528528,915434543,987645676, fernandez.carlos@gmail.com";
        Cliente prueba = new Cliente ("71437136X","carlos","fernández", "674528528", "fernandez.carlos@gmail.com");
        prueba.setTelefono("915434543");
        prueba.setTelefono("987645676");
   
        Cliente cliente = patrones.procesarDatos(cadena);
        
        System.out.println("telefonos");
        System.out.println(prueba);
        System.out.println(cliente);
        assertEquals(prueba, cliente);
    } 
    
    @Test
    public void testCadenaValidaConVariosEmails(){
        Patrones patrones = new Patrones();
        String cadena = "71437136X,\"carlos\",\"fernández\", 674528528, fernandez.carlos@gmail.com, fernandez.carlos@hotmail.com";
        String cadenaEmailsRepetidos = "71437136X,\"carlos\",\"fernández\", 674528528, fernandez.carlos@hotmail.com,fernandez.carlos@gmail.com, fernandez.carlos@hotmail.com";

        Cliente prueba = new Cliente ("71437136X","carlos","fernández", "674528528", "fernandez.carlos@gmail.com");
        prueba.setEmail("fernandez.carlos@hotmail.com");
       
        assertEquals(prueba, patrones.procesarDatos(cadena));
        assertEquals(prueba, patrones.procesarDatos(cadenaEmailsRepetidos));
    }
    
    
    
    @Test
    public void testCadenaInvalidaUnSoloCampo(){
        Patrones patrones = new Patrones();
        String cadena = "71437136X";
        Cliente cliente = patrones.procesarDatos(cadena);
        assertEquals(new Cliente(), cliente);
    }
    
     @Test
    public void testCadenaNombreyApellidosValidos(){
        String [] nombres = {"\"carlos\"","\"pepe\"","'maria'"};
        Patrones patrones = new Patrones();
        for(String nombre:nombres){
            assertTrue(patrones.esNombreOApellido(nombre));
        }
       
    }
    
     @Test
    public void testCadenaNombreyApellidosNoValidos(){
        String [] nombres = {"carlos","maria","pepe"};
        Patrones patrones = new Patrones();
        for(String nombre:nombres){
            assertFalse(patrones.esNombreOApellido(nombre));
        }
       
    }
    
    @Test
    public void testCadenaDNIValidos(){
        String [] dnis = {"00000001A","09876543b","71437136X","71437136F","99999999a"};
        Patrones patrones = new Patrones();
        for(String dni:dnis){
            assertTrue(patrones.esNCliente(dni));
        }
       
    }
    
     @Test
    public void testCadenaDNIInvalidos(){
        String [] dnis = {"071437136","714371360Ñ","9875676542","100000000Z"};
       
        Patrones patrones = new Patrones();
        for(String dni:dnis){
            assertFalse(patrones.esNCliente(dni));
        }
        
    }
}
