package com.braynsystem.generadorci.model;

public class Triplo {

    private final int indice;
    private final String operador;
    private final String argumento1;
    private final String argumento2;

    public Triplo(int indice,
                  String operador,
                  String argumento1,
                  String argumento2) {

        this.indice = indice;
        this.operador = operador;
        this.argumento1 = argumento1;
        this.argumento2 = argumento2;
    }

    public int getIndice() {
        return indice;
    }

    public String getOperador() {
        return operador;
    }

    public String getArgumento1() {
        return argumento1;
    }

    public String getArgumento2() {
        return argumento2;
    }
}