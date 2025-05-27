package org.uniquindio.edu.co.poo.proyectofinalp1.app;

import java.io.IOException;
import java.util.ArrayList;

import org.uniquindio.edu.co.poo.proyectofinalp1.model.Administrador;
import org.uniquindio.edu.co.poo.proyectofinalp1.model.Banco;
import org.uniquindio.edu.co.poo.proyectofinalp1.model.Cajero;
import org.uniquindio.edu.co.poo.proyectofinalp1.model.Cliente;
import org.uniquindio.edu.co.poo.proyectofinalp1.model.CuentaAhorro;
import org.uniquindio.edu.co.poo.proyectofinalp1.model.CuentaCorriente;
import org.uniquindio.edu.co.poo.proyectofinalp1.model.Usuario;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Clase principal de la aplicación JavaFX.
 * Gestiona la inicialización, carga de vistas y datos de prueba.
 */
public class App extends Application {
    /**
     * Instancia global del banco.
     */
    public static Banco banco = new Banco("Banco UQ");

    /**
     * Usuario autenticado actualmente.
     */
    public static Usuario usuarioAutenticado;

    /** Escena principal de la aplicación. */
    private static Scene escena;

    /**
     * Método principal de inicio de la aplicación.
     * Inicializa los datos de prueba y muestra la vista de inicio de sesión.
     *
     * @param stage Ventana principal de JavaFX.
     * @throws IOException Si ocurre un error al cargar la vista.
     */
    @Override
    public void start(Stage stage) throws IOException {
        cargarUsuariosDePrueba(); // Agrega usuarios antes de mostrar la vista
        escena = new Scene(cargarFXML("inicioView"), 640, 480);
        stage.setScene(escena);
        stage.show();
    }

    /**
     * Carga usuarios, empleados y cuentas de ejemplo para pruebas del sistema.
     * Este método inicializa administradores, cajeros, clientes y sus cuentas,
     * así como algunas transacciones de ejemplo.
     */
    private void cargarUsuariosDePrueba() {
        // Administrador activo
        Administrador adminActivo = new Administrador(
            banco, "Admin Juan", "admin@banco.com", "admin1", "adminpass", "A001", true
        );
        Usuario usuarioAdmin = new Usuario("admin", "adminpass", adminActivo, new ArrayList<>());
        banco.getUsuarios().add(usuarioAdmin);

        // Administrador inactivo
        Administrador adminInactivo = new Administrador(
            banco, "Admin Inactivo", "admin2@banco.com", "admin2", "adminpass2", "A002", false
        );
        Usuario usuarioAdminInactivo = new Usuario("admin2", "adminpass2", adminInactivo, new ArrayList<>());
        banco.getUsuarios().add(usuarioAdminInactivo);

        // Cajero activo
        Cajero cajeroActivo = new Cajero(
            banco, "Cajero Pedro", "cajero@banco.com", "cajero1", "cajeropass", "C001", true
        );
        adminActivo.agregarCajero(cajeroActivo);
        Usuario usuarioCajero = new Usuario("cajero", "cajeropass", cajeroActivo, new ArrayList<>());
        banco.getUsuarios().add(usuarioCajero);

        // Cajero inactivo
        Cajero cajeroInactivo = new Cajero(
            banco, "Cajero Inactivo", "cajero2@banco.com", "cajero2", "cajeropass2", "C002", false
        );
        adminActivo.agregarCajero(cajeroInactivo);
        Usuario usuarioCajeroInactivo = new Usuario("cajero2", "cajeropass2", cajeroInactivo, new ArrayList<>());
        banco.getUsuarios().add(usuarioCajeroInactivo);

        // Cliente 1
        Cliente cliente1 = new Cliente(
            "Cliente Ana", "cliente@banco.com", "cliente1", "clientepass", 30, "Calle 123", "321654987", "CiudadX"
        );
        adminActivo.agregarCliente(cliente1);
        Usuario usuarioCliente1 = new Usuario("cliente", "clientepass", cliente1, new ArrayList<>());
        banco.getUsuarios().add(usuarioCliente1);

        // Cliente 2
        Cliente cliente2 = new Cliente(
            "Cliente Luis", "luis@banco.com", "cliente2", "luispass", 40, "Carrera 45", "312345678", "CiudadY"
        );
        adminActivo.agregarCliente(cliente2);
        Usuario usuarioCliente2 = new Usuario("luis", "luispass", cliente2, new ArrayList<>());
        banco.getUsuarios().add(usuarioCliente2);

        // Cuentas para Cliente 1
        CuentaAhorro cuentaAhorro1 = new CuentaAhorro("AH001", 1000.0, cliente1, new ArrayList<>());
        CuentaCorriente cuentaCorriente1 = new CuentaCorriente("CC001", 500.0, cliente1, new ArrayList<>(), 200.0);
        banco.getCuentas().add(cuentaAhorro1);
        banco.getCuentas().add(cuentaCorriente1);
        usuarioCliente1.getListaCuentas().add(cuentaAhorro1);
        usuarioCliente1.getListaCuentas().add(cuentaCorriente1);

        // Cuentas para Cliente 2
        CuentaAhorro cuentaAhorro2 = new CuentaAhorro("AH002", 2000.0, cliente2, new ArrayList<>());
        CuentaCorriente cuentaCorriente2 = new CuentaCorriente("CC002", 1500.0, cliente2, new ArrayList<>(), 500.0);
        banco.getCuentas().add(cuentaAhorro2);
        banco.getCuentas().add(cuentaCorriente2);
        usuarioCliente2.getListaCuentas().add(cuentaAhorro2);
        usuarioCliente2.getListaCuentas().add(cuentaCorriente2);

        // Transacciones de ejemplo
        cuentaAhorro1.depositar(200.0, cajeroActivo);
        cuentaCorriente1.retirar(100.0, cajeroActivo);
        cuentaAhorro1.retirar(150.0, cajeroActivo);
        cuentaCorriente1.depositar(150.0, cajeroActivo);
        cuentaAhorro2.depositar(500.0, cajeroActivo);
        cuentaCorriente2.retirar(200.0, cajeroActivo);

        // Sincronizar todas las transacciones de las cuentas con la lista global del banco
        banco.getTransacciones().clear();
        for (var cuenta : banco.getCuentas()) {
            banco.getTransacciones().addAll(cuenta.getTransacciones());
        }
        for (var t : banco.getTransacciones()) {
            System.out.println("ID de transacción: " + t.getIdTransaccion());
        }
        banco.getPersonas().add(cliente1);
        banco.getPersonas().add(cliente2);
        banco.getPersonas().add(cajeroActivo);
        banco.getPersonas().add(cajeroInactivo);
        banco.getPersonas().add(adminActivo);
        banco.getPersonas().add(adminInactivo);
    }

    /**
     * Cambia la vista principal de la aplicación.
     * @param fxml Nombre del archivo FXML (sin extensión).
     * @throws IOException Si ocurre un error al cargar la vista.
     */
    public static void establecerVista(String fxml) throws IOException {
        escena.setRoot(cargarFXML(fxml));
    }

    /**
     * Carga un archivo FXML y retorna su nodo raíz.
     * @param fxml Nombre del archivo FXML (sin extensión).
     * @return Nodo raíz de la vista cargada.
     * @throws IOException Si ocurre un error al cargar el archivo.
     */
    private static Parent cargarFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/org/uniquindio/edu/co/poo/proyectofinalp1/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    /**
     * Método principal de ejecución de la aplicación.
     * @param args Argumentos de línea de comandos.
     */
    public static void main(String[] args) {
        launch();
    }

}