package com.braynsystem.generadorci.analizadores;

import java.util.Stack;

public class GeneradorAsignacion {

    public static String generar(String expresion) {

        if (expresion == null || expresion.trim().isEmpty()) {
            return "Error: escribe una asignación.";
        }

        expresion = expresion.trim();

        if (!expresion.contains("=")) {
            return "Error: falta el operador de asignación (=).";
        }

        String[] partes = expresion.split("=", 2);

        String variable = partes[0].trim();
        String valor = partes[1].trim();

        if (variable.isEmpty()) {
            return "Error: falta la variable de destino.";
        }

        if (valor.isEmpty()) {
            return "Error: falta el valor de la asignación.";
        }

        // Asignación simple
        // Ejemplo: x = 10
        if (!contieneOperador(valor)) {

            return "ASIGNACIÓN SIMPLE\n\n"
                    + variable + " = " + valor;
        }

        // Asignación con expresión
        String postfija =
                ConvertidorNotaciones.infijaAPostfija(valor);

        String[] tokens = postfija.split("\\s+");

        Stack<String> pila = new Stack<>();

        StringBuilder resultado = new StringBuilder();

        int temporal = 1;

        for (String token : tokens) {

            if (esOperador(token)) {

                if (pila.size() < 2) {
                    return "Error: expresión inválida.";
                }

                String operando2 = pila.pop();
                String operando1 = pila.pop();

                String temp = "T" + temporal;

                resultado.append(temp)
                        .append(" = ")
                        .append(operando1)
                        .append(" ")
                        .append(token)
                        .append(" ")
                        .append(operando2)
                        .append("\n");

                pila.push(temp);

                temporal++;

            } else {

                pila.push(token);
            }
        }

        if (pila.size() != 1) {
            return "Error: expresión inválida.";
        }

        String resultadoFinal = pila.pop();

        resultado.append(variable)
                .append(" = ")
                .append(resultadoFinal);

        return "ASIGNACIÓN COMPUESTA\n\n" + resultado;
    }

    private static boolean contieneOperador(String expresion) {

        return expresion.contains("+")
                || expresion.contains("-")
                || expresion.contains("*")
                || expresion.contains("/");
    }

    private static boolean esOperador(String token) {

        return token.equals("+")
                || token.equals("-")
                || token.equals("*")
                || token.equals("/");
    }
}