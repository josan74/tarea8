/*
 * En esta clase definiré los patrones
 */
package tarea8;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Jose Angel
 */
public class Patrones {


    /**
     * cadena está formada por un dni, un nombre, un apellido, y uno o mas telefonos y uno o mas emails
     * @param cadena
     * @return Devuelve un objeto cliente , si falla devolverá un cliente vacio
     */
    public Cliente procesarDatos(String cadena) {

        Cliente cliente = new Cliente();
        String dni="", nombre="", apellido="", campo;
        ArrayList<String> listaTelefonos = new ArrayList<>();
        ArrayList<String> listaEmails = new ArrayList<>();

        try {
            StringTokenizer st = new StringTokenizer(cadena.trim(), ",");
            // como mínimo debería tener un dni, nombre y apellido, eliminamos espacios vacios trim()            
            dni = st.nextToken().trim();
            nombre = st.nextToken().trim();
            apellido = st.nextToken().trim();
            //mientras existan más campos--telefonos, emails o basura
            while(st.hasMoreTokens()){
                campo = st.nextToken().trim();
                if(esTelefono(campo)) {
                    //miramos que no este repetido y si no es así lo incluimos
                    if(!listaTelefonos.contains(campo))
                        listaTelefonos.add(campo);
                }
                else if(esEmail(campo)) {
                    //miramos que no este repetido y si no es así lo incluimos
                    if(!listaEmails.contains(campo))
                    listaEmails.add(campo);
                }
            }

        } 
        catch (NoSuchElementException ex){
            System.err.println("Número de campos incorrecto ");
        }
        catch (Exception ex) {
            System.err.println("Error " + ex);
        }

        if(!esNCliente(dni)){
                System.err.format(" dni %s no es valido \n ",dni);

        } 
        else if(!esNombreOApellido(nombre)){
              System.err.format(" nombre %s no es valido \n ",nombre);

        }
        else if(!esNombreOApellido(nombre)){
              System.err.format(" apellido %s no es valido \n ",apellido);

        }
        //si no existen errores creamos un nuevo cliente
        else{
          cliente = new Cliente(dni,nombre.replace("\"", ""),apellido.replace("\"", ""),listaTelefonos,listaEmails);
        }
       
        
        return cliente;
    }

    public boolean esNCliente(String cadena) {
        /*
         DNI: 8 digitos y acabar en letra
         NIE: emprezar por X o Y 7 digitos y letra
         */

        Pattern patronDNI = Pattern.compile("^[XYxy]?[0-9]{8}[A-Za-z]$");
        Matcher m = patronDNI.matcher(cadena);
        return m.matches();

    }

    public boolean esNombreOApellido(String cadena) {
        /*
         Nombre y apellidos deben comenzar por " o ' y terminar por " o ' y contener al menos 2 caracteres
         */
        Pattern patronNombre = Pattern.compile("^[\"\'][A-ZÁÉÍÓÚÑa-záéíóúñ]{2,}[\"\']$");
        Matcher m = patronNombre.matcher(cadena);
        //System.out.println(cadena+":"+m.matches());
        return m.matches();
    }
    
    public boolean esTelefono(String cadena) {
        /*
         Telefono válido
         -Ha de tener 9 digitos y comenzar por 6-7 o 9
         */
        Pattern patronTelefono = Pattern.compile("^[679]+[0-9]{8}$");
        Matcher m = patronTelefono.matcher(cadena);
        return m.matches();
    }

    public boolean esEmail(String cadena) {
        /*
         MAIL VALIDO: Debe comenzar por numero o letra
         -Solo debe contener una @
         -El punto no puede ser ni el primer ni el último caracter
         -El dominio debe tener almenos 1 punto
         -No pueden existir 2 puntos o más juntos
         -No puede haber espacios en blanco
         */
        Pattern patronMail = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher m = patronMail.matcher(cadena);
        return m.matches();
    }
}
