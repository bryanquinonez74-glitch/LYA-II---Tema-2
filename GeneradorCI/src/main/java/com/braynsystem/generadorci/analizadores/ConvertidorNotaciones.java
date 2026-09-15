package com.braynsystem.generadorci.analizadores;

import java.util.Stack;

public class ConvertidorNotaciones {

    private static int prioridad(char operador) {

        return switch (operador) {
            case '+', '-' -> 1;
            case '*', '/' -> 2;
            default -> 0;
        };
    }

    private static boolean esOperador(char c) {

        return c == '+' ||
                c == '-' ||
                c == '*' ||
                c == '/';
    }

    public static String infijaAPostfija(String expresion) {

        StringBuilder resultado = new StringBuilder();

        Stack<Character> pila = new Stack<>();

        expresion = expresion.replace(" ", "");

        for (int i = 0; i < expresion.length(); i++) {

            char actual = expresion.charAt(i);

            // Si es letra o número
            if (Character.isLetterOrDigit(actual)) {

                resultado.append(actual).append(" ");

            }

            // Paréntesis izquierdo
            else if (actual == '(') {

                pila.push(actual);

            }

            // Paréntesis derecho
            else if (actual == ')') {

                while (!pila.isEmpty() &&
                        pila.peek() != '(') {

                    resultado
                            .append(pila.pop())
                            .append(" ");
                }

                if (!pila.isEmpty()) {
                    pila.pop();
                }

            }

            // Operador
            else if (esOperador(actual)) {

                while (!pila.isEmpty() &&
                        pila.peek() != '(' &&
                        prioridad(pila.peek()) >= prioridad(actual)) {

                    resultado
                            .append(pila.pop())
                            .append(" ");
                }

                pila.push(actual);

            }

        }

        while (!pila.isEmpty()) {

            resultado
                    .append(pila.pop())
                    .append(" ");
        }

        return resultado.toString().trim();
    }

    public static String infijaAPrefija(String expresion) {

        StringBuilder invertida = new StringBuilder();

        expresion = expresion.replace(" ", "");

        // Invertimos la expresión
        for (int i = expresion.length() - 1; i >= 0; i--) {

            char actual = expresion.charAt(i);

            if (actual == '(') {
                invertida.append(')');
            }
            else if (actual == ')') {
                invertida.append('(');
            }
            else {
                invertida.append(actual);
            }
        }

        String postfija =
                infijaAPostfija(invertida.toString());

        String[] elementos =
                postfija.split(" ");

        StringBuilder prefija =
                new StringBuilder();

        for (int i = elementos.length - 1; i >= 0; i--) {

            prefija
                    .append(elementos[i])
                    .append(" ");
        }

        return prefija.toString().trim();
    }
}