module edu.sanjose.portecamiones.portecamiones {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens edu.sanjose.portecamiones.portecamiones to javafx.fxml;
    exports edu.sanjose.portecamiones.portecamiones;
}
