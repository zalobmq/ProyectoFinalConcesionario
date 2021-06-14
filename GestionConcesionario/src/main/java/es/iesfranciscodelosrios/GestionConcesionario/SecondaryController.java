package es.iesfranciscodelosrios.GestionConcesionario;
import java.io.IOException;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import modelos.Coche;
import modelos.CocheDAO;

public class SecondaryController {
	
	@FXML
    private TableView<Coche> tablacoches;
	
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
    private TableColumn<Coche, String> asignadocoche;
	
    @FXML
    private RadioButton assi;
    @FXML
    private RadioButton asno;
    

	ObservableList<Coche> listaCoches;
	ObservableList<Coche> listaCochesSI;
	ObservableList<Coche> listaCochesNO;
    private ObservableList<Coche> listaClienActualizada;


	@FXML
	protected void initialize() {
	}
	
	private void configurarTabla() {
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
		
		asignadocoche.setCellValueFactory(cadacoche -> {
	           SimpleStringProperty v = new SimpleStringProperty();
	           if(cadacoche.getValue().getCliente() != null) {
	        	   v.setValue("SI");
	           }else {
	        	   v.setValue("NO");
	           }
	            return v;
	        });
    }
	@FXML
	private void buttonMostrarTodos() {
		configurarTabla();
		listaCoches = FXCollections.observableArrayList(CocheDAO.mostrarTodosLosCoches());

		tablacoches.setItems(listaCoches);
	}
	
	@FXML
	private void buttonAñadirCoche() {
		try {
			App.loadScene(new Stage(), "añadirCoche", "Menu añadir coche");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@FXML
	private void ButtonAsignadoSI() {
		
		configurarTablaSI();
		listaCochesSI = FXCollections.observableArrayList(CocheDAO.mostrarCochesConPropietario());
		tablacoches.setItems(listaCochesSI);
		}
	@FXML
	private void ButtonAsignadoNO() {
		configurarTablaNO();
		listaCochesNO = FXCollections.observableArrayList(CocheDAO.mostrarCochesSinPropietario());
		tablacoches.setItems(listaCochesNO);
	}
	
	private void configurarTablaSI() {
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
		
		asignadocoche.setCellValueFactory(cadacoche -> {
	           SimpleStringProperty v = new SimpleStringProperty();
	           v.setValue("SI");
	            return v;
	        });
    }
	private void configurarTablaNO() {
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
		
		asignadocoche.setCellValueFactory(cadacoche -> {
	           SimpleStringProperty v = new SimpleStringProperty();
	           v.setValue("NO");
	            return v;
	        });
    }
	
	@FXML
	private void buttonAsignar() {
		
		try {
			App.loadScene(new Stage(), "asignarCoche", "Menu asignar coche");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML
	private void buttonRetirar() {
		
		try {
			App.loadScene(new Stage(), "retirarCoche", "Menu retirar coche");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML
	private void editarCoche() {
		try {
			App.loadScene(new Stage(), "editarCoche", "Menu editar coche");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	
	@FXML 
    private void borrarCoche() {
		if (tablacoches.getSelectionModel().selectedItemProperty().get().getCliente() != null){
		try {
			App.loadScene(new Stage(), "avisoBorrarCoche", "ERROR");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	}else {
    		
    	    	String matricula=tablacoches.getSelectionModel().selectedItemProperty().get().getMatricula();
    	    	
    	    	CocheDAO coche = new CocheDAO();

    	    	coche.eliminar(matricula);
    	    	actualizarTabla();	
	}
}
	
	@FXML
	private void actualizarTabla() {
		configurarTabla();
		listaClienActualizada = FXCollections.observableArrayList(CocheDAO.mostrarTodosLosCoches());
		tablacoches.setItems(listaClienActualizada);
    }	
}