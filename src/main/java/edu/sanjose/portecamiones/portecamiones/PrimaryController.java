package edu.sanjose.portecamiones.portecamiones;

import java.io.IOException;
import javafx.fxml.FXML;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
    
    @FXML
    private void listDrivers() throws IOException {
    	App.setRoot("driverList");
    }
}
