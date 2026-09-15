package com.braynsystem.generadorci.controller;

import com.braynsystem.generadorci.analizadores.GeneradorAsignacion;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AsignacionController {

    @FXML
    private TextField txtAsignacion;

    @FXML
    private TextArea txtResultado;

    @FXML
    private Label lblMensaje;

    @FXML
    public void initialize() {

        System.out.println(
                "Módulo de Asignación cargado correctamente"
        );
    }

    @FXML
    private void generar() {

        String asignacion =
                txtAsignacion.getText().trim();

        if (asignacion.isEmpty()) {

            lblMensaje.setText(
                    "Escribe una instrucción de asignación."
            );

            txtResultado.clear();

            return;
        }

        String resultado =
                GeneradorAsignacion.generar(asignacion);

        txtResultado.setText(resultado);

        if (resultado.startsWith("Error")) {

            lblMensaje.setText(
                    "No se pudo procesar la asignación."
            );

        } else {

            lblMensaje.setText(
                    "Asignación procesada correctamente."
            );
        }
    }

    @FXML
    private void limpiar() {

        txtAsignacion.clear();
        txtResultado.clear();
        lblMensaje.setText("");

        txtAsignacion.requestFocus();
    }
}