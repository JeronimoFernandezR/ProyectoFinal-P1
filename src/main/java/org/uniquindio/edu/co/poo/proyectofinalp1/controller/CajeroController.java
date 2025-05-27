package org.uniquindio.edu.co.poo.proyectofinalp1.controller;

import java.util.ArrayList;

import org.uniquindio.edu.co.poo.proyectofinalp1.app.App;
import org.uniquindio.edu.co.poo.proyectofinalp1.model.Cajero;
import org.uniquindio.edu.co.poo.proyectofinalp1.model.Cliente;
import org.uniquindio.edu.co.poo.proyectofinalp1.model.Cuenta;
import org.uniquindio.edu.co.poo.proyectofinalp1.model.Transaccion;
import org.uniquindio.edu.co.poo.proyectofinalp1.model.Usuario;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

/**
 * Controlador para la vista de cajero.
 * Permite registrar clientes, realizar depósitos, retiros, transferencias, consultar saldo y generar reportes.
 */
public class CajeroController {

    // === PANELES Y CAMPOS ===
    @FXML private AnchorPane panelRegistrarCliente, panelDeposito, panelRetiro, panelConsultaSaldo, panelTransferencia, panelReporteTransacciones, panelCuentas;
    @FXML private StackPane panelContenido;

    // Registro de cliente
    @FXML private TextField txtName, txtEmail, txtId, txtAge, txtPhone, txtCity;
    @FXML private TextArea txtAddress;
    @FXML private TextField txtUserPassword; // Debe coincidir con el FXML
    @FXML private Button btnSubmitCustomer;

    // Depósito
    @FXML private TextField txtCuentaDeposito, txtMontoDeposito;
    @FXML private Button btnDepositar;

    // Retiro
    @FXML private TextField txtCuentaRetiro, txtMontoRetiro;
    @FXML private Button btnRetirar;

    // Transferencia
    @FXML private TextField txtCuentaOrigen, txtCuentaDestino, txtMontoTransferencia;
    @FXML private Button btnTransferir;

    // Consulta de saldo
    @FXML private TextField txtCuentaConsultaSaldo;
    @FXML private Label lblResultadoSaldo;

    // Reporte de transacciones
    @FXML private TextField txtCuentaReporte;
    @FXML private TableView<Transaccion> tablaReporteTransacciones;
    @FXML private TableColumn<Transaccion, String> colFecha, colDescripcion;
    @FXML private TableColumn<Transaccion, Double> colMonto;

    // Tipo de cuenta
    @FXML private ComboBox<String> comboTipoCuenta;

    // Nuevos campos sugeridos
    @FXML private TextField txtIdClienteCuenta, txtNIT, txtIdCuentaNueva, txtSaldoInicialCuenta;
    @FXML private Button btnCrearCuenta;

    // Campos para eliminar cuenta
    @FXML private TableView<Cuenta> tablaCuentasBanco;
    @FXML private TableColumn<Cuenta, String> colIdCuenta, colTipoCuenta, colClienteCuenta;
    @FXML private TextField txtIdEliminarCuenta;
    @FXML private Button btnEliminarCuenta;

