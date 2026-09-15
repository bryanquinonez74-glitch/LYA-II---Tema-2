package com.braynsystem.generadorci.model;

public class Cuadruplo {

    private final String operador;
    private final String argumento1;
    private final String argumento2;
    private final String resultado;

    public Cuadruplo(
            String operador,
            String argumento1,
            String argumento2,
            String resultado) {

        this.operador = operador;
        this.argumento1 = argumento1;
        this.argumento2 = argumento2;
        this.resultado = resultado;
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

    public String getResultado() {
        return resultado;
    }
}