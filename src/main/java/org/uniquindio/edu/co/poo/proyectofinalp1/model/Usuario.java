package org.uniquindio.edu.co.poo.proyectofinalp1.model;

import java.util.ArrayList;

/**
 * Clase que representa un usuario del sistema.
 * Los usuarios pueden ser clientes o empleados del banco.
 */
public class Usuario {

    private String nombreUsuario;
    private String contraseñaUsuario;
    private Persona persona;
    private ArrayList<Cuenta> listaCuentas;

    /**
     * Constructor de la clase Usuario.
     * @param nombreUsuario Nombre de usuario.
     * @param contraseñaUsuario Contraseña del usuario.
     * @param persona Persona asociada al usuario.
     * @param listaCuentas Lista de cuentas asociadas al usuario.
     */
    public Usuario(String nombreUsuario, String contraseñaUsuario, Persona persona, ArrayList<Cuenta> listaCuentas) {
        this.nombreUsuario = nombreUsuario;
        this.contraseñaUsuario = contraseñaUsuario;
        this.persona = persona;
        this.listaCuentas = new ArrayList<>();
    }

    /**
     * @return the nombreUsuario
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * @param nombreUsuario the nombreUsuario to set
     */
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    /**
     * @return the contraseñaUsuario
     */
    public String getContraseñaUsuario() {
        return contraseñaUsuario;
    }

    /**
     * @param contraseñaUsuario the contraseñaUsuario to set
     */
    public void setContraseñaUsuario(String contraseñaUsuario) {
        this.contraseñaUsuario = contraseñaUsuario;
    }

    /**
     * @return the persona
     */
    public Persona getPersona() {
        return persona;
    }

    /**
     * @param persona the persona to set
     */
    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public ArrayList<Cuenta> getListaCuentas() {
        return listaCuentas;
    }
   
}
