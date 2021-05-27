package es.iesfranciscodelosrios.GestionConcesionario;


import java.io.IOException;
import java.sql.Connection;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelos.*;
import utils.Conexion;
import utils.UtilidadXml;


public class PrimaryController {

	@FXML
    private TableView<Cliente> tabladeclientes;

    @FXML
    private Label edadcliente;

    @FXML
    private Label telefonocliente;

    @FXML
    private TableView<Coche> tabladecoches;
    @FXML
    private TableColumn<Coche, String> matriculacoche;

    @FXML
    private TableColumn<Coche, String> marcacoche;

    @FXML
    private TableColumn<Coche, String> colorcoche;

    @FXML
    private TableColumn<Coche, Integer> potenciacoche;

    @FXML
    private TableColumn<Coche, Double> preciocoche;
    @FXML
    private TableColumn<Cliente, String> nombrecliente;

    @FXML
    private TableColumn<Cliente, String> dnicliente;
    
    private ObservableList<Cliente> listaClientes;
    private ObservableList<Coche> listaCoches;
    
    @FXML
    protected void initialize () {
    	
    	configurarTabla();
    	mostrarInformacion(null);
    	listaClientes = FXCollections.observableArrayList(ClienteDAO.MostrarTodos());
    	tabladeclientes.setItems(listaClientes);
    	tabladeclientes.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> mostrarInformacion(newValue));
    	
    	
    }
    private void configurarTabla() {
        dnicliente.setCellValueFactory(cadacliente -> {
            SimpleStringProperty v = new SimpleStringProperty();
            v.setValue(cadacliente.getValue().getDni());
            return v;
        });
        nombrecliente.setCellValueFactory(cadacliente -> {
            SimpleStringProperty v = new SimpleStringProperty();
            v.setValue(cadacliente.getValue().getNombre());
            return v;
        });
    }
    
    private void mostrarInformacion(Cliente c) {
    	
    	if (c != null) {
    		edadcliente.setText(c.getEdad()+"");
    		telefonocliente.setText(c.getTelefono()+"");
    		
    	}else {
    		edadcliente.setText("");
    		telefonocliente.setText("");
    	}
    	
    	
    }
    @FXML
    private void infoApp() {
    	
    	try {
			App.loadScene(new Stage(), "infoApp", "Informacion de la app");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
