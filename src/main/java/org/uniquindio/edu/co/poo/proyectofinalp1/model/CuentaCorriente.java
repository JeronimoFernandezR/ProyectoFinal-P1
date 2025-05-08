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
public class CuentaCorriente extends Cuenta{
    
    private double capacidadSobregiro;
    
    public CuentaCorriente(String idCuenta, double saldo, Cliente cliente, ArrayList<Transaccion> transacciones, double capacidadSobregiro){
        super(idCuenta, saldo, cliente, transacciones);
        this.capacidadSobregiro = capacidadSobregiro;
    }

    /**
     * @return the capacidadSobregiro
     */
    public double getCapacidadSobregiro() {
        return capacidadSobregiro;
    }

    /**
     * @param capacidadSobregiro the capacidadSobregiro to set
     */
    public void setCapacidadSobregiro(double capacidadSobregiro) {
        this.capacidadSobregiro = capacidadSobregiro;
    }


    
}
