package com.braynsystem.generadorci.controller;

import com.braynsystem.generadorci.analizadores.ConvertidorNotaciones;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class NotacionesController {

    @FXML
    private TextField txtExpresion;

    @FXML
    private TextArea txtResultado;

    @FXML
    public void initialize() {

        System.out.println(
                "Módulo de Notaciones cargado"
        );
    }

    @FXML
    private void mostrarInfija() {

        String expresion =
                txtExpresion.getText().trim();

        if (expresion.isEmpty()) {

            txtResultado.setText(
                    "Error: escribe una expresión."
            );

            return;
        }

        txtResultado.setText(
                "NOTACIÓN INFIJA\n\n" +
                        expresion
        );
    }

    @FXML
    private void convertirPrefija() {

        String expresion =
                txtExpresion.getText().trim();

        if (expresion.isEmpty()) {

            txtResultado.setText(
                    "Error: escribe una expresión."
            );

            return;
        }

        String resultado =
                ConvertidorNotaciones
                        .infijaAPrefija(expresion);

        txtResultado.setText(
                "NOTACIÓN PREFIJA\n\n" +
                        resultado
        );
    }

    @FXML
    private void convertirPostfija() {

        String expresion =
                txtExpresion.getText().trim();

        if (expresion.isEmpty()) {

            txtResultado.setText(
                    "Error: escribe una expresión."
            );

            return;
        }

        String resultado =
                ConvertidorNotaciones
                        .infijaAPostfija(expresion);

        txtResultado.setText(
                "NOTACIÓN POSTFIJA\n\n" +
                        resultado
        );
    }

    @FXML
    private void limpiar() {

        txtExpresion.clear();
        txtResultado.clear();

        txtExpresion.requestFocus();
    }
}