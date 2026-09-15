module com.braynsystem.generadorci {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.braynsystem.generadorci to javafx.fxml;
    exports com.braynsystem.generadorci;

    exports com.braynsystem.generadorci.controller;
    opens com.braynsystem.generadorci.controller to javafx.fxml;
}