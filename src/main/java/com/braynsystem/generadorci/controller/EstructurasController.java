package com.braynsystem.generadorci.controller;

import com.braynsystem.generadorci.analizadores.GeneradorEstructuras;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class EstructurasController {

    @FXML
    private TextArea txtEstructura;

    @FXML
    private TextArea txtResultado;

    @FXML
    private Label lblMensaje;

    @FXML
    public void initialize() {

        System.out.println(
                "Módulo de Estructuras cargado correctamente"
        );
    }

    @FXML
    private void generar() {

        String codigo =
                txtEstructura.getText().trim();

        if (codigo.isEmpty()) {

            lblMensaje.setText(
                    "Escribe una estructura."
            );

            txtResultado.clear();

            return;
        }

        String resultado =
                GeneradorEstructuras.generar(codigo);

        txtResultado.setText(resultado);

        if (resultado.startsWith("Error")) {

            lblMensaje.setText(
                    "No se pudo procesar la estructura."
            );

        } else {

            lblMensaje.setText(
                    "Estructura procesada correctamente."
            );
        }
    }

    @FXML
    private void ejemploPersona() {

        txtEstructura.setText(
                """
                struct Persona {
                    int edad;
                    float altura;
                    String nombre;
                }
                """
        );

        generar();
    }

    @FXML
    private void ejemploProducto() {

        txtEstructura.setText(
                """
                struct Producto {
                    int codigo;
                    float precio;
                    int cantidad;
                }
                """
        );

        generar();
    }

    @FXML
    private void limpiar() {

        txtEstructura.clear();
        txtResultado.clear();
        lblMensaje.setText("");

        txtEstructura.requestFocus();
    }
}