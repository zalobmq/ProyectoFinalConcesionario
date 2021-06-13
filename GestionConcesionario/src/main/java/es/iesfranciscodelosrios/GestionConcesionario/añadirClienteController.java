package es.iesfranciscodelosrios.GestionConcesionario;

import java.awt.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelos.ClienteDAO;

public class añadirClienteController {

	@FXML
	private TextField dniañadir;
	@FXML
	private TextField nombreañadir;
	@FXML
	private TextField edadañadir;
	@FXML
	private TextField telefonoañadir;
	@FXML
	private Button añadir;
	@FXML
	protected void initialize() {
		
	}
	@FXML
	private void añadir() {
		
		ClienteDAO cliente = new ClienteDAO();
		cliente.setDni(dniañadir.getText());
		cliente.setNombre(nombreañadir.getText());
		cliente.setEdad(Integer.parseInt(edadañadir.getText()));
		cliente.setTelefono(Integer.parseInt(telefonoañadir.getText()));
		
		cliente.añadir();
		
		
	}
	
}
