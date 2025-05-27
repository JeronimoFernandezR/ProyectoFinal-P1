/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uniquindio.edu.co.poo.proyectofinalp1.model;

import java.util.ArrayList;

/**
 * Clase que representa un banco. Contiene listas de usuarios, clientes, cajeros, cuentas y transacciones.
 */
public class Banco {
    
    private ArrayList<Usuario> usuarios;
    private ArrayList<Cliente> clientes;
    private ArrayList<Cajero> cajeros;
    private ArrayList<Cuenta> cuentas;
    private ArrayList<Transaccion> transacciones;

    public Banco() {
        usuarios = new ArrayList<>();
        clientes = new ArrayList<>();
        cajeros = new ArrayList<>();
        cuentas = new ArrayList<>();
        transacciones = new ArrayList<>();
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
     * Obtiene la lista de clientes del banco.
     * @return Lista de clientes.
     */
    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    /**
     * Establece la lista de clientes del banco.
     * @param clientes Lista de clientes.
     */
    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }

    /**
     * Obtiene la lista de cajeros del banco.
     * @return Lista de cajeros.
     */
    public ArrayList<Cajero> getCajeros() {
        return cajeros;
    }

    /**
     * Establece la lista de cajeros del banco.
     * @param cajeros Lista de cajeros.
     */
    public void setCajeros(ArrayList<Cajero> cajeros) {
        this.cajeros = cajeros;
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
}
