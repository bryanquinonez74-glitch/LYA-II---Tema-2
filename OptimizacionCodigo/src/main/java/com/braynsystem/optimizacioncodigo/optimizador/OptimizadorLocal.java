package com.braynsystem.optimizacioncodigo.optimizador;

public class OptimizadorLocal {

    public static String optimizar(String codigo) {

        String[] lineas = codigo.split("\\n");

        StringBuilder resultado = new StringBuilder();

        for (String linea : lineas) {

            linea = linea.trim();

            if (linea.isEmpty()) {
                continue;
            }

            // Multiplicación por 1
            linea = linea.replaceAll("\\b([a-zA-Z]\\w*)\\s*\\*\\s*1\\b", "$1");
            linea = linea.replaceAll("\\b1\\s*\\*\\s*([a-zA-Z]\\w*)\\b", "$1");

            // Suma con 0
            linea = linea.replaceAll("\\b([a-zA-Z]\\w*)\\s*\\+\\s*0\\b", "$1");
            linea = linea.replaceAll("\\b0\\s*\\+\\s*([a-zA-Z]\\w*)\\b", "$1");

            // Resta de 0
            linea = linea.replaceAll("\\b([a-zA-Z]\\w*)\\s*-\\s*0\\b", "$1");

            // Multiplicación por cero
            linea = linea.replaceAll("\\b([a-zA-Z]\\w*)\\s*\\*\\s*0\\b", "0");
            linea = linea.replaceAll("\\b0\\s*\\*\\s*([a-zA-Z]\\w*)\\b", "0");

            // División entre 1
            linea = linea.replaceAll("\\b([a-zA-Z]\\w*)\\s*/\\s*1\\b", "$1");

            // Operaciones numéricas sencillas
            linea = evaluarConstantes(linea);

            resultado.append(linea).append("\n");
        }

        return resultado.toString();
    }

    private static String evaluarConstantes(String linea) {

        String regex = "(\\d+)\\s*([+\\-*/])\\s*(\\d+)";

        java.util.regex.Pattern patron =
                java.util.regex.Pattern.compile(regex);

        java.util.regex.Matcher matcher =
                patron.matcher(linea);

        if (matcher.find()) {

            int numero1 =
                    Integer.parseInt(matcher.group(1));

            String operador =
                    matcher.group(2);

            int numero2 =
                    Integer.parseInt(matcher.group(3));

            int resultado = 0;

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

                    if (numero2 != 0) {
                        resultado = numero1 / numero2;
                    } else {
                        return linea;
                    }

                    break;
            }

            linea =
                    linea.substring(0, matcher.start())
                            + resultado
                            + linea.substring(matcher.end());
        }

        return linea;
    }
}