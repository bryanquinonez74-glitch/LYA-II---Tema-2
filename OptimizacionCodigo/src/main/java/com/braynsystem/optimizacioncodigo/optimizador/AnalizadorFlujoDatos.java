package com.braynsystem.optimizacioncodigo.optimizador;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnalizadorFlujoDatos {

    public static String analizar(String codigo) {

        if (codigo == null || codigo.isBlank()) {
            return "No hay código para analizar.";
        }

        String[] lineas = codigo.split("\\n");

        StringBuilder resultado = new StringBuilder();

        resultado.append("ANÁLISIS DEL FLUJO DE DATOS\n\n");

        Set<String> variablesDefinidas = new LinkedHashSet<>();
        Set<String> variablesUsadas = new LinkedHashSet<>();

        int numeroLinea = 1;

        for (String linea : lineas) {

            String limpia = linea.trim();

            if (limpia.isEmpty()) {
                numeroLinea++;
                continue;
            }

            if (limpia.contains("=")
                    && !limpia.contains("==")
                    && !limpia.contains("<=")
                    && !limpia.contains(">=")
                    && !limpia.contains("!=")) {

                String sinPuntoComa =
                        limpia.replace(";", "");

                String[] partes =
                        sinPuntoComa.split("=", 2);

                if (partes.length == 2) {

                    String izquierda =
                            partes[0].trim();

                    String derecha =
                            partes[1].trim();

                    // Obtener nombre de variable definida
                    String variableDefinida =
                            obtenerUltimaPalabra(izquierda);

                    if (!variableDefinida.isEmpty()) {

                        variablesDefinidas.add(variableDefinida);

                        resultado.append("Línea ")
                                .append(numeroLinea)
                                .append(": ")
                                .append(variableDefinida)
                                .append(" es DEFINIDA.\n");
                    }

                    // Buscar variables usadas en la parte derecha
                    List<String> usadas =
                            obtenerVariables(derecha);

                    if (!usadas.isEmpty()) {

                        resultado.append("   Depende de: ");

                        for (int i = 0; i < usadas.size(); i++) {

                            String variable = usadas.get(i);

                            variablesUsadas.add(variable);

                            resultado.append(variable);

                            if (i < usadas.size() - 1) {
                                resultado.append(", ");
                            }
                        }

                        resultado.append("\n");
                    }
                }
            }

            numeroLinea++;
        }

        resultado.append("\nRESUMEN\n\n");

        resultado.append("Variables definidas: ");

        if (variablesDefinidas.isEmpty()) {

            resultado.append("Ninguna");

        } else {

            resultado.append(
                    String.join(", ", variablesDefinidas)
            );
        }

        resultado.append("\n");

        resultado.append("Variables utilizadas: ");

        if (variablesUsadas.isEmpty()) {

            resultado.append("Ninguna");

        } else {

            resultado.append(
                    String.join(", ", variablesUsadas)
            );
        }

        resultado.append("\n");

        // Variables utilizadas antes de estar definidas
        Set<String> noDefinidas =
                new LinkedHashSet<>(variablesUsadas);

        noDefinidas.removeAll(variablesDefinidas);

        resultado.append("Variables externas o no definidas: ");

        if (noDefinidas.isEmpty()) {

            resultado.append("Ninguna");

        } else {

            resultado.append(
                    String.join(", ", noDefinidas)
            );
        }

        return resultado.toString();
    }


    private static String obtenerUltimaPalabra(String texto) {

        String[] partes =
                texto.trim().split("\\s+");

        if (partes.length == 0) {
            return "";
        }

        return partes[partes.length - 1];
    }


    private static List<String> obtenerVariables(String expresion) {

        List<String> variables = new ArrayList<>();

        Pattern patron =
                Pattern.compile(
                        "\\b[a-zA-Z_][a-zA-Z0-9_]*\\b"
                );

        Matcher matcher =
                patron.matcher(expresion);

        while (matcher.find()) {

            String palabra =
                    matcher.group();

            if (!esPalabraReservada(palabra)
                    && !variables.contains(palabra)) {

                variables.add(palabra);
            }
        }

        return variables;
    }


    private static boolean esPalabraReservada(String palabra) {

        return palabra.equals("int")
                || palabra.equals("double")
                || palabra.equals("float")
                || palabra.equals("long")
                || palabra.equals("short")
                || palabra.equals("byte")
                || palabra.equals("boolean")
                || palabra.equals("char")
                || palabra.equals("String")
                || palabra.equals("true")
                || palabra.equals("false");
    }
}