package com.braynsystem.generadorci.controller;

import com.braynsystem.generadorci.analizadores.GeneradorTriplos;
import com.braynsystem.generadorci.model.Triplo;

import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.List;

public class TriplosController {

    @FXML
    private TextField txtExpresion;

    @FXML
    private TableView<Triplo> tablaTriplos;

    @FXML
    private TableColumn<Triplo, Number> colIndice;

    @FXML
    private TableColumn<Triplo, String> colOperador;

    @FXML
    private TableColumn<Triplo, String> colArgumento1;

    @FXML
    private TableColumn<Triplo, String> colArgumento2;

    @FXML
    private Label lblMensaje;

    @FXML
    public void initialize() {

        colIndice.setCellValueFactory(
                dato -> new ReadOnlyIntegerWrapper(
                        dato.getValue().getIndice()
                )
        );

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

        System.out.println("Módulo de Triplos cargado correctamente");
    }

    @FXML
    private void generarTriplos() {

        String expresion = txtExpresion.getText().trim();

        System.out.println("Expresión recibida: " + expresion);

        if (expresion.isEmpty()) {

            lblMensaje.setText(
                    "Escribe una expresión."
            );

            tablaTriplos.getItems().clear();

            return;
        }

        List<Triplo> resultado =
                GeneradorTriplos.generar(expresion);

        System.out.println(
                "Cantidad de triplos: " + resultado.size()
        );

        if (resultado.isEmpty()) {

            lblMensaje.setText(
                    "Expresión inválida. Usa: a = b + c * d"
            );

            tablaTriplos.getItems().clear();

            return;
        }

        tablaTriplos.setItems(
                FXCollections.observableArrayList(resultado)
        );

        tablaTriplos.refresh();

        lblMensaje.setText(
                "Triplos generados correctamente: "
                        + resultado.size()
        );
    }

    @FXML
    private void limpiar() {

        txtExpresion.clear();

        tablaTriplos.getItems().clear();

        lblMensaje.setText("");

        txtExpresion.requestFocus();
    }
}