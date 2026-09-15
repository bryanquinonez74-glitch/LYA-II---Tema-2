package com.braynsystem.generadorci.analizadores;

public class GeneradorCodigoP {

    public static String generar(String expresion) {

        if (expresion == null || expresion.trim().isEmpty()) {
            return "Error: escribe una expresión.";
        }

        expresion = expresion.trim();

        // Verificamos que exista una asignación
        if (!expresion.contains("=")) {
            return "Error: la expresión debe contener una asignación.\n" +
                    "Ejemplo: a = b + c * d";
        }

        String[] partes = expresion.split("=", 2);

        String variable = partes[0].trim();
        String operacion = partes[1].trim();

        if (variable.isEmpty() || operacion.isEmpty()) {
            return "Error: asignación incompleta.";
        }

        // Convertimos la operación a postfija
        String postfija =
                ConvertidorNotaciones.infijaAPostfija(operacion);

        String[] tokens = postfija.split("\\s+");

        StringBuilder codigo = new StringBuilder();

        for (String token : tokens) {

            switch (token) {

                case "+":
                    codigo.append("ADD\n");
                    break;

                case "-":
                    codigo.append("SUB\n");
                    break;

                case "*":
                    codigo.append("MUL\n");
                    break;

                case "/":
                    codigo.append("DIV\n");
                    break;

                default:
                    codigo.append("LOD ")
                            .append(token)
                            .append("\n");
                    break;
            }
        }

        codigo.append("STO ").append(variable);

        return codigo.toString();
    }
}