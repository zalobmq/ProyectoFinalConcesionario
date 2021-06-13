package es.iesfranciscodelosrios.GestionConcesionario;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import modelos.Cliente;
import modelos.ClienteDAO;
import modelos.Coche;
import modelos.CocheDAO;

public class asignarCoche {

	@FXML
	private TableView<Cliente> tablaclientes;
	@FXML
	private TableView<Coche> tablacoches;
	//----------------------
	
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
	//----------------------
   
    

    @FXML
    private TableColumn<Cliente, String> dnicliente;
    @FXML
    private TableColumn<Cliente, String> nombrecliente;
    
    
    private ObservableList<Cliente> listaClientes;
    private ObservableList<Coche> listaCoches;
    private ObservableList<Coche> listaClienActualizada;
    @FXML
    private TextField txDniCliente;
    @FXML
    private TextField txMatriculaCoche;
    
    
	
	@FXML
	protected void initialize() {
    	configurarTablaClientes();
		listaClientes = FXCollections.observableArrayList(ClienteDAO.MostrarTodos());
    	tablaclientes.setItems(listaClientes);
    	configurarTablaCoches();
    	listaCoches = FXCollections.observableArrayList(CocheDAO.mostrarCochesSinPropietario());
    	tablacoches.setItems(listaCoches);
 


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
	private void configurarTablaCoches() {
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
	
	@FXML
	private void buttonAsignar() {
		
		
		
		String dni;
		String matricula;
		
		dni = txDniCliente.getText();
		matricula = txMatriculaCoche.getText();
		ClienteDAO cliente = new ClienteDAO();
		
		cliente.asignar(dni,matricula);
		actualizarTabla();		
	}
	
	

	private void actualizarTabla() {
    	configurarTablaClientes();
    	listaClienActualizada = FXCollections.observableArrayList(CocheDAO.mostrarCochesSinPropietario());
    	tablacoches.setItems(listaClienActualizada);


    }
	
	
	
}
