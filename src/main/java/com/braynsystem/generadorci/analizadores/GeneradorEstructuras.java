package com.braynsystem.generadorci.analizadores;

public class GeneradorEstructuras {

    public static String generar(String codigo) {

        if (codigo == null || codigo.trim().isEmpty()) {
            return "Error: escribe una estructura.";
        }

        codigo = codigo.trim();

        if (!codigo.startsWith("struct")) {
            return "Error: la estructura debe iniciar con 'struct'.";
        }

        int inicioLlave = codigo.indexOf("{");
        int finLlave = codigo.lastIndexOf("}");

        if (inicioLlave == -1 || finLlave == -1 || finLlave <= inicioLlave) {
            return "Error: estructura inválida.";
        }

        String encabezado =
                codigo.substring(0, inicioLlave).trim();

        String[] encabezadoPartes =
                encabezado.split("\\s+");

        if (encabezadoPartes.length < 2) {
            return "Error: falta el nombre de la estructura.";
        }

        String nombreEstructura =
                encabezadoPartes[1];

        String contenido =
                codigo.substring(
                        inicioLlave + 1,
                        finLlave
                ).trim();

        if (contenido.isEmpty()) {
            return "Error: la estructura no contiene campos.";
        }

        String[] campos =
                contenido.split(";");

        StringBuilder resultado =
                new StringBuilder();

        resultado.append("ESTRUCTURA: ")
                .append(nombreEstructura)
                .append("\n\n");

        int contador = 1;

        for (String campo : campos) {

            campo = campo.trim();

            if (campo.isEmpty()) {
                continue;
            }

            String[] partes =
                    campo.split("\\s+");

            if (partes.length < 2) {

                return "Error: campo inválido -> "
                        + campo;
            }

            String tipo = partes[0];
            String nombre = partes[1];

            resultado.append("CAMPO ")
                    .append(contador)
                    .append("\n");

            resultado.append("Tipo: ")
                    .append(tipo)
                    .append("\n");

            resultado.append("Nombre: ")
                    .append(nombre)
                    .append("\n\n");

            contador++;
        }

        if (contador == 1) {
            return "Error: no se encontraron campos válidos.";
        }

        return resultado.toString().trim();
    }
}