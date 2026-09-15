package com.braynsystem.generadorci.analizadores;

import java.util.Stack;

public class GeneradorExpresiones {

    public static String generar(String expresion) {

        if (expresion == null || expresion.trim().isEmpty()) {
            return "Error: escribe una expresión.";
        }

        if (!expresion.contains("=")) {
            return "Error: debe existir una asignación.\n" +
                    "Ejemplo: a = b + c * d";
        }

        String[] partes = expresion.split("=", 2);

        String variable = partes[0].trim();
        String operacion = partes[1].trim();

        if (variable.isEmpty() || operacion.isEmpty()) {
            return "Error: asignación incompleta.";
        }

        String postfija =
                ConvertidorNotaciones.infijaAPostfija(operacion);

        String[] tokens = postfija.split("\\s+");

        Stack<String> pila = new Stack<>();

        StringBuilder codigo = new StringBuilder();

        int contador = 1;

        for (String token : tokens) {

            if (esOperador(token)) {

                if (pila.size() < 2) {
                    return "Error: expresión inválida.";
                }

                String operando2 = pila.pop();
                String operando1 = pila.pop();

                String temporal = "T" + contador;

                codigo.append(temporal)
                        .append(" = ")
                        .append(operando1)
                        .append(" ")
                        .append(token)
                        .append(" ")
                        .append(operando2)
                        .append("\n");

                pila.push(temporal);

                contador++;

            } else {

                pila.push(token);
            }
        }

        if (pila.size() != 1) {
            return "Error: expresión inválida.";
        }

        String resultado = pila.pop();

        codigo.append(variable)
                .append(" = ")
                .append(resultado);

        return codigo.toString();
    }

    private static boolean esOperador(String token) {

        return token.equals("+")
                || token.equals("-")
                || token.equals("*")
                || token.equals("/");
    }
}