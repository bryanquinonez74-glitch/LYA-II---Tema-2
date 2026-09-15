package com.braynsystem.generadorci.analizadores;

import com.braynsystem.generadorci.model.Variable;

import java.util.ArrayList;
import java.util.List;

public class AnalizadorVariables {

    public static List<Variable> analizar(String codigo) {

        List<Variable> variables = new ArrayList<>();

        if (codigo == null || codigo.trim().isEmpty()) {
            return variables;
        }

        String[] lineas = codigo.split(";");

        for (String linea : lineas) {

            linea = linea.trim();

            if (linea.isEmpty()) {
                continue;
            }

            boolean constante = false;

            if (linea.startsWith("final ")) {

                constante = true;

                linea = linea.substring(6).trim();
            }

            String valor = "Sin asignar";

            String declaracion = linea;

            if (linea.contains("=")) {

                String[] partes =
                        linea.split("=", 2);

                declaracion =
                        partes[0].trim();

                valor =
                        partes[1].trim();
            }

            String[] datos =
                    declaracion.split("\\s+");

            if (datos.length < 2) {
                continue;
            }

            String tipo =
                    datos[0];

            String nombre =
                    datos[1];

            String clase =
                    constante
                            ? "Constante"
                            : "Variable";

            Variable variable =
                    new Variable(
                            tipo,
                            nombre,
                            valor,
                            clase
                    );

            variables.add(variable);
        }

        return variables;
    }
}