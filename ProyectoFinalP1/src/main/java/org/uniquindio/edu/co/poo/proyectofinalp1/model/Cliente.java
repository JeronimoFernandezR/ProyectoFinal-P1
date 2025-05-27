package org.uniquindio.edu.co.poo.proyectofinalp1.model;

/**
 * Clase que representa a un cliente del banco.
 * Los clientes son personas que poseen cuentas en el banco y pueden realizar transacciones.
 */
public class Cliente extends Persona {

    private int edad;
    private String direccion;
    private String telefono;
    private String ciudad;

    /**
     * Constructor de la clase Cliente.
     * @param nombre Nombre del cliente.
     * @param correo Correo del cliente.
     * @param id Identificador del cliente.
     * @param contraseña Contraseña del cliente.
     * @param edad Edad del cliente.
     * @param direccion Dirección del cliente.
     * @param telefono Teléfono del cliente.
     * @param ciudad Ciudad del cliente.
     */
    public Cliente(String nombre, String correo, String id, String contraseña, int edad, String direccion, String telefono, String ciudad) {
        super(nombre, correo, id);
        this.edad = edad;
        this.direccion = direccion;
        this.telefono = telefono;
        this.ciudad = ciudad;
    }

    /**
     * Obtiene la edad del cliente.
     * @return Edad del cliente.
     */
    public int getEdad() {
        return edad;
    }

    /**
     * Establece la edad del cliente.
     * @param edad Edad del cliente.
     */
    public void setEdad(int edad) {
        this.edad = edad;
    }

    /**
     * Obtiene la dirección del cliente.
     * @return Dirección del cliente.
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Establece la dirección del cliente.
     * @param direccion Dirección del cliente.
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Obtiene el teléfono del cliente.
     * @return Teléfono del cliente.
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Establece el teléfono del cliente.
     * @param telefono Teléfono del cliente.
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Obtiene la ciudad del cliente.
     * @return Ciudad del cliente.
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * Establece la ciudad del cliente.
     * @param ciudad Ciudad del cliente.
     */
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
}
