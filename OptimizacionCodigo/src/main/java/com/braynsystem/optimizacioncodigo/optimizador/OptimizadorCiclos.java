package com.braynsystem.optimizacioncodigo.optimizador;

public class OptimizadorCiclos {

    public static String optimizar(String codigo) {

        if (codigo == null || codigo.isBlank()) {
            return "";
        }

        String[] lineas = codigo.split("\\n");

        StringBuilder codigoFuera = new StringBuilder();
        StringBuilder codigoCiclo = new StringBuilder();

        boolean dentroCiclo = false;

        for (String linea : lineas) {

            String limpia = linea.trim();

            // Detectar inicio del ciclo
            if (limpia.startsWith("for")) {

                dentroCiclo = true;

                codigoCiclo
                        .append(linea)
                        .append("\n");

                continue;
            }

            // Detectar fin del ciclo
            if (dentroCiclo && limpia.equals("}")) {

                codigoCiclo
                        .append(linea)
                        .append("\n");

                dentroCiclo = false;

                continue;
            }

            // Si estamos dentro del ciclo
            if (dentroCiclo) {

                // Ejemplo:
                // x = 5 * 4;
                if (limpia.matches(
                        "[a-zA-Z]\\w*\\s*=\\s*\\d+\\s*\\*\\s*\\d+\\s*;?"
                )) {

                    String sinPuntoComa =
                            limpia.replace(";", "");

                    String[] partes =
                            sinPuntoComa.split("=");

                    String variable =
                            partes[0].trim();

                    String expresion =
                            partes[1].trim();

                    String[] numeros =
                            expresion.split("\\*");

                    int numero1 =
                            Integer.parseInt(
                                    numeros[0].trim()
                            );

                    int numero2 =
                            Integer.parseInt(
                                    numeros[1].trim()
                            );

                    int resultado =
                            numero1 * numero2;

                    codigoFuera
                            .append(variable)
                            .append(" = ")
                            .append(resultado)
                            .append(";")
                            .append("\n");

                    continue;
                }

                codigoCiclo
                        .append(linea)
                        .append("\n");

            } else {

                codigoCiclo
                        .append(linea)
                        .append("\n");
            }
        }

        if (codigoFuera.isEmpty()) {
            return codigo;
        }

        return codigoFuera
                + "\n"
                + codigoCiclo;
    }
}