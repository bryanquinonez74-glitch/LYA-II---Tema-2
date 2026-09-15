package com.braynsystem.generadorci.controller;

import com.braynsystem.generadorci.analizadores.GeneradorControl;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ControlController {

    @FXML
    private TextField txtInstruccion;

    @FXML
    private TextArea txtResultado;

    @FXML
    private Label lblMensaje;

    @FXML
    public void initialize() {

        System.out.println(
                "Módulo de Instrucciones de Control cargado"
        );
    }

    @FXML
    private void generar() {

        String instruccion =
                txtInstruccion.getText().trim();

        if (instruccion.isEmpty()) {

            lblMensaje.setText(
                    "Escribe una instrucción."
            );

            txtResultado.clear();

            return;
        }

        String resultado =
                GeneradorControl.generar(instruccion);

        txtResultado.setText(resultado);

        if (resultado.startsWith("Error")) {

            lblMensaje.setText(
                    "No se pudo procesar la instrucción."
            );

        } else {

            lblMensaje.setText(
                    "Código intermedio generado correctamente."
            );
        }
    }

    @FXML
    private void ejemploIf() {

        txtInstruccion.setText(
                "if (a < b)"
        );

        generar();
    }

    @FXML
    private void ejemploWhile() {

        txtInstruccion.setText(
                "while (x < 10)"
        );

        generar();
    }

    @FXML
    private void ejemploFor() {

        txtInstruccion.setText(
                "for (i = 0; i < 10; i++)"
        );

        generar();
    }

    @FXML
    private void limpiar() {

        txtInstruccion.clear();
        txtResultado.clear();
        lblMensaje.setText("");

        txtInstruccion.requestFocus();
    }
}