package it.unicam.IDS.progetto.Entita;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Coordinate {

    @Id
    private String nomeCoordinate;

    @NotEmpty
    private double latitudine;

    @NotEmpty
    private double longitudine;

    // costruttore
    public Coordinate(String idNomePDI, double latitudine, double longitudine) {
        this.nomeCoordinate = idNomePDI;
        this.latitudine = latitudine;
        this.longitudine = longitudine;
    }

    public Coordinate() {}

    @Override
    public String toString() {
        return "{" +
                "X=" + latitudine +
                ", Y=" + longitudine +
                '}';
    }

    public String getNomeCoordinate() {
        return nomeCoordinate;
    }

    public double getLatitudine() {
        return latitudine;
    }

    public void setLatitudine(double latitudine) {
        this.latitudine = latitudine;
    }

    public double getLongitudine() {
        return longitudine;
    }

    public void setLongitudine(double longitudine) {
        this.longitudine = longitudine;
    }
}