    /**
     * Inicializa la vista y configura las columnas de la tabla de transacciones.
     */
    @FXML
    public void initialize() {
        if (colFecha != null) {
            colFecha.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(
                new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm").format(cellData.getValue().getFecha())));
        }
        if (colDescripcion != null) {
            colDescripcion.setCellValueFactory(cellData -> {
                Transaccion t = cellData.getValue();
                String desc;
                if (t.getCuentaOrigen() != null && t.getCuentaDestino() != null) {
                    desc = "Transferencia";
                } else if (t.getCuentaOrigen() == null) {
                    desc = "Depósito";
                } else {
                    desc = "Retiro";
                }
                return new javafx.beans.property.SimpleStringProperty(desc);
            });
        }
        if (colMonto != null) {
            colMonto.setCellValueFactory(new PropertyValueFactory<>("monto"));
        }
        if (comboTipoCuenta != null) {
            comboTipoCuenta.getItems().addAll("Ahorro", "Corriente", "Empresarial");
            comboTipoCuenta.valueProperty().addListener((obs, oldVal, newVal) -> {
                if (txtNIT != null) {
                    txtNIT.setVisible("Empresarial".equalsIgnoreCase(newVal));
                }
            });
        }
        if (tablaCuentasBanco != null) {
            colIdCuenta.setCellValueFactory(new PropertyValueFactory<>("idCuenta"));
            colTipoCuenta.setCellValueFactory(cellData -> {
                Cuenta c = cellData.getValue();
                String tipo;
                if (c instanceof org.uniquindio.edu.co.poo.proyectofinalp1.model.CuentaAhorro) {
                    tipo = "Ahorro";
                } else if (c instanceof org.uniquindio.edu.co.poo.proyectofinalp1.model.CuentaCorriente) {
                    tipo = "Corriente";
                } else if (c instanceof org.uniquindio.edu.co.poo.proyectofinalp1.model.CuentaEmpresarial) {
                    tipo = "Empresarial";
                } else {
                    tipo = "Desconocido";
                }
                return new javafx.beans.property.SimpleStringProperty(tipo);
            });
            colClienteCuenta.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(
                    cellData.getValue().getCliente() != null ? cellData.getValue().getCliente().getNombre() : ""
                )
            );
            actualizarTablaCuentas();
        }
        // ...configura otras columnas si tienes más tablas...
    }

    /**
     * Busca una cuenta por su ID.
     * @param id Identificador de la cuenta.
     * @return Cuenta encontrada o null.
     */
    private Cuenta buscarCuentaPorId(String id) {
        if (id == null) return null;
        id = id.trim();
        for (Cuenta cuenta : App.banco.getCuentas()) {
            if (cuenta.getIdCuenta().equalsIgnoreCase(id)) {
                return cuenta;
            }
        }
        return null;
    }

    /**
     * Obtiene el cajero autenticado actualmente.
     * @return Cajero autenticado o null.
     */
    private Cajero obtenerCajeroAutenticado() {
        if (App.usuarioAutenticado != null && App.usuarioAutenticado.getPersona() instanceof Cajero) {
            return (Cajero) App.usuarioAutenticado.getPersona();
        }
        return null;
    }

    // === ACCIONES ===

    @FXML
    private void manejarRegistrarCliente() {
        Cajero cajero = obtenerCajeroAutenticado();
        if (cajero == null || !cajero.isEmpleadoActivo()) {
            mostrarAlerta("El cajero no está activo. No puede realizar esta acción.");
            return;
        }
        String nombre = txtName.getText();
        String correo = txtEmail.getText();
        String id = txtId.getText();
        String contrasena = txtUserPassword.getText(); // Usa el campo correcto
        int edad;
        try {
            edad = Integer.parseInt(txtAge.getText());
        } catch (NumberFormatException e) {
            mostrarAlerta("Edad inválida.");
            return;
        }
        String direccion = txtAddress.getText();
        String telefono = txtPhone.getText();
        String ciudad = txtCity.getText();

        if (nombre.isEmpty() || correo.isEmpty() || id.isEmpty() || contrasena.isEmpty() || direccion.isEmpty() || telefono.isEmpty() || ciudad.isEmpty()) {
            mostrarAlerta("Todos los campos son obligatorios.");
            return;
        }

        for (Cliente c : App.banco.getClientes()) {
            if (c.getId().equals(id)) {
                mostrarAlerta("Ya existe un cliente con ese ID.");
                return;
            }
        }
        for (Usuario u : App.banco.getUsuarios()) {
            if (u.getNombreUsuario().equals(id)) {
                mostrarAlerta("Ya existe un usuario con ese nombre de usuario.");
                return;
            }
        }

        Cliente cliente = new Cliente(nombre, correo, id, contrasena, edad, direccion, telefono, ciudad);
        Usuario usuarioCliente = new Usuario(id, contrasena, cliente, new ArrayList<>());
        cajero.registrarCliente(cliente);
        App.banco.getUsuarios().add(usuarioCliente);
        mostrarAlerta("Cliente y usuario registrados correctamente.");
        txtName.clear(); txtEmail.clear(); txtId.clear(); txtUserPassword.clear();
        txtAge.clear(); txtAddress.clear(); txtPhone.clear(); txtCity.clear();
    }

