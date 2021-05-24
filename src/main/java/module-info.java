module edu.sanjose.portecamiones.portecamiones {
    requires transitive javafx.controls;
    requires javafx.fxml;
    requires java.sql;
	requires lombok;

    opens edu.sanjose.portecamiones.portecamiones to javafx.fxml;
    exports edu.sanjose.portecamiones.portecamiones;
}
