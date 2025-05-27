/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uniquindio.edu.co.poo.proyectofinalp1.model;

import java.util.ArrayList;

/**
 * Clase que representa una cuenta corriente.
 * Diseñada para operaciones diarias con posibilidad de sobregiro.
 */
public class CuentaCorriente extends Cuenta {

    private double capacidadSobregiro;

    /**
     * Constructor de la clase CuentaCorriente.
     * @param idCuenta Identificador único de la cuenta.
     * @param saldo Saldo inicial de la cuenta.
     * @param cliente Cliente asociado a la cuenta.
     * @param transacciones Lista de transacciones realizadas en la cuenta.
     * @param capacidadSobregiro Capacidad de sobregiro de la cuenta.
     */
    public CuentaCorriente(String idCuenta, double saldo, Cliente cliente, ArrayList<Transaccion> transacciones, double capacidadSobregiro) {
        super(idCuenta, saldo, cliente, transacciones);
        if (capacidadSobregiro < 0) {
            throw new IllegalArgumentException("La capacidad de sobregiro no puede ser negativa.");
        }
        this.capacidadSobregiro = capacidadSobregiro;
    }

    /**
     * Obtiene la capacidad de sobregiro de la cuenta.
     * @return Capacidad de sobregiro.
     */
    public double getCapacidadSobregiro() {
        return capacidadSobregiro;
    }

    /**
     * Establece la capacidad de sobregiro de la cuenta.
     * @param capacidadSobregiro Capacidad de sobregiro.
     */
    public void setCapacidadSobregiro(double capacidadSobregiro) {
        if (capacidadSobregiro < 0) {
            throw new IllegalArgumentException("La capacidad de sobregiro no puede ser negativa.");
        }
        this.capacidadSobregiro = capacidadSobregiro;
    }

    /**
     * Realiza un retiro de la cuenta, considerando el sobregiro.
     * @param monto Monto a retirar.
     * @return `true` si el retiro fue exitoso, `false` en caso contrario.
     */
    @Override
    public boolean retirar(double monto, Cajero cajero) {
        if (monto <= 0 || monto > 8_000_000 || cajero == null) {
            System.out.println("Retiro inválido. El monto debe ser mayor a 0, máximo 8 millones y debe tener cajero.");
            return false;
        }
        if (saldo + capacidadSobregiro < monto) {
            System.out.println("Retiro inválido. No puede exceder el saldo + sobregiro.");
            return false;
        }
        saldo -= monto;
        getTransacciones().add(new Transaccion(this, null, monto, "RET" + System.currentTimeMillis(), new java.util.Date(), cajero));
        return true;
    }

    /**
     * Deposita un monto en la cuenta corriente.
     * @param monto Monto a depositar.
     * @param cajero Cajero desde el cual se realiza el depósito.
     */
    @Override
    public void depositar(double monto, Cajero cajero) {
        if (monto <= 0 || monto > 8_000_000 || cajero == null) {
            System.out.println("Depósito inválido. El monto debe ser mayor a 0, máximo 8 millones y debe tener cajero.");
            return;
        }
        saldo += monto;
        getTransacciones().add(new Transaccion(null, this, monto, "DEP" + System.currentTimeMillis(), new java.util.Date(), cajero));
    }

    /**
     * Ajusta dinámicamente el límite de sobregiro.
     * @param nuevoSobregiro Nuevo límite de sobregiro.
     */
    public void ajustarSobregiro(double nuevoSobregiro) {
        if (nuevoSobregiro < 0) {
            throw new IllegalArgumentException("El nuevo límite de sobregiro no puede ser negativo.");
        }
        this.capacidadSobregiro = nuevoSobregiro;
        System.out.println("El límite de sobregiro ha sido ajustado a: " + nuevoSobregiro);
    }
}
