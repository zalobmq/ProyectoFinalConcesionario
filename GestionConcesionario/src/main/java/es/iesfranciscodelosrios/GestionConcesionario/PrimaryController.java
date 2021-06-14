package es.iesfranciscodelosrios.GestionConcesionario;

import java.io.IOException;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import modelos.Cliente;
import modelos.ClienteDAO;
import modelos.Coche;
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
    private TableColumn<Coche, String> potenciacoche;

    @FXML
    private TableColumn<Coche, String> preciocoche;
    @FXML
    private TableColumn<Cliente, String> nombrecliente;

    @FXML
    private TableColumn<Cliente, String> dnicliente;
    
    private ObservableList<Cliente> listaClientes;
    private ObservableList<Coche> listaCoches;
    private ObservableList<Cliente> listaClienActualizada;
    private ClienteDAO c;
   // private int posicionEnLaTabla;
    
    @FXML
    protected void initialize () {
    	
    	configurarTablaClientes();
    	mostrarInformacion(null);
    	listaClientes = FXCollections.observableArrayList(ClienteDAO.MostrarTodos());
    	tabladeclientes.setItems(listaClientes);
    	tabladeclientes.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> mostrarInformacion(newValue));
    	
    }
    private void configurarTablaClientes() {
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
    @FXML
    private void configurarTablaCoches() {
    	if(tabladeclientes.getSelectionModel().getSelectedItem() != null) {
    	c = new ClienteDAO(tabladeclientes.getSelectionModel().getSelectedItem().getDni());
    	listaCoches = FXCollections.observableArrayList(c.getMisCoches());    	
    	tabladecoches.setItems(listaCoches);

    	matriculacoche.setCellValueFactory(cadacoche -> {
            SimpleStringProperty v = new SimpleStringProperty();
            v.setValue(cadacoche.getValue().getMatricula());
            return v;
        });
		marcacoche.setCellValueFactory(cadacoche -> {
            SimpleStringProperty v = new SimpleStringProperty();
            v.setValue(cadacoche.getValue().getMarca());
            return v;
        });
		colorcoche.setCellValueFactory(cadacoche -> {
            SimpleStringProperty v = new SimpleStringProperty();
            v.setValue(cadacoche.getValue().getColor());
            return v;
        });
		potenciacoche.setCellValueFactory(cadacoche -> {
            SimpleStringProperty v = new SimpleStringProperty();
            v.setValue(cadacoche.getValue().getPotencia()+"");
            return v;
        });
		preciocoche.setCellValueFactory(cadacoche -> {
           SimpleStringProperty v = new SimpleStringProperty();
            v.setValue(cadacoche.getValue().getPrecio()+"");
            return v;
        });
    	}
    }
    
    
    private void mostrarInformacion(Cliente c) {
    	tabladecoches.setItems(null);
    	if (c != null) {
    		edadcliente.setText(c.getEdad()+"");
    		telefonocliente.setText(c.getTelefono()+"");
    		
    	}else {
    		edadcliente.setText("");
    		telefonocliente.setText("");
    	} 	
    }
    @FXML
    private void actualizarTabla() {
    	configurarTablaClientes();
    	listaClienActualizada = FXCollections.observableArrayList(ClienteDAO.MostrarTodos());
    	tabladeclientes.setItems(listaClienActualizada);


    }
    @FXML
	private void editarCliente() {
		try {
			App.loadScene(new Stage(), "editarCoche", "Menu editar cliente");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
    @FXML
    private void mostrarCoches() {
    	
    	try {
			App.loadScene(new Stage(), "mostrarCoches", "Gestor de coches");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    @FXML
    private void añadirCliente() {
    	
    	try {
			App.loadScene(new Stage(), "añadirCliente", "Añadir cliente");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    @FXML 
    private void borrarCliente() {
    
    	
    	String dni=tabladeclientes.getSelectionModel().selectedItemProperty().get().getDni();   	
    	ClienteDAO cliente = new ClienteDAO(dni);
    	if (cliente.getMisCoches().size() == 0) {
    		
    		cliente.eliminar(dni);
        	actualizarTabla();
        	
    	}else {
    	
    		try {
    			App.loadScene(new Stage(), "alertaBorrarCliente", "Alerta borrar cliente");
    		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
    		}
    	}
}
    
}
