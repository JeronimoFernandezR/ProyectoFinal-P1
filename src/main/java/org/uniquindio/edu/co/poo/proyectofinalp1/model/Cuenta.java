package org.uniquindio.edu.co.poo.proyectofinalp1.model;

import java.util.ArrayList;

/*
 * Cuenta de ahorro: Diseñada para clientes que buscan acumular intereses sobre su saldo.
 */

public abstract class Cuenta {

    private String idCuenta;
    protected double saldo;
    protected Cliente cliente;
    private ArrayList<Transaccion> transacciones;

    public Cuenta(String idCuenta, double saldo, Cliente cliente, ArrayList<Transaccion> transacciones) {
        this.idCuenta = idCuenta;
        this.saldo = saldo;
        this.cliente = cliente;
        this.transacciones= new ArrayList<Transaccion>();
    }

    

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    /**
     * @return the cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * @return the transacciones
     */
    public ArrayList<Transaccion> getTransacciones() {
        return transacciones;
    }

    /**
     * @param transacciones the transacciones to set
     */
    public void setTransacciones(ArrayList<Transaccion> transacciones) {
        this.transacciones = transacciones;
    }

    /**
     * @return the idCuenta
     */
    public String getIdCuenta() {
        return idCuenta;
    }

    /**
     * @param idCuenta the idCuenta to set
     */
    public void setIdCuenta(String idCuenta) {
        this.idCuenta = idCuenta;
    }

   
}
