package com.braynsystem.optimizacioncodigo.optimizador;

import java.util.HashMap;
import java.util.Map;

public class AnalizadorCriterios {

    public static String analizar(String codigo) {

        if (codigo == null || codigo.isBlank()) {
            return "No hay código para analizar.";
        }

        StringBuilder resultado = new StringBuilder();

        resultado.append("CRITERIOS PARA MEJORAR EL CÓDIGO\n\n");

        int recomendaciones = 0;

        String[] lineas = codigo.split("\\n");

        Map<String, String> expresiones = new HashMap<>();

        for (String linea : lineas) {

            String limpia = linea.trim();

            if (limpia.isEmpty()) {
                continue;
            }

            // Suma con cero
            if (limpia.matches(".*\\+\\s*0.*")
                    || limpia.matches(".*0\\s*\\+.*")) {

                resultado.append("• Eliminar suma con 0: ")
                        .append(limpia)
                        .append("\n");

                recomendaciones++;
            }

            // Multiplicación por uno
            if (limpia.matches(".*\\*\\s*1.*")
                    || limpia.matches(".*1\\s*\\*.*")) {

                resultado.append("• Eliminar multiplicación por 1: ")
                        .append(limpia)
                        .append("\n");

                recomendaciones++;
            }

            // Multiplicación por cero
            if (limpia.matches(".*\\*\\s*0.*")
                    || limpia.matches(".*0\\s*\\*.*")) {

                resultado.append("• Sustituir multiplicación por 0: ")
                        .append(limpia)
                        .append("\n");

                recomendaciones++;
            }

            // División entre uno
            if (limpia.matches(".*/\\s*1.*")) {

                resultado.append("• Eliminar división entre 1: ")
                        .append(limpia)
                        .append("\n");

                recomendaciones++;
            }

            // Detectar expresiones repetidas
            if (limpia.contains("=")) {

                String sinPuntoComa =
                        limpia.replace(";", "");

                String[] partes =
                        sinPuntoComa.split("=", 2);

                if (partes.length == 2) {

                    String variable =
                            partes[0].trim();

                    String expresion =
                            partes[1].trim();

                    if (expresiones.containsKey(expresion)) {

                        resultado.append("• Reutilizar resultado de ")
                                .append(expresiones.get(expresion))
                                .append(" en lugar de calcular otra vez: ")
                                .append(variable)
                                .append(" = ")
                                .append(expresion)
                                .append("\n");

                        recomendaciones++;

                    } else {

                        expresiones.put(
                                expresion,
                                variable
                        );
                    }
                }
            }
        }

        resultado.append("\n");

        if (recomendaciones == 0) {

            resultado.append(
                    "No se encontraron mejoras simples."
            );

        } else {

            resultado.append(
                    "Total de recomendaciones: "
            ).append(recomendaciones);
        }

        return resultado.toString();
    }
}