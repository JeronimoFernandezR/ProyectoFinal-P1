package org.uniquindio.edu.co.poo.proyectofinalp1.controller;

import java.util.ArrayList;
import java.util.Date;

import org.uniquindio.edu.co.poo.proyectofinalp1.app.App;
import org.uniquindio.edu.co.poo.proyectofinalp1.model.Administrador;
import org.uniquindio.edu.co.poo.proyectofinalp1.model.Banco;
import org.uniquindio.edu.co.poo.proyectofinalp1.model.Cajero;
import org.uniquindio.edu.co.poo.proyectofinalp1.model.Cliente;
import org.uniquindio.edu.co.poo.proyectofinalp1.model.Cuenta;
import org.uniquindio.edu.co.poo.proyectofinalp1.model.CuentaAhorro;
import org.uniquindio.edu.co.poo.proyectofinalp1.model.CuentaCorriente;
import org.uniquindio.edu.co.poo.proyectofinalp1.model.Transaccion;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Controlador para la vista de administrador.
 * Permite gestionar cajeros, clientes, cuentas y transacciones del banco.
 */
public class AdministradorController {

    // === Elementos de la interfaz ===
    @FXML private TextField nombreCajero, correoCajero, idCajero, codigoCajero;
    @FXML private PasswordField contrasenaCajero;
    @FXML private CheckBox estadoActivoCajero;
    @FXML private Button btnAgregarCajero, btnListarCajeros, btnEliminarCajero;

    @FXML private TextField nombreCliente, correoCliente, idCliente;
    @FXML private PasswordField contrasenaCliente;
    @FXML private TextField edadCliente, direccionCliente, telefonoCliente, ciudadCliente;
    @FXML private Button btnAgregarCliente, btnListarClientes, btnEliminarCliente;

    @FXML private TextField idCuenta, tipoCuenta, saldoInicialCuenta, idEliminarCuenta;
    @FXML private Button btnAgregarCuenta, btnListarCuentas, btnEliminarCuenta;

    @FXML private TextField idBuscarTransaccion;
    @FXML private Button btnBuscarTransaccion;
    @FXML private TableView<Transaccion> tablaTransacciones;
    @FXML private TableColumn<Transaccion, String> colIdTransaccion, colFechaTransaccion, colTipoTransaccion;
    @FXML private TableColumn<Transaccion, Double> colMontoTransaccion;

    @FXML private TableView<Cajero> tablaCajeros;
    @FXML private TableColumn<Cajero, String> colNombreCajero, colIdCajero, colCorreoCajero, colCiudadCajero;

    @FXML private TableView<Cliente> tablaClientes;
    @FXML private TableColumn<Cliente, String> colIdCliente, colNombreCliente, colCorreoCliente;

    @FXML private TableView<Cuenta> tablaCuentas;
    @FXML private TableColumn<Cuenta, String> colIdCuenta, colTipoCuenta;
    @FXML private TableColumn<Cuenta, Double> colSaldoCuenta;

    // Referencia al banco y administrador autenticado
    private Banco banco = App.banco;
    private Administrador administrador;

