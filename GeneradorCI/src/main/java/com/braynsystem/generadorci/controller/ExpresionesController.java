package com.braynsystem.generadorci.controller;

import com.braynsystem.generadorci.analizadores.GeneradorExpresiones;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ExpresionesController {

    @FXML
    private TextField txtExpresion;

    @FXML
    private TextArea txtResultado;

    @FXML
    private Label lblMensaje;

    @FXML
    public void initialize() {

        System.out.println(
                "Módulo de Expresiones cargado"
        );
    }

    @FXML
    private void generarCodigo() {

        String expresion =
                txtExpresion.getText().trim();

        if (expresion.isEmpty()) {

            lblMensaje.setText(
                    "Escribe una expresión."
            );

            txtResultado.clear();

            return;
        }

        String resultado =
                GeneradorExpresiones.generar(expresion);

        txtResultado.setText(resultado);

        if (resultado.startsWith("Error")) {

            lblMensaje.setText(
                    "No se pudo generar el código."
            );

        } else {

            lblMensaje.setText(
                    "Código de tres direcciones generado correctamente."
            );
        }
    }

    @FXML
    private void limpiar() {

        txtExpresion.clear();
        txtResultado.clear();
        lblMensaje.setText("");

        txtExpresion.requestFocus();
    }
}