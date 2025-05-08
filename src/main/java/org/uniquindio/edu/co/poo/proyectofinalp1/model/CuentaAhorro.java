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
public class CuentaAhorro extends Cuenta{
    
    private double tasaInteres;
    
    public CuentaAhorro(String idCuenta, double saldo, Cliente cliente, ArrayList<Transaccion> transacciones, double tasaInteres){
        super(idCuenta, saldo, cliente,transacciones );
        this.tasaInteres = tasaInteres;
    }

    /**
     * @return the tasaInteres
     */
    public double getTasaInteres() {
        return tasaInteres;
    }

    /**
     * @param tasaInteres the tasaInteres to set
     */
    public void setTasaInteres(double tasaInteres) {
        this.tasaInteres = tasaInteres;
    }
    
    // Método para calcular y aplicar intereses al saldo
    public void aplicarInteres() {
        double interes;
        if( tasaInteres < 1 && tasaInteres > 0 ){
            interes = saldo * tasaInteres;
        }else{
            interes = this.saldo * (tasaInteres / 100);
        }
        this.saldo += interes;
        System.out.println("Interés aplicado: " + interes + ". Nuevo saldo: " + saldo);
    }
}