    @FXML
    private void manejarDeposito() {
        Cajero cajero = obtenerCajeroAutenticado();
        if (cajero == null || !cajero.isEmpleadoActivo()) {
            mostrarAlerta("El cajero no está activo. No puede realizar esta acción.");
            return;
        }
        String cuentaId = txtCuentaDeposito.getText();
        String montoStr = txtMontoDeposito.getText();
        if (cuentaId.isEmpty() || montoStr.isEmpty()) {
            mostrarAlerta("Debes ingresar cuenta y monto.");
            return;
        }
        Cuenta cuenta = buscarCuentaPorId(cuentaId);
        double monto = parsearMonto(montoStr);
        if (cuenta != null && monto > 0) {
            cuenta.depositar(monto, cajero); // Suma el monto al saldo
            mostrarAlerta("Depósito realizado. Nuevo saldo: $" + String.format("%,.2f", cuenta.getSaldo()));
            lblResultadoSaldo.setText(String.format("Saldo: $%,.2f", cuenta.getSaldo()));
            txtCuentaDeposito.clear(); txtMontoDeposito.clear();
        } else {
            mostrarAlerta("Datos inválidos para depósito.");
        }
    }

    @FXML
    private void manejarRetiro() {
        Cajero cajero = obtenerCajeroAutenticado();
        if (cajero == null || !cajero.isEmpleadoActivo()) {
            mostrarAlerta("El cajero no está activo. No puede realizar esta acción.");
            return;
        }
        String cuentaId = txtCuentaRetiro.getText();
        String montoStr = txtMontoRetiro.getText();
        if (cuentaId.isEmpty() || montoStr.isEmpty()) {
            mostrarAlerta("Debes ingresar cuenta y monto.");
            return;
        }
        Cuenta cuenta = buscarCuentaPorId(cuentaId);
        double monto = parsearMonto(montoStr);

        if (cuenta == null || monto <= 0) {
            mostrarAlerta("Datos inválidos para retiro.");
            return;
        }

        boolean retiroExitoso = false;

        if (cuenta instanceof org.uniquindio.edu.co.poo.proyectofinalp1.model.CuentaCorriente) {
            org.uniquindio.edu.co.poo.proyectofinalp1.model.CuentaCorriente cc = (org.uniquindio.edu.co.poo.proyectofinalp1.model.CuentaCorriente) cuenta;
            // Puede retirar hasta saldo + capacidad de sobregiro
            if (monto <= cc.getSaldo() + cc.getCapacidadSobregiro()) {
                retiroExitoso = cc.retirar(monto, cajero);
            } else {
                mostrarAlerta("El monto excede el saldo y el sobregiro permitido.");
                return;
            }
        } else {
            // Solo puede retirar hasta el saldo disponible
            if (monto <= cuenta.getSaldo()) {
                retiroExitoso = cuenta.retirar(monto, cajero);
            } else {
                mostrarAlerta("El monto excede el saldo disponible.");
                return;
            }
        }

        if (retiroExitoso) {
            mostrarAlerta("Retiro realizado. Nuevo saldo: $" + String.format("%,.2f", cuenta.getSaldo()));
            lblResultadoSaldo.setText(String.format("Saldo: $%,.2f", cuenta.getSaldo()));
            txtCuentaRetiro.clear(); txtMontoRetiro.clear();
        } else {
            mostrarAlerta("No se pudo realizar el retiro.");
        }
    }

