/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uniquindio.edu.co.poo.proyectofinalp1.model;

import java.util.ArrayList;

/**
 * Clase que representa una cuenta empresarial.
 * Diseñada para empresas con movimientos de alto volumen.
 */
public class CuentaEmpresarial extends Cuenta {

    private int NIT;

    /**
     * Constructor de la clase CuentaEmpresarial.
     * @param idCuenta Identificador único de la cuenta.
     * @param saldo Saldo inicial de la cuenta.
     * @param cliente Cliente asociado a la cuenta.
     * @param transacciones Lista de transacciones realizadas en la cuenta.
     * @param NIT Número de Identificación Tributaria de la empresa.
     */
    public CuentaEmpresarial(String idCuenta, double saldo, Cliente cliente, ArrayList<Transaccion> transacciones, int NIT) {
        super(idCuenta, saldo, cliente, transacciones);
        this.NIT = NIT;
    }

    /**
     * Obtiene el NIT de la empresa asociada a la cuenta.
     * @return NIT de la empresa.
     */
    public int getNIT() {
        return NIT;
    }

    /**
     * Establece el NIT de la empresa asociada a la cuenta.
     * @param NIT NIT de la empresa.
     */
    public void setNIT(int NIT) {
        this.NIT = NIT;
    }

    /**
     * Genera un reporte de transacciones de alto volumen.
     * @return Reporte de transacciones.
     */
    public String generarReporteTransacciones() {
        StringBuilder reporte = new StringBuilder("Reporte de transacciones para la empresa con NIT: " + NIT + "\n");
        for (Transaccion transaccion : getTransacciones()) {
            reporte.append(transaccion.toString()).append("\n");
        }
        return reporte.toString();
    }

    @Override
    public boolean retirar(double monto, Cajero cajero) {
        if (monto <= 0 || saldo < monto || cajero == null) {
            System.out.println("Retiro inválido. El monto debe ser mayor a 0, no exceder el saldo y debe tener cajero.");
            return false;
        }
        saldo -= monto;
        getTransacciones().add(new Transaccion(this, null, monto, "RET" + System.currentTimeMillis(), new java.util.Date(), cajero));
        return true;
    }

    @Override
    public void depositar(double monto, Cajero cajero) {
        if (monto <= 0 || cajero == null) {
            System.out.println("Depósito inválido. El monto debe ser mayor a 0 y debe tener cajero.");
            return;
        }
        saldo += monto;
        getTransacciones().add(new Transaccion(null, this, monto, "DEP" + System.currentTimeMillis(), new java.util.Date(), cajero));
    }
}

