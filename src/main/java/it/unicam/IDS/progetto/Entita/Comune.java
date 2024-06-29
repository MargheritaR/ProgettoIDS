package it.unicam.IDS.progetto.Entita;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Comune {

    @Id
    private String nomeComune;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "nomeComune", referencedColumnName = "coordinate_id")
    private Coordinate coordinate;

    public Comune(String nomeComune, double asseX, double asseY) {
        this.nomeComune = nomeComune;
        this.coordinate = new Coordinate(nomeComune,asseX, asseY);
    }

    public String getNomeComune() {
        return nomeComune;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setNomeComune(String nomeComune) {
        this.nomeComune = nomeComune;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    @Override
    public String toString() {
        return "Comune: " + '\n' +
                "nomeComune: " + nomeComune + '\n' +
                ", coordinate: " + coordinate + '\n';
    }
}
