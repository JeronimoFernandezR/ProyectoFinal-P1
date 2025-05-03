/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uniquindio.edu.co.poo.proyectofinalp1.model;

/**
 *
 * @author river
 */
public class CuentaCorriente extends Cuenta{
    
    private double capacidadSobregiro;
    
    public CuentaCorriente(String id, double saldo, Cliente cliente, double capacidadSobregiro){
        super(id, saldo, cliente);
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
