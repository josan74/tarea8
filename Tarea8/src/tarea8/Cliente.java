/*
 *La clase Cliente tendrá definidos los elementos que definen a un cliente
 */
package tarea8;

import java.util.ArrayList;

/**
 *
 * @author Jose Angel
 */
public class Cliente {
     Patrones patrones = new Patrones();
    //nCliente será el DNI o NIE
    private String nCliente;
    private String nombre;
    private String apellidos;
    //Al no saber cuantos elementos contendrá hacemos un ArrayList
    private ArrayList<String> listaTelefonos;
    private ArrayList<String> listaEmails;
    
    //constructor vacio
    public Cliente(){
        nCliente="";
        nombre="";
        apellidos="";
        listaTelefonos= new ArrayList<>();
        listaEmails = new ArrayList<>();
    }
    
    //constructor con datos
    public Cliente(String nCliente, String nombre, String apellidos, String telefono,String email){
      
        this.listaTelefonos= new ArrayList<>();
        this.listaEmails = new ArrayList<>();
         if(patrones.esTelefono(telefono))this.listaTelefonos.add(telefono);
         if(patrones.esEmail(email)) this.listaEmails.add(email);
        if(patrones.esNCliente(nCliente)) this.nCliente=nCliente;
        this.nombre=nombre;
        this.apellidos=apellidos;
    }

    public Cliente(String nCliente, String nombre, String apellidos, ArrayList<String>telefono, ArrayList<String>email){
        this.nCliente=nCliente;
        this.nombre=nombre;
        this.apellidos=apellidos;
        this.listaTelefonos=telefono;
        this.listaEmails=email;
    }

    public String getnCliente() {
        return nCliente;
    }

    public void setnCliente(String nCliente) {
        this.nCliente = nCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public ArrayList<String> getTelefono() {
        return listaTelefonos;
    }

    public void setTelefono(String telefono) {
       if(patrones.esTelefono(telefono))
        this.listaTelefonos.add(telefono);
    }

    public ArrayList<String> getEmail() {
        return listaEmails;
    }

    public void setEmail(String email) {
        if (patrones.esEmail(email))
        this.listaEmails.add(email);
    }

    @Override
    public String toString() {
        return "Cliente{" + "nCliente=" + nCliente + ", nombre=" + nombre + ", apellidos=" 
                + apellidos + ", telefono=" + listaTelefonos + ", email=" + listaEmails + '}';
    }
    
    @Override
    public boolean equals(Object o){
        if(this==o) return true;
        Cliente cliente = (Cliente) o;
        if(!this.nCliente.equals(cliente.getnCliente()) || 
           !this.nombre.equals(cliente.getNombre()) ||
           !this.apellidos.equals(cliente.getApellidos()))
            return false;
        for(String telefono:this.getTelefono()){
            if(!cliente.getTelefono().contains(telefono))
                return false;
        }
        for(String email:this.getEmail()){
            if(!cliente.getEmail().contains(email))
                return false;
        }
        
        return true;
    }
    
    
}
