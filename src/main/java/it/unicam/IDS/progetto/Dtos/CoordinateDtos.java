package it.unicam.IDS.progetto.Dtos;

import jakarta.validation.constraints.NotEmpty;

public class CoordinateDtos {

    @NotEmpty
    private double X;

    @NotEmpty
    private double Y;

    public CoordinateDtos() {}

    @NotEmpty
    public double getX() {
        return X;
    }

    @NotEmpty
    public double getY() {
        return Y;
    }
}
