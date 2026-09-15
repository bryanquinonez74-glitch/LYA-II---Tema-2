module com.braynsystem.optimizacioncodigo {

    requires javafx.controls;
    requires javafx.fxml;

    opens com.braynsystem.optimizacioncodigo to javafx.fxml;
    opens com.braynsystem.optimizacioncodigo.controller to javafx.fxml;

    exports com.braynsystem.optimizacioncodigo;
    exports com.braynsystem.optimizacioncodigo.controller;
}