package org.uniquindio.edu.co.poo.proyectofinalp1.model;

/**
 * Interfaz para operaciones bancarias de depósito y retiro.
 */
public interface Depositable {
    /**
     * Realiza un depósito en la cuenta.
     * @param monto Monto a depositar.
     * @param cajero Cajero que realiza la operación.
     */
    void depositar(double monto, Cajero cajero);

    /**
     * Realiza un retiro de la cuenta.
     * @param monto Monto a retirar.
     * @param cajero Cajero que realiza la operación.
     * @return true si el retiro fue exitoso, false en caso contrario.
     */
    boolean retirar(double monto, Cajero cajero);
}
