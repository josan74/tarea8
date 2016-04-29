/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea8;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Carlos
 */
public class Ficheros {

    public static ArrayList<Cliente>  leerFichero(File fichero) {
        ArrayList<Cliente> clientes = new ArrayList<>();
        Patrones patron = new Patrones();
        Cliente cliente;
        String sCliente;

        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {

            while ( (sCliente = br.readLine())!=null) {
                cliente = patron.procesarDatos(sCliente);
                if (!cliente.getnCliente().equals("")) 
                    clientes.add(cliente);
                
            }
            
        } catch (EOFException ex) {
                // termina de leer el fichero
        } catch (FileNotFoundException ex) {
            System.err.println("Fichero no encontrado");
        } catch (IOException ex) {
            System.err.println("Error de entrada y salida");
        }
        
        return clientes;
    }

}
