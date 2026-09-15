package com.braynsystem.generadorci.controller;

import com.braynsystem.generadorci.analizadores.ConvertidorNotaciones;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class PolacaController {

    @FXML
    private TextField txtExpresion;

    @FXML
    private TextArea txtResultado;

    @FXML
    public void initialize() {
        System.out.println("Módulo de Notación Polaca cargado");
    }

    @FXML
    private void generarPolaca() {

        String expresion = txtExpresion.getText().trim();

        if (expresion.isEmpty()) {

            txtResultado.setText(
                    "Error: escribe una expresión."
            );

            return;
        }

        String prefija =
                ConvertidorNotaciones.infijaAPrefija(expresion);

        String postfija =
                ConvertidorNotaciones.infijaAPostfija(expresion);

        txtResultado.setText(
                "EXPRESIÓN INFIJA\n" +
                        expresion +
                        "\n\n" +

                        "NOTACIÓN POLACA PREFIJA\n" +
                        prefija +
                        "\n\n" +

                        "NOTACIÓN POLACA POSTFIJA\n" +
                        postfija
        );
    }

    @FXML
    private void limpiar() {

        txtExpresion.clear();
        txtResultado.clear();
        txtExpresion.requestFocus();
    }
}