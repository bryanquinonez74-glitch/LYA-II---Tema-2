package com.braynsystem.generadorci.analizadores;

import com.braynsystem.generadorci.model.Triplo;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class GeneradorTriplos {

    public static List<Triplo> generar(String expresion) {

        List<Triplo> triplos = new ArrayList<>();

        if (expresion == null || expresion.trim().isEmpty()) {
            return triplos;
        }

        if (!expresion.contains("=")) {
            return triplos;
        }

        String[] partes = expresion.split("=", 2);

        String variable = partes[0].trim();
        String operacion = partes[1].trim();

        if (variable.isEmpty() || operacion.isEmpty()) {
            return triplos;
        }

        String postfija =
                ConvertidorNotaciones.infijaAPostfija(operacion);

        String[] tokens = postfija.split("\\s+");

        Stack<String> pila = new Stack<>();

        int indice = 0;

        for (String token : tokens) {

            if (esOperador(token)) {

                if (pila.size() < 2) {
                    return new ArrayList<>();
                }

                String argumento2 = pila.pop();
                String argumento1 = pila.pop();

                Triplo triplo = new Triplo(
                        indice,
                        token,
                        argumento1,
                        argumento2
                );

                triplos.add(triplo);

                pila.push("(" + indice + ")");

                indice++;

            } else {

                pila.push(token);
            }
        }

        if (pila.size() != 1) {
            return new ArrayList<>();
        }

        String resultado = pila.pop();

        triplos.add(
                new Triplo(
                        indice,
                        "=",
                        resultado,
                        variable
                )
        );

        return triplos;
    }

    private static boolean esOperador(String token) {

        return token.equals("+") ||
                token.equals("-") ||
                token.equals("*") ||
                token.equals("/");
    }
}