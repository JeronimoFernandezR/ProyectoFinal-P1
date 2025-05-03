module org.uniquindio.edu.co.poo.proyectofinalp1 {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.uniquindio.edu.co.poo.proyectofinalp1 to javafx.fxml;
    exports org.uniquindio.edu.co.poo.proyectofinalp1;
}
