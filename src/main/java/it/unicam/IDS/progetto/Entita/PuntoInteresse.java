package it.unicam.IDS.progetto.Entita;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class PuntoInteresse {

    @Id
    @NotEmpty
    private String nomePDI;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "coordinate_id", referencedColumnName = "idNomePDI")
    private Coordinate coordinate;

    //TODO Aggiungere ICollection dei Contenuti, unire i contenuti multimediali e testuali

    // costruttore
    public PuntoInteresse(String nomePDI,double asseX, double asseY) {
        this.nomePDI = nomePDI;
        this.coordinate = new Coordinate(nomePDI,asseX, asseY);
    }

    public PuntoInteresse() {}

    @Override
    public String toString() {
        return "Punti di Interesse" + '\n' +
                "nomePDI: " + nomePDI + '\n' +
                "coordinate: " + coordinate + '\n';
    }

    public String getNomePDI() {
        return nomePDI;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(int asseX, int asseY) {
        this.coordinate = new Coordinate(nomePDI,asseX,asseY);
    }

    public void setNomePDI(String nomePDI) {
        this.nomePDI = nomePDI;
    }
}
