package com.braynsystem.optimizacioncodigo.controller;

import com.braynsystem.optimizacioncodigo.optimizador.AnalizadorCostos;
import com.braynsystem.optimizacioncodigo.optimizador.AnalizadorCriterios;
import com.braynsystem.optimizacioncodigo.optimizador.AnalizadorFlujoDatos;
import com.braynsystem.optimizacioncodigo.optimizador.OptimizadorCiclos;
import com.braynsystem.optimizacioncodigo.optimizador.OptimizadorGlobal;
import com.braynsystem.optimizacioncodigo.optimizador.OptimizadorLocal;
import com.braynsystem.optimizacioncodigo.optimizador.OptimizadorMirilla;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class OptimizadorCController {

    @FXML
    private ComboBox<String> cmbTipoOptimizacion;

    @FXML
    private TextArea txtCodigo;

    @FXML
    private TextArea txtResultado;

    @FXML
    private TextArea txtExplicacion;


    @FXML
    private void initialize() {

        cmbTipoOptimizacion.getItems().addAll(
                "Optimización Local",
                "Optimización de Ciclos",
                "Optimización Global",
                "Optimización de Mirilla",
                "Costo de Ejecución",
                "Criterios para Mejorar el Código",
                "Análisis del Flujo de Datos"
        );
    }


    @FXML
    private void onOptimizar() {

        String codigo = txtCodigo.getText();

        String seleccion =
                cmbTipoOptimizacion.getValue();


        if (seleccion == null) {

            txtResultado.setText(
                    "Seleccione un tipo de optimización o análisis."
            );

            txtExplicacion.clear();

            return;
        }


        if (codigo == null || codigo.isBlank()) {

            txtResultado.setText(
                    "Ingrese código para analizar."
            );

            txtExplicacion.clear();

            return;
        }


        switch (seleccion) {

            case "Optimización Local":

                ejecutarLocal(codigo);

                break;


            case "Optimización de Ciclos":

                ejecutarCiclos(codigo);

                break;


            case "Optimización Global":

                ejecutarGlobal(codigo);

                break;


            case "Optimización de Mirilla":

                ejecutarMirilla(codigo);

                break;


            case "Costo de Ejecución":

                ejecutarCostos(codigo);

                break;


            case "Criterios para Mejorar el Código":

                ejecutarCriterios(codigo);

                break;


            case "Análisis del Flujo de Datos":

                ejecutarFlujoDatos(codigo);

                break;


            default:

                txtResultado.setText(
                        "Opción no reconocida."
                );

                txtExplicacion.clear();

                break;
        }
    }


    private void ejecutarLocal(String codigo) {

        txtResultado.setText(
                OptimizadorLocal.optimizar(codigo)
        );

        txtExplicacion.setText(
                "OPTIMIZACIÓN LOCAL\n\n"
                        + "Analiza instrucciones dentro de un bloque pequeño "
                        + "de código.\n\n"
                        + "Permite simplificar expresiones, eliminar operaciones "
                        + "innecesarias y realizar plegado de constantes."
        );
    }


    private void ejecutarCiclos(String codigo) {

        txtResultado.setText(
                OptimizadorCiclos.optimizar(codigo)
        );

        txtExplicacion.setText(
                "OPTIMIZACIÓN DE CICLOS\n\n"
                        + "Busca operaciones que se repiten dentro de un ciclo "
                        + "pero cuyo resultado no cambia.\n\n"
                        + "Estas operaciones pueden ejecutarse una sola vez "
                        + "fuera del ciclo para reducir el trabajo realizado."
        );
    }


    private void ejecutarGlobal(String codigo) {

        txtResultado.setText(
                OptimizadorGlobal.optimizar(codigo)
        );

        txtExplicacion.setText(
                "OPTIMIZACIÓN GLOBAL\n\n"
                        + "Analiza una región más amplia del programa.\n\n"
                        + "Permite detectar expresiones repetidas y reutilizar "
                        + "resultados previamente calculados."
        );
    }


    private void ejecutarMirilla(String codigo) {

        txtResultado.setText(
                OptimizadorMirilla.optimizar(codigo)
        );

        txtExplicacion.setText(
                "OPTIMIZACIÓN DE MIRILLA\n\n"
                        + "Analiza pequeñas secuencias de instrucciones.\n\n"
                        + "Busca reemplazar instrucciones innecesarias "
                        + "por otras más simples y eficientes."
        );
    }


    private void ejecutarCostos(String codigo) {

        txtResultado.setText(
                AnalizadorCostos.analizar(codigo)
        );

        txtExplicacion.setText(
                "COSTO DE EJECUCIÓN\n\n"
                        + "Permite estimar los recursos utilizados por "
                        + "un fragmento de código.\n\n"
                        + "Se consideran operaciones, asignaciones, memoria, "
                        + "registros y uso aproximado de la pila."
        );
    }


    private void ejecutarCriterios(String codigo) {

        txtResultado.setText(
                AnalizadorCriterios.analizar(codigo)
        );

        txtExplicacion.setText(
                "CRITERIOS PARA MEJORAR EL CÓDIGO\n\n"
                        + "Busca posibles mejoras como eliminar operaciones "
                        + "redundantes, simplificar expresiones y evitar "
                        + "cálculos repetidos."
        );
    }


    private void ejecutarFlujoDatos(String codigo) {

        txtResultado.setText(
                AnalizadorFlujoDatos.analizar(codigo)
        );

        txtExplicacion.setText(
                "ANÁLISIS DEL FLUJO DE DATOS\n\n"
                        + "Permite identificar variables definidas, utilizadas "
                        + "y las dependencias existentes entre ellas.\n\n"
                        + "También ayuda a detectar variables externas "
                        + "o posiblemente no definidas."
        );
    }


    @FXML
    private void onLimpiar() {

        txtCodigo.clear();

        txtResultado.clear();

        txtExplicacion.clear();

        cmbTipoOptimizacion.getSelectionModel().clearSelection();
    }
}