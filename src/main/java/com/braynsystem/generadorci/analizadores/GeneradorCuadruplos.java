package com.braynsystem.generadorci.analizadores;

import com.braynsystem.generadorci.model.Cuadruplo;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class GeneradorCuadruplos {

    public static List<Cuadruplo> generar(String expresion) {

        List<Cuadruplo> cuadruplos = new ArrayList<>();

        if (expresion == null || expresion.trim().isEmpty()) {
            return cuadruplos;
        }

        if (!expresion.contains("=")) {
            return cuadruplos;
        }

        String[] partes = expresion.split("=", 2);

        String variable = partes[0].trim();
        String operacion = partes[1].trim();

        if (variable.isEmpty() || operacion.isEmpty()) {
            return cuadruplos;
        }

        String postfija =
                ConvertidorNotaciones.infijaAPostfija(operacion);

        System.out.println("Postfija para cuádruplos: " + postfija);

        String[] tokens = postfija.split("\\s+");

        Stack<String> pila = new Stack<>();

        int contadorTemporal = 1;

        for (String token : tokens) {

            if (esOperador(token)) {

                if (pila.size() < 2) {
                    return new ArrayList<>();
                }

                String argumento2 = pila.pop();
                String argumento1 = pila.pop();

                String temporal =
                        "T" + contadorTemporal;

                Cuadruplo cuadruplo =
                        new Cuadruplo(
                                token,
                                argumento1,
                                argumento2,
                                temporal
                        );

                cuadruplos.add(cuadruplo);

                pila.push(temporal);

                contadorTemporal++;

            } else {

                pila.push(token);
            }
        }

        if (pila.size() != 1) {
            return new ArrayList<>();
        }

        String resultadoFinal = pila.pop();

        cuadruplos.add(
                new Cuadruplo(
                        "=",
                        resultadoFinal,
                        "-",
                        variable
                )
        );

        return cuadruplos;
    }

    private static boolean esOperador(String token) {

        return token.equals("+")
                || token.equals("-")
                || token.equals("*")
                || token.equals("/");
    }
}