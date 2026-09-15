package com.braynsystem.generadorci.analizadores;

public class GeneradorFunciones {

    public static String generar(String instruccion) {

        if (instruccion == null || instruccion.trim().isEmpty()) {
            return "Error: escribe una llamada a función.";
        }

        instruccion = instruccion.trim();

        String variableDestino = null;
        String llamada = instruccion;

        if (instruccion.contains("=")) {

            String[] partes = instruccion.split("=", 2);

            variableDestino = partes[0].trim();
            llamada = partes[1].trim();

            if (variableDestino.isEmpty() || llamada.isEmpty()) {
                return "Error: asignación de función inválida.";
            }
        }

        int inicio = llamada.indexOf("(");
        int fin = llamada.lastIndexOf(")");

        if (inicio == -1 || fin == -1 || fin <= inicio) {
            return "Error: formato de función inválido.\n" +
                    "Ejemplo: suma(a, b)";
        }

        String nombreFuncion =
                llamada.substring(0, inicio).trim();

        if (nombreFuncion.isEmpty()) {
            return "Error: falta el nombre de la función.";
        }

        String contenidoParametros =
                llamada.substring(inicio + 1, fin).trim();

        StringBuilder codigo = new StringBuilder();

        int cantidadParametros = 0;

        if (!contenidoParametros.isEmpty()) {

            String[] parametros =
                    contenidoParametros.split(",");

            cantidadParametros = parametros.length;

            for (String parametro : parametros) {

                parametro = parametro.trim();

                if (parametro.isEmpty()) {
                    return "Error: parámetro inválido.";
                }

                codigo.append("PARAM ")
                        .append(parametro)
                        .append("\n");
            }
        }

        codigo.append("T1 = CALL ")
                .append(nombreFuncion)
                .append(", ")
                .append(cantidadParametros);

        if (variableDestino != null) {

            codigo.append("\n")
                    .append(variableDestino)
                    .append(" = T1");
        }

        return codigo.toString();
    }

    public static String generarReturn(String expresion) {

        if (expresion == null || expresion.trim().isEmpty()) {
            return "Error: escribe un valor de retorno.";
        }

        return "RETURN " + expresion.trim();
    }
}