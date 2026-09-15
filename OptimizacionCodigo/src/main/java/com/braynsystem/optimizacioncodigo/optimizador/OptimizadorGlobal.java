package com.braynsystem.optimizacioncodigo.optimizador;

import java.util.HashMap;
import java.util.Map;

public class OptimizadorGlobal {

    public static String optimizar(String codigo) {

        if (codigo == null || codigo.isBlank()) {
            return "";
        }

        String[] lineas = codigo.split("\\n");

        StringBuilder resultado = new StringBuilder();

        Map<String, String> expresionesEncontradas = new HashMap<>();

        for (String linea : lineas) {

            String limpia = linea.trim();

            if (limpia.isEmpty()) {
                continue;
            }

            if (!limpia.contains("=")) {
                resultado.append(linea).append("\n");
                continue;
            }

            String sinPuntoComa =
                    limpia.replace(";", "");

            String[] partes =
                    sinPuntoComa.split("=", 2);

            if (partes.length != 2) {
                resultado.append(linea).append("\n");
                continue;
            }

            String variable =
                    partes[0].trim();

            String expresion =
                    partes[1].trim();


            if (expresionesEncontradas.containsKey(expresion)) {

                String variableAnterior =
                        expresionesEncontradas.get(expresion);

                resultado
                        .append(variable)
                        .append(" = ")
                        .append(variableAnterior)
                        .append(";")
                        .append("\n");

            } else {

                String expresionOptimizada =
                        evaluarConstantes(expresion);

                resultado
                        .append(variable)
                        .append(" = ")
                        .append(expresionOptimizada)
                        .append(";")
                        .append("\n");

                expresionesEncontradas.put(
                        expresion,
                        variable
                );
            }
        }

        return resultado.toString();
    }


    private static String evaluarConstantes(String expresion) {

        String regex =
                "(\\d+)\\s*([+\\-*/])\\s*(\\d+)";

        java.util.regex.Pattern patron =
                java.util.regex.Pattern.compile(regex);

        java.util.regex.Matcher matcher =
                patron.matcher(expresion);

        if (matcher.matches()) {

            int numero1 =
                    Integer.parseInt(matcher.group(1));

            String operador =
                    matcher.group(2);

            int numero2 =
                    Integer.parseInt(matcher.group(3));

            int resultado;

            switch (operador) {

                case "+":
                    resultado = numero1 + numero2;
                    break;

                case "-":
                    resultado = numero1 - numero2;
                    break;

                case "*":
                    resultado = numero1 * numero2;
                    break;

                case "/":

                    if (numero2 == 0) {
                        return expresion;
                    }

                    resultado = numero1 / numero2;
                    break;

                default:
                    return expresion;
            }

            return String.valueOf(resultado);
        }

        return expresion;
    }
}