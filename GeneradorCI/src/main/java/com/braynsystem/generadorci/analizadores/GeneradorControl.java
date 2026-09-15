package com.braynsystem.generadorci.analizadores;

public class GeneradorControl {

    public static String generar(String instruccion) {

        if (instruccion == null || instruccion.trim().isEmpty()) {
            return "Error: escribe una instrucción de control.";
        }

        instruccion = instruccion.trim();

        if (instruccion.startsWith("if")) {
            return generarIf(instruccion);
        }

        if (instruccion.startsWith("while")) {
            return generarWhile(instruccion);
        }

        if (instruccion.startsWith("for")) {
            return generarFor(instruccion);
        }

        return "Error: instrucción no reconocida.\n" +
                "Usa if, while o for.";
    }

    private static String generarIf(String instruccion) {

        String condicion = extraerCondicion(instruccion);

        if (condicion.isEmpty()) {
            return "Error: condición IF inválida.";
        }

        return """
                INSTRUCCIÓN IF

                IF %s GOTO L1
                GOTO L2

                L1:
                    // bloque verdadero

                L2:
                """.formatted(condicion);
    }

    private static String generarWhile(String instruccion) {

        String condicion = extraerCondicion(instruccion);

        if (condicion.isEmpty()) {
            return "Error: condición WHILE inválida.";
        }

        return """
                INSTRUCCIÓN WHILE

                L1:
                IF %s GOTO L2
                GOTO L3

                L2:
                    // cuerpo del while
                GOTO L1

                L3:
                """.formatted(condicion);
    }

    private static String generarFor(String instruccion) {

        int inicio = instruccion.indexOf("(");
        int fin = instruccion.lastIndexOf(")");

        if (inicio == -1 || fin == -1 || fin <= inicio) {
            return "Error: estructura FOR inválida.";
        }

        String contenido =
                instruccion.substring(inicio + 1, fin).trim();

        String[] partes = contenido.split(";");

        if (partes.length != 3) {
            return "Error: usa el formato:\n" +
                    "for (i = 0; i < 10; i++)";
        }

        String inicializacion = partes[0].trim();
        String condicion = partes[1].trim();
        String incremento = partes[2].trim();

        return """
                INSTRUCCIÓN FOR

                %s

                L1:
                IF %s GOTO L2
                GOTO L3

                L2:
                    // cuerpo del for

                %s
                GOTO L1

                L3:
                """.formatted(
                inicializacion,
                condicion,
                incremento
        );
    }

    private static String extraerCondicion(String instruccion) {

        int inicio = instruccion.indexOf("(");
        int fin = instruccion.lastIndexOf(")");

        if (inicio == -1 || fin == -1 || fin <= inicio) {
            return "";
        }

        return instruccion
                .substring(inicio + 1, fin)
                .trim();
    }
}