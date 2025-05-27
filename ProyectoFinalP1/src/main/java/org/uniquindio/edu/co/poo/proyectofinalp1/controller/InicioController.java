package org.uniquindio.edu.co.poo.proyectofinalp1.controller;

import org.uniquindio.edu.co.poo.proyectofinalp1.app.App;
import org.uniquindio.edu.co.poo.proyectofinalp1.model.Administrador;
import org.uniquindio.edu.co.poo.proyectofinalp1.model.Cajero;
import org.uniquindio.edu.co.poo.proyectofinalp1.model.Cliente;
import org.uniquindio.edu.co.poo.proyectofinalp1.model.Persona;
import org.uniquindio.edu.co.poo.proyectofinalp1.model.Usuario;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * Controlador para la vista de inicio de sesión.
 * Permite autenticar usuarios y redirigirlos a la vista correspondiente.
 */
public class InicioController {

    @FXML
    private TextField txtUsuario;

    @FXML
    private TextField txtContrasena;

    @FXML
    private Button btnIngresar;

    /**
     * Maneja el evento de inicio de sesión.
     * Valida usuario y contraseña, y redirige según el tipo de usuario.
     */
    @FXML
    private void handleLogin() {
        String usuario = txtUsuario.getText().trim();
        String contrasena = txtContrasena.getText().trim();

        if (usuario.isEmpty() || contrasena.isEmpty()) {
            System.out.println("Por favor ingresa usuario y contraseña.");
            return;
        }

        for (Usuario u : App.banco.getUsuarios()) {
            if (u.getNombreUsuario().equals(usuario) && u.getContraseñaUsuario().equals(contrasena)) {
                Persona persona = u.getPersona();
                App.usuarioAutenticado = u;
                try {
                    if (persona instanceof Administrador) {
                        App.establecerVista("administradorView");
                    } else if (persona instanceof Cajero) {
                        App.establecerVista("cajeroView");
                    } else if (persona instanceof Cliente) {
                        App.establecerVista("clienteView");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return;
            }
        }
        System.out.println("Usuario o contraseña incorrectos");
    }
}
