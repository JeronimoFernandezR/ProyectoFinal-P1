package org.uniquindio.edu.co.poo.proyectofinalp1.model;

/*
 * Usuarios
    Clientes: Personas que poseen cuentas en el banco y pueden realizar transacciones.
    Cajeros: Encargados de gestionar transacciones para los clientes.
    Administradores: Personal con acceso total para la gestión del banco.
 */

public abstract class Usuario {
    String nombre;
    String correo;
    String id;
    private String contraseña;

    public Usuario(String nombre, String correo, String id, String contraseña) {
        this.nombre = nombre;
        this.correo = correo;
        this.id = id;
        this.contraseña=contraseña;
        
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the contraseña
     */
    public String getContraseña() {
        return contraseña;
    }

    /**
     * @param contraseña the contraseña to set
     */
    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
    

}
