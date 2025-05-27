package org.uniquindio.edu.co.poo.proyectofinalp1.model;

public interface Depositable {
    void depositar(double monto, Cajero cajero);
    boolean retirar(double monto, Cajero cajero);
}
