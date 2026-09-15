package com.braynsystem.optimizacioncodigo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class OptimizadorCApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(OptimizadorCApplication.class.getResource("Optimizacion-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 850, 700);
        stage.setTitle( "Optimización de Código");
        stage.setScene(scene);
        stage.show();
    }
}
