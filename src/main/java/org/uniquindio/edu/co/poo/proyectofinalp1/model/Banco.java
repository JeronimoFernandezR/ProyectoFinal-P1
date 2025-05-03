/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uniquindio.edu.co.poo.proyectofinalp1.model;

import java.util.ArrayList;

/**
 *
 * @author river
 */
public class Banco {
    
    private ArrayList<Usuario> usuarios;
    private ArrayList<Cuenta> cuentas;
    private ArrayList<Transaccion> transacciones;

    /**
     * @return the usuarios
     */
    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    /**
     * @return the cuentas
     */
    public ArrayList<Cuenta> getCuentas() {
        return cuentas;
    }

    /**
     * @return the transacciones
     */
    public ArrayList<Transaccion> getTransacciones() {
        return transacciones;
    }
    
}
