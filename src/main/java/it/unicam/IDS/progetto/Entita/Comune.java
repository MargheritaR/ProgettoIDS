package it.unicam.IDS.progetto.Entita;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
public class Comune {

    @Id
    private String nomeComune;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "nomeComune", referencedColumnName = "coordinate_id")
    private Coordinate coordinate;

    private String cap;

    private List<Contenuti> listaContenuti;

    public Comune(String nomeComune, double asseX, double asseY, String cap) {
        this.nomeComune = nomeComune;
        this.coordinate = new Coordinate(nomeComune,asseX, asseY);
        this.cap = cap;
        this.listaContenuti = null;
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

    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public void AddContenuti (List<Contenuti> listaContenuti, Contenuti contenuto){
        if (listaContenuti.contains(contenuto))
            System.out.println("Il nome del contenuto è già presente nella piattaforma");

        listaContenuti.add(contenuto);
        System.out.println("il contenuto è stato aggiunto al punto di interesse");
    }

    public void RimuoviContenuti(List<Contenuti> listaContenuti, Contenuti contenuto) {
        if (!listaContenuti.contains(contenuto))
            System.out.println("Il nome del contenuto è già presente nella piattaforma");

        listaContenuti.remove(contenuto);
        System.out.println("il contenuto è stato eliminato dal punto di interesse");
    }
}
