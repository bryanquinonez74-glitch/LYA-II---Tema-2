package com.braynsystem.generadorci.model;

public class Variable {

    private final String tipo;
    private final String nombre;
    private final String valor;
    private final String clase;

    public Variable(
            String tipo,
            String nombre,
            String valor,
            String clase) {

        this.tipo = tipo;
        this.nombre = nombre;
        this.valor = valor;
        this.clase = clase;
    }

    public String getTipo() {
        return tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getValor() {
        return valor;
    }

    public String getClase() {
        return clase;
    }
}