package org.uniquindio.edu.co.poo.proyectofinalp1.model;

/*
 * Cuenta de ahorro: Diseñada para clientes que buscan acumular intereses sobre su saldo.
 */

public abstract class Cuenta {

    protected Cliente cliente;
    protected String id;
    protected double saldo;

    public Cuenta(String id, double saldo, Cliente cliente) {
        this.id = id;
        this.saldo = saldo;
        this.cliente = cliente;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

   
}
