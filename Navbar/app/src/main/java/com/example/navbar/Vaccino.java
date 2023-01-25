package com.example.navbar;

class Vaccino {
    private String tipo;
    private String data;
    private String fine;

    public Vaccino(){}

    public Vaccino(String tipo, String data, String fine) {
        this.tipo = tipo;
        this.data = data;
        this.fine = fine;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getFine() {
        return fine;
    }

    public void setFine(String fine) {
        this.fine = fine;
    }
}
