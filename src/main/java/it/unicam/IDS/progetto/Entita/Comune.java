package it.unicam.IDS.progetto.Entita;

import jakarta.persistence.*;


import java.util.List;

@Entity
public class Comune {

    @Id
    private String nomeComune;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "nomeComune", referencedColumnName = "coordinate_id")
    private Coordinate coordinate;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Utente> listaUtente;

    public Comune(String nomeComune, double asseX, double asseY) {
        this.nomeComune = nomeComune;
        //this.coordinate = new Coordinate(asseX, asseY);
        this.listaUtente = null;
    }

    public Comune() {

    }

    public String getNomeComune() {
        return nomeComune;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public List<Utente> getListaUtente() {
        return listaUtente;
    }

    public void setNomeComune(String nomeComune) {
        this.nomeComune = nomeComune;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public void setListaUtente(List<Utente> listaUtente) {
        this.listaUtente = listaUtente;
    }

    @Override
    public String toString() {
        return "Comune: " + '\n' +
                "nomeComune: " + nomeComune + '\n' +
                ", coordinate: " + coordinate + '\n';
    }
}
