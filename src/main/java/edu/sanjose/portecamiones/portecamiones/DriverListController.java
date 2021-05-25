package edu.sanjose.portecamiones.portecamiones;

import java.net.URL;
import java.util.ResourceBundle;

import database.connector.Conector;
import database.dao.Driver;
import database.manager.DriverManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class DriverListController implements Initializable {
	
	@FXML TableView<Driver> table;
	@FXML TableColumn<Driver, Integer> id;
	@FXML TableColumn<Driver, String> nombre;
	@FXML TableColumn<Driver, String> apellidos;
	@FXML TableColumn<Driver, String> telefono;
	@FXML TableColumn<Driver, String> email;
	@FXML TableColumn<Driver, String> camion;
	
	ObservableList<Driver> obList = FXCollections.observableArrayList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		obList.addAll(DriverManager.findAll(new Conector().getMySQLConnection()));
		nombre.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		table.setItems(obList);
	}
	
	@FXML
	private void addDriver() {
		
	}
	
	@FXML
	private void changeTruck() {
		
	}

}
