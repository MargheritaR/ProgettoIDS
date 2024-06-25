package it.unicam.IDS.progetto.Dtos;

import it.unicam.IDS.progetto.Entita.Coordinate;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

public class PuntoInteresseDtos {

    @Id
    @NotEmpty
    private String nomePDI;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private CoordinateDtos coordinate;

    public PuntoInteresseDtos() {}

    public String getNomePDI() {
        return nomePDI;
    }

    public CoordinateDtos getCoordinate() {
        return coordinate;
    }

}
