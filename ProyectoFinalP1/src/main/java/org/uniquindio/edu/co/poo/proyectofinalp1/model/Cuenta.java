package org.uniquindio.edu.co.poo.proyectofinalp1.model;

import java.util.ArrayList;
import java.util.Date;

/**
 * Clase abstracta que representa una cuenta bancaria.
 * Las cuentas están asociadas a un cliente y permiten realizar transacciones.
 */
public abstract class Cuenta implements Depositable {

    private String idCuenta;
    protected double saldo;
    protected Cliente cliente;
    private ArrayList<Transaccion> transacciones;

    /**
     * Constructor de la clase Cuenta.
     * @param idCuenta Identificador único de la cuenta.
     * @param saldo Saldo inicial de la cuenta.
     * @param cliente Cliente asociado a la cuenta.
     * @param transacciones Lista de transacciones realizadas en la cuenta.
     */
    public Cuenta(String idCuenta, double saldo, Cliente cliente, ArrayList<Transaccion> transacciones) {
        this.idCuenta = idCuenta;
        this.saldo = saldo;
        this.cliente = cliente;
        this.transacciones = new ArrayList<>();
    }

    /**
     * Obtiene el saldo de la cuenta.
     * @return Saldo de la cuenta.
     */
    public double getSaldo() {
        return saldo;
    }

    /**
     * Establece el saldo de la cuenta.
     * @param saldo Saldo de la cuenta.
     */
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    /**
     * Obtiene el cliente asociado a la cuenta.
     * @return Cliente asociado.
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Obtiene la lista de transacciones realizadas en la cuenta.
     * @return Lista de transacciones.
     */
    public ArrayList<Transaccion> getTransacciones() {
        return transacciones;
    }

    /**
     * Establece la lista de transacciones realizadas en la cuenta.
     * @param transacciones Lista de transacciones.
     */
    public void setTransacciones(ArrayList<Transaccion> transacciones) {
        this.transacciones = transacciones;
    }

    /**
     * Obtiene el identificador único de la cuenta.
     * @return ID de la cuenta.
     */
    public String getIdCuenta() {
        return idCuenta;
    }

    /**
     * Establece el identificador único de la cuenta.
     * @param idCuenta ID de la cuenta.
     */
    public void setIdCuenta(String idCuenta) {
        this.idCuenta = idCuenta;
    }

    /**
     * Realiza un depósito en la cuenta.
     * @param monto Monto a depositar.
     */
    @Override
    public void depositar(double monto, Cajero cajero) {
        if (monto > 0) {
            saldo += monto;
            transacciones.add(new Transaccion(null, this, monto, "DEP" + System.currentTimeMillis(), new Date(), cajero));
        }
    }

    /**
     * Realiza un retiro de la cuenta si hay saldo suficiente.
     * @param monto Monto a retirar.
     * @return `true` si el retiro fue exitoso, `false` en caso contrario.
     */
    @Override
    public boolean retirar(double monto, Cajero cajero) {
        if (monto > 0 && saldo >= monto) {
            saldo -= monto;
            transacciones.add(new Transaccion(this, null, monto, "RET" + System.currentTimeMillis(), new Date(), cajero));
            return true;
        }
        return false;
    }

    /**
     * Consulta el saldo disponible en la cuenta.
     * @return Mensaje con el saldo disponible.
     */
    public String consultarSaldo() {
        return "El saldo disponible en la cuenta " + idCuenta + " es: " + saldo;
    }
}
