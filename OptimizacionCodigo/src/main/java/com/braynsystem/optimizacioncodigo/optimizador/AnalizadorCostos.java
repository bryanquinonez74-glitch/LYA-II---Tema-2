package com.braynsystem.optimizacioncodigo.optimizador;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnalizadorCostos {

    public static String analizar(String codigo) {

        if (codigo == null || codigo.isBlank()) {
            return "No hay código para analizar.";
        }

        int operaciones = contarOperaciones(codigo);
        int asignaciones = contarAsignaciones(codigo);
        int variables = contarVariables(codigo);

        int memoriaEstimada = variables * 4;
        int registrosEstimados = Math.min(variables, 8);
        int usoPilaEstimado = Math.max(0, variables - registrosEstimados) * 4;

        StringBuilder resultado = new StringBuilder();

        resultado.append("ANÁLISIS DE COSTOS DE EJECUCIÓN\n\n");

        resultado.append("Operaciones aritméticas: ")
                .append(operaciones)
                .append("\n");

        resultado.append("Asignaciones: ")
                .append(asignaciones)
                .append("\n");

        resultado.append("Variables detectadas: ")
                .append(variables)
                .append("\n\n");

        resultado.append("Memoria estimada: ")
                .append(memoriaEstimada)
                .append(" bytes\n");

        resultado.append("Registros estimados utilizados: ")
                .append(registrosEstimados)
                .append("\n");

        resultado.append("Uso estimado de pila: ")
                .append(usoPilaEstimado)
                .append(" bytes\n\n");

        resultado.append("Costo total aproximado de instrucciones: ")
                .append(operaciones + asignaciones)
                .append("\n");

        return resultado.toString();
    }


    private static int contarOperaciones(String codigo) {

        int contador = 0;

        for (char caracter : codigo.toCharArray()) {

            if (caracter == '+'
                    || caracter == '-'
                    || caracter == '*'
                    || caracter == '/') {

                contador++;
            }
        }

        return contador;
    }


    private static int contarAsignaciones(String codigo) {

        int contador = 0;

        String[] lineas = codigo.split("\\n");

        for (String linea : lineas) {

            if (linea.contains("=")
                    && !linea.contains("==")
                    && !linea.contains("<=")
                    && !linea.contains(">=")
                    && !linea.contains("!=")) {

                contador++;
            }
        }

        return contador;
    }


    private static int contarVariables(String codigo) {

        Set<String> variables = new HashSet<>();

        Pattern patron =
                Pattern.compile("\\b[a-zA-Z_][a-zA-Z0-9_]*\\b");

        Matcher matcher =
                patron.matcher(codigo);

        while (matcher.find()) {

            String palabra = matcher.group();

            if (!esPalabraReservada(palabra)) {
                variables.add(palabra);
            }
        }

        return variables.size();
    }


    private static boolean esPalabraReservada(String palabra) {

        return palabra.equals("int")
                || palabra.equals("double")
                || palabra.equals("float")
                || palabra.equals("String")
                || palabra.equals("for")
                || palabra.equals("while")
                || palabra.equals("if")
                || palabra.equals("else")
                || palabra.equals("return")
                || palabra.equals("true")
                || palabra.equals("false");
    }
}