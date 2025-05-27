/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uniquindio.edu.co.poo.proyectofinalp1.model;

import java.util.ArrayList;

/**
 * Clase que representa un banco. Contiene listas de usuarios, personas, cuentas y transacciones.
 */
public class Banco {

    private String nombre;
    private ArrayList<Usuario> usuarios;
    private ArrayList<Persona> personas; // Ahora una sola lista de personas
    private ArrayList<Cuenta> cuentas;
    private ArrayList<Transaccion> transacciones;

    /**
     * Constructor de la clase Banco.
     * @param nombre Nombre del banco.
     */
    public Banco(String nombre) {
        this.nombre = nombre;
        usuarios = new ArrayList<>();
        personas = new ArrayList<>();
        cuentas = new ArrayList<>();
        transacciones = new ArrayList<>();
    }

    /**
     * Constructor por defecto. El nombre será una cadena vacía.
     */
    public Banco() {
        this("");
    }

    /**
     * Obtiene el nombre del banco.
     * @return Nombre del banco.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del banco.
     * @param nombre Nombre del banco.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la lista de usuarios del banco.
     * @return Lista de usuarios.
     */
    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    /**
     * Establece la lista de usuarios del banco.
     * @param usuarios Lista de usuarios.
     */
    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    /**
     * Obtiene la lista de personas del banco.
     * @return Lista de personas.
     */
    public ArrayList<Persona> getPersonas() {
        return personas;
    }

    /**
     * Establece la lista de personas del banco.
     * @param personas Lista de personas.
     */
    public void setPersonas(ArrayList<Persona> personas) {
        this.personas = personas;
    }

    /**
     * Obtiene la lista de cuentas del banco.
     * @return Lista de cuentas.
     */
    public ArrayList<Cuenta> getCuentas() {
        return cuentas;
    }

    /**
     * Establece la lista de cuentas del banco.
     * @param cuentas Lista de cuentas.
     */
    public void setCuentas(ArrayList<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }

    /**
     * Obtiene la lista de transacciones del banco.
     * @return Lista de transacciones.
     */
    public ArrayList<Transaccion> getTransacciones() {
        return transacciones;
    }

    /**
     * Establece la lista de transacciones del banco.
     * @param transacciones Lista de transacciones.
     */
    public void setTransacciones(ArrayList<Transaccion> transacciones) {
        this.transacciones = transacciones;
    }

    // Métodos utilitarios para filtrar personas por tipo:
    public ArrayList<Cliente> getClientes() {
        ArrayList<Cliente> clientes = new ArrayList<>();
        for (Persona p : personas) {
            if (p instanceof Cliente) {
                clientes.add((Cliente) p);
            }
        }
        return clientes;
    }

    public ArrayList<Cajero> getCajeros() {
        ArrayList<Cajero> cajeros = new ArrayList<>();
        for (Persona p : personas) {
            if (p instanceof Cajero) {
                cajeros.add((Cajero) p);
            }
        }
        return cajeros;
    }

    public ArrayList<Administrador> getAdministradores() {
        ArrayList<Administrador> administradores = new ArrayList<>();
        for (Persona p : personas) {
            if (p instanceof Administrador) {
                administradores.add((Administrador) p);
            }
        }
        return administradores;
    }
}
