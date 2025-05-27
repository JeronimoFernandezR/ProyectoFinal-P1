package org.uniquindio.edu.co.poo.proyectofinalp1.model;

import java.util.ArrayList;

/**
 * Clase que representa un administrador del banco.
 * El administrador puede gestionar cajeros, clientes y cuentas, además de supervisar transacciones.
 */
public class Administrador extends Persona {
    private Banco banco;

    /**
     * Constructor de la clase Administrador.
     * @param banco Banco al que pertenece el administrador.
     * @param nombre Nombre del administrador.
     * @param correo Correo del administrador.
     * @param id Identificador del administrador.
     * @param contraseña Contraseña del administrador.
     * @param codigo Código del administrador.
     * @param empleadoActivo Indica si el administrador está activo.
     */
    public Administrador(Banco banco, String nombre, String correo, String id, String contraseña, String codigo, boolean empleadoActivo) {
        super(nombre, correo, id, codigo, empleadoActivo);
        this.banco = banco;
    }

    // Métodos para gestionar cajeros

    /**
     * Agrega un cajero a la lista de cajeros si no está registrado.
     * @param cajero Cajero a agregar.
     */
    public boolean agregarCajero(Cajero cajero) {
        if (!isEmpleadoActivo()) {
            System.out.println("El administrador no está activo. No puede agregar cajeros.");
            return false;
        }
        for (Cajero c : banco.getCajeros()) {
            if (c.getId().equals(cajero.getId())) {
                System.out.println("El cajero con ID " + cajero.getId() + " ya está registrado.");
                return false;
            }
        }
        banco.getPersonas().add(cajero);
        System.out.println("Cajero agregado exitosamente.");
        return true;
    }

    /**
     * Elimina un cajero de la lista según su ID.
     * @param idCajero ID del cajero a eliminar.
     */
    public boolean eliminarCajero(String idCajero) {
        return banco.getPersonas().removeIf(p -> p instanceof Cajero && p.getId().equals(idCajero));
    }

    /**
     * Lista todos los cajeros registrados.
     */
    public void listarCajeros() {
        for (Cajero c : banco.getCajeros()) {
            System.out.println(c);
        }
    }

    // Métodos para gestionar clientes

    /**
     * Agrega un cliente a la lista de clientes si no está registrado.
     * @param cliente Cliente a agregar.
     */
    public boolean agregarCliente(Cliente cliente) {
        if (!isEmpleadoActivo()) {
            System.out.println("El administrador no está activo. No puede agregar clientes.");
            return false;
        }
        for (Cliente c : banco.getClientes()) {
            if (c.getId().equals(cliente.getId())) {
                System.out.println("El cliente con ID " + cliente.getId() + " ya está registrado.");
                return false;
            }
        }
        banco.getPersonas().add(cliente);
        System.out.println("Cliente agregado exitosamente.");
        return true;
    }

    /**
     * Elimina un cliente de la lista según su ID.
     * @param idCliente ID del cliente a eliminar.
     */
    public boolean eliminarCliente(String idCliente) {
        return banco.getPersonas().removeIf(p -> p instanceof Cliente && p.getId().equals(idCliente));
    }

    /**
     * Lista todos los clientes registrados.
     */
    public void listarClientes() {
        for (Cliente c : banco.getClientes()) {
            System.out.println(c);
        }
    }

    // Métodos para gestionar cuentas

    /**
     * Agrega una cuenta a la lista de cuentas si no está registrada.
     * @param cuenta Cuenta a agregar.
     */
    public boolean agregarCuenta(Cuenta cuenta) {
        if (!isEmpleadoActivo()) {
            System.out.println("El administrador no está activo. No puede agregar cuentas.");
            return false;
        }
        for (Cuenta c : banco.getCuentas()) {
            if (c.getIdCuenta().equals(cuenta.getIdCuenta())) {
                System.out.println("La cuenta con ID " + cuenta.getIdCuenta() + " ya está registrada.");
                return false;
            }
        }
        // Validar que el cliente exista
        boolean clienteExiste = banco.getClientes().stream()
            .anyMatch(c -> c.getId().equals(cuenta.getCliente().getId()));
        if (!clienteExiste) {
            System.out.println("El cliente asociado a la cuenta no existe.");
            return false;
        }
        banco.getCuentas().add(cuenta);
        System.out.println("Cuenta agregada exitosamente.");
        return true;
    }

    /**
     * Elimina una cuenta de la lista según su ID.
     * @param idCuenta ID de la cuenta a eliminar.
     */
    public boolean eliminarCuenta(String idCuenta) {
        if (!isEmpleadoActivo()) {
            System.out.println("El administrador no está activo. No puede eliminar cuentas.");
            return false;
        }
        boolean removed = banco.getCuentas().removeIf(cuenta -> cuenta.getIdCuenta().equals(idCuenta));
        if (removed) {
            System.out.println("Cuenta eliminada exitosamente.");
        } else {
            System.out.println("No se encontró cuenta con ese ID.");
        }
        return removed;
    }

    /**
     * Lista todas las cuentas registradas.
     */
    public void listarCuentas() {
        System.out.println("Lista de cuentas:");
        for (Cuenta cuenta : banco.getCuentas()) {
            System.out.println("- Cuenta ID: " + cuenta.getIdCuenta() + ", Saldo: " + cuenta.getSaldo());
        }
    }

    // Métodos para supervisar transacciones

    /**
     * Lista todas las transacciones realizadas.
     * @param transacciones Lista de transacciones a supervisar.
     */
    public void supervisarTransacciones(ArrayList<Transaccion> transacciones) {
        if (!isEmpleadoActivo()) {
            System.out.println("El administrador no está activo. No puede supervisar transacciones.");
            return;
        }
        System.out.println("Lista de transacciones:");
        for (Transaccion transaccion : transacciones) {
            System.out.println(transaccion.toString());
        }
    }

    /**
     * Busca una transacción específica por su ID.
     * @param transacciones Lista de transacciones.
     * @param idTransaccion ID de la transacción a buscar.
     */
    public void buscarTransaccionPorId(ArrayList<Transaccion> transacciones, String idTransaccion) {
        if (!isEmpleadoActivo()) {
            System.out.println("El administrador no está activo. No puede buscar transacciones.");
            return;
        }
        for (Transaccion transaccion : transacciones) {
            if (transaccion.getIdTransaccion().equals(idTransaccion)) {
                System.out.println("Transacción encontrada: " + transaccion.toString());
                return;
            }
        }
        System.out.println("No se encontró ninguna transacción con el ID: " + idTransaccion);
    }
}
