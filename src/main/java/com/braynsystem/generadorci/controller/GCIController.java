package com.braynsystem.generadorci.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class GCIController {

    @FXML
    private StackPane panelContenido;

    @FXML
    public void initialize() {
        System.out.println("Dashboard cargado correctamente");
    }
    private void cargarVista(String archivo) {

        try {

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/com/braynsystem/generadorci/" + archivo
                    )
            );

            Node vista = loader.load();

            panelContenido.getChildren().clear();
            panelContenido.getChildren().add(vista);

        } catch (IOException e) {

            System.out.println(
                    "Error al cargar la vista: " + archivo
            );

            e.printStackTrace();
        }
    }
    @FXML
    private void abrirNotaciones() {

        cargarVista("Notaciones.fxml");
    }
    @FXML
    private void abrirPolaca() {
        cargarVista("Polaca.fxml");
    }
    @FXML
    private void abrirCodigoP() {
        cargarVista("CodigoP.fxml");
    }
    @FXML
    private void abrirTriplos() {
        cargarVista("Triplos.fxml");
    }
    @FXML
    private void abrirCuadruplos() {
        cargarVista("Cuadruplos.fxml");
    }
    @FXML
    private void abrirVariables() {
        cargarVista("Variables.fxml");
    }
    @FXML
    private void abrirExpresiones() {
        cargarVista("Expresiones.fxml");
    }
    @FXML
    private void abrirAsignacion() {
        cargarVista("Asignacion.fxml");
    }
    @FXML
    private void abrirControl() {
        cargarVista("Control.fxml");
    }
    @FXML
    private void abrirFunciones() {
        cargarVista("Funciones.fxml");
    }
    @FXML
    private void abrirEstructuras() {
        cargarVista("Estructuras.fxml");
    }
}