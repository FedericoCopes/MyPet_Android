package com.example.navbar;

import java.util.Date;

public class Animale {
    String tipologia;
    String nome;
    String razza;
    String colore;
    String sesso;
    double peso;
    String data;

    public Animale(String tipologia, String nome, String razza, String colore, String sesso, double peso, String data) {
        this.tipologia = tipologia;
        this.nome = nome;
        this.razza = razza;
        this.colore = colore;
        this.sesso = sesso;
        this.peso = peso;
        this.data = data;
    }

    public String getTipologia() {
        return tipologia;
    }

    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRazza() {
        return razza;
    }

    public void setRazza(String razza) {
        this.razza = razza;
    }

    public String getColore() {
        return colore;
    }

    public void setColore(String colore) {
        this.colore = colore;
    }

    public String getProvenienza() {
        return sesso;
    }

    public void setProvenienza(String provenienza) {
        this.sesso = provenienza;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
