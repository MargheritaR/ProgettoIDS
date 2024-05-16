package it.unicam.IDS.progetto.Entita;

public class Coordinate {

    private int X;

    private int Y;

    // costruttore
    public Coordinate(int x, int y) {
        X = x;
        Y = y;
    }

    @Override
    public String toString() {
        return "{" +
                "X=" + X +
                ", Y=" + Y +
                '}';
    }
}
