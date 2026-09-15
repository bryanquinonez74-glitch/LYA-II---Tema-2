package com.braynsystem.generadorci.controller;

import com.braynsystem.generadorci.analizadores.GeneradorCuadruplos;
import com.braynsystem.generadorci.model.Cuadruplo;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.List;

public class CuadruplosController {

    @FXML
    private TextField txtExpresion;

    @FXML
    private TableView<Cuadruplo> tablaCuadruplos;

    @FXML
    private TableColumn<Cuadruplo, String> colOperador;

    @FXML
    private TableColumn<Cuadruplo, String> colArgumento1;

    @FXML
    private TableColumn<Cuadruplo, String> colArgumento2;

    @FXML
    private TableColumn<Cuadruplo, String> colResultado;

    @FXML
    private Label lblMensaje;

    @FXML
    public void initialize() {

        colOperador.setCellValueFactory(
                dato -> new ReadOnlyStringWrapper(
                        dato.getValue().getOperador()
                )
        );

        colArgumento1.setCellValueFactory(
                dato -> new ReadOnlyStringWrapper(
                        dato.getValue().getArgumento1()
                )
        );

        colArgumento2.setCellValueFactory(
                dato -> new ReadOnlyStringWrapper(
                        dato.getValue().getArgumento2()
                )
        );

        colResultado.setCellValueFactory(
                dato -> new ReadOnlyStringWrapper(
                        dato.getValue().getResultado()
                )
        );

        System.out.println("Módulo de Cuádruplos cargado");
    }

    @FXML
    private void generarCuadruplos() {

        String expresion =
                txtExpresion.getText().trim();

        if (expresion.isEmpty()) {

            lblMensaje.setText(
                    "Escribe una expresión."
            );

            tablaCuadruplos.getItems().clear();
            return;
        }

        List<Cuadruplo> resultado =
                GeneradorCuadruplos.generar(expresion);

        if (resultado.isEmpty()) {

            lblMensaje.setText(
                    "Expresión inválida. Usa: a = b + c * d"
            );

            tablaCuadruplos.getItems().clear();
            return;
        }

        tablaCuadruplos.setItems(
                FXCollections.observableArrayList(resultado)
        );

        tablaCuadruplos.refresh();

        lblMensaje.setText(
                "Cuádruplos generados correctamente: "
                        + resultado.size()
        );
    }

    @FXML
    private void limpiar() {

        txtExpresion.clear();
        tablaCuadruplos.getItems().clear();
        lblMensaje.setText("");

        txtExpresion.requestFocus();
    }
}