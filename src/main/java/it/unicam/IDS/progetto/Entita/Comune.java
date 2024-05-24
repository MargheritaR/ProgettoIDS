package it.unicam.IDS.progetto.Entita;

public class Comune {

    private String nomeComune;

    private Coordinate coordinate;

    public Comune(String nomeComune, int asseX, int asseY) {
        this.nomeComune = nomeComune;
        this.coordinate = coordinate;
    }

    @Override
    public String toString() {
        return "Comune: " + '\n' +
                "nomeComune: " + nomeComune + '\n' +
                ", coordinate: " + coordinate + '\n';
    }
}
