package es.iesfranciscodelosrios.GestionConcesionario;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import modelos.CocheDAO;

public class añadirCocheController {

	@FXML
	private TextField txmatricula;
	@FXML
	private TextField txmarca;
	@FXML
	private TextField txcolor;
	@FXML
	private TextField txpotencia;
	@FXML
	private TextField txprecio;
	@FXML
	private Button añadir;
	
	@FXML
	protected void initialize() {
		
	}
	
	@FXML
	private void añadir() {
		CocheDAO coche = new CocheDAO();
		
		coche.setMatricula(txmatricula.getText());
		coche.setMarca(txmarca.getText());
		coche.setColor(txcolor.getText());
		coche.setPotencia(Integer.parseInt(txpotencia.getText()));
		coche.setPrecio(Integer.parseInt(txprecio.getText()));
		coche.añadir();
		
	}
}