    @FXML
    private void manejarTransferencia() {
        Cajero cajero = obtenerCajeroAutenticado();
        if (cajero == null || !cajero.isEmpleadoActivo()) {
            mostrarAlerta("El cajero no está activo. No puede realizar esta acción.");
            return;
        }
        String idOrigen = txtCuentaOrigen.getText();
        String idDestino = txtCuentaDestino.getText();
        String montoStr = txtMontoTransferencia.getText();
        if (idOrigen.isEmpty() || idDestino.isEmpty() || montoStr.isEmpty()) {
            mostrarAlerta("Debes ingresar todas las cuentas y el monto.");
            return;
        }
        Cuenta origen = buscarCuentaPorId(idOrigen);
        Cuenta destino = buscarCuentaPorId(idDestino);
        double monto = parsearMonto(montoStr);
        if (origen != null && destino != null && cajero != null && monto > 0) {
            cajero.transferir(App.banco, origen, destino, monto);
            mostrarAlerta("Transferencia realizada.\nSaldo origen: $" + String.format("%,.2f", origen.getSaldo()) +
                          "\nSaldo destino: $" + String.format("%,.2f", destino.getSaldo()));
            txtCuentaOrigen.clear(); txtCuentaDestino.clear(); txtMontoTransferencia.clear();
        } else {
            mostrarAlerta("Datos inválidos para transferencia.");
        }
    }

    @FXML
    private void manejarConsultaSaldo() {
        Cajero cajero = obtenerCajeroAutenticado();
        if (cajero == null || !cajero.isEmpleadoActivo()) {
            mostrarAlerta("El cajero no está activo. No puede realizar esta acción.");
            return;
        }
        Cuenta cuenta = buscarCuentaPorId(txtCuentaConsultaSaldo.getText());
        if (cuenta != null) {
            lblResultadoSaldo.setText(String.format("Saldo: $%,.2f", cuenta.getSaldo()));
        } else {
            lblResultadoSaldo.setText("Cuenta no encontrada.");
        }
    }

    @FXML
    private void manejarGenerarReporte() {
        Cajero cajero = obtenerCajeroAutenticado();
        if (cajero == null || !cajero.isEmpleadoActivo()) {
            mostrarAlerta("El cajero no está activo. No puede realizar esta acción.");
            return;
        }
        Cuenta cuenta = buscarCuentaPorId(txtCuentaReporte.getText());
        if (cuenta != null) {
            tablaReporteTransacciones.setItems(FXCollections.observableArrayList(cuenta.getTransacciones()));
        } else {
            tablaReporteTransacciones.setItems(FXCollections.observableArrayList());
            mostrarAlerta("Cuenta no encontrada.");
        }
    }

    @FXML
    private void manejarCrearCuenta() {
        Cajero cajero = obtenerCajeroAutenticado();
        if (cajero == null || !cajero.isEmpleadoActivo()) {
            mostrarAlerta("El cajero no está activo. No puede realizar esta acción.");
            return;
        }
        String idCliente = txtIdClienteCuenta.getText();
        String idCuenta = txtIdCuentaNueva.getText();
        String saldoInicialStr = txtSaldoInicialCuenta.getText();
        String tipoCuenta = comboTipoCuenta.getValue();
        String nit = txtNIT.getText();

        if (idCliente.isEmpty() || idCuenta.isEmpty() || saldoInicialStr.isEmpty() || tipoCuenta == null ||
            ("Empresarial".equals(tipoCuenta) && nit.isEmpty())) {
            mostrarAlerta("Todos los campos son obligatorios.");
            return;
        }

        Cliente cliente = null;
        for (Cliente c : App.banco.getClientes()) {
            if (c.getId().equals(idCliente)) {
                cliente = c;
                break;
            }
        }
        if (cliente == null) {
            mostrarAlerta("Cliente no encontrado.");
            return;
        }

        if (buscarCuentaPorId(idCuenta) != null) {
            mostrarAlerta("Ya existe una cuenta con ese ID.");
            return;
        }

        double saldoInicial = parsearMonto(saldoInicialStr);
        if (saldoInicial < 0) {
            mostrarAlerta("Saldo inicial inválido.");
            return;
        }

        Cuenta nuevaCuenta = null;
        if ("Ahorro".equals(tipoCuenta)) {
            nuevaCuenta = new org.uniquindio.edu.co.poo.proyectofinalp1.model.CuentaAhorro(idCuenta, saldoInicial, cliente, new ArrayList<>());
        } else if ("Corriente".equals(tipoCuenta)) {
            nuevaCuenta = new org.uniquindio.edu.co.poo.proyectofinalp1.model.CuentaCorriente(idCuenta, saldoInicial, cliente, new ArrayList<>(), 200.0);
        } else if ("Empresarial".equals(tipoCuenta)) {
            int nitInt;
            try {
                nitInt = Integer.parseInt(nit);
            } catch (NumberFormatException e) {
                mostrarAlerta("NIT inválido.");
                return;
            }
            nuevaCuenta = new org.uniquindio.edu.co.poo.proyectofinalp1.model.CuentaEmpresarial(idCuenta, saldoInicial, cliente, new ArrayList<>(), nitInt);
        }

        if (nuevaCuenta != null) {
            App.banco.getCuentas().add(nuevaCuenta);
            mostrarAlerta("Cuenta creada exitosamente. ID de cuenta: " + idCuenta);
            txtIdClienteCuenta.clear(); txtNIT.clear(); txtIdCuentaNueva.clear(); txtSaldoInicialCuenta.clear();
            comboTipoCuenta.getSelectionModel().clearSelection();
            txtNIT.setVisible(false);
            actualizarTablaCuentas();
        } else {
            mostrarAlerta("Error al crear la cuenta.");
        }
    }

