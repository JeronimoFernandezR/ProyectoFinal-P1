package org.uniquindio.edu.co.poo.proyectofinalp1.app;

import java.util.ArrayList;

import org.uniquindio.edu.co.poo.proyectofinalp1.model.Administrador;
import org.uniquindio.edu.co.poo.proyectofinalp1.model.Banco;
import org.uniquindio.edu.co.poo.proyectofinalp1.model.Cajero;
import org.uniquindio.edu.co.poo.proyectofinalp1.model.Cliente;
import org.uniquindio.edu.co.poo.proyectofinalp1.model.Cuenta;
import org.uniquindio.edu.co.poo.proyectofinalp1.model.CuentaAhorro;
import org.uniquindio.edu.co.poo.proyectofinalp1.model.CuentaCorriente;
import org.uniquindio.edu.co.poo.proyectofinalp1.model.Usuario;

/**
 * Clase de prueba para inicializar el banco y sus entidades desde consola.
 *
 * Autor: Jerónimo Fernández Rivera
 * Fecha: 27/05/2025
 */
public class Main {
    public static void main(String[] args) {
        // Crear banco
        Banco banco = new Banco();

        // Crear administrador
        Administrador admin = new Administrador(
            banco, "Admin Juan", "admin@banco.com", "admin1", "adminpass", "A001", true
        );
        Usuario usuarioAdmin = new Usuario("admin", "adminpass", admin, new ArrayList<>());
        banco.getUsuarios().add(usuarioAdmin);

        // El admin crea un cajero
        Cajero cajero = new Cajero(
            banco, "Cajero Pedro", "cajero@banco.com", "cajero1", "cajeropass", "C001", true
        );
        admin.agregarCajero(cajero);
        Usuario usuarioCajero = new Usuario("cajero", "cajeropass", cajero, new ArrayList<>());
        banco.getUsuarios().add(usuarioCajero);

        // El admin crea un cliente
        Cliente cliente = new Cliente(
            "Cliente Ana", "cliente@banco.com", "cliente1", "clientepass", 30, "Calle 123", "321654987", "CiudadX"
        );
        admin.agregarCliente(cliente);
        Usuario usuarioCliente = new Usuario("cliente", "clientepass", cliente, new ArrayList<>());
        banco.getUsuarios().add(usuarioCliente);

        // Crear cuentas para el cliente
        CuentaAhorro cuentaAhorro = new CuentaAhorro("AH001", 1000.0, cliente, new ArrayList<>());
        CuentaCorriente cuentaCorriente = new CuentaCorriente("CC001", 500.0, cliente, new ArrayList<>(), 200.0);
        banco.getCuentas().add(cuentaAhorro);
        banco.getCuentas().add(cuentaCorriente);
        usuarioCliente.getListaCuentas().add(cuentaAhorro);
        usuarioCliente.getListaCuentas().add(cuentaCorriente);

        banco.getPersonas().add(cliente);
        banco.getPersonas().add(cajero);
        banco.getPersonas().add(admin);

        // Prueba: mostrar usuarios y cuentas
        System.out.println("Usuarios en el banco:");
        for (Usuario u : banco.getUsuarios()) {
            System.out.println("- " + u.getNombreUsuario() + " (" + u.getPersona().getNombre() + ")");
        }
        System.out.println("Cuentas del cliente:");
        for (Cuenta c : usuarioCliente.getListaCuentas()) {
            System.out.println("- " + c.getIdCuenta() + ": $" + c.getSaldo());
        }
    }
}
