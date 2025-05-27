open module org.uniquindio.edu.co.poo.proyectofinalp1 {
    requires javafx.controls;
    requires javafx.fxml;

    // Como el módulo es 'open', no es necesario usar declaraciones 'opens' específicas.

    // Exporta los paquetes que deben ser accesibles desde otros módulos
    exports org.uniquindio.edu.co.poo.proyectofinalp1.controller;
    exports org.uniquindio.edu.co.poo.proyectofinalp1.model;
}
