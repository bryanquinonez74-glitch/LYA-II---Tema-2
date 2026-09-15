package com.braynsystem.generadorci.controller;

import com.braynsystem.generadorci.analizadores.AnalizadorVariables;
import com.braynsystem.generadorci.model.Variable;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;

import java.util.List;

public class VariablesController {

    @FXML
    private TextArea txtCodigo;

    @FXML
    private TableView<Variable> tablaVariables;

    @FXML
    private TableColumn<Variable, String> colTipo;

    @FXML
    private TableColumn<Variable, String> colNombre;

    @FXML
    private TableColumn<Variable, String> colValor;

    @FXML
    private TableColumn<Variable, String> colClase;

    @FXML
    private Label lblMensaje;

    @FXML
    public void initialize() {

        colTipo.setCellValueFactory(
                dato -> new ReadOnlyStringWrapper(
                        dato.getValue().getTipo()
                )
        );

        colNombre.setCellValueFactory(
                dato -> new ReadOnlyStringWrapper(
                        dato.getValue().getNombre()
                )
        );

        colValor.setCellValueFactory(
                dato -> new ReadOnlyStringWrapper(
                        dato.getValue().getValor()
                )
        );

        colClase.setCellValueFactory(
                dato -> new ReadOnlyStringWrapper(
                        dato.getValue().getClase()
                )
        );

        System.out.println(
                "Módulo Variables y Constantes cargado"
        );
    }

    @FXML
    private void analizar() {

        String codigo =
                txtCodigo.getText().trim();

        if (codigo.isEmpty()) {

            lblMensaje.setText(
                    "Escribe una o más declaraciones."
            );

            tablaVariables
                    .getItems()
                    .clear();

            return;
        }

        List<Variable> resultado =
                AnalizadorVariables
                        .analizar(codigo);

        if (resultado.isEmpty()) {

            lblMensaje.setText(
                    "No se encontraron declaraciones válidas."
            );

            tablaVariables
                    .getItems()
                    .clear();

            return;
        }

        tablaVariables.setItems(
                FXCollections
                        .observableArrayList(resultado)
        );

        tablaVariables.refresh();

        lblMensaje.setText(
                "Elementos encontrados: "
                        + resultado.size()
        );
    }

    @FXML
    private void limpiar() {

        txtCodigo.clear();

        tablaVariables
                .getItems()
                .clear();

        lblMensaje.setText("");

        txtCodigo.requestFocus();
    }
}