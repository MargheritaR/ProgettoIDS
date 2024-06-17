package it.unicam.IDS.progetto.Entita;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Coordinate {

    @Id
    private String idNomePDI;

    @NotEmpty
    private double X;

    @NotEmpty
    private double Y;

    // costruttore
    public Coordinate(String idNomePDI,double x, double y) {
        this.idNomePDI = idNomePDI;
        this.X = x;
        this.Y = y;
    }

    public Coordinate() {}

    @Override
    public String toString() {
        return "{" +
                "X=" + X +
                ", Y=" + Y +
                '}';
    }

    public String getIdNomePDI() {
        return idNomePDI;
    }

    @NotEmpty
    public double getX() {
        return X;
    }

    public void setX(@NotEmpty double x) {
        X = x;
    }

    @NotEmpty
    public double getY() {
        return Y;
    }

    public void setY(@NotEmpty double y) {
        Y = y;
    }
}
