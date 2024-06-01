package it.unicam.IDS.progetto.Entita;

public class Comune {

    private String nomeComune;

    private Coordinate coordinate;

    public Comune(String nomeComune, int asseX, int asseY) {
        this.nomeComune = nomeComune;
        this.coordinate = new Coordinate(asseX, asseY);
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
