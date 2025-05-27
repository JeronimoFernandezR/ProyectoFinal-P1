package org.uniquindio.edu.co.poo.proyectofinalp1.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import org.uniquindio.edu.co.poo.proyectofinalp1.app.App;
import org.uniquindio.edu.co.poo.proyectofinalp1.model.Cliente;
import org.uniquindio.edu.co.poo.proyectofinalp1.model.Cuenta;
import org.uniquindio.edu.co.poo.proyectofinalp1.model.Transaccion;
import org.uniquindio.edu.co.poo.proyectofinalp1.model.Usuario;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Controlador para la vista de cliente.
 * Permite visualizar cuentas y el historial de transacciones.
 */
public class ClienteController {

    @FXML
    private TableView<Cuenta> tablaCuentas;

    @FXML
    private TableColumn<Cuenta, String> columnaNumeroCuenta;

    @FXML
    private Label etiquetaSaldo;

    @FXML
    private TableView<Transaccion> historialTransacciones;

    @FXML
    private TableColumn<Transaccion, String> columnaIdTransaccion;

    @FXML
    private TableColumn<Transaccion, String> columnaFecha;

    @FXML
    private TableColumn<Transaccion, String> columnaDescripcion;

    @FXML
    private TableColumn<Transaccion, Double> columnaMonto;

    @FXML
    private TableColumn<Transaccion, String> columnaCajeroAsociado;

    /**
     * Inicializa la vista del cliente, mostrando cuentas y transacciones.
     */
    @FXML
    public void initialize() {
        Usuario usuario = App.usuarioAutenticado;
        if (usuario == null || !(usuario.getPersona() instanceof Cliente)) {
            return;
        }
        Cliente cliente = (Cliente) usuario.getPersona();

        // Configurar columna de número de cuenta
        columnaNumeroCuenta.setCellValueFactory(new PropertyValueFactory<>("idCuenta"));

        // Obtener cuentas asociadas al cliente
        ArrayList<Cuenta> cuentasCliente = obtenerCuentasDeCliente(cliente);
        tablaCuentas.setItems(FXCollections.observableArrayList(cuentasCliente));

        // Mostrar saldo de la primera cuenta (si existe)
        if (!cuentasCliente.isEmpty()) {
            etiquetaSaldo.setText("$" + cuentasCliente.get(0).getSaldo());
            // Mostrar transacciones de la primera cuenta por defecto
            mostrarTransaccionesDeCuenta(cuentasCliente.get(0));
            tablaCuentas.getSelectionModel().selectFirst();
        } else {
            etiquetaSaldo.setText("$0.00");
            historialTransacciones.setItems(FXCollections.observableArrayList());
        }

        // Configurar columnas del historial de transacciones
        columnaIdTransaccion.setCellValueFactory(new PropertyValueFactory<>("idTransaccion"));
        columnaFecha.setCellValueFactory(cellData -> {
            Date fecha = cellData.getValue().getFecha();
            String fechaStr = fecha != null ? new SimpleDateFormat("yyyy-MM-dd HH:mm").format(fecha) : "";
            return new SimpleStringProperty(fechaStr);
        });
        columnaDescripcion.setCellValueFactory(cellData -> {
            Transaccion t = cellData.getValue();
            String desc;
            if (t.getCuentaOrigen() != null && t.getCuentaDestino() != null) {
                desc = "Transferencia";
            } else if (t.getCuentaOrigen() == null) {
                desc = "Depósito";
            } else {
                desc = "Retiro";
            }
            return new SimpleStringProperty(desc);
        });
        columnaMonto.setCellValueFactory(new PropertyValueFactory<>("monto"));
        columnaCajeroAsociado.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(
                cellData.getValue().getCajero() != null ? cellData.getValue().getCajero().getNombre() : ""
            );
        });

        // Escuchar selección de cuenta
        tablaCuentas.getSelectionModel().selectedItemProperty().addListener(
            new ChangeListener<Cuenta>() {
                @Override
                public void changed(ObservableValue<? extends Cuenta> observable, Cuenta oldValue, Cuenta newValue) {
                    if (newValue != null) {
                        etiquetaSaldo.setText("$" + newValue.getSaldo());
                        mostrarTransaccionesDeCuenta(newValue);
                    }
                }
            }
        );
    }

    /**
     * Muestra las transacciones de la cuenta seleccionada.
     * @param cuenta Cuenta seleccionada.
     */
    private void mostrarTransaccionesDeCuenta(Cuenta cuenta) {
        if (cuenta != null) {
            historialTransacciones.setItems(FXCollections.observableArrayList(cuenta.getTransacciones()));
        } else {
            historialTransacciones.setItems(FXCollections.observableArrayList());
        }
    }

    /**
     * Obtiene todas las cuentas asociadas a un cliente.
     * @param cliente Cliente autenticado.
     * @return Lista de cuentas del cliente.
     */
    private ArrayList<Cuenta> obtenerCuentasDeCliente(Cliente cliente) {
        ArrayList<Cuenta> cuentasCliente = new ArrayList<>();
        for (Cuenta cuenta : App.banco.getCuentas()) {
            if (cuenta.getCliente().equals(cliente)) {
                cuentasCliente.add(cuenta);
            }
        }
        return cuentasCliente;
    }
}

