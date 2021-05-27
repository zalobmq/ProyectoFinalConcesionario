module es.iesfranciscodelosrios.GestionConcesionario {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
	requires com.sun.xml.bind;
    requires jakarta.xml.bind;
	requires java.desktop;
	requires javafx.base;

    opens es.iesfranciscodelosrios.GestionConcesionario to javafx.fxml, javafx.controls, javafx.graphics;
    
    opens modelos to com.sun.xml.bind, jakarta.xml.bind;
    opens utils to com.sun.xml.bind, jakarta.xml.bind;
    
    exports es.iesfranciscodelosrios.GestionConcesionario;
}