    /**
     * Inicializa la vista y configura las columnas de las tablas.
     */
    @FXML
    public void initialize() {
        if (App.usuarioAutenticado != null && App.usuarioAutenticado.getPersona() instanceof Administrador) {
            administrador = (Administrador) App.usuarioAutenticado.getPersona();
        }

        // Configuración de columnas
        if (colNombreCajero != null) colNombreCajero.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getNombre()));
        if (colIdCajero != null) colIdCajero.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getId()));
        if (colCorreoCajero != null) colCorreoCajero.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getCorreo()));

        if (colIdCliente != null) colIdCliente.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getId()));
        if (colNombreCliente != null) colNombreCliente.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getNombre()));
        if (colCorreoCliente != null) colCorreoCliente.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getCorreo()));

        if (colIdCuenta != null) colIdCuenta.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getIdCuenta()));
        if (colTipoCuenta != null) colTipoCuenta.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getClass().getSimpleName()));
        if (colSaldoCuenta != null) colSaldoCuenta.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("saldo"));

        if (colIdTransaccion != null)
            colIdTransaccion.setCellValueFactory(new PropertyValueFactory<>("idTransaccion"));
        if (colFechaTransaccion != null)
            colFechaTransaccion.setCellValueFactory(cellData -> {
                Date fecha = cellData.getValue().getFecha();
                String fechaStr = fecha != null ? new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm").format(fecha) : "";
                return new javafx.beans.property.SimpleStringProperty(fechaStr);
            });
        if (colTipoTransaccion != null)
            colTipoTransaccion.setCellValueFactory(cellData -> {
                Transaccion t = cellData.getValue();
                String tipo;
                if (t.getCuentaOrigen() != null && t.getCuentaDestino() != null) {
                    tipo = "Transferencia";
                } else if (t.getCuentaOrigen() == null) {
                    tipo = "Depósito";
                } else {
                    tipo = "Retiro";
                }
                return new javafx.beans.property.SimpleStringProperty(tipo);
            });
        if (colMontoTransaccion != null)
            colMontoTransaccion.setCellValueFactory(new PropertyValueFactory<>("monto"));
    }

    /**
     * Muestra una alerta informativa en pantalla.
     * @param mensaje Mensaje a mostrar.
     */
    private void mostrarAlerta(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    @FXML
    private void handleAgregarCajero() {
        if (administrador != null && !administrador.isEmpleadoActivo()) {
            mostrarAlerta("El administrador no está activo. No puede realizar esta acción.");
            return;
        }
        if (nombreCajero.getText().isEmpty() || correoCajero.getText().isEmpty() || idCajero.getText().isEmpty()
                || contrasenaCajero.getText().isEmpty() || codigoCajero.getText().isEmpty()) {
            mostrarAlerta("Todos los campos de cajero son obligatorios.");
            return;
        }
        Cajero cajero = new Cajero(
            banco,
            nombreCajero.getText(),
            correoCajero.getText(),
            idCajero.getText(),
            contrasenaCajero.getText(),
            codigoCajero.getText(),
            estadoActivoCajero.isSelected()
        );
        boolean exito = administrador.agregarCajero(cajero);
        mostrarAlerta(exito ? "Cajero agregado." : "No se pudo agregar el cajero.");
        listarCajeros();
        nombreCajero.clear(); correoCajero.clear(); idCajero.clear(); contrasenaCajero.clear(); codigoCajero.clear();
        estadoActivoCajero.setSelected(false);
    }

    @FXML
    private void handleListarCajeros() {
        listarCajeros();
    }

    private void listarCajeros() {
        tablaCajeros.setItems(FXCollections.observableArrayList(banco.getCajeros()));
    }

    @FXML
    private void handleEliminarCajero() {
        if (administrador != null && !administrador.isEmpleadoActivo()) {
            mostrarAlerta("El administrador no está activo. No puede realizar esta acción.");
            return;
        }
        if (idCajero.getText().isEmpty()) {
            mostrarAlerta("Debes ingresar el ID del cajero a eliminar.");
            return;
        }
        boolean exito = administrador.eliminarCajero(idCajero.getText());
        mostrarAlerta(exito ? "Cajero eliminado." : "No se pudo eliminar el cajero.");
        listarCajeros();
        idCajero.clear();
    }

    @FXML
    private void handleAgregarCliente() {
        if (administrador != null && !administrador.isEmpleadoActivo()) {
            mostrarAlerta("El administrador no está activo. No puede realizar esta acción.");
            return;
        }
        if (nombreCliente.getText().isEmpty() || correoCliente.getText().isEmpty() || idCliente.getText().isEmpty()
                || contrasenaCliente.getText().isEmpty() || edadCliente.getText().isEmpty()
                || direccionCliente.getText().isEmpty() || telefonoCliente.getText().isEmpty() || ciudadCliente.getText().isEmpty()) {
            mostrarAlerta("Todos los campos de cliente son obligatorios.");
            return;
        }
        try {
            Cliente cliente = new Cliente(
                nombreCliente.getText(),
                correoCliente.getText(),
                idCliente.getText(),
                contrasenaCliente.getText(),
                Integer.parseInt(edadCliente.getText()),
                direccionCliente.getText(),
                telefonoCliente.getText(),
                ciudadCliente.getText()
            );
            boolean exito = administrador.agregarCliente(cliente);
            mostrarAlerta(exito ? "Cliente agregado." : "No se pudo agregar el cliente.");
            listarClientes();
            nombreCliente.clear(); correoCliente.clear(); idCliente.clear(); contrasenaCliente.clear();
            edadCliente.clear(); direccionCliente.clear(); telefonoCliente.clear(); ciudadCliente.clear();
        } catch (Exception e) {
            mostrarAlerta("Datos inválidos para cliente.");
        }
    }

    @FXML
    private void handleListarClientes() {
        listarClientes();
    }

    private void listarClientes() {
        tablaClientes.setItems(FXCollections.observableArrayList(banco.getClientes()));
    }

    @FXML
    private void handleEliminarCliente() {
        if (administrador != null && !administrador.isEmpleadoActivo()) {
            mostrarAlerta("El administrador no está activo. No puede realizar esta acción.");
            return;
        }
        if (idCliente.getText().isEmpty()) {
            mostrarAlerta("Debes ingresar el ID del cliente a eliminar.");
            return;
        }
        boolean exito = administrador.eliminarCliente(idCliente.getText());
        mostrarAlerta(exito ? "Cliente eliminado." : "No se pudo eliminar el cliente.");
        listarClientes();
        idCliente.clear();
    }

    @FXML
    private void handleAgregarCuenta() {
        if (administrador != null && !administrador.isEmpleadoActivo()) {
            mostrarAlerta("El administrador no está activo. No puede realizar esta acción.");
            return;
        }
        if (idCuenta.getText().isEmpty() || tipoCuenta.getText().isEmpty() || saldoInicialCuenta.getText().isEmpty() || idCliente.getText().isEmpty()) {
            mostrarAlerta("Todos los campos de cuenta son obligatorios.");
            return;
        }
        try {
            Cliente cliente = banco.getClientes().stream()
                .filter(c -> c.getId().equals(idCliente.getText()))
                .findFirst().orElse(null);
            if (cliente == null) {
                mostrarAlerta("Cliente no encontrado.");
                return;
            }
            Cuenta cuenta;
            String tipo = tipoCuenta.getText().toLowerCase();
            double saldoInicial = Double.parseDouble(saldoInicialCuenta.getText());
            if (tipo.equals("ahorro")) {
                cuenta = new CuentaAhorro(idCuenta.getText(), saldoInicial, cliente, new ArrayList<>());
            } else if (tipo.equals("corriente")) {
                cuenta = new CuentaCorriente(idCuenta.getText(), saldoInicial, cliente, new ArrayList<>(), 200.0);
            } else {
                mostrarAlerta("Tipo de cuenta no válido.");
                return;
            }
            boolean exito = administrador.agregarCuenta(cuenta);
            mostrarAlerta(exito ? "Cuenta agregada." : "No se pudo agregar la cuenta.");
            listarCuentas();
            idCuenta.clear(); tipoCuenta.clear(); saldoInicialCuenta.clear(); idCliente.clear();
        } catch (Exception e) {
            mostrarAlerta("Datos inválidos para cuenta.");
        }
    }

    @FXML
    private void handleListarCuentas() {
        listarCuentas();
    }

    private void listarCuentas() {
        tablaCuentas.setItems(FXCollections.observableArrayList(banco.getCuentas()));
    }

    @FXML
    private void handleEliminarCuenta() {
        if (administrador != null && !administrador.isEmpleadoActivo()) {
            mostrarAlerta("El administrador no está activo. No puede realizar esta acción.");
            return;
        }
        if (idEliminarCuenta.getText().isEmpty()) {
            mostrarAlerta("Debes ingresar el ID de la cuenta a eliminar.");
            return;
        }
        boolean exito = administrador.eliminarCuenta(idEliminarCuenta.getText());
        mostrarAlerta(exito ? "Cuenta eliminada." : "No se pudo eliminar la cuenta.");
        listarCuentas();
        idEliminarCuenta.clear();
    }

    @FXML
    private void handleListarTransacciones() {
        tablaTransacciones.setItems(FXCollections.observableArrayList(banco.getTransacciones()));
    }

    @FXML
    private void handleBuscarTransaccion() {
        if (administrador != null && !administrador.isEmpleadoActivo()) {
            mostrarAlerta("El administrador no está activo. No puede realizar esta acción.");
            return;
        }
        if (idBuscarTransaccion.getText().isEmpty()) {
            mostrarAlerta("Debes ingresar el ID de la transacción.");
            return;
        }
        String id = idBuscarTransaccion.getText();
        Transaccion encontrada = banco.getTransacciones().stream()
            .filter(t -> t.getIdTransaccion().equals(id))
            .findFirst().orElse(null);
        if (encontrada != null) {
            tablaTransacciones.setItems(FXCollections.observableArrayList(encontrada));
        } else {
            mostrarAlerta("Transacción no encontrada.");
        }
        idBuscarTransaccion.clear();
    }

    /**
     * Maneja el evento para volver a la vista principal del administrador.
     * Intenta recargar la vista de administrador y muestra una alerta si ocurre un error.
     */
    @FXML
    private void manejarVolver() {
        try {
            App.establecerVista("administradorView");
        } catch (java.io.IOException e) {
            mostrarAlerta("Error al volver a la vista de administrador.");
        }
    }
}
