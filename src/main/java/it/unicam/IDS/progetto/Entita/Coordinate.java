package it.unicam.IDS.progetto.Entita;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Coordinate {

    @Id
    private String IdNomeCoordinate;

    @NotEmpty
    private double X;

    @NotEmpty
    private double Y;

    // costruttore
    public Coordinate(String idNomePDI,double x, double y) {
        this.IdNomeCoordinate = idNomePDI;
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

    public String getIdNomeCoordinate() {
        return IdNomeCoordinate;
    }

    public double getX() {
        return X;
    }

    public void setX(double x) {
        X = x;
    }

    public double getY() {
        return Y;
    }

    public void setY(double y) {
        Y = y;
    }
}
