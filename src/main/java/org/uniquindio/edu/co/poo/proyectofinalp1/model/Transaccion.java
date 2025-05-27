package org.uniquindio.edu.co.poo.proyectofinalp1.model;

import java.util.Date;

/**
 * Clase que representa una transacción bancaria.
 * Las transacciones pueden ser depósitos, retiros o transferencias.
 */
public class Transaccion {

    private Cuenta cuentaOrigen;
    private Cuenta cuentaDestino;
    private double monto;
    private String idTransaccion;
    private Date fecha;
    private Cajero cajero;

    /**
     * Constructor de la clase Transaccion.
     * @param cuentaOrigen Cuenta de origen de la transacción.
     * @param cuentaDestino Cuenta de destino de la transacción.
     * @param monto Monto de la transacción.
     * @param idTransaccion Identificador único de la transacción.
     * @param fecha Fecha de la transacción.
     * @param cajero Cajero que realizó la transacción.
     */
    public Transaccion(Cuenta cuentaOrigen, Cuenta cuentaDestino, double monto, String idTransaccion, Date fecha, Cajero cajero) {
        this.cuentaOrigen = cuentaOrigen;
        this.cuentaDestino = cuentaDestino;
        this.monto = monto;
        this.idTransaccion = idTransaccion;
        this.fecha = fecha;
        this.cajero = cajero;
    }

    // Getters y Setters
    public Cuenta getCuentaOrigen() {
        return cuentaOrigen;
    }

    public void setCuentaOrigen(Cuenta cuentaOrigen) {
        this.cuentaOrigen = cuentaOrigen;
    }

    public Cuenta getCuentaDestino() {
        return cuentaDestino;
    }

    public void setCuentaDestino(Cuenta cuentaDestino) {
        this.cuentaDestino = cuentaDestino;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(String idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Cajero getCajero() {
        return cajero;
    }

    public void setCajero(Cajero cajero) {
        this.cajero = cajero;
    }

    /**
     * Representación textual de la transacción.
     * @return Cadena con los detalles de la transacción.
     */
    @Override
    public String toString() {
        return "Transaccion{" +
                "cuentaOrigen=" + cuentaOrigen +
                ", cuentaDestino=" + cuentaDestino +
                ", monto=" + monto +
                ", idTransaccion='" + idTransaccion + '\'' +
                ", fecha=" + fecha +
                ", cajero=" + cajero +
                '}';
    }
}
