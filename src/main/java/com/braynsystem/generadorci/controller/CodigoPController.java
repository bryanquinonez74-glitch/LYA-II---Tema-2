package com.braynsystem.generadorci.controller;

import com.braynsystem.generadorci.analizadores.GeneradorCodigoP;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CodigoPController {

    @FXML
    private TextField txtExpresion;

    @FXML
    private TextArea txtResultado;

    @FXML
    public void initialize() {
        System.out.println("Módulo Código P cargado");
    }

    @FXML
    private void generarCodigo() {

        String expresion = txtExpresion.getText().trim();

        if (expresion.isEmpty()) {

            txtResultado.setText(
                    "Error: escribe una expresión."
            );

            return;
        }

        String codigo =
                GeneradorCodigoP.generar(expresion);

        txtResultado.setText(
                "CÓDIGO P GENERADO\n\n" +
                        codigo
        );
    }

    @FXML
    private void limpiar() {

        txtExpresion.clear();
        txtResultado.clear();

        txtExpresion.requestFocus();
    }
}