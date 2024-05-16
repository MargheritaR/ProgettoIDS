package it.unicam.IDS.progetto.Entita;

import it.unicam.IDS.progetto.Entita.Coordinate;

public class PuntoInteresse {

    private String nomePDI;

    private Coordinate coordinate;

    // costruttore
    public PuntoInteresse(String nomePDI, int asseX, int asseY) {
        this.nomePDI = nomePDI;
        this.coordinate = new Coordinate(asseX,asseY);
    }

    @Override
    public String toString() {
        return "PuntoInteresse{" +
                ", nomePDI='" + nomePDI + '\'' +
                ", coordinate=" + coordinate +
                '}';
    }

}
