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
public class CuentaEmpresarial extends Cuenta{
    
    private int NIT;
    
    public CuentaEmpresarial(String idCuenta, double saldo, Cliente cliente,ArrayList<Transaccion> transacciones, int NIT, String nombre){
        super(idCuenta, saldo, cliente,transacciones);
        this.NIT = NIT;
        
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
        
}

