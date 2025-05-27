package org.uniquindio.edu.co.poo.proyectofinalp1.model;

import java.util.ArrayList;
import java.util.Date;

/**
 * Clase que representa un cajero del banco. Realiza operaciones bancarias como registrar clientes,
 * depósitos, retiros, transferencias y generación de reportes.
 */
public class Cajero extends Persona {

    private Banco banco;

    /**
     * Constructor de la clase Cajero.
     * @param banco Banco donde trabaja el cajero.
     * @param nombre Nombre del cajero.
     * @param correo Correo del cajero.
     * @param id Identificador del cajero.
     * @param contraseña Contraseña del cajero.
     * @param codigo Código del cajero.
     * @param empleadoActivo Indica si el cajero está activo.
     */
    public Cajero(Banco banco, String nombre, String correo, String id, String contraseña, String codigo, boolean empleadoActivo) {
        super(nombre, correo, id, codigo, empleadoActivo);
        this.banco = banco;
    }

    /**
     * Registra un cliente en el banco si no está registrado.
     * @param cliente Cliente a registrar.
     */
    public void registrarCliente(Cliente cliente) {
        for (Persona p : banco.getPersonas()) {
            if (p instanceof Cliente && p.getId().equals(cliente.getId())) {
                System.out.println("El cliente con ID " + cliente.getId() + " ya está registrado.");
                return;
            }
        }
        banco.getPersonas().add(cliente);
        System.out.println("Cliente registrado exitosamente.");
    }

    /**
     * Realiza un depósito en una cuenta.
     * @param cuenta Cuenta donde se realizará el depósito.
     * @param monto Monto a depositar.
     */
    public void depositar(Depositable cuenta, double monto) {
        if (!isEmpleadoActivo()) {
            System.out.println("El cajero no está activo. No puede realizar depósitos.");
            return;
        }
        if (monto > 0) {
            cuenta.depositar(monto, this);
            System.out.println("Depósito realizado exitosamente.");
        } else {
            System.out.println("El monto debe ser mayor a 0.");
        }
    }

    /**
     * Realiza un retiro de una cuenta.
     * @param cuenta Cuenta de donde se realizará el retiro.
     * @param monto Monto a retirar.
     */
    public void retirar(Depositable cuenta, double monto) {
        if (!isEmpleadoActivo()) {
            System.out.println("El cajero no está activo. No puede realizar retiros.");
            return;
        }
        if (monto <= 0) {
            System.out.println("El monto debe ser mayor a 0.");
            return;
        }
        if (cuenta.retirar(monto, this)) {
            System.out.println("Retiro realizado exitosamente.");
        } else {
            System.out.println("Fondos insuficientes o monto inválido.");
        }
    }

    /**
     * Consulta el saldo de una cuenta.
     * @param cuenta Cuenta a consultar.
     */
    public void consultarSaldo(Cuenta cuenta) {
        System.out.println(cuenta.consultarSaldo());
    }

    /**
     * Realiza una transferencia entre cuentas.
     * @param banco Banco donde se realiza la transferencia.
     * @param cuentaOrigen Cuenta de origen.
     * @param cuentaDestino Cuenta de destino.
     * @param monto Monto a transferir.
     */
    public void transferir(Banco banco, Depositable cuentaOrigen, Depositable cuentaDestino, double monto) {
        if (!isEmpleadoActivo()) {
            System.out.println("El cajero no está activo. No puede realizar transferencias.");
            return;
        }
        if (cuentaOrigen == null || cuentaDestino == null) {
            System.out.println("Cuentas inválidas para transferencia.");
            return;
        }
        if (monto <= 0) {
            System.out.println("El monto debe ser mayor a 0.");
            return;
        }
        if (cuentaOrigen.retirar(monto, this)) {
            cuentaDestino.depositar(monto, this);
            Transaccion transaccion = new Transaccion(
                (Cuenta) cuentaOrigen, (Cuenta) cuentaDestino, monto,
                "TRA" + System.currentTimeMillis(), new Date(), this
            );
            if (banco.getTransacciones() == null) {
                banco.setTransacciones(new ArrayList<>());
            }
            banco.getTransacciones().add(transaccion);
            System.out.println("Transferencia realizada exitosamente.");
        } else {
            System.out.println("Transferencia fallida. Fondos insuficientes o monto inválido.");
        }
    }

    /**
     * Genera un reporte de movimientos financieros para un cliente.
     * @param banco Banco donde se realiza la consulta.
     * @param cliente Cliente para el cual se genera el reporte.
     */
    public void generarReportePorCliente(Banco banco, Cliente cliente) {
        if (!isEmpleadoActivo()) {
            System.out.println("El cajero no está activo. No puede generar reportes.");
            return;
        }
        StringBuilder reporte = new StringBuilder("Reporte de movimientos para el cliente: " + cliente.getNombre() + "\n");
        for (Cuenta cuenta : banco.getCuentas()) {
            if (cuenta.getCliente().equals(cliente)) {
                reporte.append("Cuenta: ").append(cuenta.getIdCuenta()).append("\n");
                for (Transaccion transaccion : cuenta.getTransacciones()) {
                    reporte.append(transaccion.toString()).append("\n");
                }
            }
        }
        System.out.println(reporte.toString());
    }
}