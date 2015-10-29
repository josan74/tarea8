/**
 *Aula.java
 *@author Laura Lozano
 *@version 1.0
 */

package ut01.eje02;

import java.util.ArrayList;
import java.util.List;

/**
 *  @descrition Clase que describe un Aula de Alumnos para los ejercicios
 *	@author Laura
 *  @date 26/3/2015
 *  @version 1.0
 *  @license GPLv3
 */
public class Aula {
    private List<Alumno> alumnos;
    private int numalumnos; //atributo para controlar el número real de elementos que tiene nuestro almacén

    /**
     * Constructor del Almacén con un determinado tamano
     * @param tamano
     */
    public Aula(int tamano){
        alumnos=new ArrayList<Alumno>(tamano);
        numalumnos=0;
        
    }
    /**
     * Comprueba si el almacén está vacio
     * @return true si está vacio
     */
    public boolean estaVacio(){
        return alumnos.isEmpty();
    }

    /**
     * Comprueba si el almacén está lleno
     * @return
     */
    public boolean estaLLeno(){
        return numalumnos==alumnos.size();
    }
    /**
     * Anade un nuevo elemento al almacén si hay sitio
     * @param valor a anadir al almacén
     */
    public void add(Alumno alumno){
        if (!this.estaLLeno()){
            alumnos.add(alumno);
            numalumnos++;
        }
    }
    /**
     * Elimina un elemento del almacén si está en el almacen
     * @param valor
     * @return true si elimina el elemento, false en caso contrario
     */
    public boolean eliminar(Alumno alumno){
    	return alumnos.remove(alumno);
        
    }
   
   
    /**
     * Imprime por pantalla los elementos del almacén
     */
    public void informacionAlumnos(){
        System.out.println("El aula tiene los siguientes alumnos:");
        for (int j=0;j<alumnos.size();j++){
             System.out.println(alumnos.get(j).toString()+" ");
         }
    }
}
