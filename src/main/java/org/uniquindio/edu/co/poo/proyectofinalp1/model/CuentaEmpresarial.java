/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uniquindio.edu.co.poo.proyectofinalp1.model;

/**
 *
 * @author river
 */
public class CuentaEmpresarial extends Cuenta{
    
    private int NIT;
    private String nombre;
    
    public CuentaEmpresarial(String id, double saldo, Cliente cliente, int NIT, String nombre){
        super(id, saldo, cliente);
        this.NIT = NIT;
        this.nombre = nombre;
    }

    /**
     * @return the NIT
     */
    public int getNIT() {
        return NIT;
    }

    /**
     * @param NIT the NIT to set
     */
    public void setNIT(int NIT) {
        this.NIT = NIT;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    
}

