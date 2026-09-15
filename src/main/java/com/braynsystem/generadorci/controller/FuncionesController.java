package com.braynsystem.generadorci.controller;

import com.braynsystem.generadorci.analizadores.GeneradorFunciones;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FuncionesController {

    @FXML
    private TextField txtFuncion;

    @FXML
    private TextArea txtResultado;

    @FXML
    private Label lblMensaje;

    @FXML
    public void initialize() {

        System.out.println(
                "Módulo de Funciones cargado correctamente"
        );
    }

    @FXML
    private void generar() {

        String funcion =
                txtFuncion.getText().trim();

        if (funcion.isEmpty()) {

            lblMensaje.setText(
                    "Escribe una llamada a función."
            );

            txtResultado.clear();
            return;
        }

        String resultado =
                GeneradorFunciones.generar(funcion);

        txtResultado.setText(resultado);

        if (resultado.startsWith("Error")) {

            lblMensaje.setText(
                    "No se pudo procesar la función."
            );

        } else {

            lblMensaje.setText(
                    "Código intermedio generado correctamente."
            );
        }
    }

    @FXML
    private void ejemploSimple() {

        txtFuncion.setText(
                "suma(a, b)"
        );

        generar();
    }

    @FXML
    private void ejemploAsignacion() {

        txtFuncion.setText(
                "resultado = suma(a, b)"
        );

        generar();
    }

    @FXML
    private void ejemploReturn() {

        txtFuncion.setText(
                "a + b"
        );

        String resultado =
                GeneradorFunciones.generarReturn(
                        txtFuncion.getText()
                );

        txtResultado.setText(resultado);

        lblMensaje.setText(
                "Instrucción RETURN generada correctamente."
        );
    }

    @FXML
    private void limpiar() {

        txtFuncion.clear();
        txtResultado.clear();
        lblMensaje.setText("");

        txtFuncion.requestFocus();
    }
}