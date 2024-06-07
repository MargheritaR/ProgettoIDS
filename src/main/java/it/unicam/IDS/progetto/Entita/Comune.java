package it.unicam.IDS.progetto.Entita;

public class Comune {

    private String nomeComune;

    private Coordinate coordinate;

    private Utente listaUtente;

    public Comune(String nomeComune, int asseX, int asseY) {
        this.nomeComune = nomeComune;
        this.coordinate = new Coordinate(asseX, asseY);
        this.listaUtente = null;
    }

    public String getNomeComune() {
        return nomeComune;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public Utente getListaUtente() {
        return listaUtente;
    }

    public void setNomeComune(String nomeComune) {
        this.nomeComune = nomeComune;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public void setListaUtente(Utente listaUtente) {
        this.listaUtente = listaUtente;
    }

    @Override
    public String toString() {
        return "Comune: " + '\n' +
                "nomeComune: " + nomeComune + '\n' +
                ", coordinate: " + coordinate + '\n';
    }
}
