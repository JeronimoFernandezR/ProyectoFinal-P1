package org.uniquindio.edu.co.poo.proyectofinalp1.model;

import java.util.ArrayList;

/*Clientes: Personas que poseen cuentas en el banco y pueden realizar transacciones.
 */

public class Cliente extends Persona{

    private int edad;
    private String direccion;
    private String telefono;
    private String ciudad;
    private ArrayList<Cuenta> cuentas;

    public Cliente(String nombre, String correo, String id,String contraseña, int edad, String direccion, String telefono, String ciudad) {
        super(nombre, correo, id);
        this.edad = edad;
        this.direccion = direccion;
        this.telefono = telefono;
        this.ciudad = ciudad;
        this.cuentas = new ArrayList<>();
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    /**
     * @return the cuentas
     */
    public ArrayList<Cuenta> getCuentas() {
        return cuentas;
    }

}
