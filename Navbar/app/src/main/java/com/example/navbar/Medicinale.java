package com.example.navbar;

import java.util.Date;

public class Medicinale {

    private String nome;
    private String datascadenza;
    private String giorniassunzione;
    private String orarioassunzione;
    private String quantità;

    public Medicinale(String nome, String datascadenza, String giorniassunzione, String orarioassunzione, String quantità) {
        this.nome = nome;
        this.datascadenza = datascadenza;
        this.giorniassunzione = giorniassunzione;
        this.orarioassunzione = orarioassunzione;
        this.quantità = quantità;
    }
    public Medicinale(){}

    public String getQuantità() {
        return quantità;
    }

    public void setQuantità(String quantità) {
        this.quantità = quantità;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDatascadenza() {
        return datascadenza;
    }

    public void setDatascadenza(String datascadenza) {
        this.datascadenza = datascadenza;
    }

    public String getGiorniassunzione() {
        return giorniassunzione;
    }

    public void setGiorniassunzione(String giorniassunzione) {
        this.giorniassunzione = giorniassunzione;
    }

    public String getOrarioassunzione() {
        return orarioassunzione;
    }

    public void setOrarioassunzione(String orarioassunzione) {
        this.orarioassunzione = orarioassunzione;
    }
}
