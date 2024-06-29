package it.unicam.IDS.progetto.Dtos;

import jakarta.persistence.*;

public class ComuneDtos {

    @Id
    private String nomeComune;

    private double asseX;
    private double asseY;

    public ComuneDtos(String nomeComune, double asseX, double asseY) {
        this.nomeComune = nomeComune;
        this.asseX = asseX;
        this.asseY = asseY;
    }

    public String getNomeComune() {
        return nomeComune;
    }

    public void setNomeComune(String nomeComune) {
        this.nomeComune = nomeComune;
    }

    public double getAsseX() {
        return asseX;
    }

    public void setAsseX(double asseX) {
        this.asseX = asseX;
    }

    public double getAsseY() {
        return asseY;
    }

    public void setAsseY(double asseY) {
        this.asseY = asseY;
    }
}