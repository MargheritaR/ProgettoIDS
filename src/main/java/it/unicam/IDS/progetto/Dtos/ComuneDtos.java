package it.unicam.IDS.progetto.Dtos;

import it.unicam.IDS.progetto.Entita.Coordinate;

import jakarta.persistence.*;


public class ComuneDtos {
    @Id
    private String nomeComune;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "nomeComune", referencedColumnName = "coordinate_id")
    private Coordinate coordinate;

    public ComuneDtos(){ }

    public String getNomeComune() {
        return nomeComune;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

}