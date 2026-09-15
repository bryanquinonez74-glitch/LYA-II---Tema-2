package com.braynsystem.optimizacioncodigo.optimizador;

public class OptimizadorMirilla {

    public static String optimizar(String codigo) {

        if (codigo == null || codigo.isBlank()) {
            return "";
        }

        String[] lineas = codigo.split("\\n");

        StringBuilder resultado = new StringBuilder();

        for (String linea : lineas) {

            String limpia = linea.trim();

            if (limpia.isEmpty()) {
                continue;
            }

            // x = x + 0  ->  x = x
            limpia = limpia.replaceAll(
                    "(\\w+)\\s*=\\s*\\1\\s*\\+\\s*0\\s*;?",
                    "$1 = $1;"
            );

            // x = x - 0  ->  x = x
            limpia = limpia.replaceAll(
                    "(\\w+)\\s*=\\s*\\1\\s*-\\s*0\\s*;?",
                    "$1 = $1;"
            );

            // x = x * 1  ->  x = x
            limpia = limpia.replaceAll(
                    "(\\w+)\\s*=\\s*\\1\\s*\\*\\s*1\\s*;?",
                    "$1 = $1;"
            );

            // x = x / 1  ->  x = x
            limpia = limpia.replaceAll(
                    "(\\w+)\\s*=\\s*\\1\\s*/\\s*1\\s*;?",
                    "$1 = $1;"
            );

            // x = x * 0 -> x = 0
            limpia = limpia.replaceAll(
                    "(\\w+)\\s*=\\s*\\1\\s*\\*\\s*0\\s*;?",
                    "$1 = 0;"
            );

            // Eliminar saltos innecesarios simples
            if (limpia.equalsIgnoreCase("goto siguiente;")) {
                continue;
            }

            resultado
                    .append(limpia)
                    .append("\n");
        }

        return resultado.toString();
    }
}