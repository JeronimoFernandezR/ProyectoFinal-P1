/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 * Clase que representa una cuenta de ahorro con interés simple mensual.
 * El interés se aplica automáticamente el primer día de cada mes del calendario.
 */
package org.uniquindio.edu.co.poo.proyectofinalp1.model;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Clase que representa una cuenta de ahorro con interés simple mensual.
 * El interés se aplica automáticamente el primer día de cada mes del calendario.
 */
public class CuentaAhorro extends Cuenta {

    /**
     * Tasa de interés fija mensual (porcentaje).
     */
    private static final double TASA_INTERES_FIJA = 2.0; // 2% mensual

    /**
     * Fecha del último cálculo de intereses.
     * Se utiliza para determinar cuántos meses han pasado desde la última aplicación de intereses.
     */
    private LocalDate fechaUltimoInteres;

    /**
     * Constructor de la clase CuentaAhorro.
     * 
     * @param idCuenta      Identificador único de la cuenta.
     * @param saldo         Saldo inicial de la cuenta.
     * @param cliente       Cliente asociado a la cuenta.
     * @param transacciones Lista de transacciones realizadas en la cuenta.
     */
    public CuentaAhorro(String idCuenta, double saldo, Cliente cliente, ArrayList<Transaccion> transacciones) {
        super(idCuenta, saldo, cliente, transacciones);
        this.fechaUltimoInteres = LocalDate.now().withDayOfMonth(1); // Primer día del mes actual
    }

    /**
     * Aplica los intereses automáticamente el primer día de cada mes del calendario (interés simple).
     * El cálculo se basa en la cantidad de meses transcurridos desde la última aplicación de intereses.
     * Si no ha pasado ningún mes, no se aplica interés.
     * El interés se calcula sobre el saldo actual y se suma al saldo total.
     */
    public void aplicarInteresMensual() {
        LocalDate hoy = LocalDate.now().withDayOfMonth(1);
        int meses = (hoy.getYear() - fechaUltimoInteres.getYear()) * 12 + (hoy.getMonthValue() - fechaUltimoInteres.getMonthValue());
        if (meses > 0) {
            double interesTotal = saldo * (TASA_INTERES_FIJA / 100) * meses;
            saldo += interesTotal;
            fechaUltimoInteres = hoy;
            System.out.println("Intereses simples aplicados por " + meses + " mes(es). Nuevo saldo: " + saldo);
        } else {
            System.out.println("No hay intereses por aplicar este mes.");
        }
    }

    /**
     * Calcula los intereses simples acumulados en un período de tiempo.
     * El interés simple se calcula como: saldo * tasa * meses
     * 
     * @param meses Número de meses.
     * @return Intereses acumulados en el período especificado.
     * @throws IllegalArgumentException si el número de meses es negativo.
     */
    public double calcularIntereses(int meses) {
        if (meses < 0) {
            throw new IllegalArgumentException("El número de meses no puede ser negativo.");
        }
        return saldo * (TASA_INTERES_FIJA / 100) * meses;
    }

    @Override
    public boolean retirar(double monto, Cajero cajero) {
        if (monto <= 0 || monto > 8_000_000 || saldo < monto || cajero == null) {
            System.out.println("Retiro inválido. El monto debe ser mayor a 0, máximo 8 millones, no exceder el saldo y debe tener cajero.");
            return false;
        }
        saldo -= monto;
        getTransacciones().add(new Transaccion(this, null, monto, "RET" + System.currentTimeMillis(), new java.util.Date(), cajero));
        return true;
    }

    @Override
    public void depositar(double monto, Cajero cajero) {
        if (monto <= 0 || monto > 8_000_000 || cajero == null) {
            System.out.println("Depósito inválido. El monto debe ser mayor a 0, máximo 8 millones y debe tener cajero.");
            return;
        }
        saldo += monto;
        getTransacciones().add(new Transaccion(null, this, monto, "DEP" + System.currentTimeMillis(), new java.util.Date(), cajero));
    }
}