    @FXML
    private void manejarEliminarCuenta() {
        String id = txtIdEliminarCuenta.getText();
        if (id == null || id.isEmpty()) {
            mostrarAlerta("Debes ingresar el ID de la cuenta a eliminar.");
            return;
        }
        Cuenta cuenta = buscarCuentaPorId(id);
        if (cuenta == null) {
            mostrarAlerta("No existe una cuenta con ese ID.");
            return;
        }
        boolean eliminada = App.banco.getCuentas().remove(cuenta);
        if (eliminada) {
            mostrarAlerta("Cuenta eliminada exitosamente.");
            actualizarTablaCuentas();
            txtIdEliminarCuenta.clear();
            tablaCuentasBanco.getSelectionModel().clearSelection();
        } else {
            mostrarAlerta("No se pudo eliminar la cuenta.");
        }
    }

    // === UTILIDAD ===

    /**
     * Parsea un texto a double para montos.
     * @param texto Texto a convertir.
     * @return Valor double o -1 si es inválido.
     */
    private double parsearMonto(String texto) {
        try {
            return Double.parseDouble(texto);
        } catch (Exception e) {
            return -1;
        }
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

    // Métodos para mostrar paneles (ya implementados)
    @FXML private void mostrarPanelRegistrarCliente() { establecerPanelVisible(panelRegistrarCliente); }
    @FXML private void mostrarPanelDeposito() { establecerPanelVisible(panelDeposito); }
    @FXML private void mostrarPanelRetiro() { establecerPanelVisible(panelRetiro); }
    @FXML private void mostrarPanelConsultaSaldo() { establecerPanelVisible(panelConsultaSaldo); }
    @FXML private void mostrarPanelTransferencia() { establecerPanelVisible(panelTransferencia); }
    @FXML private void mostrarPanelReporteTransacciones() { establecerPanelVisible(panelReporteTransacciones); }
    @FXML private void mostrarPanelCuentas() {
        establecerPanelVisible(panelCuentas);
        actualizarTablaCuentas();
    }

    /**
     * Muestra solo el panel indicado y oculta los demás.
     * @param panelAMostrar Panel a mostrar.
     */
    private void establecerPanelVisible(AnchorPane panelAMostrar) {
        panelRegistrarCliente.setVisible(false);
        panelDeposito.setVisible(false);
        panelRetiro.setVisible(false);
        panelConsultaSaldo.setVisible(false);
        panelTransferencia.setVisible(false);
        panelReporteTransacciones.setVisible(false);
        panelCuentas.setVisible(false);
        panelAMostrar.setVisible(true);
    }

    // Método para actualizar la tabla con las cuentas actuales
    private void actualizarTablaCuentas() {
        if (tablaCuentasBanco != null) {
            tablaCuentasBanco.setItems(FXCollections.observableArrayList(App.banco.getCuentas()));
            tablaCuentasBanco.getSelectionModel().clearSelection();
        }
    }
}
